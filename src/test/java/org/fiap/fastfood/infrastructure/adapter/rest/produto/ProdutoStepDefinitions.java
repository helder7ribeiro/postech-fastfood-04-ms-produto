package org.fiap.fastfood.infrastructure.adapter.rest.produto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.fiap.fastfood.infrastructure.adapter.persistence.entity.CategoriaEntity;
import org.fiap.fastfood.infrastructure.adapter.persistence.entity.ProdutoEntity;
import org.fiap.fastfood.infrastructure.adapter.persistence.repository.CategoriaRepositoryJpa;
import org.fiap.fastfood.infrastructure.adapter.persistence.repository.ProdutoRepositoryJpa;
import org.fiap.fastfood.infrastructure.adapter.rest.produto.dto.AtualizarProdutoRequest;
import org.fiap.fastfood.infrastructure.adapter.rest.produto.dto.RegistrarProdutoRequest;
import org.fiap.fastfood.util.TestFixtureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProdutoStepDefinitions {

    private static final String ENDPOINT = "/api/v1/produtos";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestFixtureUtil testFixtureUtil;

    @Autowired
    private ProdutoRepositoryJpa produtoRepositoryJpa;

    @Autowired
    private CategoriaRepositoryJpa categoriaRepositoryJpa;

    @Autowired
    private ObjectMapper objectMapper;

    private ProdutoEntity produtoAtual;
    private CategoriaEntity categoriaAtual;
    private MockHttpServletResponse ultimaResposta;
    private String nomeProdutoAtual;

    @Dado("que não existe um produto com nome {string}")
    public void queNaoExisteUmProdutoComNome(String nome) {
        produtoRepositoryJpa.deleteAll();
        nomeProdutoAtual = nome;
    }

    @Quando("eu crio um produto com nome {string} e preço {double}")
    public void euCrioUmProdutoComNomeEPreco(String nome, double preco) throws Exception {
        categoriaAtual = testFixtureUtil.criarCategoriaEntity();

        RegistrarProdutoRequest request = new RegistrarProdutoRequest(
            nome, 
            "Descrição do " + nome, 
            BigDecimal.valueOf(preco), 
            categoriaAtual.getId()
        );

        String jsonRequest = objectMapper.writeValueAsString(request);

        ultimaResposta = mvc.perform(
            MockMvcRequestBuilders.post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        ).andReturn().getResponse();

        if (ultimaResposta.getStatus() == 201) {
            JsonNode responseNode = objectMapper.readTree(ultimaResposta.getContentAsString());
            Integer produtoId = responseNode.get("id").asInt();
            produtoAtual = produtoRepositoryJpa.findById(produtoId).orElse(null);
        }
    }

    @Entao("o produto deve ser criado com sucesso")
    public void oProdutoDeveSerCriadoComSucesso() {
        assertThat(ultimaResposta.getStatus()).isEqualTo(201);
        assertThat(produtoAtual).isNotNull();
        assertThat(produtoAtual.getNome()).isEqualTo(nomeProdutoAtual);
    }

    @Dado("que existe um produto com nome {string}")
    public void queExisteUmProdutoComNome(String nome) {
        categoriaAtual = testFixtureUtil.criarCategoriaEntity();
        produtoAtual = new ProdutoEntity();
        produtoAtual.setNome(nome);
        produtoAtual.setDescricao("Descrição do " + nome);
        produtoAtual.setPreco(BigDecimal.valueOf(20.0));
        produtoAtual.setCategoria(categoriaAtual);
        produtoAtual = produtoRepositoryJpa.save(produtoAtual);
        nomeProdutoAtual = nome;
    }

    @Quando("eu busco o produto pelo nome {string}")
    public void euBuscoOProdutoPeloNome(String nome) throws Exception {
        ultimaResposta = mvc.perform(
            MockMvcRequestBuilders.get(ENDPOINT + "/" + produtoAtual.getId())
        ).andReturn().getResponse();
    }

    @Entao("o produto deve ser retornado")
    public void oProdutoDeveSerRetornado() throws UnsupportedEncodingException {
        assertThat(ultimaResposta.getStatus()).isEqualTo(200);
        assertThat(ultimaResposta.getContentAsString()).isNotBlank();
        
        try {
            JsonNode responseNode = objectMapper.readTree(ultimaResposta.getContentAsString());
            assertThat(responseNode.get("nome").asText()).isEqualTo(nomeProdutoAtual);
        } catch (Exception e) {
            fail("Erro ao parsear resposta JSON: " + e.getMessage());
        }
    }

    @Quando("eu atualizo o preço do produto para {double}")
    public void euAtualizoOPrecoDoProdutoPara(double preco) throws Exception {
        AtualizarProdutoRequest request = new AtualizarProdutoRequest(
            produtoAtual.getNome(),
            produtoAtual.getDescricao(),
            BigDecimal.valueOf(preco),
            categoriaAtual.getId()
        );

        String jsonRequest = objectMapper.writeValueAsString(request);

        ultimaResposta = mvc.perform(
            MockMvcRequestBuilders.put(ENDPOINT + "/" + produtoAtual.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        ).andReturn().getResponse();
    }

    @Entao("o produto deve refletir o novo preço")
    public void oProdutoDeveRefletirONovoPreco() {
        assertThat(ultimaResposta.getStatus()).isEqualTo(200);

        Optional<ProdutoEntity> produtoAtualizado = produtoRepositoryJpa.findById(produtoAtual.getId());
        assertThat(produtoAtualizado).isPresent();
        assertThat(produtoAtualizado.get().getPreco()).isEqualTo(BigDecimal.valueOf(25.00).setScale(2, RoundingMode.CEILING));
    }

    @Quando("eu removo o produto")
    public void euRemovoOProduto() throws Exception {
        ultimaResposta = mvc.perform(
            MockMvcRequestBuilders.delete(ENDPOINT + "/" + produtoAtual.getId())
        ).andReturn().getResponse();
    }

    @Entao("o produto não deve mais existir")
    public void oProdutoNaoDeveMaisExistir() {
        assertThat(ultimaResposta.getStatus()).isEqualTo(200);

        Optional<ProdutoEntity> produtoRemovido = produtoRepositoryJpa.findById(produtoAtual.getId());
        assertThat(produtoRemovido).isEmpty();
    }
} 
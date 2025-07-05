package org.fiap.fastfood.infrastructure.adapter.rest.produto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fiap.fastfood.infrastructure.adapter.persistence.entity.CategoriaEntity;

import org.fiap.fastfood.infrastructure.adapter.persistence.entity.ProdutoEntity;
import org.fiap.fastfood.infrastructure.adapter.persistence.repository.CategoriaRepositoryJpa;
import org.fiap.fastfood.infrastructure.adapter.persistence.repository.ProdutoRepositoryJpa;
import org.fiap.fastfood.infrastructure.adapter.rest.produto.dto.AtualizarProdutoRequest;
import org.fiap.fastfood.infrastructure.adapter.rest.produto.dto.RegistrarProdutoRequest;
import org.fiap.fastfood.util.TestFixtureUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class ProdutoRestAdapterTest {

    private final String ENDPOINT = "/api/v1/produtos";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestFixtureUtil testFixtureUtil;

    @Autowired
    private ProdutoRepositoryJpa produtoRepositoryJpa;

    @Autowired
    private CategoriaRepositoryJpa categoriaRepository;

    @Autowired
    private JacksonTester<RegistrarProdutoRequest> registrarProdutoRequestJacksonTester;

    @Autowired
    private JacksonTester<AtualizarProdutoRequest> atualizarProdutoRequestJacksonTester;

    @BeforeEach
    void beforeEach() {
        produtoRepositoryJpa.deleteAll();
        categoriaRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve retornar 201 quando chamar via POST o endpoint /api/v1/produtos passando dados válidos")
    void testCreate() throws Exception {
        // Given
        CategoriaEntity categoriaEntity = testFixtureUtil.criarCategoriaEntity();
        RegistrarProdutoRequest registrarProdutoRequest =
                new RegistrarProdutoRequest("Nome", "Descricao", BigDecimal.ONE, categoriaEntity.getId());
        String dadosValidos = registrarProdutoRequestJacksonTester.write(registrarProdutoRequest).getJson();
        RequestBuilder request = post(ENDPOINT).contentType(APPLICATION_JSON).content(dadosValidos);

        // When
        MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isNotBlank();
    }

    @Test
    @DisplayName("Deve retornar 200 com uma lista das categorias quando chamar via GET o endpoint /api/v1/produtos")
    void testList() throws Exception {
        // Given
        testFixtureUtil.criarProdutoEntity();
        testFixtureUtil.criarProdutoEntity();

        // When
        MockHttpServletResponse response = mvc.perform(get(ENDPOINT)).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotBlank();
    }

    @Test
    @DisplayName("Deve retornar 200 com um produto quando chamar via GET o endpoint /api/v1/produtos/{id}")
    void testFindById() throws Exception {
        // Given
        ProdutoEntity produtoEntity = testFixtureUtil.criarProdutoEntity();

        // When
        MockHttpServletResponse response = mvc.perform(get(ENDPOINT + "/" + produtoEntity.getId())).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotBlank();
    }

    @Test
    @DisplayName("Deve retornar 200 com uma lista de produtos quando chamar via GET o endpoint /api/v1/produtos/find-by-category/{categoryId}")
    void testFindByCategoryId() throws Exception {
        // Given
        CategoriaEntity categoriaEntity = testFixtureUtil.criarCategoriaEntity();
        ProdutoEntity produtoEntity1 = testFixtureUtil.criarProdutoEntity(categoriaEntity);
        ProdutoEntity produtoEntity2 = testFixtureUtil.criarProdutoEntity(categoriaEntity);

        // When
        MockHttpServletResponse response = mvc.perform(get(ENDPOINT + "/find-by-category/" + categoriaEntity.getId())).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotBlank();
    }

    @Test
    @DisplayName("Deve retornar 200 quando chamar via PUT o endpoint /api/v1/produtos/{id} passando dados validos")
    void testUpdate() throws Exception {
        // Given
        CategoriaEntity categoriaEntity = testFixtureUtil.criarCategoriaEntity();
        ProdutoEntity produtoEntity = testFixtureUtil.criarProdutoEntity();
        AtualizarProdutoRequest atualizarProdutoRequest =
                new AtualizarProdutoRequest("Nome", "Descricao", BigDecimal.ONE, categoriaEntity.getId());
        String dadosValidos = atualizarProdutoRequestJacksonTester.write(atualizarProdutoRequest).getJson();
        RequestBuilder request = put(ENDPOINT + "/" + produtoEntity.getId()).contentType(APPLICATION_JSON).content(dadosValidos);

        // When
        MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotBlank();
    }

    @Test
    @DisplayName("Deve retornar 200 quando chamar via DELETE o endpoint /api/v1/produtos/{id} passando ID valido")
    void testDelete() throws Exception {
        // Given
        ProdutoEntity produtoEntity = testFixtureUtil.criarProdutoEntity();
        RequestBuilder request = delete(ENDPOINT + "/" + produtoEntity.getId());

        // When
        MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isBlank();
    }


}
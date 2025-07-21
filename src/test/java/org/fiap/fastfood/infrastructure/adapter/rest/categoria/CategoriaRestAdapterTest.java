package org.fiap.fastfood.infrastructure.adapter.rest.categoria;

import org.fiap.fastfood.infrastructure.adapter.persistence.entity.CategoriaEntity;
import org.fiap.fastfood.infrastructure.adapter.persistence.repository.CategoriaRepositoryJpa;
import org.fiap.fastfood.infrastructure.adapter.persistence.repository.ProdutoRepositoryJpa;
import org.fiap.fastfood.infrastructure.adapter.rest.categoria.dto.AtualizarCategoriaRequest;
import org.fiap.fastfood.infrastructure.adapter.rest.categoria.dto.RegistrarCategoriaRequest;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class CategoriaRestAdapterTest {

    private static final String ENDPOINT = "/api/v1/categorias";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestFixtureUtil testFixtureUtil;

    @Autowired
    private CategoriaRepositoryJpa categoriaRepository;

    @Autowired
    private ProdutoRepositoryJpa produtoRepository;

    @Autowired
    private JacksonTester<RegistrarCategoriaRequest> registrarCategoriaRequestJacksonTester;

    @Autowired
    private JacksonTester<AtualizarCategoriaRequest> atualizarCategoriaRequestJacksonTester;

    @BeforeEach
    void beforeAll() {
        produtoRepository.deleteAll();
        categoriaRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve retornar 201 quando chamar via POST o endpoint /api/v1/categorias passando dados válidos")
    void testCreate() throws Exception {
        // Given
        RegistrarCategoriaRequest registrarCategoriaRequest = new RegistrarCategoriaRequest("Nome", "Descricao");
        String dadosValidos = registrarCategoriaRequestJacksonTester.write(registrarCategoriaRequest).getJson();
        RequestBuilder request = post(ENDPOINT).contentType(APPLICATION_JSON).content(dadosValidos);

        // When
        MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isNotBlank();
    }

    @Test
    @DisplayName("Deve retornar 200 com uma lista das categorias quando chamar via GET o endpoint /api/v1/categorias")
    void testList() throws Exception {
        // Given
        testFixtureUtil.criarCategoriaEntity();
        testFixtureUtil.criarCategoriaEntity();

        // When
        MockHttpServletResponse response = mvc.perform(get(ENDPOINT)).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotBlank();
    }

    @Test
    @DisplayName("Deve retornar 200 com uma categoria quando chamar via GET o endpoint /api/v1/categorias/{id}")
    void testFindById() throws Exception {
        // Given
        CategoriaEntity categoriaEntity = testFixtureUtil.criarCategoriaEntity();

        // When
        MockHttpServletResponse response = mvc.perform(get(ENDPOINT + "/" + categoriaEntity.getId())).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotBlank();
    }

    @Test
    @DisplayName("Deve retornar 200 quando chamar via PUT o endpoint /api/v1/categorias/{id} passando dados validos")
    void testUpdate() throws Exception {
        // Given
        CategoriaEntity categoriaEntity = testFixtureUtil.criarCategoriaEntity();
        AtualizarCategoriaRequest atualizarCategoriaRequest = new AtualizarCategoriaRequest("Nome", "Descricao");
        String dadosValidos = atualizarCategoriaRequestJacksonTester.write(atualizarCategoriaRequest).getJson();
        RequestBuilder request = put(ENDPOINT + "/" + categoriaEntity.getId()).contentType(APPLICATION_JSON).content(dadosValidos);

        // When
        MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotBlank();
    }

    @Test
    @DisplayName("Deve retornar 200 quando chamar via DELETE o endpoint /api/v1/categorias/{id} passando ID valido")
    void testDelete() throws Exception {
        // Given
        CategoriaEntity categoriaEntity = testFixtureUtil.criarCategoriaEntity();
        RequestBuilder request = delete(ENDPOINT + "/" + categoriaEntity.getId());

        // When
        MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isBlank();
    }

}
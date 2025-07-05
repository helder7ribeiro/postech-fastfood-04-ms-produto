package org.fiap.fastfood.infrastructure.adapter.rest.categoria;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fiap.fastfood.application.port.driver.AtualizarCategoriaUseCase;
import org.fiap.fastfood.application.port.driver.BuscarCategoriaPeloIdUseCase;
import org.fiap.fastfood.application.port.driver.CriarCategoriaUseCase;
import org.fiap.fastfood.application.port.driver.ListarCategoriasUseCase;
import org.fiap.fastfood.application.port.driver.RemoverCategoriaUseCase;
import org.fiap.fastfood.domain.model.Categoria;
import org.fiap.fastfood.infrastructure.adapter.HeaderUtil;
import org.fiap.fastfood.infrastructure.adapter.persistence.entity.CategoriaEntity;
import org.fiap.fastfood.infrastructure.adapter.rest.categoria.dto.AtualizarCategoriaRequest;
import org.fiap.fastfood.infrastructure.adapter.rest.categoria.dto.AtualizarCategoriaResponse;
import org.fiap.fastfood.infrastructure.adapter.rest.categoria.dto.ListarCategoriaResponse;
import org.fiap.fastfood.infrastructure.adapter.rest.categoria.dto.RegistrarCategoriaRequest;
import org.fiap.fastfood.infrastructure.adapter.rest.categoria.dto.RegistrarCategoriaResponse;
import org.fiap.fastfood.infrastructure.adapter.rest.categoria.mapper.CategoriaRestMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class CategoriaRestAdapter {

    public static final String ENTITY_NAME = CategoriaEntity.class.getName();

    private final CriarCategoriaUseCase criarCategoria;
    private final ListarCategoriasUseCase listarCategorias;
    private final BuscarCategoriaPeloIdUseCase buscarCategoriaPeloId;
    private final AtualizarCategoriaUseCase atualizarCategoria;
    private final RemoverCategoriaUseCase removerCategoriaUseCase;

    private final CategoriaRestMapper categoriaRestMapper;

    @Value("${app.name}")
    private String applicationName;

    @PostMapping
    public ResponseEntity<RegistrarCategoriaResponse> create(@RequestBody @Valid RegistrarCategoriaRequest request) throws URISyntaxException {
        Categoria categoria = categoriaRestMapper.toDomain(request);
        categoria = criarCategoria.execute(categoria);
        RegistrarCategoriaResponse response = categoriaRestMapper.toRegistrarCategoriaResponse(categoria);

        HttpHeaders headers = HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, response.id().toString());

        return ResponseEntity.created(new URI("/api/v1/categorias/" + response.id())).headers(headers).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ListarCategoriaResponse>> list() {
        List<Categoria> categorias = listarCategorias.execute();
        List<ListarCategoriaResponse> responseList = categorias.stream().map(categoriaRestMapper::toListarCategoriaResponse).toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListarCategoriaResponse> findById(@PathVariable("id") Integer id) {
        Categoria categoria = buscarCategoriaPeloId.execute(id);
        ListarCategoriaResponse response = categoriaRestMapper.toListarCategoriaResponse(categoria);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizarCategoriaResponse> update(@PathVariable("id") Integer id, @RequestBody @Valid AtualizarCategoriaRequest request) {
        Categoria categoria = categoriaRestMapper.toDomain(request);
        categoria.setId(id);
        categoria = atualizarCategoria.execute(categoria);
        AtualizarCategoriaResponse response = categoriaRestMapper.toAtualizarCategoriaResponse(categoria);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        removerCategoriaUseCase.execute(id);
        return ResponseEntity.ok().build();
    }

}

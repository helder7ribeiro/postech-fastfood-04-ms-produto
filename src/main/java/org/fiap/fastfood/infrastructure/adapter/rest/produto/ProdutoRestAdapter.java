package org.fiap.fastfood.infrastructure.adapter.rest.produto;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fiap.fastfood.application.port.driver.AtualizarProdutoUseCase;
import org.fiap.fastfood.application.port.driver.BuscarProdutoPeloIdUseCase;
import org.fiap.fastfood.application.port.driver.BuscarProdutosPelaCategoriaUseCase;
import org.fiap.fastfood.application.port.driver.CriarProdutoUseCase;
import org.fiap.fastfood.application.port.driver.ListarProdutosUseCase;
import org.fiap.fastfood.application.port.driver.RemoverProdutoUseCase;
import org.fiap.fastfood.domain.model.Produto;
import org.fiap.fastfood.infrastructure.adapter.HeaderUtil;
import org.fiap.fastfood.infrastructure.adapter.persistence.entity.ProdutoEntity;
import org.fiap.fastfood.infrastructure.adapter.rest.produto.dto.AtualizarProdutoRequest;
import org.fiap.fastfood.infrastructure.adapter.rest.produto.dto.AtualizarProdutoResponse;
import org.fiap.fastfood.infrastructure.adapter.rest.produto.dto.ListarProdutoResponse;
import org.fiap.fastfood.infrastructure.adapter.rest.produto.dto.RegistrarProdutoRequest;
import org.fiap.fastfood.infrastructure.adapter.rest.produto.dto.RegistrarProdutoResponse;
import org.fiap.fastfood.infrastructure.adapter.rest.produto.mapper.ProdutoRestMapper;
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
@RequestMapping("/api/v1/produtos")
@RequiredArgsConstructor
public class ProdutoRestAdapter {

    public static final String ENTITY_NAME = ProdutoEntity.class.getName();

    private final CriarProdutoUseCase criarProdutoUseCase;
    private final ListarProdutosUseCase listarProdutosUseCase;
    private final BuscarProdutoPeloIdUseCase buscarProdutoPeloIdUseCase;
    private final BuscarProdutosPelaCategoriaUseCase buscarProdutoPelaCategoriaUseCase;
    private final AtualizarProdutoUseCase atualizarProdutoUseCase;
    private final RemoverProdutoUseCase removerProdutoUseCase;

    private final ProdutoRestMapper produtoRestMapper;

    @Value("${app.name}")
    private String applicationName;

    @PostMapping
    public ResponseEntity<RegistrarProdutoResponse> create(@RequestBody @Valid RegistrarProdutoRequest request) throws URISyntaxException {
        Produto produto = produtoRestMapper.toDomain(request);
        produto = criarProdutoUseCase.execute(produto);
        RegistrarProdutoResponse response = produtoRestMapper.toRegistrarProdutoResponse(produto);

        HttpHeaders headers = HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, response.id().toString());

        return ResponseEntity.created(new URI("/api/v1/produtos/" + response.id())).headers(headers).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ListarProdutoResponse>> list() {
        List<Produto> produtos = listarProdutosUseCase.execute();
        List<ListarProdutoResponse> responseList = produtos.stream().map(produtoRestMapper::toListarProdutoResponse).toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListarProdutoResponse> findById(@PathVariable("id") Integer id) {
        Produto produto = buscarProdutoPeloIdUseCase.execute(id);
        ListarProdutoResponse response = produtoRestMapper.toListarProdutoResponse(produto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find-by-category/{categoryId}")
    @Operation(method = "GET", description = "Busca todos os produtos para um id de categoria.")
    public ResponseEntity<List<ListarProdutoResponse>> findByCategory(@PathVariable("categoryId") Integer categoryId) {
        List<Produto> produtos = buscarProdutoPelaCategoriaUseCase.execute(categoryId);
        List<ListarProdutoResponse> response = produtos.stream().map(produtoRestMapper::toListarProdutoResponse).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizarProdutoResponse> update(@PathVariable("id") Integer id, @RequestBody @Valid AtualizarProdutoRequest request) {
        Produto produto = produtoRestMapper.toDomain(request);
        produto.setId(id);
        produto = atualizarProdutoUseCase.execute(produto);
        AtualizarProdutoResponse response = produtoRestMapper.toAtualizarProdutoResponse(produto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        removerProdutoUseCase.execute(id);
        return ResponseEntity.ok().build();
    }

}

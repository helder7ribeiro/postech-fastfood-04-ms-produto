Feature: CRUD de Produto

  Scenario: Criar um novo produto
    Given que não existe um produto com nome "Hamburguer"
    When eu crio um produto com nome "Hamburguer" e preço 20.0
    Then o produto deve ser criado com sucesso

  Scenario: Buscar um produto existente
    Given que existe um produto com nome "Hamburguer"
    When eu busco o produto pelo nome "Hamburguer"
    Then o produto deve ser retornado

  Scenario: Atualizar um produto existente
    Given que existe um produto com nome "Hamburguer"
    When eu atualizo o preço do produto para 25.00
    Then o produto deve refletir o novo preço

  Scenario: Remover um produto existente
    Given que existe um produto com nome "Hamburguer"
    When eu removo o produto
    Then o produto não deve mais existir 
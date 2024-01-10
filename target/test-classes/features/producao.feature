#language: pt

Funcionalidade: Gerenciamento de Pedidos

  Cenario: Recuperar informações do pedido
    Quando solicito solicito o retorno desse pedido utilizando o ID:"451b7ce3-373e-44a2-8c10-df163540056c"
    Então o código de status da resposta deve ser 200
    E a resposta deve conter o retorno de acordo com o contrato do pedido


  Cenário: Atualizar status do pedido
    Quando atualizo o status do pedido para o ID "451b7ce3-373e-44a2-8c10-df163540056c" para "CANCELADO"
    Então o código de status da resposta deve ser 204
Feature: Testes da API de Usuários

  Scenario: Obter usuários da página 2
  Given que eu solicito a lista de usuários na página 2
  When eu faço a requisição
  Then o status da resposta deve ser 200
  And a página deve ser 2
  And a lista de usuários deve ter 6 usuários
  And o primeiro usuário deve ter o email "michael.lawson@reqres.in"

  Scenario: Solicitar usuários de uma página inválida
  Given que eu solicito a lista de usuários na página invalida 23
  When eu faço a requisição
  Then o status da resposta deve ser 404
  And o corpo da resposta deve ser igual a "{}"

  Scenario: Criar um novo usuário
  Given que eu criar um usuário com nome "morpheus" e trabalho "leader"
  When eu faço a requisição
  Then o status da resposta deve ser 201
  And o usuário criado deve ter nome "morpheus" e trabalho "leader"

  Scenario: Atualizar um usuário existente usando PUT
  Given que eu atualizar o usuário com ID 2 para ter nome "morpheus" e trabalho "zion resident"
  When eu faço a requisição
  Then o status da resposta deve ser 200
  And o usuário atualizado deve ter nome "morpheus" e trabalho "zion resident"

  Scenario: Atualizar um usuário existente usando PATCH
  Given que eu atualizar o usuário com ID 2 para ter nome "morpheus" e trabalho "zion resident"
  When eu faço a requisição
  Then o status da resposta deve ser 200
  And o usuário atualizado deve ter nome "morpheus" e trabalho "zion resident"

  Scenario: Deletar um usuário existente
  Given que eu deletar o usuário com ID 2
  When eu faço a requisição
  Then o status da resposta deve ser 204

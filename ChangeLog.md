#FEATURES - 10-06-2026
Adicionado Autenticação de Usuário

#FEATURES - 03-06-2026
Implementado Tabela N-N entre Usuario e Equipamento
Endpoints criados para Equipamentos
Conexão com MariaDB

#FIXES
Removido campo rua do cadastro do usuário


#FIXES
DELETE Usuários e Endereco da 200 mesmo sem ter usuário cadastrado
Validar apagar endereco com usuário cadastrado com ele
PUT usuário não está mudando enderecoId (validar enderecoId null)
Ajustado requisição POST/Usuários 
POST/Endereco ajustar para retornar requisição inválida, atualmente está internal server error

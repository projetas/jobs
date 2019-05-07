# Projetas Application Server (REST API)
> Aplicação desenvolvida seguindo alguns padrões comerciais de segurança (autenticação + autorização), seguindo também algumas das melhores práticas para a construção de API's. Como objeto de estudo, foi implementado um CRUD de veículos onde podemos verificar algumas operações básicas em funcionamento.

Esta aplicação foi desenvolvida em Java 8, utilizando Spring Boot 2. Foi adicionado o componente Swagger UI para facilitar a realização de testes e servir também como documentação atualizada da API. Apesar de existir configuração relacionada à conexão com uma base de dados MySQL, foi adicionado uma base (em memória), visando facilitar a visualização da aplicação em funcionamento.

Como segurança, foi implementado um controle de acesso com Spring Security + JWT que são responsáveis por identificar e autorizar os usuários, limitando-os às operações que lhes foram disponibilizadas. Os perfis disponíveis são: Admin (controle irrestrito) e User (apenas consulta).
Utilize os usuários pre-cadastrados (admin:123456) e (user:123456) para verificar o funcionamento da aplicação. É possível ainda registrar-se com o perfil (user) a partir da aplicação.

Foi disponibilizado um cadastro básico de veículos, onde é possível listar, cadastrar, editar e excluir registros.


## Instalação

OS X & Linux & Windows:

```sh
mvn install
java -jar target/projetas-app-server-0.0.1-SNAPSHOT.jar
```


## Configuração para Desenvolvimento

Esta aplicação utiliza Maven como gerenciador de dependências, ao carregar o projeto na sua IDE será realizado o download de todas as dependências de forma automática. Para executar os testes da aplicação com o comando:

```sh
mvn clean test
```


## TO DO
+ Implementar recurso para atualização do token de autenticação, evitando expiração enquanto a aplicação estiver sendo utilizada.
+ Adicionar profiles (prod, dev, test) à aplicação.
+ Criar testes unitários e integrados.
+ Centralizar as mensagens da aplicação.
+ Aprimorar o end-point [(GET) /vehicles] para disponibilizar resultados paginados, com filtragem e ordenação de registros.
+ Aprimorar o tratamento de exceções.
+ Melhorar o feedback do usuário (mensagens) em resposta as ações realizadas.
+ Criar documentação JavaDoc (comentários de códigos).




## Histórico de lançamentos

* 0.0.1-SNAPSHOT
    * Aplicação inicial - CRUD de veículos.

## Meta

Ronivon Peixoto Sampaio – ronivon.peixoto@gmail.com

Distribuído sob a licença MIT.

[https://github.com/ronivon-peixoto/jobs](https://github.com/ronivon-peixoto/jobs)

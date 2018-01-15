# vehicle-rest-webapp
> Aplicação REST de cadastro de veículos.

[![Build Status][travis-image]][travis-url]

Trata-se de uma aplicação REST com Spring Boot + Spring Data JPA + Spring Security + Maven para cadastro de veículos.

## Instalação

OS X & Linux & Windows :

```sh
java -jar vehicle-rest-webapp-1.0.jar
```

## Exemplo de uso

A aplicação conta com o banco de dados embarcado H2, o qual permite facilmente que o CRUD de acesso ao DB do sistema possa ser realizado e testado. O Spring Security controla dois tipos de papéis de acesso à aplicação, usuário (user) e administrador (admin). Apenas o admin pode realizar o cadastro, a atualização e a remoção de um veículo. Tanto o user quanto o admin podem consultar todos os veículos ou um veículo em específico. Dois cadastros de acesso estão configurados de modo hardcoded no código fonte, sendo eles: 

Usúario: user / Senha: user
Usuário: admin / Senha: admin

Para teste e utilização da aplicação, estes usuários devem ser informados conforme cada tipo de requisição. O CRUD será realizado contra o banco de dados embarcado H2, via Spring Data JPA, e os dados persistirão enquanto a aplicação estiver em execução. Caso a aplicação seja reiniciada, os dados serão apagados.

A API da aplicação disponibiliza as seguintes rotas de acesso:

- GET user/vehicles
- POST admin/vehicles
- GET user/vehicles/{id}
- PUT admin/vehicles/{id}
- DELETE admin/vehicles/{id}

Os seguintes exemplos de uso podem ser estudados com a utilização do software Postman, levando-se em conta que todos os caminhos são relativos ao http://localhost:8080, no qual a aplicação irá iniciar:

### Requisição: consulta de todos os veículos via "GET user/vehicles" (sem autorização)

![Requisição GET user/vehicles sem autorização](doku/getAll_without_auth_req.png?raw=true "Requisição GET user/vehicles sem autorização")

### Resposta: consulta de todos os veículos via "GET user/vehicles" (sem autorização)

![Resposta GET user/vehicles sem autorização](doku/getAll_without_auth_res.png?raw=true "Resposta GET user/vehicles sem autorização")

### Requisição: consulta de todos os veículos via "GET user/vehicles" (com autorização de usuário)

![Requisição GET user/vehicles com autorização](doku/getAll_with_user_auth_req.png?raw=true "Requisição GET user/vehicles com autorização")

### Resposta: consulta de todos os veículos via "GET user/vehicles" (com autorização de usuário)

![Resposta GET user/vehicles com autorização](doku/getAll_with_user_auth_res.png?raw=true "Resposta GET user/vehicles com autorização")

### Requisição: cadastro de veículo via "POST admin/vehicles" (com autorização de usuário)

![Requisição POST admin/vehicles com autorização](doku/post_with_user_auth_req.png?raw=true "Requisição POST admin/vehicles com autorização")

### Resposta: cadastro de veículo via "POST admin/vehicles" (com autorização de usuário)

![Resposta POST admin/vehicles com autorização](doku/post_with_user_auth_res.png?raw=true "Resposta POST admin/vehicles com autorização")

### Requisição: cadastro de veículo via "POST admin/vehicles" (com autorização de admin)

![Requisição POST admin/vehicles com autorização](doku/post_with_admin_auth_req.png?raw=true "Requisição POST admin/vehicles com autorização")

### Resposta: cadastro de veículo via "POST admin/vehicles" (com autorização de admin)

![Resposta POST admin/vehicles com autorização](doku/post_with_admin_auth_res.png?raw=true "Resposta POST admin/vehicles com autorização")

### Requisição: consulta do cadastro de veículo via "GET admin/vehicles/{id}" (com id = 1 e autorização de admin)

![Requisição GET admin/vehicles/{id} com autorização](doku/getById_with_admin_auth_req.png?raw=true "Requisição GET admin/vehicles/{id} com autorização")

### Resposta: consulta do cadastro de veículo via "GET admin/vehicles/{id}" (com id = 1 e autorização de admin)

![Resposta GET admin/vehicles/{id} com autorização](doku/getById_with_admin_auth_res.png?raw=true "Resposta GET admin/vehicles/{id} com autorização")

### Requisição: atualização de veículo via "PUT admin/vehicles/{id}" (com id = 1 e autorização de admin)

![Requisição PUT admin/vehicles/{id} com autorização](doku/put_with_admin_auth_req.png?raw=true "Requisição PUT admin/vehicles/{id} com autorização")

### Resposta: consulta da atualização de veículo via "GET admin/vehicles/{id}" (com id = 1 e autorização de admin)

![Resposta GET admin/vehicles/{id} com autorização](doku/put_with_admin_auth_res.png?raw=true "Resposta GET admin/vehicles/{id} com autorização")

### Requisição: remoção de veículo via "DELETE admin/vehicles/{id}" (com id = 1 e autorização de admin)

![Requisição DELETE admin/vehicles/{id} com autorização](doku/delete_with_admin_auth_req.png?raw=true "Requisição DELETE admin/vehicles/{id} com autorização")

### Resposta: remoção de veículo via "DELETE admin/vehicles/{id}" (com id = 1 e autorização de admin)

![Resposta DELETE admin/vehicles/{id} com autorização](doku/delete_with_admin_auth_res.png?raw=true "Resposta DELETE admin/vehicles/{id} com autorização")


## Configuração para Desenvolvimento

A aplicação é do tipo standalone, sendo necessário apenas que uma JVM esteja instalada na plataforma. As dependências internas da aplicação são gerenciadas através da ferramenta Maven. A aplicação pode ser modificada (via pom.xml) para ser compilada para um .war que pode ser executado em qualquer Servlet container ou Webserver típico (Tomcat, WildFly, Glassfish etc). 

Para execução desta aplicação é necessário apenas que o download deste repositório seja realizado, extraído e acessado via terminal. O projeto vehicle-rest-webapp se encontrará dentro do diretório extraído, o seguinte comando deve então ser executado no terminal, com uma JVM pré-instalada, a JDK configurada adequadamente na variável de ambiente JAVA_HOME, bem como o Maven configurado no PATH do sistema: 

```sh
mvn spring-boot:run
```

## Histórico de lançamentos

* 1.0
    * O primeiro lançamento adequado
* 0.0.1-SNAPSHOT
    * Projeto em andamento

## Meta

Glênio Silva Pimentel – [@GlenioSP](https://www.linkedin.com/in/gleniosp/) – glenio.sp@gmail.com

Distribuído sob a licença MIT.

[https://github.com/GlenioSP/](https://github.com/GlenioSP/)

[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://ci.spring.io/teams/spring-boot/pipelines/spring-boot?groups=Build

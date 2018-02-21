Webservice para manipulação de Veículos.

Tecnologias utilizadas:
- Spring Boot
- Spring Data JPA 
- Spring Security
- Maven


Usúario: user / Senha: user (Permissão para listagem de veiculos /user/ )
Usuário: admin / Senha: admin (Permissão para as urls /admin)

## Instalação

1- Entrar no diretorio raiz do projeto (Onde está o pom.xml) executar o comando: mvn clean install

2 - Após o build ser finalizado com sucesso, rodar o seguinte comando: java -jar target/gs-accessing-data-mysql-0.1.0.jar

Alternativa para instalação: 

mvn spring-boot:run 


## Exemplo de uso


//Retorna todos os veiculos - GET
http://localhost:8080/user/vehicles

//Retorna os dados do veiculo solicitado - GET
http://localhost:8080/user/vehicles/{cod}

//Adiciona um veiculo novo - POST
http://localhost:8080/admin/vehicles

//Deleta um veiculo - DELETE
http://localhost:8080/admin/vehicles/{cod}

//Altera um veiculo - PUT
http://localhost:8080/admin/vehicles/{cod}

## Configuração para Desenvolvimento

mvn clean install
java -jar target/gs-accessing-data-mysql-0.1.0.jar

## Meta

Fernando Henrique - fernandimgts@gmail.com

Distribuído sob a licença MIT. Veja `LICENSE` para mais informações.

[https://github.com/fernandohs1500/jobs](https://github.com/fernandohs1500/)

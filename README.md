# Projetas - Jobs
> Gerenciamento de Veículos.

Cadastra, atualiza, busca, e remove veículos, respeitando as regras do negócio.


## Detalhando a arquitetura

Foi utilizada no backend, a versão 8 do JAVA, com maven e spring-boot para melhor gerenciamento das dependências.
Para implementação dos serviços RESTFul, foi usado o spring web, hibernate para gerenciar as entidades, flyway para controle de scripts, hsql como banco de dados, e JUnit puro como ferramenta de testes.

No front, basicamente ReactJS com Material-UI e PubsubJS.


## Instalação

A implementação foi separada em 2 projetos, sendo um de frontend utilizando ReactJS, e um de backend, utilizando Java 8.
Sendo assim, a instalação consiste em rodar os 2 projetos.

Backend (vá até o subdiretório backend, no diretório raíz):
```sh
mvn package
java -jar target/jobs-0.0.1-SNAPSHOT.jar
```

Frontend (vá até o subdiretório frontend, no diretório raíz):
```sh
npm install
npm start
```


## Exemplo de uso
O próprio sistema já faz uma pequena carga de veículos para evitar muito trabalho para o tester, mas, além das telas do frontend, as funcionalidades também podem ser testadas por chamadas REST.

Para isso, foi gerada uma collection do Postman com exemplos de todos os serviços REST, e ela pode ser encontrada no subdiretório postman, do diretório raíz.


## Informações adicionais

O backend está rodando na porta 8080, e o front, na 3000.


## Meta

Renato – renatoufu@gmail.com

[https://github.com/sabaum/](https://github.com/sabaum/)

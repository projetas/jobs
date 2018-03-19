# Nome do produto

O projeto possui duas telas para manutenção cadastral de carro, sendo que uma tela paginada exibe todos os carros já cadastrados, além de direcionar algumas ações como realizar um novo cadastro, editar ou remover um cadastro já existente. As ações de novo e edição de cadastro direcionam para a segunda tela desenvolvida.

Esse sistema foi construído através de um backend feito em java usando springboot (http://localhost:8080/cars) e banco de dados em memória H2, e um frontend usando angular 2 (http://localhost:4200/cars-ui).


## Instalação

### Instalação do backend

mkdir car-api

git clone https://github.com/vanderzago/jobs.git car-api

cd car-api

mvn install

java -jar target\car-api-0.0.1-SNAPSHOT.jar

### Instalação do frontend

mkdir car-ui

git clone https://github.com/vanderzago/car-ui.git car-ui 

cd car-ui

npm install

npm start

## Exemplo de uso

Abra um navegador e digite http://localhost:4200/cars-ui

## Configuração para Desenvolvimento

### Pre requisitos

1. Instalar NodeJS

Link: https://nodejs.org/en/

Abra um prompt de comando e digite node -v & npm -v

2. Instalar Maven

Link: https://maven.apache.org/download.cgi

Descompacte o arquivo binário e salve o caminho do diretório bin na variável de ambiente do sistema operacional.

Abra um prompt de comando e digite mvn -v

2. Instalar Git

Link: https://git-scm.com/downloads

Abra um prompt de comando e digite git --version

## Histórico de lançamentos

* 0.0.1
    * Liberação de versão do sistema que realiza operações básicas de listagem, criação, edição e remoção de cadastro de carro.

## Meta

Nome: Vander Eduardo Zago Alves (vanderzago@gmail.com)

# Vehicle

O projeto um API que fornece serviços que permite realizar consultas e cadastros de veículos.
O sistema permite realizar tanto a persistência de carros bem como marca e cores.
A arquitetura do projeto foi desenvolvida utilizando springboot como framework base.
Lombok foi outro framework utilizado para diminuir a verbosidade das classes.

## Pré-requisitos

Para desenvolver:
* JDK8 +
* Eclipse
* Lombok instalado no Eclipse
* Postman

Para implantar a API:
* JDK8 +
* Git
* Maven

## Instalação

### Instalação da API

* mkdir Vehicle
* git clone https://github.com/mnleles/jobs.git Vehicle
* cd Vehicle/vehicle-parent
* mvn clean install
* java -jar vehicle-api/target/vehicle-api.jar

## Exemplo de uso

Abra o Postman e importe a collection:
* Vehicle/vehicle-parent/development-stuff/VehiclePostman.json

## Configuração para Desenvolvimento

### Pre requisitos

1. Instalar JDK8
	Link: http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html

2. Instalar Eclipse
	Link: https://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/oxygen3a
	Import o java code formatter localizado na pasta: 
	* Vehicle/vehicle-parent/development-stuff/eclipse-config/code-formatter.xml
	
3. Instalar Lombok no eclipse 
	Execute na pasta do projeto: 
	* java -jar Vehicle/vehicle-parent/development-stuff/eclipse-config/java -jar lombok.jar

4. Instalar Git
	Link: https://git-scm.com/downloads

5. Instalar Maven
	Link:  https://maven.apache.org/download.cgi
	Descompacte o arquivo e crie uma variável de ambiente do sistema operacional.

6. Postman
	Link: https://www.getpostman.com/apps

## Histórico de lançamentos

* 0.0.1-SNAPSHOT
	* Starting project.
	* Improving structure, it was added database connection, profiles and development stuffs.
	* It was added structure to handle exceptions and to validate data
	* It was implemented repository, service, rest and integrated tests.
	* It was improve code and it implemented junits tests.

## Meta

Nome: Marcos Nasário (mnleles@gmail.com)

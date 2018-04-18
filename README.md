# Projetas CRUD CAR
> Aplicação para cadastro, alteração e remoção de informações sobre carros.


## Instalação:
Esta aplicação utiliza springboot e para executar bastar executar o comando:
    mvn spring-boot:run

Obs: É necessário ter o maven instalado e java8.

O banco de dados utilizado é na memoria java (MySql), para evitar complicações de instalação do banco de dados.


## Exemplo de uso
A pagina inicial: http://localhost:8090
Será solicitado uma autenticação simples de usuário e nome.
A lista de usuários cadastra é:
joaol
teste
user

todos possuem a senha: 123

Logo após se autenticar o usuário será redirecionado para um tela onde,
será mostrado alguns campos editaveis: Marca, Modelo, Ano, Cor, Novo e Preço seguido de um botão "Adicionar".
O botão "Adicionar" permite fazer a adição de novos dados ao banco de dados, com as informações preenchidas nos campos informados acima.
Logo abaixo desse pequeno formulário é mostrado a lista de carros cadastrados no banco.
Para atualizar a lista apos cadastrar um novo carro basta atualizar a pagina.

Caso o usuário clique no botão "DELETE" em um dos itens da lista de carros o mesmo será removido do banco de dados.
E da mesma forma se ele clica no link ele será redirecionado para outra tela onde ele poderá editar as informações do carro.



## Configuração para Desenvolvimento
O gerenciador de dependencia é o maven, para baixar todas as dependencias basta executar:
 mvn clean install

Para rodas os testes unitários execute:
 mvn test


## Histórico de lançamentos

* 0.0.1-SNAPSHOT
    * Versão inicial


## Meta
 João Ludovico Maximiano Barbosa - joaolmbarbosa@gmail.com


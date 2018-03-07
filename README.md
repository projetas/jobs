# Especificação da Aplicação

Nós criamos esta pequena especificação para ajudá-lo a criar aplicativos incríveis e consistentes. As diretrizes e requisitos descritos abaixo fazem parte do nosso processo de desenvolvimento. Certifique-se de que não só leia, mas também a compreenda. 

_Observação:_ [Sócrates](https://en.wikipedia.org/wiki/Socrates) e [nós](http://projetas.com.br/) estamos cientes de que ninguém sabe tudo, então não se preocupe se não terminar toda a aplicação, faça o seu melhor, nos envie e divirta-se ;-).

> Sócrates diz: Sábio é aquele que conhece os limites da própria ignorância.

## README

Todos as aplicações devem incluir um README descrevendo a estrutura, a implementação geral e o processo de compilação, se necessário. Existe um [exemplo de readme](README.template.md) como ponto de partida.

## Código

Certifique-se de seguir estas diretrizes:

* Siga algum [estilo de código](https://en.wikipedia.org/wiki/Programming_style), de preferência o oficial da própria linguagem/framework.
* Escreva código em inglês.
* Use algum [gerenciador de pacotes](https://en.wikipedia.org/wiki/Package_manager) para dependências de terceiros, se necessário.
* Organize o código de acordo com as melhores práticas da linguagem/framework e padrões de projeto.
* Cobertura de testes (unitários, integrados):
  * Entrega com no mínimo 80% de cobertura dos testes;
  * Menos que 20% de duplicação de código;
  * Nenhum defeito, vulnerabilidade, débito técnico ou fator de complexidade de código alta;
* Mantenha o código simples ([Princípio KISS](https://en.wikipedia.org/wiki/KISS_principle)).
* Preze pela extensibilidade e manutenibilidade do código.
* Exigimos que as aplicações funcionem em plataformas com arquitetura x86 e sistemas operacionais, tais como: Microsoft Windows, macOS e Linux.

## Funcionalidades

Crie uma aplicação (cliente e servidor) de cadastro de veículos. O servidor deve expor uma [API](https://en.wikipedia.org/wiki/Application_programming_interface) que a aplicação cliente irá acessar para compor o [CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete) de uma entidade `Veículo`.

#### Atributos do Veículo

* Marca, texto, não nulo, 40 caracteres;
* Modelo, texto, não nulo, 50 caracteres, 
* Cor, texto, não nulo, 30 caracteres;
* Ano, inteiro positivo, não nulo;
* Preço, decimal positivo, não nulo;
* Descrição, texto;
* É novo?, boleano, não nulo;
* Data de cadastro, data e hora, não nulo;
* Data de atualização, data e hora, nulo.

## Instruções

* Utilize a tecnologia de sua escolha (sempre seguindo as diretrizes de boas práticas).
* Caso você não tenha domínio do `cliente` ou do `servidor`, faça apenas um deles. Se necessário, utilize algum tipo de simulação (mock) para o funcionamento da aplicação.
* Faça a [cópia](https://help.github.com/articles/fork-a-repo/) do repositório (fork), desenvolva e submeta uma [solicitação de mudança](https://help.github.com/articles/creating-a-pull-request/) (pull request) contra o ramo mestre padrão (master). 
* [Squash](https://github.com/servo/servo/wiki/Beginner's-guide-to-rebasing-and-squashing) seus compromissos (commits). 
* Escreva uma descrição convincente de sua solicitação de mudança (pull request) de acordo com o guia [How to Write a Git Commit Message](https://chris.beams.io/posts/git-commit/).

## Referências

* [git - the simple guide](https://rogerdudler.github.io/git-guide/)
* [How to Write a Git Commit Message](https://chris.beams.io/posts/git-commit/)
* [Markdown Cheat Sheet](https://www.markdownguide.org/cheat-sheet)
* [A curated list of awesome READMEs](https://github.com/matiassingers/awesome-readme)
* [README.md template for your open-source project](https://github.com/dbader/readme-template)
* [Learn REST: A RESTful Tutorial](http://www.restapitutorial.com/)
* [Best Practices for Designing a Pragmatic RESTful API](http://www.vinaysahni.com/best-practices-for-a-pragmatic-restful-api)
* [Roy Fielding on Versioning, Hypermedia, and REST](https://www.infoq.com/articles/roy-fielding-on-versioning)
* [10 Best Practices for Better RESTful API](https://blog.mwaysolutions.com/2014/06/05/10-best-practices-for-better-restful-api/)
* [Best RPC Programming Practices](https://msdn.microsoft.com/en-us/library/windows/desktop/aa373563(v=vs.85).aspx)
* [Best practices for service interface design in SOA](https://www.ibm.com/developerworks/library/ar-servdsgn1/)

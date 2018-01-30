# Cadastro de veículos
> Aplicação (cliente e servidor) de cadastro de veículos.

[![Greenkeeper badge](https://badges.greenkeeper.io/ismaelqueiroz/jobs.svg)](https://greenkeeper.io/)
[![Build Status](https://secure.travis-ci.org/ismaelqueiroz/jobs.png)](http://travis-ci.org/ismaelqueiroz/jobs)
[![Coverage Status](https://coveralls.io/repos/github/ismaelqueiroz/jobs/badge.svg)](https://coveralls.io/github/ismaelqueiroz/jobs)

Projeto exemplo de um CRUD de veículos, projeto feito em NodeJS e Angular, composto de back-end e front-end respectivamente.

Plataformas / Frameworks
-------------------------
* [**Angular**]('http://angular.io') **5.2.0**.
* [**Material Angular**]('https://material.angular.io') **5.1.0**.
* [**Express**]('https://express.io/') **4.16.2**.
* [**Mocha**]('https://mochajs.org//') **4.1.0**.
* [**Travis CI**]('https://travis-ci.org/').
* [**Coveralls**]('https://coveralls.io/').

## Instalação

Instalando dependência para o back-end e front-end, navegar nas pastas `jobs-sl` e `jobs-ui`, respectivamente e executar os procedimentos abaixo:

OS X | Linux | Windows:

```sh
npm install
```

## Testando

Navegar até a pasta jobs-sl, executar:

```sh
npm test
```

## Executando back-end

Navegar até a pasta jobs-sl, executar:

```sh
npm start
```
Após execução com sucesso, o seguinte trecho de código será exibido:

```shell
> nodemon -e ts --exec ts-node ./server.ts

[nodemon] 1.12.1
[nodemon] to restart at any time, enter `rs`
[nodemon] watching: *.*
[nodemon] starting `ts-node ./server.ts`
Server listening on http://localhost:8000
Application ready to serve requests.
Environment: development
```
O serviço está exposto no seguinte endereço: [http://localhost:8000](http://localhost:8000)

Api's publicadas: 

* GET /api/v1/vehicles
* GET /api/v1/vehicles/{id}
* POST /api/v1/vehicles
* PUT /api/v1/vehicles
* DELETE /api/v1/vehicles/{id}

## Executando front-end

Navegar até a pasta jobs-ui, executar:

```sh
npm start
```
Após execução com sucesso, o seguinte trecho de código será exibido:

```shell
** NG Live Development Server is listening on localhost:4200, open your browser on http://localhost:4200/ **                                                         
Hash: 73b7a45a8eb7618f1131
Time: 12667ms
chunk {inline} inline.bundle.js (inline) 5.79 kB [entry] [rendered]
chunk {main} main.bundle.js (main) 80.8 kB [initial] [rendered]
chunk {polyfills} polyfills.bundle.js (polyfills) 558 kB [initial] [rendered]
chunk {scripts} scripts.bundle.js (scripts) 140 kB [initial] [rendered]
chunk {styles} styles.bundle.js (styles) 162 kB [initial] [rendered]
chunk {vendor} vendor.bundle.js (vendor) 16.2 MB [initial] [rendered]

webpack: Compiled successfully.
```

Acesse a url em algum browser: [http://localhost:4200](http://localhost:4200)

## Histórico de lançamentos

* 1.0.0-alpha.1
    * Primeira versão do projeto

## Meta

Ismael Queiroz – [@IsmaelQueiroz](https://twitter.com/isqueiroz) – ismaelqueiroz@gmail.com

Distribuído sob a licença MIT.

[https://github.com/ismaelqueiroz/jobs](https://github.com/ismaelqueiroz/)

[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics
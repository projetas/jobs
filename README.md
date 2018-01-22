# CARful
> RESTful API server for car registration

[![NPM Version][npm-image]][npm-url]
[![Build Status][travis-image]][travis-url]
[![Downloads Stats][npm-downloads]][npm-url]

My project is RESTful API server created using Maven, Spring Boot and Java. It is a Car API, where you can use HTTP Verbs to do CRUD operations in a resgister of cars. You can register information like:

Brand;
Model;
Color;
Year;
Price;
Description;
IsNew;
Time Register;
Time Update;

It consists only of the server, tests can be run with POSTman, sending json.

![](../header.png)

## Install

OS X & Linux:
Download Maven from:
https://maven.apache.org/download.cgi

extract using

```sh
tar xzvf apache-maven-3.5.2-bin.tar.gz
```
Add the bin directory of the created directory apache-maven-3.5.2 to the PATH environment variable using

```sh
export PATH=/opt/apache-maven-3.5.2/bin:$PATH
```
go to project directory and run
```sh
mvn spring-boot:run
```
The application will start...

Windows:

Download Maven from:
https://maven.apache.org/download.cgi

extract from the zip file

Add the bin directory of the created directory apache-maven-3.5.2 to the PATH environment variable

```sh
Adding to PATH: Add the unpacked distribution’s bin directory to your user PATH environment variable by opening up the system properties (WinKey + Pause), selecting the “Advanced” tab, and the “Environment Variables” button, then adding or selecting the PATH variable in the user variables with the value C:\Program Files\apache-maven-3.5.2\bin. 
```
go to project directory and run
```sh
mvn spring-boot:run
```
The application will start...

## Histórico de lançamentos

* 1.0.0 RESTful API server

## Meta

Miguel Juvencio – miguelhjfreitas@gmail.com

Distribuído sob a licença XYZ. Veja `LICENSE` para mais informações.

[https://github.com/miguelhjfreitas/github-link](https://github.com/miguelhjfreitas/)

[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics

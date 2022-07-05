# FireOtaku

<p align="center">
    <a href="#fireotaku">Início</a> ⚯ 
    <a href="#endpoints">Endpoints</a> ⚯ 
    <a href="#execução">Execução</a> ⚯ 
    <a href="#ferramentas">Ferramentas</a> 
</p>

> Status: Beta

<p>Este é um projeto desenvolvido para o <strong>Desafio Kitsu - Firedev IT</strong>. O desafio consiste em consumir a API https://kitsu.docs.apiary.io e alcançar os seguintes endpoints:</p>

+ Retorno dos animes mais populares;
+ Retorno dos mangas mais populares;
+ Buscar anime ou manga com filtros (título, ano, etc);
+ Obter informações de um anime ou manga;
+ Obter informações de um episodio de um anime especifico.

## Endpoints

    🟢 Retorno dos animes mais populares;
    🟢 Retorno dos mangas mais populares;
    🟢 Buscar anime ou manga com filtros (título, ano, etc);
    🟢 Obter informações de um anime ou manga;
    🟢 Obter informações de um episodio de um anime especifico.
    🟢 Criar estrutura no banco para salvar as buscas do usuário.
    🟢 Criar um relatório de itens mais pesquisados com base na estrutura criada.

## Execução

A execução do aplicativo é feita através do spring-boot.

No console de uma IDE, insira o comando:

`mvn spring-boot:run`

Em seguida, abra o navegador na porta: localhost:8080

## Ferramentas

* Spring Boot
* Maven
* Visual Studio Code

### Extensões VSCode:
* API Elements
* IntelliCode
* Extension Pack for Java
* Spring Boot Extension Pack

Toda a execução de testes foi feita através do servidor TomCat do Spring Boot na porta: localhost:8080

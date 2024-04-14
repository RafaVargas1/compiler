# Compilador
Projeto prático da disciplina de teoria dos compiladores onde será desenvolvido um compilador utilizando a linguagem Java. O objetivo final será compilar uma linguagem própria.

Projeto sendo desenvolvido pelos alunos:
Arthur Vieira da Silva    - 202035013
Rafael de Oliveira Vargas - 202035022

Para compilar o projeto é preciso primeiro gerar o arquivo do scanner usando o jflex:

```jflex ./src/main/scanner/lang.flex```

Em seguida compilar o projeto:

```javac -d src/build src/main/token/*.java src/main/scanner/*.java src/main/*.java```

Finalmente, para executar o programa basta chamar a função principal passando um arquivo de entrada, como no exemplo abaixo:

```java -cp src/build Main testes/lexico/testFile1.txt```

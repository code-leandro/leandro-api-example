
### Para o desenvolvimento deste projeto foi utilizado
- Spring Boot
- Apache maven
- JDK 17
- Banco de dados H2 em memória
- Swagger
- JUnit 5

Seguem abaixo algumas instruções básicas de execução.

### Baixar o projeto usando git clone
```
git clone git@github.com:leandro-souza-dev/leandro-agrotis.git
```


### Executar o projeto
O projeto vai subir na porta 8080. Portanto, para execução, esteja certo de estar porta estar disponível.
```
$ cd leandro-agrotis
$ mvn spring-boot:run
```

Caso já esteja utilizando a porta 8080, poderá especificar uma outra porta como por exemplo 8081:
```
$ mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

### Swagger
Após a execução local, é possível acessar o swagger no link:
[Link Swagger](https://localhost:8080/swagger-ui/index.html)

### Para executar apenas os testes unitários
```
$ mvn test
```
Obs.: A cobertura de testes unitários ficou acima de 80%.

### Para executar somente os testes de integração
Foi criado um profile no pom.xml para essa finalidade. Executar:
```
mvn test -Pintegration-tests
```
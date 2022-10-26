
### Executar o projeto

```
$ mvn spring-boot:run
```

### Swagger
Após a execução local, é possível acessar o swagger no link:
[Link Swagger on Localhost](https://localhost:8080/swagger-ui/index.html)

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
# Mini Autorizador

# Tecnologias

- Java 8
- Spring Boot
- Lombok
- Intellij
- Maven
- H2
- Docker
- MySql
- MockMvc
- OpenApi

# Arquitetura

Optei por fazer uma arquitetura mais simples, assim como faria no dia a dia se tivesse apenas uma api ou serviço. 
Usei uma controller, service e repository. Mesmo com a complexidade dos vários ifs, não quis usar nenhum padrão de 
projeto como o strategy, pois no fim teria arquivos separados, aumentando a complexidade do código e dificultando o 
entendendimento.

# Banco de dados

Por se tratar de uma api de teste, usei o H2 para os testes unitários e MySql para a aplicação. 
Bastando rodar o docker-compose para subir.


## Executando a aplicação

Acesse o diretório docker dentro da raiz do projeto.

```
$ cd docker
```

Agora execute o docker-compose para subir o Mysql

```
$ sudo docker-compose up -d
```

Após isso, volte para a pasta raiz.

```
$ cd ..
```

Execute o mvn clean install e depois o spring-boot:run

```
$ mvn clean install -DskipTests=true
```

```
$ mvn spring-boot:run
```

Com a aplicação rodando, vá ao navegador e digite:

```
http://localhost:8080/swagger-ui.html
```

# Como usar

Acessando o Swagger verá que temos duas API's para cartões, um POST e um GET com o mapping "/cartoes"

Se acessar o POST no caminho abaixo
```
http://localhost:8080/cartoes
```

Com o body
```
{
    "numeroCartao": "8888999988889111",
    "senha": "8787"
}
```

Terá o resultado

```
{
    "numeroCartao": "8888999988889111",
    "senha": "8787",
    "saldoCartao": 500
}
```

Usando o cUrl abaixo terá o mesmo resultado.

```
curl -X 'POST' \
  'http://localhost:8080/cartoes' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "numeroCartao": "8888999988889111",
  "senha": "8787"
}'

```


Se acessar via GET no caminho abaixo, terá acesso ao saldo.
```
http://localhost:8080/cartoes/8888999988889111
```

Resultado
```
500
```

Usando o cUrl abaixo terá o mesmo resultado.
```
curl -X 'GET' \
'http://localhost:8080/cartoes/8888999988889111' \
-H 'accept: */*'
```


Para fazer uma transação, basta acessar via POST o caminho abaixo.

```
http://localhost:8080/transacoes
```

```
{
"numeroCartao": "8888999988889111",
"senha": "8787",
"valor": 10
}
```

Terá o resultado

```
OK
```

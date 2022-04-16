# CampeonatoApi
#### *Desenvolvido por: Jessica Machado / Matrícula: 138932*

API de gerenciamento do campeonato de futebol brasileiro. Conta com:

- CRUD de jogadores
- CRUD de times
- Registro e busca de torneios
- Resgistro de eventos de partidas

*obs.: para uma visão mais detalhada da estrutura, vide arquivo swagger.yaml*

---

Utilize o comando abaixo para subir o serviço localmente:

````shell script
mvn package mn:run
````

Para testar a api de exemplo execute:

````shell script
curl http://localhost:8080/api/campeonato/v1/ping

< HTTP/1.1 200 OK
< Content-Type: application/json
< content-length: 54
<
{"message":"Hello World!","when":1588280219.031339000}
````

Para gerar um **jar** executável, basta empacotar com o perfil `shade` ativado:

````shell script
mvn clean package -Pshade
```` 

---

### Stack

- Micronaut
- PostgreSQL
- Swagger/Swagger Codegen
- Lombok
- SLF4J
- JUnit5
- Mockito
- Pitest

---

### Melhorias

Ao montar os endpoints deste projeto, nota-se que o serviço ficou bastante grande. Num cenário real talvez seria uma
manobra melhor criar serviços diferentes para cada um dos recursos. Outra melhoria possível seria a possibilidade de ser
uma estrutura genérica que poderia ser usada por qualquer campeonato. E acho que a mais importante melhoria seria o
acrescimo de uma camada de segurança/autenticação, como OAuth.

---

Sinta-se a vontade para abrir forks e contribuir!
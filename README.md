### Doc:

- Na pasta raiz do projeto há a coleção de requisições prontas no POSTMAN com todos os casos de uso.</br>
- Disponível na raiz do projeto o diagrama de classe das entidades. </br>

## Docker
- Instale Docker + Docker-Compose .</br>
- Abra o terminal na pasta onde o arquivo docker-compose.yml se encontra:</br>
- Execute o comando:
```
$docker-compose up
```
- Enquanto o container do banco de dados não sobe, o app java ficará reinicalizando.</br>
- Teste os endpoints da API com a coleção do POSTMAN.

## Maquina Local:

- Instale Maven 3.9.6 (registrado no path do sistema);
- Instale Java na versão 21 (JAVA_HOME no path do sistema);
- Abra o terminal na pasta raiz do projeto e execute os comandos abaixo:</br>
```
$cd /teste-attus</br>
$mvn clean install spring-boot:run</br>
```
- Teste os endpoints da API com a coleção do POSTMAN.

### Class Diagram

![image](https://github.com/AndOliver46/teste-spring-ats/assets/101358552/64727b6b-9510-4cae-ad79-f85632a0965b)

### Stack:

Spring Boot </br>
Spring Web </br>
Spring Data JPA </br>
Spring Validation </br>
Spring HATEOAS </br>
Flyway </br>
H2 Database </br>
Junit </br>
Mockito </br>




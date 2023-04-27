## Sobre o projeto
Este projeto é uma aplicação de gerenciamento de carro desenvolvido com Spring Boot. Ele permite que os administradores criem, atualizem, excluam e obtenham informações sobre os veículos cadastrados no sistema, e os usuários apenas visualizem os veículos.

## Tecnologias utilizadas

- Java 17
- Spring Boot
- MySQL
- Spring Security
- JWT
- Maven
- JPA
- H2 database

## Instalação
Clone o repositório para o seu computador.
Abra o terminal na pasta raiz do projeto e execute o seguinte comando para compilar e empacotar a aplicação:
```
mvn clean package
```
Após o empacotamento, execute o seguinte comando para iniciar a aplicação:
```
java -jar target/exame-chunin-0.0.1-SNAPSHOT.jar
```
Acesse a aplicação no navegador no endereço:
```
http://localhost:8080
```

## Realizar testes do projeto
- Abra o projeto e navegue até o arquivo /src/resources/application.properties
- Troque o profile do spring para "test"
```
spring.profiles.active=test
```

## Uso

A aplicação possui uma API REST para realizar as operações de CRUD. Para realizar as operações, utilize os seguintes endpoints:

- GET /vehicles: retorna uma pagina de todos os veículos cadastrados no sistema.
- GET /vehicles/{id}: retorna as informações do veículo com o ID informado.
- POST /vehicles: cria um novo veículo no sistema.
- PUT /vehicles/{id}: atualiza as informações do veículo com o ID informado.
- DELETE /vehicles/{id}: exclui o veículo com o ID informado.

Para utilizar a API, é necessário enviar as informações no formato JSON no corpo da requisição. O seguinte é um exemplo de um objeto JSON para criar um carro:
```
{
    "name": "AMG A45",
    "year": 2017,
    "price": 256580.64,
    "description": "O Mercedes A45 AMG é um hatch médio esportivo de alta performance, que tem como destaque seu poderoso motor M139, uma pequena usina de força com nada menos que 421 cavalos. Isso tudo em 2.0 litros, sendo o motor deste tipo com a maior potência por litro, mais de 210 cavalos ou 105,25 cavalos por cilindro.",
    "imageUrl": "https://car-images.bauersecure.com/wp-images/12184/mercedes-amg-a45-03.jpg",
    "vehicleType": "CAR",
    "vehicleModel": "SEDAN",
    "vehicleFuel": "GASOLINE",
    "vehicleBrand": "MERCEDES_BENZ"
}
```

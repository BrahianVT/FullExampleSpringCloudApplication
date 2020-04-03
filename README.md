# Example Spring CloudApplication

This project is an cloud application example using Spring Boot and Spring Cloud Netflix with some
components such as Eureka Server, Sleuth, Zipkin, Feign and another related technologies like Spring Cloud Gateway
and vault.

This application is the Back-End, later I will create the Front-end.

## Prerequisites and Technologies 

For the development environment I use:
* Intellij Idea, Windows 10 Home
* Lombok Intellij Idea plugin
* Docker and Docker Compose(docker tool box) (https://docs.docker.com/toolbox/toolbox_install_windows/) depending in the operating system in my case Windows 10 home
* [Spring Initialzr](https://start.spring.io/) 
* MySql and H2 as databases
* Java 8, Spring Boot 2, Maven, Spring Data, Netflix Eureka, Ribbon, Hystrix, Zipkin, Sleuth, Spring Data, Lombok, JUnit, Mockito, Hamcrest, Vault, Docker Compose


### Configurations and Installing

First download the project from the repository, then run the command in CMD in the root folder(make sure you already have docker compose installed on your computer)

```
docker-compose build
```

You make sure the three services is running before run the proyect you can check it with the command **docker ps** , if all works as expected.
You will be able to see three containers running vault, mysqldb and zipkin.

After all the containers are running, (before this step install the Lombok Intellij Idea plugin you need it and restart IntelliJ) import the components to Inllij Idea,
I did it with the option File -> New -> " Module from Existing Sources.." and import one by one each of the modules or if you prefer you can import as project 
each component but you will create a workspace per proyect, also you can use another IDE like Ecipse for java development because of the projects are maven projects 
there isn't problem with that.

Then afterwards import all the projects you will end up with the following components in IntelliJ:

* api-gateway - The component that will route the incoming requests to microservices
* catalog-service -Component that will store data in the MySql database.
* config-service - The component stores almost all the components configurations.
* hystrix-dashboard - Create a dashboard where you will be able to visualize metrics about each HystrixCommand.
* inventory-service - Component that will store data in the H2 dataBase.
* service-registry - Eureka Server for registration and discovery of microservices.

#### Test

After that, run all components as spring boot applications, you can test one microservice **http://localhost:8080/v1/api/products/findProductByCode/P001**
in PostMan or in any web browser and if it works, you will visualize the response with data consulted with the catalog-service that retrieves 
the information from the Mysql database and also consumes the inventory-service from an H2 database.
Basically the essence of microservices architecture, distributed microservices working independently covering a specific business functionality,interacting 
with other microservices.

Also I used the tecnologies listed above to handle the most common problems that you can face when you are working with distributed applications, Eureka, Vault, Ribbon, Hystrix
Sleuth and Zipkin will provide you with a lot of functionality to achive that.

For more information and documentation check the **WIKI SECTION**.   
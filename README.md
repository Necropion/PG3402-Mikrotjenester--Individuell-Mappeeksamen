# PG3402-Mikrotjenester--Individuell-Mappeeksamen

### Overview

The proposed project is a Vehicle Store Platform, designed to facilitate the buying and
selling of vehicles through a modern, scalable microservices architecture. The platform
aims to provide users with a seamless experience for browsing available vehicles,
managing their shopping carts and completing purchases. Leveraging Spring Boot
for the backend services and React for the frontend. Key components include an
Authentication Service for user management, a Dealership Service for vehicle listings and
collections of owned vehicles, a Cart Service for managing user selections and purchases 
and a Gateway Service to handle routing and security.

### Running the project locally

### Running the project with Docker containers locally

After unzipping this project, you will need to create a docker image for all of my services

Here are the commands and locations necessary to build all images:

Service: Client  
Location: Vehicle Store/Client  
Command: docker build -t client:1.0 .  
  
Service: Authentication  
Location: Vehicle Store/Authentication  
Command: mvn spring-boot:build-image -DskipTests  

Service: Cart
Location: Vehicle Store/Cart
Command: mvn spring-boot:build-image -DskipTests  
  
Service: Dealership  
Location: Vehicle Store/Dealership  
Command: mvn spring-boot:build-image -DskipTests  
  
Service: Gateway  
Location: Vehicle Store/Gateway  
Command: mvn spring-boot:build-image -DskipTests  
  
If you do not have consul and rabbitmq images pre-downloaded, run these commands:

Consul:  
docker pull hashicorp/consul:1.19.2  
  
RabbitMQ:  
docker pull rabbitmq:3-management  
  
With these images ready, you should be able to run my docker compose file:  
from project root folder Vehicle Store:

Move to docker folder:
cd ./docker

This will run logs for all services in your terminal at the same time:
docker compose -f "docker-compose-services-v1.yml" up

This will not run logs for all the services in your terminal:
docker compose -f "docker-compose-services-v1.yml" up -d  


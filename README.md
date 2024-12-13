# PG3402-Mikrotjenester--Individuell-Mappeeksamen

## Vehicle Online Store

# Tech Stack
- React.js
- Parcel
- Local H2 Database
- Spring Boot Java Backend

# Dependencies
- Tailwind CSS
- Gateway
- RabbitMQ
-

# Frontend Features
- Login
- Cart
- Vehicle Recommendations
- Service and Repair
- Part Shop

# Backend
- Message Broker
- Sync/Async communication


# Startup Commands

### Running teh project with Docker

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
docker compose -f "docker-compose-services-v1.yml" up (This will run logs for all services in your terminal at the same time)  
docker compose -f "docker-compose-services-v1.yml" up -d (This will not run logs for all the services in your terminal)  


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

### User Stories

#### Authentication and Profile Management

1. As a user, I want to create an account and log in securely so that I can access my personal profile and system features like managing carts and purchases.
2. As a user, I want to view my profile, including a list of my owned cars and past receipts, so that I can track my purchases and ownership.
  
#### Car Listing and Search

3. As a user, I want to browse the dealership’s inventory and see detailed information about cars (e.g., make, model, color, year, stock) so that I can explore what’s available for purchase.
4. As a user, I want to quickly find specific cars using a search bar so that I can locate cars that meet my needs without scrolling through the entire inventory.

#### Shopping Cart Management

5. As a user, I want to add cars to my shopping cart so that I can prepare for a purchase.
6. As a user, I want to review the contents of my shopping cart, including quantities and total cost, so that I can confirm my purchase before proceeding.
7. As a user, I want to be prevented from adding out-of-stock cars to my cart so that I don’t attempt to buy unavailable items.
  
#### Purchase and Receipts

8. As a user, I want to complete a purchase by checking out my cart, generating a receipt that records the transaction (e.g., purchase date, total price, items, and their quantities).
9. As a user, I want to view my past receipts so that I can track my purchase history, including total amounts, items purchased, and dates.
  
#### Owned Cars
10. As a user, I want to see a list of cars I currently own after purchasing them so that I can track my acquisitions.
11. As a user, I want the dealership to automatically track my owned cars after purchases so that ownership records are accurate and linked to my profile.
  
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


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

#### User Authentication

1. As a user, I want to create an account by providing my username, email, and password so that I can access the system securely.
2. As a user, I want to log in using my credentials so that I can access my profile and perform actions like purchasing cars and managing carts.
3. As a user, I want to be notified if my email or username is already in use during registration so that I can choose valid credentials.
4. As a user, I want to stay logged in even after refreshing the page by saving my session data locally.  
  
#### Car Listing and Search

5. As a user, I want to view a list of available cars so that I can explore the inventory and make a purchase.
6. As a user, I want to see detailed information about each car, including the make, model, color, stock availability, and year, so that I can make an informed decision.
7. As a user, I want to search for specific cars by typing keywords like make or model so that I can quickly find the car I am interested in.

#### Shopping Cart Management

8. As a user, I want to add cars to my shopping cart so that I can prepare for a purchase.
9. As a user, I want to be notified if an item I add to the cart already exists, and I want its quantity to be updated instead of duplicated.
10. As a user, I want to see the current items in my shopping cart, including the total quantity and cost, so that I can review my purchase.
11. As a user, I want to be prevented from adding cars to the cart if they are out of stock so that I don't attempt to purchase unavailable items.
  
#### Purchase and Receipts

12. As a user, I want to purchase the items in my cart by creating a receipt that records the transaction details, including the total price, items purchased, and date.
13. As a user, I want the stock of cars in the dealership to decrease after I make a purchase so that inventory reflects my transaction.
14. As a user, I want to view a list of my previous receipts so that I can track my purchase history.
15. As a user, I want to see the total number of items purchased on each receipt so that I can understand my past orders.
16. As a user, I want to click on a receipt to view more details about the purchased items so that I can verify the transaction.  
  

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


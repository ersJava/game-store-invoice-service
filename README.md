# Game Store Invoice Service

## How It Works

A simple service that allows CRUD operations for a video game store inventory system and creates an invoice for the order.

Please see YAML for API documenation.

## Project Details

This application is a simple database backed REST inventory management web service for a Video Game Store. The Game Store Invoice Service is a typical Spring Boot REST web service with controller, DAO (utilizing Jdbc template and prepared statements), unit test and service layer components. Exceptions are handled through Controller Advice and return proper HTTP status codes and data when exception occur and violations of business rules. 


### Business Rules

- Sales tax applies only to the cost of the items.
- Each item has it's own unique processing fee. Any orders over 10 will have an additional processing fee of $15.49 is applied to the order
- The order process logic properly updates the quantity so the order quantity must be less than or equal to the number of items on hand in the inventory.

### Technologies Used
* Java
* Spring Boot
* MySQL
* Mockito

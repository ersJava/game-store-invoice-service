# Game Store Invoice Service

## How It Works

A simple service that allows CRUD operations for a video game store inventory system and creates an invoice for the order.

Please see YAML document for API instructions.

## Project Details

This application is a simple database backed REST inventory management web service for a Video Game Store. The Game Store Invoice Service is a typical Spring Boot REST web service with controller, DAO (utilizing Jdbc and prepared statements), unit test and service layer components. Exceptions are handled through Controller Advice and return proper HTTP status codes and data when exception occur and violations of business rules. 


### Business Rules

- Sales tax applies only to the cost of the items.
- Sales tax does not apply to any processing fees for an invoice.
- The processing fee is applied only once per order regardless of the number of items in the order unless the number of items on the order is greater than 10 in which case an additional processing fee of $15.49 is applied to the order.
The order process logic must properly update the quantity on hand for the item in the order.
Order quantity must be greater than zero.
Order quantity must be less than or equal to the number of items on hand in inventory.

### Technologies Used
* Java
* Spring Boot
* MySQL
* Mockito

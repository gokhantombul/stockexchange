# Stock Exchange Management API v1
This is a Java Spring Boot-based backend application that manages stock exchanges and stocks. It provides various endpoints for creating, updating, and managing stock exchanges and their stocks, along with ensuring business rules like "A stock exchange with less than 5 stocks cannot be live in the market."

## Disclaimers
* List a stock exchange along with its stocks.
* Create a new stock.
* Add a stock to a stock exchange.
* Remove a stock from a stock exchange.
* Update the price of a stock.
* Delete a stock from the system.
* Multi-user capability with basic authentication (optional).

## Technologies Used
* Java 21
* Spring Boot 3.x
* Cucumber for BDD (Behavior Driven Development) testing
* H2 in-memory database for easy testing and development
* Maven for dependency management and build

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/ing/stockexchange/v1/
│   └── resources/
├── test/
│   ├── java/
│   │   └── com/ing/stockexchange/
│   │       └── service                # Test definitions
│   └── resources/
│       └── features/                  # Gherkin feature files
└── pom.xml
```
## Endpoints
| Method  | Endpoint                       | 	Description                                |
|---------|--------------------------------|---------------------------------------------|
| GET	    | /api/v1/stock-exchange/{name}	 | List one stock exchange with all its stocks |
| POST    | 	/api/v1/stock                 | 	Create a new stock                         |
| POST    | 	/api/v1/stock-exchange/{name} | 	Add a stock to a stock exchange            |
| DELETE  | 	/api/v1/stock-exchange/{name} | 	Remove a stock from a stock exchange       |
| PATCH	  | /api/v1/stock	                 | Update the price of a stock                 |
| DELETE	 | /api/v1/stock	                 | Delete a stock from the system              |

## Business Rules
* Each stock exchange can have multiple stocks.
* A stock exchange with fewer than 5 stocks cannot be live in the market (liveInMarket is set to false).
* A particular stock can be listed on multiple stock exchanges with the same properties.
* How to Run the Project
* Clone the repository:
```github
git clone https://github.com/your-username/stock-exchange-api.git
cd stock-exchange-api
```
1. Build the project with Maven:
```bash
mvn clean install
```
2. Run the application:
```bash
mvn spring-boot:run
```
3. Access the application:

The application will be running at http://localhost:1581/stock-exchange

## Running Tests
The project uses Cucumber for BDD testing. To run the tests:
```bash
mvn test
```
Cucumber reports will be generated in target/cucumber-reports.

## H2 Database
The application uses an H2 in-memory database for simplicity. You can access the H2 console at http://localhost:1581/stock-exchange/h2-console with the following credentials:
* Driver Class: org.h2.Driver
* JDBC URL: jdbc:h2:mem:stockexchangedb
* Username: sa
* Password: password

## Authentication
Spring security role-based JWT token structure was used. The default user credentials are as follows:
Bearer Token from the ```http://localhost:1581/stock-exchange/api/v1/auth/signin``` token and get Bearer token.

```json
{
    "email": "gokhantombul@hotmail.com",
    "password": "1234"
}
```

## Postman Json Test
File -> Import -> Copy And Paste Link
```
https://api.postman.com/collections/10152965-edd12e77-1a8d-40e7-bc87-62360c9a27d8?access_key=PMAT-01J5G31BEK45R7A51KNH9KRTG7
```
## License
This project is licensed under the MIT License.

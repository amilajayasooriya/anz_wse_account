# anz_wse_account
Sample project for interview purpose. This project include two api endpoints. Dev profile database configured as in memory H2 database and Prod profile database is configured as PostgreSQL.

This application developed using **Spring boot version 3.0.4 and using java version 17** (minimum version for spring boot 3.0).

Please change application-prod.yml database configurations according to the production database. Password accept as plain password for simplicity.
Console log has disabled for prod profile. 

### This app main features as following

* retrieve accounts list for a customer
* retrieve transactions for given account number

### Full api documentation provided in below links

* **OpenAPI definition can be found here**

http://localhost:10910/wse-accounts/swagger-ui/index.html


* **OpenAPI definition in Json format can be found here**

http://localhost:10910/wse-accounts/v3/api-docs


# How to Start the app in the dev profile
execute `./gradlew runDev`

## How to Start the app in the prod profile
execute `./gradlew runProd`

## How execute tests
execute `./gradlew test`


<br />
Postman api collection is attached in `/postman` directory

Log files are configured to save in `/logs` directory
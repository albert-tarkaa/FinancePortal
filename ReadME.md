# Microservices-Based Finance Portal

This is a simple microservices-based application which exposes a RESTful API. It is written in Java using the Spring Boot framework. It is intended for use as part of the SESC module at Leeds Beckett University..

![Finance Portal](finance.png?raw=true "Finance Portal")

Postman documentation available here `https://documenter.getpostman.com/view/32686033/2sA3BobXMA`.

## Requirements

To fulfill the objectives of this module, the following features need to be implemented:

1. **Create Account**: Create a finance account by passing a student ID.
2. **Query Account**: Find a finance account by passing a student ID. The response shows whether the account has an
   outstanding balance.
3. **View Invoice**: View all invoices or a single invoice, by invoice ID.
4. **Create Invoice**: Create a new outstanding invoice by passing a student ID.
5. **Pay Invoice**: Pay an outstanding invoice.
6. **Cancel Invoice**: Cancel an outstanding invoice.

## Integrations
### 1. Database
The application integrates with a MySQL relational database.</br>

### 2. Student
The application integrates with the [Student microservice](https://github.com/albert-tarkaa/SESCStudentPortal) via REST.
1. When a student is created, a request is sent to this application to create an account.
2. When a student enrols in a course, a request is sent to this application to create an invoice.
3. When checking the eligibility to graduate, a request is sent to this application to see if there are any outstanding invoices.

### 3. Library
The application integrates with the [Library microservice](https://github.com/AidanCurley/CESBooks) via REST.
1. When a book is returned late, a fine is issued. A request is sent to this application to create an invoice.
2. The invoice must be paid via this application's Payment Portal.

## Test using Postman
Download Postman from https://www.postman.com/ and import the collections found in the root directory.

## License

This project is licensed under the MIT License.



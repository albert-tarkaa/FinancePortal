# Microservices-Based Library Portal

This project is a microservices-based Library portal, which allows Librarys to enrol in courses, view their enrolments, and manage their profiles. The portal is integrated with a finance microservice to manage Library accounts and invoices, and a library microservice to manage library accounts and fines.

![Library Portal](finance.png?raw=true "Library Portal")

Postman documentation available here `https://documenter.getpostman.com/view/32686033/2sA3BobXMA`.

## Requirements
To fulfill the objectives of this module, the following features need to be implemented:

1. **Create Account**: Create a finance account by passing a student ID.
2. **Query Account**: Find a finance account by passing a student ID. The response shows whether the account has an outstanding balance.
3. **View Invoice**: View all invoices or a single invoice, by invoice ID.
4. **Create Invoice**: Create a new outstanding invoice by passing a student ID.
5. **Pay Invoice**: Pay an outstanding invoice.
6. **Cancel Invoice**: Cancel an outstanding invoice.

## License
This project is licensed under the MIT License.



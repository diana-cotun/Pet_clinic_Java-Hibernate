# Pet Clinic Sample Application (backend version using Java and Hibernate)

This application is designed to assist the management of a veterinary clinic, making it easier for staff to keep track of appointments, veterinary doctors, pets and their owners. Below you'll find information on how to set up and use the app effectively.

# Features:

Appointment Management:
* Schedule appointments for pets.

Vet Records:
* Register a vet:
  - Vets should have a unique badge id (4 digits generated randomly);
* View all registered vets;
* Edit their details;
* Remove a vet based on their badge id;
* Add holidays. 

Owners Records:
* Register as a pet owner:
  - Each owner should have a unique phone number;
* View all owners;
* Edit owner’s details;
* Remove owners based on phone number.

Pet Records:
* Register a pet:
  - Pets should only be allowed if they are linked to an existing owner;
  - Pets should have a unique collar id (4 letters generated randomly);
* View all pets and the names of their owners, respectively;
* Edit pet details;
* Remove pet based on collar id.

# Installation:
* Requirements:
  - Java Development Kit (JDK) installed on your system;
  - Apache Maven for dependency management;
  - A relational database (e.g., MySQL, PostgreSQL) supported by Hibernate.

* Clone Repository:
```
https://github.com/diana-cotun/Pet_Clinic.git
```

* Database Setup:
Create a database for the application.
Configure the database connection settings in src/main/resources/application.properties.
Ensure Hibernate configurations are set up to create/update the database schema accordingly.






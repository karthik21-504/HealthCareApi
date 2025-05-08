# HealthCareApi
A simple RESTful API endpoints for managing patients,appointments and Authentication
Built with Spring Boot,Spring Security,and in-memory Database(H2)

1.[_System Requirements and Tools_](https://github.com/karthik21-504/HealthCareApi/README.md#system-requirements-and-tools)<br/>
2.[_Setup and Installation_](https://github.com/karthik21-504/HealthCareApi/README.md#setup-and-installation-)<br/>
3.[_API EndPoints_](https://github.com/karthik21-504/HealthCareApi/README.md#api-endpoints) <br/>
4.[_Testing_](https://github.com/karthik21-504/HealthCareApi/README.md#testing)<br/>

## **System Requirements and Tools**<br/>
_Hardware Requirements_ <br/>
Intel Core i5 (or equivalent) or higher<br/>
RAM: 8GB (minimum), 16GB recommended<br/>
Storage: 10GB free space (for IDE, dependencies, and database)<br/>
<br/>
_Development Tools_ <br/>
Java Development Kit (JDK): 17 (OpenJDK or Oracle JDK)<br/>
Build Tool: Apache Maven 3.8+<br/>
IDE: Spring Tools Suite 3.9.18.RELEASE<br/>
Database (Development): H2 Database (Embedded)<br/>
<br/>
_Dependencies_ <br/>
Spring Boot 3.x<br/>
Spring Security (JWT Authentication)<br/>
Spring Data JPA (Database Interaction)<br/>

_Libraries_ <br/>
JJWT (JSON Web Token support)<br/>
Hibernate Validator (Request validation)<br/>

_Testing_ <br/>
JUnit 4<br/>
Mockito (Unit Testing)<br/>
<br/>

## **Setup and Installation** <br/>
Clone the repository and edit it in Spring Tool Suite for easy deploying<br/>
Default database used is H2 and properties file can be manually changed to use MySQL/PostgreSQL<br/>
DB Schema with table and their column information<br/>
<pre>
users
id, username, password, role

patients
patient_id, patient_name, email, mobile_number, date_of_birth

appointments
booking_id, appointment_date, disease, patient_id, priority
</pre>

Edit application.properties for MySQL/PostgreSQL:<br/>
<br/>
spring.datasource.url=jdbc:mysql://localhost:3306/healthcare_db<br/>
spring.datasource.username=root<br/>
spring.datasource.password=yourpassword<br/>
<br/>
maven commands to compile sources and run spring-boot-application<br/>
_mvn clean install<br/>
mvn spring-boot:run<br/>_
<br/>


## **API EndPoints**<br/>

<pre>
Authentication<br/>
===========================================================
Endpoint              Method	     Description    
============================================================
/api/auth/signup	POST	   Register a new user<br/>
/api/auth/signin	POST	   Login & get JWT token<br/>
</pre>
<pre>
Patient Management<br/>
============================================================
Endpoint	        Method	         Description
=============================================================
/api/patients           GET	       Get all patients<br/>
/api/patients/{id}      GET	       Get patient by ID<br/>
/api/patients           POST	       Add a new patient<br/>
/api/patients/{id}      PUT	       Update patient details<br/>
/api/patients/{id}      DELETE	       Delete a patient<br/>
</pre>
<pre>
Appointment Management<br/>
=====================================================================
Endpoint	            Method	        Description
======================================================================
/api/appointments           GET           Get all appointments<br/>
/api/appointments/{id}      GET           Get appointment by ID<br/>
/api/appointments           POST          Book a new appointment<br/>
/api/appointments/{id}      PUT           Update appointment<br/>
/api/appointments/{id}      DELETE        Cancel appointment<br/>
</pre>
<br/>

## **Testing**<br/>

Test endpoints with Postman:<br/>
<br/>
Auth Token: Include in Headers → Authorization: Bearer <token>.<br/>
Sample Requests: Check examples/ folder.<br/>

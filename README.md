# Final Project - Conferenc

## Technical description of the project:

- Java 8
- Apache Tomcat 9 is used as a servlet container
- Database - MySQL 8
- JDBC is used to connect to the database
- Java EE (Java Servlets, JSP)
- JSTL is used to extend JSP
- Log4j logging library is used for logging
- JUnit and Mockito were used as a testing framework

## About the project:
The web application is designed to familiarize you with the list of training in an IT school.
The application implements the differentiation of access rights by roles. In total , there are two roles available in it:
- Administrator;
- User;

For unregistered users, access to the following functionality is provided:
- the opportunity to get acquainted with the list of conferences and their sections;
- registration;
- view information about the company "IT ReCode".

The administrator is provided with access to the following functionality:
- view, edit and add new categories;
- view, edit and add conferences and their sections;
- viewing, and changing the user role;
- view and add answers to users' questions;
- view and edit user training applications;
- other features that are available to unregistered users.

The user is provided with access to the following functionality:
- ask as well as delete your questions;
- register for training;
- view and delete your applications for training;
- view and edit your profile in your personal account;
- as well as to all the features that are available to unregistered users.
 
 The life cycle of the meeting
1. Creating an application for training.<br/>
The user can select a conference section. After creation, the assignment has the status "Activate"
2. Confirmation of the application.<br/>
The administrator can see all created applications.The administrator can see all the information about the section and the client. The administrator calls the client, and then can confirm the request (change the status to "Waiting"), or cancel the request (change the status to "Deleted"). The administrator can also view all other appointments for the selected section for double checking.
3. After the procedure.<br/>
The administrator must complete the application (change the status of the meeting to "Completed").

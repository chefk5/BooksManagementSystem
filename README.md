# Books Management System [![Build Status](https://travis-ci.com/chefk5/BooksManagementSystem.svg?token=fYVqGUaqqtxz2tS9DWqx&branch=master)](https://travis-ci.com/chefk5/BooksManagementSystem)

This is a Spring Boot application containing a REST API for the book management system.  
* You can find the JAR file inside the **src** folder.
* The API is in Heroku under this link: https://pure-ridge-19017.herokuapp.com/
* All the API calls work using the Heroku too.
* All the API calls are documented in swagger under those links:
  * http://localhost:8080/swagger-ui.html (after running the applicaion locally)
  * https://pure-ridge-19017.herokuapp.com/swagger-ui.html
* CI by Travis.
* Tests were added too.
* There is a index.html page. It only lists the available books.
  * Due to time constraints, was not able to make the front end. However, I focused on the backend.
  
**What does the API do?**  
* Add books
* List all books
* Get book by id
* Update Books
* Add comments
* Delete books  
**More details in swagger documentation**
* There is also an ISBN code validator
* The API has customized HTTP code returns (200, 201, 404, etc)




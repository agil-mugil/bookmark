# bookmark 
Repository to maintain service layer of a bookmark application
#Author
Prabhu MURUGESAN

[Bookmark Swagger URL](<https://managebookmark.herokuapp.com/swagger-ui.html>)
[Bookmark-ui application URL](<https://manage-bookmark-ui.herokuapp.com/>)

![Build Status](https://travis-ci.com/agil-mugil/bookmark.svg?token=ffZVVypfS6pQcVNnCYe7&branch=master)
![Docker Build](https://img.shields.io/docker/cloud/build/pmurugesan15/bookmark)
[![codecov](https://codecov.io/gh/agil-mugil/bookmark/branch/master/graph/badge.svg?token=LP0NFXRR7O)](https://codecov.io/gh/agil-mugil/bookmark)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/3ef55d4f85f2496b97c4b3bff1b141f8)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=agil-mugil/bookmark&amp;utm_campaign=Badge_Grade)

Setup:

 1. Clone the project bookmark
 2. Build using JDK 11 and Maven
  -- mvn clean
  -- mvn clean install
	
 3. Run the application
 4. Access the application Swagger using (<http://localhost:8080/Swagger-ui.html>)

Heroku Swagger URL:
 1. API - Swagger URL - (<https://managebookmark.herokuapp.com/swagger-ui.html>)
 2. UI URL - (<https://manage-bookmark-ui.herokuapp.com/>)

bookmark-ui setup:
 1. Clone the project bookmark-ui
 2. go to root directory
 3. execute npm install to get all the dependency
 4. execute npm start (in the server.js specify the server url (API URL: (<http://localhost:8080/>) or the (<https://managebookmark.herokuapp.com)>)
 5. Once the app is up and running access using the port specfied in the server.js file
 6. Login with prompted option and access the functionality of the application

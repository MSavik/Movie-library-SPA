# Movie Library SPA

## About

A single page application for storing information about your favorite movies.  
This project was developed for the Internet Programming course.

## Description

### How it works

The application consists of three pages:
* Main Page
* Add Movie Page
* Modify Movie Page

The Main Page is used for displaying the information about existing movies.  
It contains multiple filters for filtering movies based on their info.

The Add Movie Page allows the user to add a new movie to the library by filling the form with info and clicking the "Add Movie" button.

The Modify Movie page is used for modifying info about a movie that is already stored in the library, or removing existing movies.  
First, the movie is selected from a dropdown list, and then the form is filled with new info.  
Clicking on the "Modify Movie" button will permanently modify the movie info.  
The user can also remove the movie from the library by selecting the movie and clicking on the "Remove Movie" button.  
**CAUTION**: Removal of the movie is permanent.

The navigation section is located on the top of each of these three pages, and is used for switching between them.

A few movies have been added to the library for demonstration purposes.

### Technical details

The application is based on a simple MVC architecture.  
The data is stored in a JSON file.  
The View is created using the Handlebars template engine for generating dynamic content on the front end. Minimal styling is applied using CSS and Bootstrap. The data is sent from the View to the Controler throuh AJAX.  
The Controler is built as and API using the Spark Java framework. It reads data from the JSON file and writes data into it, and also generates the requested content on the front end using Handlebars.  

### How to run

* Run the "Launcher.java" class on an IDE which supports back end Java code.
* Enter the following address into the URL: [http://localhost:5000](http://localhost:5000)

## Tech Stack

* HTML
* CSS
* JavaScript
* Java
* Spark
* jQuery
* Handlebars
* AJAX
* JSON
* Bootstrap
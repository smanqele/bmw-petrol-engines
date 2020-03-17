The data used in this project is borrowed from the Wiki page https://en.wikipedia.org/wiki/List_of_BMW_engines

The CRUD implementations have been done for just reading from the H2 database.

Once the project has been cloned simply run under the root folder this command:
mvn install dockerfile:build

After the build run the project in one two modes:
1) Run with visible console: docker run -p 4040:4040 -t bmw_repo/bmw-petrol-engines
OR
2) Run as a background process: docker run -d -p 4040:4040 -t bmw_repo/bmw-petrol-engines

For the purposes of this excercise it is advisable to run like 1) for the benefits of the logs

The during Startup the data from {project}/src/main/resources/data/bmw_petrol_engines.csv is uploaded to the in-memory H2 DB

After startup you have access to two URLs:
1) The Swagger-UI for REST operations: http://localhost:4040/bmw/swagger-ui.html
2) The H2 DB web console: http://localhost:4040/bmw/h2-console/

You can use the H2 DB to check the uploaded data before using the Swagger URL 

When logging to the H2 DB please ensure this URL string shows in the GUI:
'jdbc:h2:mem:testdb'

In the GUI the User Name is 'sa'.
The password is 'password'

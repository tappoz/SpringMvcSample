SpringMvcSample
===============

The aim of this project is to show some common functionalities for RESTful web applications.
The web app serves an HTTP GET request and an HTTP POST request involving JSON serialization and deserialization and checks on the HTTP headers.

Frameworks
----------

 - **Spring Boot** is used to provide an annotation driven webapp (**no XML configuration files** here);
 - **Google Guava** is used to mock a dummy DAO using a bidirectional map;
 - **Jackson** is involved in the serialization/deserialization of the Java beans into JSON objects;
 - **rest-driver** is used in the acceptance tests to check the content and structure of the returned HTTP packet.


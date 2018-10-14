# Key Value Store
Java based implementation of a disk backed memory based Key Value Store.
This is a simple implementation using Redis as the disk backed memory.
 
## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 
  * Clone the project into your local machine via terminal using ```git clone https://github.com/KarthikNA/keyvaluestore.git```
  * Make sure redis is running on your system.
    * **NOTE : In this project redis is running on its default port and utilising the bin 1 for all operations** 
  * Run the following commands - 
    * ```mvn clean install -Pdev```
    * ```mvn spring-boot:run -Pdev```
  * You're good to go!

## Prerequisites  
You'll need the following to successfully run the software on your system -
  * Java 8
  * Maven 3.0 or above
  * Redis 4.0

## Using the Project
Once the project is up and running, you can use the [Postman Collection](https://www.getpostman.com/collections/56886fd6ca63ebe49369) to test the project.
If you do not have a REST client like Postman, the following cURL commands can be used for testing the application - 
  * Status Check of the Application -
    * `curl -X GET \
           http://127.0.0.1:1732/key-value-store/status`
  * Store Key-Value Pair -
    * `curl -X PUT \
         http://127.0.0.1:1732/key-value-store/ops/key \
         -H 'Content-Type: application/json' \
         -d '{
       	"key":"<key name>",
       	"value":"<value associated with the string>"
       }'`
  * Get Value From Key -
    *  `curl -X GET \
          http://127.0.0.1:1732/key-value-store/ops/key/<key name>`
  * Run The Test Suite -
    *  `curl -X POST \
          http://127.0.0.1:1732/key-value-store/run-tests`                      

## Assumptions
* This is a simple implementation of the Disk Backed Key Value Store in Java.
* This SpringBoot application is designed to run on port **1732** by default.
* The **default local storage limit is set to 3 key value pairs** for both in the testing suite and regular application functionality.
  For any further insertion of new entries, the local storage is backed in the disk storage and new data is stored in the local storage.
* The application supports reads from both local storage and disk storage.
* This application does not utilise any testing frameworks like TestNG for running the test suite. The REST API call will execute the test cases written in plain Java. 

## Other Basic Information
* Insertion of a new key value pair has a time complexity of **O(1)**.
* Fetching the value corresponding to a key has a time complexity of **O(1)**.    
      
## Author
* **[Karthik N A](https://github.com/KarthikNA)**
  
# spring-boot-example project

The project is collection of useful spring boot examples. All examples are divided from each other by own profiles.
Please use the following instructions to try one of the examples:
1. Spring dynamic scheduling. Run in CLI with following command 
    ```
    gradle bootRun -Dspring.profiles.active=scheduling 
    ```
    
2. MongoDb aggregation query example. Run in CLI with following command 
     ```
     gradle bootRun -Dspring.profiles.active=mongo-aggregation 
     ```
    To call one of aggregation query use REST interface (Replace `<IP>` and `Name` with real parameters):
    * getLastDocument - ` curl -X GET 'http://<IP>:8055/springbootexample/getLastDocument?name=<Name>' `
    * getDocumentsNumber - ` curl -X GET 'http://<IP>:8055/springbootexample/getDocumentsNumber?name=<Name>' `
 
3. Dynamic spring beans instantiation.
     ```
     gradle bootRun -Dspring.profiles.active=dynamic-bean-creation 
     ```
    For simple test to call dynamically configured client REST interface was added (Replace `<IP>` and `ClientName` with real parameters):
    * call - `http://<IP>:8055/springbootexample/call?name=<ClientName>`
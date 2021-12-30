### OptimalSolutionsChallenge

## General structure and aproach
This API was created with `Spring` and `Java 8`. 
The database I chose to work with is `MSSQL`, initially I went with `H2` but since it's functionality is very poor,
I had to switch to `MSSQL`. It was very important for me to be able to use `stored procedures`.
I also created additional tables and classes to make everything as clean as possible.   
* The `Config` table wasn't used at all, this is because it had to be filled with Json data from the file.   
* The `PurchaseHistory` table was an important table that held information about payments, I created it because I thought that adding a timestamp of when the item was bought was kind of important. This tables functionality was never used in the end because everything became more complex after I started added more new tables and methods to work with my database.   
* `MachineSlots` table speaks for itself, I wanted to show my knowledge of working with sql and databases, so I didn't just add a new `terminalSlot` column to the `Items` table, I thought this wouldn't be that great, even tho it would've worked.   
Here we are done with the `SQL` part, now let's switch to Java code:   
* There are some things that don't work, this is the get by slot http request and the JSON get request, we'll get back to JSON later.
* Main library to work with JDBC was `import com.jcabi.jdbc`, It's very straightforward in terms of use.

The API is structured in the following modules:    
* `Dao` layer - the layer that includes methods that fetch information from the database.   
* `Service` layer - I decided to divide the service layer into service class which contain method names and javadocs for them and service implementation class that overrides the methods from service and gives them functionality.   
* `Controller` layer -  this is the layer that contains the http routes.    

## Exceptions
Custom exceptions were created in a separated package `exceptions`, I only created one, `DaoException` which stands for database error but something like `ItemNotFoundException` could also be added when executing procedures that fetch 1 item by Id. 

## Logging
For `logging` I am going to use the [slf4j](https://www.slf4j.org/) library, I've had some practical experience at my previous work place with it and this is why I chose it.

## Running the application
In order to test the application, you will have to use `Postman`(or anything else you like), app runs on `localhost: 8080`

## List of dependencies 

```xml
     <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.microsoft.sqlserver/mssql-jdbc -->
        <dependency>
            <groupId>com.microsoft.sqlserver</groupId>
            <artifactId>mssql-jdbc</artifactId>
            <version>9.4.0.jre8</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
            <version>2.6.2</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.14</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-bom</artifactId>
            <version>2020.0.3</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <dependency>
            <groupId>com.jcabi</groupId>
            <artifactId>jcabi-jdbc</artifactId>
            <version>0.17.6</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.9</version>
        </dependency>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple -->
        <dependency>
            <groupId>com.googlecode.json-simple</groupId>
            <artifactId>json-simple</artifactId>
            <version>1.1.1</version>
        </dependency>
    </dependencies>
```

## Problems I encountered while coding
* At first, my main problem was the H2 databse and the fact that I had to write ugly queries because I couldn't use stored procedures, I ended up change the database which took some time and the refactoring also took some time.
* The second problem was JSON(I watned to use GSON), it was very hard for me to find information on how to deserialize everything correctly and I ended up using a method that I am not really satisfied with.

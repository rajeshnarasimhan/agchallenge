Auto General API Solution using Spring boot application
=======================================================
IDE Used: Eclipse IDE
DB: H2 in memory
Server: Apache Tomcat
Framework/Libraries Used:
Spring Boot - Framework to build rest services
JPA - ORM Framework
Log4j - Logger Framework

Steps to run the project:
------------------------
a. Clone the project

b. Use Maven to install required jars from pom.xml

c. The database script file available in resources folder

d. Deploy the project to run


Service APIs as below

1. Tasks : General algorithmic tasks
-------------------------
GET URL: http://localhost:8181/test/1.0/tasks/validateBrackets?input={(}

Response:
{
    "input": "{(}",
    "balanced": false
}


GET URL: http://localhost:8181/test/1.0/tasks/validateBrackets?input={(This is a long string to test validation of parameter}

Response:
{
    "status": 400,
    "details": [
        {
            "location": "params",
            "param": "input",
            "msg": "Must be between 1 and 50 chars long",
            "value": "{(This is a long string to test validation of parameter}"
        }
    ],
    "name": "ValidationError"
}


2. Add item:
---------------------------------------------
POST URL: http://localhost:8181/test/1.0/todo/

Post method body (JSON): (For successful scenario)

{
  "text": "Uulwi ifis halahs gag erh'ongg w'ssh."
}


Response:
{
    "id": 3,
    "text": "Uulwi ifis halahs gag erh'ongg w'ssh.",
    "createdAt": "2019-08-27T15:47:42.408+10:00",
    "completed": false
}

Post method body (JSON): (For empty string)
{
  "text": ""
}

Response:
{
    "status": 400,
    "name": "ValidationError",
    "details": [
        {
            "location": "params",
            "param": "text",
            "msg": "Must be between 1 and 50 chars long",
            "value": ""
        }
    ]
}


3. Get an item:
--------------------------
GET URL: http://localhost:8181/test/1.0/todo/3

{
    "id": 3,
    "text": "Uulwi ifis halahs gag erh'ongg w'ssh.",
    "createdAt": "2019-08-27T15:47:42.408+10:00",
    "completed": false
}

Try to get Item that is not present:

http://localhost:8181/test/1.0/todo/4

{
    "status": 404,
    "details": [
        {
            "message": "Item with id 4 not found"
        }
    ],
    "name": "NotFoundError"
}

4. Modify an item:
------------------

HTTP PATCH URL: http://localhost:8181/test/1.0/todo/3
Request Body:
{
  "text": "Uulwi ifis halahs gag erh.",
  "isCompleted": true
}

Response:
{
    "id": 3,
    "text": "Uulwi ifis halahs gag erh.",
    "createdAt": "2019-08-27T15:47:42.408+10:00",
    "completed": true
}

Item not present:

HTTP PATCH URL: http://localhost:8181/test/1.0/todo/4
Request Body:
{
  "text": "Uulwi ifis halahs gag erh.",
  "isCompleted": true
}

Response:
{
    "status": 404,
    "details": [
        {
            "message": "Item with id 4 not found"
        }
    ],
    "name": "NotFoundError"
}

Item with long text :

HTTP PATCH URL: http://localhost:8181/test/1.0/todo/3
Request Body:
{
  "text": "Uulwi ifis halahs gag erh. uh sloasf sad sadasdasd asdas dsadasd asd asdasd",
  "isCompleted": true
}

Response:
{
    "status": 400,
    "name": "ValidationError",
    "details": [
        {
            "location": "params",
            "param": "text",
            "msg": "Must be between 1 and 50 chars long",
            "value": "Uulwi ifis halahs gag erh. uh sloasf sad sadasdasd asdas dsadasd asd asdasd"
        }
    ]
}

5. Monitor status:
------------------
HTTP GET : http://localhost:8181/test/1.0/status

{
    "status": "healthy"
}

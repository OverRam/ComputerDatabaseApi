Rest API accepts Computer Objects in JSON format, at least 1 cost must contain a value. Then it connects to the Api of the NBP bank and downloads the USD exchange rate from that day, the next step is to convert the missing USD/PLN amount, complete it and add an entry to the database.

JSON format:
     {
         "date": "2022-01-03",
         "name": "computer 1",
         "costUsd": 345.0
"costPln": 345.0
     }
http://localhost:28852/h2 the url to the console
login: sa
password: 123
to be changed in the resources/application.properties file

http://localhost:28852/computer/ homepage

Endpoints
<br>
add adds a computer
addList adds a list of computers
listByName returns a list of computers sorted in date order
listByDate returns a list of computers sorted in date order

If the data is valid but the element is not found, it returns the HTTP code NOT_FOUND
If the data is incorrect, it returns the HTTP code BAD_REQUEST
If everything was successful, it returns the HTTP OK code

If the Api of the bank does not return a value, the object is still saved in the database
If there is an error in the list of computers, the entire list is not saved

Rest API accepts Computer Objects in JSON format, at least 1 cost must contain a value. Then it connects to the Api of the NBP bank and downloads the USD exchange rate from that day, the next step is to convert the missing USD/PLN amount, complete it and add an entry to the database.<br><br>

JSON format:<br>
     {<br>
         "date": "2022-01-03",<br>
         "name": "computer 1",<br>
         "costUsd": 345.0<br>
         "costPln": 345.0<br>
     }<br>
http://localhost:28852/h2 the url to the console<br>
login: sa<br>
password: 123<br>
to be changed in the resources/application.properties file
<br>
http://localhost:28852/computer/ homepage
<br>
Endpoints<br>
GET add adds a computer<br>
GET addList adds a list of computers<br>
POST listByName returns a list of computers sorted in date order<br>
POST listByDate returns a list of computers sorted in date order<br>

If the data is valid but the element is not found, it returns the HTTP code NOT_FOUND<br>
If the data is incorrect, it returns the HTTP code BAD_REQUEST<br>
If everything was successful, it returns the HTTP OK code<br><br>

If the Api of the bank does not return a value, the object is still saved in the database<br><br>
If there is an error in the list of computers, the entire list is not saved

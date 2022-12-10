Rest API accepts Computer Objects in JSON format, at least 1 cost must contain a value. Then it connects to the Api of the NBP bank and downloads the USD exchange rate from that day, the next step is to convert the missing USD/PLN amount, complete it and add an entry to the database.<br><br>

JSON format:<br>
     {<br>
         "date": "YYYY-MM-DD",<br>
         "name": "name",<br>
         "costUsd": 345.0<br>
         "costPln": 345.0<br>
     }<br><br>
     
http://localhost:28852/h2 -the url to the console<br>
login: sa<br>
password: 123<br>
to be changed in the resources/application.properties file<br><br>
http://localhost:28852/computer/ -the url homepage<br>
Endpoints<br><br>
POST: <br>
add -adds a computer<br>
addList -adds a list of computers<br><br>
GET: <br>
listByName?name= -returns a list of computers sorted in date order<br>
listByDate?date= -returns a list of computers sorted in date order, date format YYYY-MM-DD<br>

If the data is valid but the element is not found, it returns the HTTP code NOT_FOUND<br>
If the data is incorrect, it returns the HTTP code BAD_REQUEST<br>
If everything was successful, it returns the HTTP OK code<br><br>

If the Api of the bank does not return a value, the object is still saved in the database<br>
If there is an error in the list of computers, the entire list is not saved
<br><br>
#Frontend<br>
Home page: http://localhost:28852/index.html<br>

This client connects via rest API. Currently, you can: Search for a computer by name or purchase date, then you can sort the results. Add the computer to the database, then the server fills in the field of the purchase cost in PLN after conversion from the date of purchase and then returns the result to the client, this result is downloaded by the user in the XML format.

Format:

`<faktura> <komputer> <date>2022-12-20</date> <name>kompu</name> <costUsd>213</costUsd> <costPln>0</costPln> </komputer> <komputer> <date>2022-12-20</date> <name>kompu</name> <costUsd>213</costUsd> <costPln>0</costPln> </komputer> </faktura>`

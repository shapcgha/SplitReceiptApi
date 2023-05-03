# SplitReceiptApi
Api for sptlitting receipts after big parties

## What implimented know?
GET api/1/users : get JWT from login and password

POST api/1/users : register user and get his JWT

example of JSON :
{
"login" : "тест3"
"password" : "password"
}

<br>

GET api/1/receipts : get all receipts from JWT as request parameter

POST api/1/receipts : create new receipt from name

example of JSON :
{
"name" : "тест3"
}

<br>

GET api/1/receipts/{receiptId}/products : get all products of this receipt

POST api/1/receipts/{receiptId}/products : create new product to receipt from price and name

example of JSON :
{
"name" : "тест3",
"price" : 10,
"own" : true,
"count" : 2
}

<br>

GET api/1/receipts/{receiptId}/price : get price for user from JWT

all operations with receipt need JWT in request parameter

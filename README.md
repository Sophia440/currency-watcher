# CryptoСurrency watcher

Prerequisites:

- PostgreSQL (I used 15, other versions will probably work too)

- Create a `crypto` database manually, please. You can change the DB properties in `application.properties`

Endpoints:

- GET `/currencies`: Get all currencies from the database (`currency` table). 

The app is configured to get the updated prices from Crypto API | CoinLore and save them in the DB every minute.
- GET `/currencies/{symbol}`: Get the current price by currency code (symbol).
- POST `/currencies/notify?symbol=&username=`: Register a price for a specified username. Pass String username and String symbol as parameters.

This will create a record in the `user_record` table. After that the registered price will be compared with the current (from Crypto API) every minute. You'll get a warning if the registered price is different from the current by more than 1%.

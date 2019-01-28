Customer API services

This microservice offers following services.
1. Fetches customer by id
2. Fetches all customer via nextRequestedTimeStamp, incase nextRequestedTimeStamp is not provided it returns all the customers. The api returns the nextRequestedTimeStamp which can be used by calling api to get updated customers after given time stamp
3. Adds a customer related data to online mysql database.
4. Updates customer data by id
5. Deletes a customer by id, incase a customer is not found returs a 404

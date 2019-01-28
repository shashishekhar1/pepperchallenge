# pepperchallenge

This repository has all the deliverables related to customer api microservice and web client.

usecase 1: The get customers api returns customers data depending if customer provides the lastRequestedTimestamp value.
When the lastRequestedTimestamp value is not provided, the api returns all the customers in database, when the lastRequestedTimestamp value is provided the api returns all the customers created and updated after the lastRequestedTimestamp value.

usecase 2: The mobile application to update customers has been designed in angular js and basic html. It interacts with the customer api's get customer id service to fetch a customer by id and edit customer details via patch method customer api.

usecase 3: The orders and products can designed as separate microservices to keep them independent with customers, additional services have to be designed in case customer, product and order need to communicate among themselves.

Design Decision:

The Customer api has been implemented as a microservice pattern, to keep all customer related interactions in one api. The api is implemented in Restful manner and uses http methods  for exposing different services. The api interacts with an online mysql database for saving customer related data.

The get all customer api's can be enhanced to return customers in a paginated way, this will prevent timeout in case there are large number of customers in database.

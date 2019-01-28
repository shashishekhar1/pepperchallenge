Performs a search across all accounts, using the criteria and parameters provided.

Note there are two search modes:
- **standard**: This searches for contacts based on the criteria and parameters provided. Records returned will be an exact match for the criteria provided.
- **find duplicate**: This searches for duplicate contacts based on the criteria provided. This search invokes additional duplicate-check logic and requires a minimum set of criteria to be provided. Where no duplicates are found or minimum criteria are not satisfied, no records will be returned.

#### Object Type ####

The account search operation takes in an *AccountSearch* object that consists of two main components:
- the **paramas** component which contains parmeters to modify the search results, including pagination, ordering, and selecting between *standard* and *find duplicate* search functionality
- the **criteria** component which contains the desired search criteria

##### Params #####

Parameter Name | Required | Default | Description
--- | --- | --- | ---
**limit** | *OPTIONAL* | 100 | (Pagination) Behaviour is equivalent to the SQL 'LIMIT' keyword
**offset**: | *OPTIONAL* | 0 |  (Pagination) Behaviour is equivalent to the SQL 'OFFSET' keyword
**orderBy**: | *OPTIONAL* | n/a | This allows the search results to be ordered by one or more criteria fields, in ascending or descending order
**duplicateSearch** | OPTIONAL |  false) | This toggles the search between a regular search and a 'find duplicate' search which invokes additional duplicate search logic
**metadataOnly**: | OPTIONAL |  false) | Only returns search metadata (i.e. total number of results) without returning records

##### Criteria #####

All criteria fields are **optional**.

Search / find duplicate results will satisfy all criteria supplied.

Field Name | Type | Required | Example
--- | --- | --- | ---
version | number | yes | 2
accountName | string | no
tradingName | string | no
persona | string (enum) | no
sourceSystem | string | no
{phone,fax}.number | string | no
{phone,fax}.countryCode | string | no
{phone,fax}.extension | string | no
email | string | no
secondaryEmail | string | no
abn | string | no
acn | string | no
communicationPreference | string | no
{billingAddress,shippingAddress}.line1 | string | no
{billingAddress,shippingAddress}.line2 | string | no
{billingAddress,shippingAddress}.line3 | string | no
{billingAddress,shippingAddress}.city | string | no
{billingAddress,shippingAddress}.country | string | no
{billingAddress,shippingAddress}.state | string | no
{billingAddress,shippingAddress}.postalCode | string | no
contractedProvider | boolean | no | false
description | string | no | "Free text field"
registeredForGst | boolean | no | false
legalSpecialty | string (enum) | no |
medicalSpecialty | string (enum) | no |

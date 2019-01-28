Performs a search across all accounts, using the criteria and parameters provided.

The search expects a *ContactSearch* object, which is similar to a *Contact* object except most attributes are optional.

Note there are two search modes:
- **standard**: This searches for contacts based on the criteria and parameters provided. Records returned will be an exact match for the criteria provided.
- **find duplicate**: This searches for duplicate contacts based on the criteria provided. This search invokes additional duplicate-check logic and requires a minimum set of criteria to be provided. Where no duplicates are found or minimum criteria are not satisfied, no records will be returned.

Optional attributes can be omitted from the request. Providing a *null* attribute value will be handled the same as an attribute that is omitted.

#### Object Type ####

The account search operation takes in an *ContactSearch* object that consists of two main components:
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

All criteria fields are **optional**. All fields are free text fields, except as defined in below. The target system may apply additional validation rules not listed here that may trigger a bad request error.

Field Name | Type | Required | Example
--- | --- | --- | ---
persona | string (enum) | yes | Provider_Contact
salutation | string (enum) | no | "Mr"
firstName | string | no | "John"
lastName | string | no | "Smith"
formerName | string | no |
phonePreference | string (enum) | no |
email | string | no | "johns@example.org"
secondaryEmail | string | no |
description | string | no | "Free text field"
{homePhone,mobilePhone,otherPhone,fax}.number: | string | no |
{homePhone,mobilePhone,otherPhone,fax}.countryCode: | string | no |
{homePhone,mobilePhone,otherPhone,fax}.extension: | string | no |
{mailingAddress,otherAddress}.line1 | string | no | "A Large Building"
{mailingAddress,otherAddress}.line2 | string | no | "123 Sample St"
{mailingAddress,otherAddress}.line3 | string | no | null
{mailingAddress,otherAddress}.city | string | no | "Sydney"
{mailingAddress,otherAddress}.state | string | no | "NSW"
{mailingAddress,otherAddress}.postalCode | string | no | "2000"
{mailingAddress,otherAddress}.country | string | no | Australia
birthdate | string | no | "1999-03-20"
gender | string | no | "Male"
maritalStatus | string | no | "Married"
contractedProvider | string | no | false
registeredForGst | string | no | true
abn | string | no | "ABN34252"
agreeToSmsNotification | string | no | false
xdrTaxFileNumber | string | no | "XDR3456789"
medicalSpecialty | string | no | null
legalSpeciality | string | no | null

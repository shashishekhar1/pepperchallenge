Returns the *Contact* record for the specified ID. Returns a 404 if there is no record with the specified ID.

#### Object Type ####

This operation returns a *Contact* object type, as specified in the example.

##### Attributes #####

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

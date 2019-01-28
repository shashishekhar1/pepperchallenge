Creates a new account.

#### Object Type ####

This operation requires an *Account* object type. Check the example provided which describes the full *Account* object.

##### Attributes #####

The table below describes the *Account* object fields in detail. Note the target system may apply additional validation rules not listed here - failing a validation rule will result in a 400 Bad Request error.

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

Returns the *Account* record for the specified ID. Returns a 404 if there is no record with the specified ID.

#### Object Type ####

This operation returns an *Account* object type, as specified in the example.

##### Attributes #####

The following table describes the *Account* object that is returned upon successful retrieval.

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

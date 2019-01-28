Updates an existing account. This uses a similar structure to the creation of an *Account* except all fields are optional.

Note:
- All attributes within the *Account* object are optional for an update.
- You cannot update the *persona* of an existing *Account* object. Attempting to do so will result in a error.
- If you specify an attribute with a *null* value it will be ignored
- To clear an existing (string) value for an attribute, provide an empty string as the value
- You can omit the *id* field as it is already provided in the URI. However if both a provided, they must match or a bad request error will be returned.

#### Object Type ####

This operation requires an *Account* object type. Reference the account update example for the full *Account* object definition.

##### Attributes #####

All criteria fields are **optional**. All fields are free text fields, except as defined in below. The target system may apply additional validation rules not listed here that may trigger a bad request error.

Valid criteria field names are:

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

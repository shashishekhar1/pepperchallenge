Links or delinks a contact with a another given object based on ACR, PCR or CCR principles.

Note:
- If you specify an attribute with a *null* value it will be ignored
- You can omit the *id* field as it is already provided in the URI. However if both a provided, they must match or a bad request error will be returned.

#### Object Type ####

This operation requires a *Contact* object type in addition to one of the following depending on link type.
*Account*
*Policy*
*Claim*

##### Attributes #####


Field Name | Type | Required | Example
--- | --- | --- | ---

type| string | yes | "AccountContactRelationship"
linkType| string (enum) | yes | "Account"
relationshipId | string | yes | "1234"
linkedObjectId | string | yes | "5678"
contactId | string | yes | "4567"
active | boolean | yes | true
role | string | no | "Agent"
		
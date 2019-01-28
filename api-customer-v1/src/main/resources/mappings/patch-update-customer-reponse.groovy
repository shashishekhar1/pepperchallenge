import groovy.json.JsonOutput

message.setOutboundProperty('Content-Type', 'application/vnd.api+json')

def root = [
    type: "UpdateCustomer",
    attributes: [
        id: flowVars['id'],
        firstname: flowVars['firstName'],
        lastname: flowVars['lastName'],
        addressline1: flowVars['addressLine1'],
        addressline2:  flowVars['addressLine2'],
        suburb: flowVars['suburb'],
        state: flowVars['state1'],
        postcode: flowVars['postCode'],
        country: flowVars['country'],
        link:  "https://api.customer.com/" + flowVars['id']
    ]
]

return JsonOutput.toJson(root)
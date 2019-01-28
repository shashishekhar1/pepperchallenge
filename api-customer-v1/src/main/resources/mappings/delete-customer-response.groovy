import groovy.json.JsonOutput

message.setOutboundProperty('Content-Type', 'application/vnd.api+json')

def root = [
    type: "DeleteCustomer",
    attributes: [
        status: "deleted"
    ]
]

return JsonOutput.toJson(root)
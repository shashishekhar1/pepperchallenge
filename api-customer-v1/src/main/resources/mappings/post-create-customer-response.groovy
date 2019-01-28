import groovy.json.JsonOutput

message.setOutboundProperty('Content-Type', 'application/vnd.api+json')

def root = [
    type: "CreateCustomer",
    attributes: [
        status: "Created"
    ]
]

return JsonOutput.toJson(root)
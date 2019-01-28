import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import groovy.time.TimeCategory

def slurper = new JsonSlurper()
def result = slurper.parseText(payload)

message.setOutboundProperty('Content-Type', 'application/vnd.api+json')
message.setOutboundProperty('http.status', '200')

def root = [
    type: "GetCustomers",
    data: [
                id: result[0]?.id,
                firstname: result[0]?.firstname,
                lastname: result[0]?.lastname,
                addressline1: result[0]?.addressLine1,
                addressline2: result[0]?.addressLine2,
                suburb: result[0]?.suburb,
                state: result[0]?.state,
                postcode: result[0]?.postCode,
                country: result[0]?.country,
                link: "https://api.pepper.com/v1/customer/" + result[0]?.id
            ]
]

return JsonOutput.toJson(root)
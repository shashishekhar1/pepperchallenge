import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import groovy.time.TimeCategory

def slurper = new JsonSlurper()
def result = slurper.parseText(payload)

def now = new Date()
message.setOutboundProperty('Content-Type', 'application/vnd.api+json')
message.setOutboundProperty('http.status', '200')

use( TimeCategory ) {
    after5Mins = now + 5.minutes
}

def timestamp = after5Mins.format("yyyy-MM-dd HH:mm:ss", TimeZone.getTimeZone('UTC'))

def root = [
    type: "GetCustomers",
    nextRequestingTimeStamp: timestamp,
    data: 
        result.collect { it -> [
                id: it?.id,
                firstname: it?.firstname,
                lastname: it?.lastname,
                addressline1: it?.addressLine1,
                addressline2: it?.addressLine2,
                suburb: it?.suburb,
                state: it?.state,
                postcode: it?.postCode,
                country: it?.country,
                link: "https://api.pepper.com/v1/customer/" + it?.id
            ]
        }
]

return JsonOutput.toJson(root)
import groovy.json.JsonSlurper

def slurper = new JsonSlurper()
def result = slurper.parse(payload)
def data = result.data.attributes
def now = new Date()

flowVars['firstname']= data.firstName
flowVars['lastname']= data.lastName
flowVars['addressLine1']= data.addressLine1
flowVars['addressLine2']= data.addressLine2
flowVars['suburb']= data.suburb
flowVars['state1']= data.state
flowVars['postCode']= data.postCode
flowVars['country']= data.country
flowVars['timestamp1'] = now.format("yyyy-MM-dd-HH:mm:ss", TimeZone.getTimeZone('UTC'))

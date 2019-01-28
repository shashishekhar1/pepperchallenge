import groovy.json.JsonSlurper

def slurper = new JsonSlurper()
def result = slurper.parse(payload)
def data = result.data.attributes
def now = new Date()

if(data.firstName)
{
  flowVars['firstname']= data.firstName
}
else
{
  throw new java.lang.IllegalArgumentException("FirstName is mandatory for creating contact")
}

if(data.lastName)
{
  flowVars['lastName']= data.lastName
}
else
{
  throw new java.lang.IllegalArgumentException("LastName is mandatory for creating contact")
}

if(data.addressLine1)
{
  flowVars['addressLine1']= data.addressLine1
}
else
{
  throw new java.lang.IllegalArgumentException("AddressLine1 is mandatory for creating contact")
}

if(data.postCode)
{
  flowVars['postCode']= data.postCode
}
else
{
  throw new java.lang.IllegalArgumentException("PostCode is mandatory for creating contact")
}

if(data.country)
{
  flowVars['country']= data.country
}
else
{
  throw new java.lang.IllegalArgumentException("Country is mandatory for creating contact")
}

flowVars['addressLine2']= data.addressLine2
flowVars['suburb']= data.suburb
flowVars['state1']= data.state

flowVars['timestamp1'] = now.format("yyyy-MM-dd-HH:mm:ss", TimeZone.getTimeZone('UTC'))

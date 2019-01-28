package mappings

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

message.setOutboundProperty('Content-Type', 'application/json')

def _out = [
	  errors: [
		  [
		code: flowVars['errorCode'],
		detail: flowVars['errorMessage']
	   ]
	]
  ]

JsonOutput.toJson(_out)
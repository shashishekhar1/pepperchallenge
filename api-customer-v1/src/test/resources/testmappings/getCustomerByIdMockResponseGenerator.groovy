import groovy.json.JsonOutput

def sampleResult1 = [
					  [
                        id: 5,
                        firstname: "Shivam",
                        lastname: "Sharma",
                        addressline1: "kapashera border",
                        addressline2: "room no 10",
                        suburb: "kapashera",
                        state: "New Delhi",
                        postcode: "1104",
                        country: "India",
                        link: "https://api.pepper.com/v1/customer/5"
                      ]
                    ]
                    
JsonOutput.toJson(sampleResult1)
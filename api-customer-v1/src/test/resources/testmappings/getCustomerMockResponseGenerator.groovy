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
                      ],
                      [
                        id: 12,
                        firstname: "Shashi",
                        lastname: "Prashar",
                        addressline1: "321 Kent Street",
                        addressline2: "floor 10",
                        suburb: "Barangroo",
                        state: "NSW",
                        postcode: "2000",
                        country: "AUSTRALIA",
                        link: "https://api.pepper.com/v1/customer/12"
                      ]  
                    ]
                    
JsonOutput.toJson(sampleResult1)
Feature: validating exact location of city bikes around the world 
This feature demonstrates for every valid request returns the location and corresponding latitude,longitude
For every invalid request returns error response

Scenario Outline: Verify the exact location of city 
  Given I access the city bikes api service  
   When I send GET request for "<path>" 
   Then I validate cycle brand "<company>" city "<cityName>" belongs to Country "<countryName>" 
    And response should contain the <latitude> and <longitude>
    And I validate the response with status code <httpStatusCode>

  Examples: 
    | path                | company       | cityName  | countryName | latitude | longitude | httpStatusCode | 
    | /visa-frankfurt     | Nextbike GmbH | Frankfurt | DE          | 50.1072  | 8.66375   | 200            | 
    | /velocea            | Veolia        | Vannes    | FR          | 47.6559  | -2.7603   | 200            | 
    | /nextbike-london    | Nextbike GmbH | London    | GB          | 51.4862  | -0.11966  | 200            | 
    | /nextbike-hyderabad | Nextbike GmbH | Hyderabad | IN          | 17.38    | 78.4725   | 200            | 


Scenario Outline: Validate error status code when invalid city is provided 
  Given I access the city bikes api service  
   When I send GET request for "<path>" 
    And I validate the response with status code 404

  Examples: 
    | path          |
    | /invalid-city |
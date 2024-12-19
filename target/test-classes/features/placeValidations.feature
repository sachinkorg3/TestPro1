Feature: Validation of Place API
@AddPlace @Regression
Scenario Outline: verify Place is being added successfully
    Given user Add Playload with "<name>" "<language>" "<address>"
    When Use call "AddPlaceAPI" with "post" Http requst
    Then APi got call success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify Place_Id created maps to "<name>" using "GetPlaceAPI"
    
Examples:
|name|language|address|
|AAhouse|English|World cross ceter|
#|BBhouse|Frecnch|Bombay ceter|
@DeletePlace  @Regression
Scenario: verify Delete Place is functionality is working
    Given user DeletePlaceAPI payload
    When Use call "DeletePlaceAPI" with "post" Http requst
    Then "status" in response body is "OK"
   
    

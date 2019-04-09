Feature: Accessing CSV file to access Database and query it


  Scenario: Accessing CSV file to manipulate database
    Given The CSV file with data
    When I access a given database from the CSV file provided
    Then I should be presented with correct output as a serenity report

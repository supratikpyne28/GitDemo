
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidations
  Scenario Outline: Negative scenario of Login Test
    Given I landed on ECommerce page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username  							   | password 		 |
      | supratikpyne28@outlook.com | Selenium@8017 |

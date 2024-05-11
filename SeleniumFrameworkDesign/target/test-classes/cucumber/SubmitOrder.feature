
@tag
Feature: Purchase the Order from ECommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on ECommerce page


  @Regression
  Scenario Outline: Positive scenario of submitting the order
    Given Logged in with username <username> and password <password>
    When I add <prodName> in Cart
    And Checkout <prodName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed in ConfirmationPage

    Examples: 
      | username  							 | password 		 | prodName  	 |
      | supratikpyne28@gmail.com | Selenium@8017 | ZARA COAT 3 |

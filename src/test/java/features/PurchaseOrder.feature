@tag
Feature: Purchase the order from Ecommerce Website
  I want add items to card and check out

	Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
  
    Given Logged in with username "<username>" and password "<password>"
    When I add product "<productName>" to Cart
    And Checkout "<productName>" and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | username  		  			|  password		 | productName |
      | sara@yahoo.com        |  Test1234    | ZARA COAT 3 | 
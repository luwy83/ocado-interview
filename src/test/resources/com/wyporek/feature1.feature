Feature: Logged out customer is able to add items on promo to basket

  Scenario Outline: Add items on offer 'Buy any XX for XX' to the basket
    Given a customer on Browse Shop page
    When 'Buy any <noOfItems> for £<expectedPrice>' promo is available
    And any <noOfItems> are added to basket
    Then total amount in basket equals £<expectedPrice>

    Examples:
      | noOfItems | expectedPrice |
      |  2        |  3.50         |
      |  3        |  3            |
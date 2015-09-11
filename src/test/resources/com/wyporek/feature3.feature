Feature: Savings feature in top menu display correct value

  Scenario Outline: As logged out customer add Half price item to basket

    Given a customer on Browse Shop page
    When customer add item in '<promoName>' promo to basket
    Then saving field is updated
    And contains value £<expectedSavings>

    Examples:
      | promoName           | expectedSavings |
      |  Half Price, was £4 |  2.00           |
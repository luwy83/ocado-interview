package com.wyporek;

import com.wyporek.pages.BrowseShopPage;
import com.wyporek.pages.PromotionSubPage;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class Feature1Stepdefs extends BaseStepDefs {

    @Given("^a customer on Browse Shop page$")
    public void a_customer_on_Browse_Shop_page() throws Throwable {
        BrowseShopPage page = new BrowseShopPage(getDriver());
        page.open();
    }

    @When("^'Buy any (\\d+) for £([\\.0-9]+)' promo is available$")
    public void _Buy_any_for_£_promo_is_available(int noOfItems, String expectedPrice) throws Throwable {
        BrowseShopPage page = new BrowseShopPage(getDriver());
        page.clickFirstMatchingOffer(String.format("Buy any %d for £%s", noOfItems, expectedPrice));
    }

    @And("^any (\\d+) are added to basket$")
    public void any_noOfItems_are_added_to_basket(int noOfItems) throws Throwable {
        PromotionSubPage promotionSubPage = new PromotionSubPage(getDriver());
        promotionSubPage.addFirstNProducts(noOfItems);
    }

    @Then("^total amount in basket equals £([\\.0-9]+)")
    public void total_amount_in_basket_equals_£_expectedPrice(String expectedPrice) throws Throwable {
        PromotionSubPage promotionSubPage = new PromotionSubPage(getDriver());
        assertThat(promotionSubPage.getBasketSummaryTotal()).isEqualByComparingTo(new BigDecimal(expectedPrice));
    }

    @After
    public void after() {
        killDriver();
    }
}

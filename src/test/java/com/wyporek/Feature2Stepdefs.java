package com.wyporek;

import com.wyporek.pages.BrowseShopPage;
import com.wyporek.pages.LoginPopup;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class Feature2Stepdefs extends BaseStepDefs {

    @Given("^a logged in customer$")
    public void a_logged_in_customer() throws Throwable {
        BrowseShopPage page = new BrowseShopPage(getDriver());
        page.open();
        LoginPopup loginPage = page.clickLoginBtn();
        loginPage.login("nitrotrucl@thrma.com", "abc123");
    }

    @When("^Offers page is displayed$")
    public void Offers_page_is_displayed() throws Throwable {
        BrowseShopPage page = new BrowseShopPage(getDriver());
        page.goToOffers();
    }

    @Then("^add items met promo conditions to trolley$")
    public void add_items_met_promo_conditions_to_trolley() throws Throwable {
        BrowseShopPage page = new BrowseShopPage(getDriver());
        BigDecimal initialBasketSummaryTotal = page.getBasketSummaryTotal();
        String expectedPrice = page.addFirstPromoItemToBasket();
        assertThat(page.getBasketSummaryTotal()).isEqualByComparingTo(
                new BigDecimal(expectedPrice.substring(1)).add(initialBasketSummaryTotal));
    }

    @After
    public void after() {
        killDriver();
    }
}

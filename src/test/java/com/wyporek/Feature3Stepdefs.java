package com.wyporek;

import com.wyporek.pages.BrowseShopPage;
import com.wyporek.pages.PromotionSubPage;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class Feature3Stepdefs extends BaseStepDefs {

    @When("^customer add item in '(.+)' promo to basket$")
    public void customer_add_item_in_promoName_to_basket(String promoName) throws Throwable {
        BrowseShopPage page = new BrowseShopPage(getDriver());
        PromotionSubPage promotionSubPage = page.clickFirstMatchingOffer(promoName);
        promotionSubPage.addFirstNProducts(1);
    }

    @Then("^saving field is updated$")
    public void saving_field_is_updated() throws Throwable {
        BrowseShopPage page = new BrowseShopPage(getDriver());
        assertThat(page.getBasketSummarySavings()).isGreaterThan(BigDecimal.ZERO);
    }

    @And("^contains value £([\\.0-9]+)$")
    public void contains_value_£(String expectedSavings) throws Throwable {
        BrowseShopPage page = new BrowseShopPage(getDriver());
        assertThat(page.getBasketSummarySavings()).isEqualByComparingTo(new BigDecimal(expectedSavings));
    }

    @After
    public void after() {
        killDriver();
    }
}

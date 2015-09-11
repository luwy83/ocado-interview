package com.wyporek.pages;

import com.wyporek.exceptions.NoSuchDataException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BrowseShopPage {

    private WebDriver driver;

    private PageHeader header;

    @FindBy(css = "#offers a")
    private WebElement offers;

    @FindBy(css = "p.onOffer span")
    private List<WebElement> offerButtons;

    @FindBy(xpath = "//div[div[span[@class='wasPrice']]]")
    private List<WebElement> promoItems;

    public BrowseShopPage(WebDriver driver) {
        this.driver = driver;
        this.header = new PageHeader(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://www.ocado.com/webshop/getCategories.do?tags=");
    }

    public BigDecimal getBasketSummaryTotal() {
        return header.getBasketSummaryTotal();
    }

    public BigDecimal getBasketSummarySavings() {
        return header.getBasketSummarySavings();
    }

    public LoginPopup clickLoginBtn() {
        return header.clickLoginBtn();
    }

    public PromotionSubPage clickFirstMatchingOffer(String offerTitle) throws NoSuchDataException {
        try {
            getMatchingOffers(offerTitle).get(0).click();
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchDataException("There are no items in promo: " + offerTitle);
        }
        return new PromotionSubPage(driver);
    }

    public void goToOffers() {
        offers.click();
    }

    public String addFirstPromoItemToBasket() {
        WebElement promoItem = promoItems.get(0);
        promoItem.findElement(By.cssSelector(".productAdd")).click();
        return promoItem.findElement(By.cssSelector(".nowPrice")).getText();
    }

    // PRIVATE HELPERS

    private List<WebElement> getMatchingOffers(String offerTitle) {
        return offerButtons.stream().filter(e -> offerTitle.equals(e.getText())).collect(Collectors.toList());
    }
}

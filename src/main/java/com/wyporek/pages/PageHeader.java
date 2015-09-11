package com.wyporek.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;

public class PageHeader {

    private WebDriver driver;

    private WebElement loginButton;

    private WebElement basketSummaryTotal;

    private WebElement basketSummarySavings;

    public PageHeader(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public BigDecimal getBasketSummaryTotal() {
        return new BigDecimal(basketSummaryTotal.getText().substring(1));
    }

    public BigDecimal getBasketSummarySavings() {
        return new BigDecimal(basketSummarySavings.getText().substring(1));
    }

    public LoginPopup clickLoginBtn() {
        loginButton.click();
        return new LoginPopup(driver);
    }
}

package com.wyporek.pages;

import com.wyporek.exceptions.NoSuchDataException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;
import java.util.List;

public class PromotionSubPage {

    private PageHeader header;

    @FindBy(css = "input.productAdd")
    private List<WebElement> addButtons;

    public PromotionSubPage(WebDriver driver) {
        header = new PageHeader(driver);
        PageFactory.initElements(driver, this);
    }

    public void addFirstNProducts(int n) {
        try {
            addButtons.subList(0, n).forEach(WebElement::click);
        } catch (Exception e) {
            throw new NoSuchDataException("There are not enough products to choose from.");
        }
    }

    public BigDecimal getBasketSummaryTotal() {
        return header.getBasketSummaryTotal();
    }
}

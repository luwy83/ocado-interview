package com.wyporek.pages;

import com.wyporek.exceptions.NoSuchDataException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

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
        } catch (IndexOutOfBoundsException e) {
            // if there are not enough products to choose from, add the first one n times
            IntStream.range(0, n + 1).forEach(i -> { addButtons.get(0).click(); });
        }
    }

    public BigDecimal getBasketSummaryTotal() {
        return header.getBasketSummaryTotal();
    }
}

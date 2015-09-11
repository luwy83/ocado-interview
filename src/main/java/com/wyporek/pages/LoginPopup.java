package com.wyporek.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPopup {

    private WebDriver driver;

    private WebElement username;

    private WebElement password;

    @FindBy(css = "#js-popupLoginButton")
    private WebElement loginBtn;

    public LoginPopup(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String user, String pass) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(username));
        username.sendKeys(user);
        password.sendKeys(pass);
        loginBtn.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#myShopNav")));
    }
}

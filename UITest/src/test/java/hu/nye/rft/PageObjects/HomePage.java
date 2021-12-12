package hu.nye.rft.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import hu.nye.rft.Factory.WebDriverFactory;

@Component
public class HomePage extends hu.nye.rft.Pageobjects.CommonPageObject {
    private static final String HOME_PAGE_URL = "http://localhost:8092";

    @FindBy(id = "Register Button")
    private static WebElement registrationButton;

    @FindBy(id = "Login Button")
    private WebElement loginButton;

    public HomePage(final WebDriverFactory factory) {
        super(factory);
    }

    public void navigateToHomePage() {
        navigateToUrl(HOME_PAGE_URL);
    }

    public static void clickOnRegistrationButton() {
        waitForElementToBeClickable(registrationButton);
        registrationButton.click();
        waitForPageReadiness();
    }
    public void clickOnLoginButton() {
        waitForElementToBeClickable(loginButton);
        loginButton.click();
        waitForPageReadiness();
    }
}

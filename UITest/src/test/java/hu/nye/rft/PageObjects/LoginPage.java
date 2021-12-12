package hu.nye.rft.PageObjects;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import hu.nye.rft.Factory.WebDriverFactory;

@Component
public class LoginPage extends hu.nye.rft.Pageobjects.CommonPageObject {

    @FindBy(id = "emailInput")
    private WebElement emailInput;

    @FindBy(id = "loginInput")
    private WebElement loginInput;

    @FindBy(id = "passwordInput")
    private WebElement passwordInput;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    private final Map<String, WebElement> inputFieldsMap = Map.of(
            "Add meg az e-mail címed.", emailInput,
            "Adj meg egy jelszót.", passwordInput
    );

    public LoginPage(final WebDriverFactory factory) {
        super(factory);
    }

    public void clickOnLoginButton() {
        waitForElementToBeClickable(loginButton);
        loginButton.click();
        waitForPageReadiness();
    }

    public WebElement getInputFieldByName(final String name) {
        return inputFieldsMap.get(name);
    }
}

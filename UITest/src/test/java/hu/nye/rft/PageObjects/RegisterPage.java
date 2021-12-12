package hu.nye.rft.PageObjects;


import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import hu.nye.rft.Factory.WebDriverFactory;

@Component
public class RegisterPage extends hu.nye.rft.Pageobjects.CommonPageObject {

    @FindBy(id = "emailInput")
    private WebElement emailInput;

    @FindBy(id = "registerInput")
    private WebElement registerInput;

    @FindBy(id = "passwordInput")
    private WebElement passwordInput;

    @FindBy(id = "registerButton")
    private WebElement registerButton;

    private final Map<String, WebElement> inputFieldsMap = Map.of(
            "Add meg az e-mail címed.", emailInput,
            "Adj meg egy jelszót.", passwordInput
    );

    public RegisterPage(final WebDriverFactory factory) {
        super(factory);
    }

    public void clickOnRegisterButton() {
        waitForElementToBeClickable(registerButton);
        registerButton.click();
        waitForPageReadiness();
    }

    public WebElement getInputFieldByName(final String name) {
        return inputFieldsMap.get(name);
    }
}
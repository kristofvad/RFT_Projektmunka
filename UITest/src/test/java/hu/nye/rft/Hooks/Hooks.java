package hu.nye.rft.Hooks;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import hu.nye.rft.Factory.WebDriverFactory;
import io.cucumber.java.After;

public class Hooks {

    @Autowired
    private WebDriverFactory webDriverFactory;

    @After
    public void afterScenario() {
        final WebDriver webDriver = webDriverFactory.getWebDriver();

        webDriver.manage().deleteAllCookies();
    }
}

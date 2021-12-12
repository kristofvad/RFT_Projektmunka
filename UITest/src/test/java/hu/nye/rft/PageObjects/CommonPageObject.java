package hu.nye.rft.Pageobjects;

import static hu.nye.rft.Config.TestConfig.PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hu.nye.rft.Factory.WebDriverFactory;

public class CommonPageObject {

    private static WebDriverFactory factory;

    public CommonPageObject(final WebDriverFactory factory) {
        PageFactory.initElements(factory.getWebDriver(), this);
        this.factory = factory;
    }

    public static WebDriver getWebDriverFromFactory() {
        return factory.getWebDriver();
    }

    public static void waitForElementToBeClickable(final WebElement webElement) {
        try {
            new WebDriverWait(getWebDriverFromFactory(), PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS).until(
                    ExpectedConditions.elementToBeClickable(webElement)
            );
        } catch (NoSuchElementException exception) {
            throw new RuntimeException("Element is not clickable!");
        }
    }

    public void scrollToTheBottomOfThePage() {
        ((JavascriptExecutor) getWebDriverFromFactory()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void clickWithJsExecutor(final WebElement webElement) {
        ((JavascriptExecutor) getWebDriverFromFactory()).executeScript("arguments[0].click();", webElement);
    }

    public static void waitForPageReadiness() {
        new WebDriverWait(getWebDriverFromFactory(), PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS).until(
                driver ->
                        String.valueOf(
                                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")
                        )
        );
    }

    protected void navigateToUrl(final String url) {
        getWebDriverFromFactory().get(url);
        waitForPageReadiness();
    }
}

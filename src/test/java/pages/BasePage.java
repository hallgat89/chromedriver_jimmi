package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected boolean isElementPresent(By element) {
        try {
            this.driver.findElement(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void removeElement(WebElement delThis) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].remove();",delThis);
    }

}

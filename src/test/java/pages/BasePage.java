package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

}

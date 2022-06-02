package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.xml.xpath.XPath;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainPage {
    public static final String START_URL = "https://www.jimmi.hu/";
    public static final String PAGE_PARAM = "?page=%d";
    public static final String CONTENT_BLOCK = "//div[@id='text']";
    public static final By ARTICLES = By.xpath(CONTENT_BLOCK + "/div[@class='day']");
    public static final List<String> ignore = Arrays.asList();
    public static final By NEXT_BUTTON = By.xpath("//span[@id='pageforward']");
    public static final By PREV_BUTTON = By.xpath("//span[@id='pageback']");
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(START_URL);
    }

    public MainPage nextPage() {
        this.driver.findElement(NEXT_BUTTON).click();
        return new MainPage(this.driver);
    }

    public MainPage prevPage() {
        this.driver.findElement(PREV_BUTTON).click();
        return new MainPage(this.driver);
    }

    public void open(int pageNumber) {
        if (pageNumber > 0) {
            driver.get(String.format(START_URL + PAGE_PARAM, pageNumber));
        } else {
            open();
        }
    }

    public List<Article> getArticlesOnPage() {
        return driver.findElements(ARTICLES).stream().map(e -> new Article(e)).collect(Collectors.toList());
    }

    public void closeBrowser() {
        driver.close();
    }

}

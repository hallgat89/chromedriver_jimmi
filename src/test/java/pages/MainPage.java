package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainPage extends BasePage {
    public static final String START_URL = "https://www.jimmi.hu/";
    public static final String PAGE_PARAM = "?page=%d";
    public static final String CONTENT_BLOCK = "//div[@id='text']";
    private static final String ARTICLES_SEL = CONTENT_BLOCK + "/div[@class='day']";
    public static final By ARTICLES = By.xpath(ARTICLES_SEL);
    public static final List<String> ignore = Arrays.asList();
    public static final By NEXT_BUTTON = By.xpath("//span[@id='pageforward']");
    public static final By PREV_BUTTON = By.xpath("//span[@id='pageback']");

    // FOR PAGE CLEANING
    public static final By CHILDS_IN_ARTICLE = By.xpath(ARTICLES_SEL+"/*");
    private static final List<String> IGNORE_CLASSES = Arrays.asList("facebook", "comment","firstcomment");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(START_URL);
    }

    public MainPage nextPage() {
        this.driver.findElement(NEXT_BUTTON).click();
        return new MainPage(this.driver);
    }

    public boolean hasNextPage() {
        return isElementPresent(NEXT_BUTTON);
    }

    public boolean hasPrevPage() {
        return isElementPresent(PREV_BUTTON);
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

    public void cleanPage() {
        List<WebElement> elementsToRemove = driver.findElements(CHILDS_IN_ARTICLE).stream()
                .filter(e -> IGNORE_CLASSES.contains(e.getAttribute("class"))).collect(Collectors.toList());

        elementsToRemove.forEach(e->removeElement(e));

        System.out.println();
    }

    public List<Article> getArticlesOnPage() {
        cleanPage();
        return driver.findElements(ARTICLES).stream().map(e -> new Article(e)).collect(Collectors.toList());
    }

    public void closeBrowser() {
        driver.close();
    }

}

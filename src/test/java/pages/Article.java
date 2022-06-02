package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Article {
    private final WebElement thisArticle;

    private static final By ATITLE = By.xpath("./p[@class='title']");
    private static final By ADATE = By.xpath("./p[@class='smalldate']");
    private static final By ATEXT_ELEMENT = By.xpath("./p");
    private static final List<String> IGNORE_CLASSES = Arrays.asList("facebook", "comment");

    private final String title;
    private final String date;
    private final List<WebElement> textContent;

    public Article(WebElement fromElement) {
        thisArticle = fromElement;
        this.title = thisArticle.findElement(ATITLE).getText();
        this.date = thisArticle.findElement(ADATE).getText();
        List<WebElement> filteredElements = thisArticle.findElements(ATEXT_ELEMENT).stream()
                .filter(e -> !IGNORE_CLASSES.contains(e.getAttribute("class"))).collect(Collectors.toList());
        this.textContent = filteredElements;
    }
    public String getTitle() {
        return this.title;
    }
    public String getDate() {
        return this.date;
    }
    public List<WebElement> getParagraphs() {
        return Collections.unmodifiableList(textContent);
    }

}

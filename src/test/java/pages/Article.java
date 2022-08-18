package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utils.DateParser;

import java.util.Calendar;
import java.util.Date;

public class Article implements Comparable{
    private final String innerHTML;
    private static final By ATITLE = By.xpath(".//p[@class='title']");
    private static final By ADATE = By.xpath(".//p[@class='smalldate']");

    private static final By OLDDATE = By.xpath(".//p[@class='date']");

    private final String title;
    private final String dateString;
    private final Date date;

    public Article(WebElement fromElement) {
        this.dateString = tryFetchDate(fromElement);
        this.title = tryFetchTitle(fromElement,dateString);
        date = DateParser.fromString(dateString);
        this.innerHTML=fromElement.getAttribute("innerHTML");
    }

    public String tryFetchDate(WebElement thisArticle) {
        String date;
        try {
            date = thisArticle.findElement(ADATE).getText();
        } catch (NoSuchElementException e) {
            try {
                date = thisArticle.findElement(OLDDATE).getText();
            } catch (NoSuchElementException ee) {
                date = "no date";
            }
        }
        return date;
    }

    public String tryFetchTitle(WebElement thisArticle,String defaultTitle) {
        String title;
        try {
            title = thisArticle.findElement(ATITLE).getText();
        } catch (NoSuchElementException e) {
            title = defaultTitle;
        }
        return title;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDateString() {
        return this.dateString;
    }

    public Date getDate() {
        return this.date;
    }

    public Integer getYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    @Override
    public String toString() {
        return this.innerHTML;
    }

    @Override
    public int compareTo(Object o) {
        return this.date.compareTo(((Article)o).date);
    }
}

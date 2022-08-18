import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.Article;
import pages.MainPage;

import java.util.List;

public class Downloader {

    public static final int OLDEST_PAGE_NUMBER = 23;
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = getDriver();
    }

    @Test
    public void download() throws InterruptedException {
        MainPage page = new MainPage(driver);

        page.open(OLDEST_PAGE_NUMBER);
        for(int i=0;i<1;i++) { // TODO do something reasonable
            handleArticlesOnPage(page);
            page = page.nextPage();
        }

        System.out.println();
    }

    private void handleArticlesOnPage(MainPage page) {
        List<Article> articles = page.getArticlesOnPage();
        articles.stream().forEach(e -> System.out.println(e.getTitle()+": "+e.getDate()));
    }

    @AfterEach
    public void destruct() {
        driver.close();
    }

    public WebDriver getDriver() {
        WebDriver driver = null;
        WebDriverManager.chromedriver().browserVersion("77.0.3865.40").setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-certificate-errors"); // for unsafe sites
        return new ChromeDriver(options);
    }


}

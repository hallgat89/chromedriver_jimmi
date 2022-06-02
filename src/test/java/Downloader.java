import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;

public class Downloader {

    private MainPage page;

    @BeforeEach
    public void setUp() {
        page = new MainPage(getDriver());
    }

    @Test
    public void download() throws InterruptedException {
        page.open();
        System.out.println("BREAK");
    }

    @AfterEach
    public void destruct() {
        page.closeBrowser();
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

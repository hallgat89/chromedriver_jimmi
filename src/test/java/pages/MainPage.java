package pages;

import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class MainPage {
    public static final String START_URL = "https://www.jimmi.hu/";
    public static final String CONTENT_BLOCK = "";
    public static final List<String> ignore = Arrays.asList();
    public static final String NEXT_BUTTON = "";
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(START_URL);
    }

    public void closeBrowser() {
        driver.close();
    }

}

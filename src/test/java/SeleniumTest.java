import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class SeleniumTest {

    WebDriver driver;
    String baseUrl = "https://www.google.com";

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\Win11 Pro\\Downloads\\edgedriver_win32\\msedgedriver.exe");

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new"); // ✅ Edge now requires `--headless=new`
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new EdgeDriver(options);
    }

    @Test
    public void testGoogleHomePageTitle() {
        driver.get(baseUrl);
        String title = driver.getTitle();
        System.out.println("Page title: " + title);
        Assert.assertTrue(title.contains("Google"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

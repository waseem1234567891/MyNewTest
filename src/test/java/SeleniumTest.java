import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class SeleniumTest {

    WebDriver driver;
    String baseUrl = "https://www.google.com";

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--disable-gpu");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    @Test
    public void testGoogleHomePage() {
        driver.get(baseUrl);
        System.out.println("Title is: " + driver.getTitle());
    }

    @Test
    public void testGoogleHomePageTitle() {
        driver.get(baseUrl);
        String title = driver.getTitle();
        System.out.println("Title: " + title);
        Assert.assertTrue(title.contains("Google"), "Title should contain 'Google'");
    }

    @Test
    public void testGoogleSearchBoxPresence() {
        driver.get(baseUrl);
        WebElement searchBox = driver.findElement(By.name("q"));
        Assert.assertTrue(searchBox.isDisplayed(), "Search box should be visible");
    }

    @Test
    public void testGoogleSearchFunctionality() {
        driver.get(baseUrl);
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium Grid");
        searchBox.submit();

        WebElement results = driver.findElement(By.id("search"));
        Assert.assertTrue(results.isDisplayed(), "Search results should be visible");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("selenium grid"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

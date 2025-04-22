import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;

public class SeleniumTest {



    WebDriver driver;
    String baseUrl = "https://www.google.com";

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browser.equalsIgnoreCase("firefox")) {
            capabilities.setBrowserName("firefox");
        } else if (browser.equalsIgnoreCase("edge")) {
            capabilities.setBrowserName("MicrosoftEdge");
        }

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
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

        // Wait for results
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

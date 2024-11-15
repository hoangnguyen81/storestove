package commons;

import factoryBrowsers.BROWSERS;
import factoryBrowsers.ChromeDriverManager;
import factoryBrowsers.EdgeDriverManager;
import factoryBrowsers.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.HomePageObject;
import utils.ConfigLoader;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected HomePageObject homePage;

    @Parameters({"browser"})
    @BeforeClass
    protected void beforeClass(@Optional("chrome") String browserName){
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getHomePageObject(driver);
    }

    @AfterClass
    protected void afterClass(){
        driver.quit();
    }

    private WebDriver getBrowserDriver(String browserName){
        BROWSERS browser = BROWSERS.valueOf(browserName.toUpperCase());
        switch (browser){
            case CHROME:
                driver = new ChromeDriverManager().getBrowserDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriverManager().getBrowserDriver();
                break;
            case EDGE:
                driver = new EdgeDriverManager().getBrowserDriver();
                break;
        }
        driver.get(ConfigLoader.getInstance().getPropertyByName("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    protected void verifyTrue(boolean condition){
        Assert.assertTrue(condition);
    }

    protected void verifyFalse(boolean condition){
        Assert.assertFalse(condition);
    }

    protected void verifyEqual(Object actual, Object expected){
        Assert.assertEquals(actual, expected);
    }


}

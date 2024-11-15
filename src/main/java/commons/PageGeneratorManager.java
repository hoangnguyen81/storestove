package commons;

import locators.HomePageLocator;
import org.openqa.selenium.WebDriver;
import pageobjects.HomePageObject;
import pageobjects.ResultPageObject;

public class PageGeneratorManager {

    public static HomePageObject getHomePageObject(WebDriver driver){
        return new HomePageObject(driver);
    }

    public static ResultPageObject getResultPageObject(WebDriver driver){
        return new ResultPageObject(driver);
    }
}

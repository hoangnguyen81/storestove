package pageobjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import locators.HomePageLocator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends BasePage {

    protected WebDriver driver;
    private static HomePageObject instance;

    public HomePageObject(WebDriver driver){
        this.driver = driver;
    }

    public static HomePageObject getInstance(WebDriver driver){
        if(instance == null){
            instance = new HomePageObject(driver);
        }
        return instance;
    }

    public boolean verifyPopupIsDisplayed() {
        return isElementDisplayed(driver, HomePageLocator.POPUP_LOCATOR);
    }

    public void closePopup() {
        if(isElementDisplayed(driver, HomePageLocator.POPUP_LOCATOR)){
            waitElementPresnce(driver, HomePageLocator.POPUP_CHECKBOX_DO_NOT_DISPLAY_LOCATOR);
            clickToElements(driver, HomePageLocator.POPUP_CHECKBOX_DO_NOT_DISPLAY_LOCATOR);
        }
    }

    public boolean isPopupIsUnDisplayed() {
        return isElementUnDisplayed(driver, HomePageLocator.POPUP_LOCATOR);
    }

    public void refreshHomePage() {
        refreshPage(driver);
    }

    public void inputToSearchBox(String value) {
        waitElementVisible(driver, HomePageLocator.SEARCH_BOX_LOCATOR);
        sendKeyToElement(driver, HomePageLocator.SEARCH_BOX_LOCATOR, value);
    }

    public boolean isGameLordNineDisplayed() {
        return isElementDisplayed(driver, HomePageLocator.ITEM_GAME_BY_NAME_DYNAMIC_LOCATOR, "Lord Nine");
    }

    public ResultPageObject pressEnter() {
        pressKey(driver, Keys.ENTER);
        sleepInSecond(3);
        return PageGeneratorManager.getResultPageObject(driver);
    }

    public void clickToHomeMenu() {
        waitElementClickable(driver, HomePageLocator.HOME_MENU_LOCATOR);
        clickToElements(driver, HomePageLocator.HOME_MENU_LOCATOR);
    }

    public boolean isSlideHomePageVisible() {
        return isElementDisplayed(driver, HomePageLocator.SLIDE_HOME_PAGE_LOCATOR);
    }
}

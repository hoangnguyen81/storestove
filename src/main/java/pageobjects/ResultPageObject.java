package pageobjects;

import commons.BasePage;
import commons.GlobalConstants;
import locators.ResultPageLocator;
import org.openqa.selenium.WebDriver;

public class ResultPageObject extends BasePage{
    protected WebDriver driver;
    private static ResultPageObject instance;

    public ResultPageObject(WebDriver driver){
        this.driver = driver;
    }

    public static ResultPageObject getInstance(WebDriver driver){
        if(instance == null){
            instance = new ResultPageObject(driver);
        }
        return instance;
    }

    public void scrollPriceBarToZero() {
        waitElementVisible(driver, ResultPageLocator.GAME_PRICE_SCROLL_BAR_LOCATOR);
        setAttributeELementByJS(driver, ResultPageLocator.GAME_PRICE_SCROLL_BAR_LOCATOR, "style", "--inds-slider-progress-left: 0%; --inds-slider-progress-right: 100%;");
    }

    public void clickDemoGameType() {
        waitElementClickable(driver, ResultPageLocator.GAME_TYPE_BY_NAME_DYNAMIC_LOCATOR, "DEMO");
        clickToElements(driver, ResultPageLocator.GAME_TYPE_BY_NAME_DYNAMIC_LOCATOR, "DEMO");
    }

    public boolean isTotalItemsEqualSearchResult() {
        waitPageLoadAndItemsStable(driver, ResultPageLocator.TOTAL_BUTTON_PAGE_LOCATOR , GlobalConstants.SHORT_TIMEOUT);

        int actualGameItems = 0;

        // Get total page
        int totalPage = getListWebElements(driver, ResultPageLocator.TOTAL_BUTTON_PAGE_LOCATOR).size();

        // Get total numbers of expected game items at Search Result
        int expectedGameItems = Integer.parseInt(getWebElement(driver, ResultPageLocator.TOTAL_NUMBER_GAME_ITEMS_SEARCH_RESULT_LOCATOR).getText());

        // Go to each page and get game items
        for(int i = 1; i <= totalPage; i++){
            scrollToElementByJS(driver, ResultPageLocator.BUTTON_NAME_PAGE_DYNAMIC_LOCATOR, String.valueOf(i));
            waitElementClickable(driver, ResultPageLocator.BUTTON_NAME_PAGE_DYNAMIC_LOCATOR, String.valueOf(i));
            clickToElements(driver, ResultPageLocator.BUTTON_NAME_PAGE_DYNAMIC_LOCATOR, String.valueOf(i));
            sleepInSecond(1);
            waitElementClickable(driver, ResultPageLocator.GAME_ITEM_LOCATOR);
            actualGameItems +=  getListWebElements(driver, ResultPageLocator.GAME_ITEM_LOCATOR).size();
        }
        return (actualGameItems == expectedGameItems);
    }

    public void changeToGridView() {
        waitElementClickable(driver, ResultPageLocator.BUTTON_CARD_TYPE_LOCATOR);
        clickToElements(driver, ResultPageLocator.BUTTON_CARD_TYPE_LOCATOR);
    }

    public boolean isCartIconByGameNameVisible(String gameName) {
        waitElementVisible(driver, ResultPageLocator.ICON_CART_BY_GAME_ITEM_DYNAMIC_LOCATOR, gameName);
        return isElementDisplayed(driver, ResultPageLocator.ICON_CART_BY_GAME_ITEM_DYNAMIC_LOCATOR, gameName);
    }

    public boolean isHeartIconByGameNameVisible(String gameName) {
        waitElementVisible(driver, ResultPageLocator.ICON_HEART_BY_GAME_ITEM_DYNAMIC_LOCATOR, gameName);
        return isElementDisplayed(driver, ResultPageLocator.ICON_HEART_BY_GAME_ITEM_DYNAMIC_LOCATOR, gameName);
    }

    public void hoveToGameName(String gameName) {
        waitElementVisible(driver, ResultPageLocator.GAME_ITEM_BY_NAME_LOCATOR, gameName);
        hoverToElement(driver, ResultPageLocator.GAME_ITEM_BY_NAME_LOCATOR, gameName);
    }
}

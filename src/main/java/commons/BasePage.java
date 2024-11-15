package commons;

import locators.ResultPageLocator;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    protected void getUrl(WebDriver driver, String url){
        driver.get(url);
    }

    protected String getTitle(WebDriver driver){
        return driver.getTitle();
    }

    protected String getCurrentUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

    protected String getPageSource(WebDriver driver){
        return driver.getPageSource();
    }

    protected void backToPage(WebDriver driver){
        driver.navigate().back();
    }

    protected void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }

    protected void refreshPage(WebDriver driver){
        driver.navigate().refresh();
    }

    protected Alert waitForAlertPresence(WebDriver driver){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptAlert(WebDriver driver){
        waitForAlertPresence(driver).accept();
    }

    protected void cancelAlert(WebDriver driver){
        waitForAlertPresence(driver).dismiss();
    }

    protected String getAlertText(WebDriver driver){
        return waitForAlertPresence(driver).getText();
    }

    protected void sendKeyToAlert(WebDriver driver, String value){
        waitForAlertPresence(driver).sendKeys(value);
    }

    protected void switchWindowById(WebDriver driver, String currentWindowID){
        Set<String> allWindowIDs = driver.getWindowHandles();

        for(String id : allWindowIDs){
            if(!id.equals(currentWindowID)){
                driver.switchTo().window(id);
                break;
            }
        }
    }

    protected void switchWindowByTitle(WebDriver driver, String windowTitle){
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs){
            driver.switchTo().window(id);
            if(driver.getTitle().equals(windowTitle)){
                break;
            }
        }
    }

    private By getByLocator(String locatorType){
        By by = null;
        if(locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")){
            by = By.id(locatorType.substring(3));
        }else if(locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")){
            by = By.className(locatorType.substring(6));
        }else if(locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")){
            by = By.name(locatorType.substring(5));
        }else if(locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")){
            by = By.cssSelector(locatorType.substring(4));
        }else if(locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")){
            by = By.xpath(locatorType.substring(6));
        }else {
            throw new RuntimeException("Locator type is not supported!");
        }
        return by;
    }

    private String getDynamicLocator(String locator, String... dynamicValue){
        locator = String.format(locator,(Object[])dynamicValue);
        return locator;
    }

    protected WebElement getWebElement(WebDriver driver, String locator){
        return driver.findElement(getByLocator(locator));
    }

    protected WebElement getWebElement(WebDriver driver, String locator, String...dynamicValue){
        return driver.findElement(getByLocator(getDynamicLocator(locator, dynamicValue)));
    }

    protected List<WebElement> getListWebElements(WebDriver driver, String locator){
        return driver.findElements(getByLocator(locator));
    }

    protected List<WebElement> getListWebElements(WebDriver driver, String locator, String...dynamicValue){
        return driver.findElements(getByLocator(getDynamicLocator(locator, dynamicValue)));
    }

    protected void clickToElements(WebDriver driver, String locator){
        getWebElement(driver, locator).click();
    }

    protected void clickToElements(WebDriver driver, String locator, String...dynamicValue){
        getWebElement(driver, locator, dynamicValue).click();
    }

    protected void sendKeyToElement(WebDriver driver, String locator, String value){
        getWebElement(driver, locator).sendKeys(value);
    }

    protected void sendKeyToElement(WebDriver driver, String locator, String value, String...dynamicValue){
        getWebElement(driver, locator, dynamicValue).sendKeys(value);
    }

    protected String getElementText(WebDriver driver, String locator){
        return getWebElement(driver, locator).getText();
    }

    protected String getElementText(WebDriver driver, String locator, String...dynamicValue){
        return getWebElement(driver, getDynamicLocator(locator, dynamicValue)).getText();
    }

    protected void pressKey(WebDriver driver, Keys key){
        Actions actions = new Actions(driver);
        actions.keyDown(key).perform();
    }

    protected void releaseKey(WebDriver driver, Keys key){
        Actions actions = new Actions(driver);
        actions.keyUp(key).perform();
    }

    protected void hoverToElement(WebDriver driver, String locator){
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(driver,locator)).perform();
    }

    protected void hoverToElement(WebDriver driver, String locator, String...dynamicValue){
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(driver,getDynamicLocator(locator, dynamicValue))).perform();
    }

    protected void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
    }

    protected void setAttributeELementByJS(WebDriver driver, String locator, String attributeName ,String attributeValue){
        JavascriptExecutor jsExecutor =  (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('"+attributeName+"', '"+attributeValue+"');", getWebElement(driver,locator));
    }

    protected void scrollToElementByJS(WebDriver driver, String locator){
        JavascriptExecutor jsExecutor =  (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoViews({block: 'center'});", getWebElement(driver, locator));
    }

    protected void scrollToElementByJS(WebDriver driver, String locator, String...dynamicValue){
        JavascriptExecutor jsExecutor =  (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", getWebElement(driver, getDynamicLocator(locator, dynamicValue)));
    }

    protected String getWebElementValidationMessage(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        return (String)jsExecutor.executeScript("arguments[0].validationMessage;", getWebElement(driver, locatorType));
    }

    protected void waitPageLoad(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").equals("complete");
            }
        });

    }

    protected void waitPageLoadAndItemsStable(WebDriver driver, String locator, int timeoutInSeconds) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").equals("complete");
            }
        });

        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                List<WebElement> initialItems = getListWebElements(driver, locator);
                sleepInSecond(GlobalConstants.TEMP_TIMEOUT);
                List<WebElement> newItems = getListWebElements(driver, locator);
                return initialItems.size() == newItems.size();
            }
        });
    }


    protected boolean isElementDisplayed(WebDriver driver, String locator){
        boolean status = true;
        overrideImplicitWait(driver, GlobalConstants.TEMP_TIMEOUT);
        try{
            WebElement element = getWebElement(driver, locator);
            if(element.isDisplayed()){
                return status;
            }
        }catch (Exception e){
            status = false;
        }
        overrideImplicitWait(driver, GlobalConstants.TEMP_TIMEOUT);
        return status;
    }

    protected boolean isElementDisplayed(WebDriver driver, String locator, String...dynamicValue){
        boolean status = true;

        overrideImplicitWait(driver, GlobalConstants.TEMP_TIMEOUT);
        try{
            WebElement element = getWebElement(driver, getDynamicLocator(locator, dynamicValue));
            if(element.isDisplayed()){
                return status;
            }
        }catch (Exception e){
            status = false;
        }

        overrideImplicitWait(driver, GlobalConstants.LONG_TIMEOUT);

        return status;
    }

    protected boolean isElementUnDisplayed(WebDriver driver, String locator){
        boolean status = true;

        overrideImplicitWait(driver, GlobalConstants.TEMP_TIMEOUT);

        List<WebElement> listElements = getListWebElements(driver, locator);
        if(listElements.size() == 0){
            status = true;
        }else if(listElements.size() > 0 && listElements.get(0).isDisplayed()){
            status = true;
        }else{
            status = false;
        }

        overrideImplicitWait(driver, GlobalConstants.LONG_TIMEOUT);

        return status;
    }

    private void overrideImplicitWait(WebDriver driver, long timeout){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

    protected void waitElementVisible(WebDriver driver, String locator){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    protected void waitElementVisible(WebDriver driver, String locator, String... dynamicValue){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locator, dynamicValue))));
    }

    protected void waitAllElementsVisible(WebDriver driver, String locator){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    protected void waitElementClickable(WebDriver driver, String locator){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    protected void waitElementClickable(WebDriver driver, String locator, String...dynamicValue){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locator, dynamicValue))));
    }

    protected void waitElementPresnce(WebDriver driver, String locator){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    protected void waitAllElementsPresnce(WebDriver driver, String locator){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
    }

    protected void sleepInSecond(long timeout){
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}

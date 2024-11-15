package testcases.resultpage;

import commons.BaseTest;
import org.testng.annotations.Test;
import pageobjects.ResultPageObject;
import io.qameta.allure.*;

public class SearchItem extends BaseTest {
    ResultPageObject resultPage;

    // 4. Test case: Check total items in search result page
    @Test
    public void TC_01_Check_Total_Item_In_Result_Page(){
        String searchKey = "action";

        // Close popup
        Allure.step("Close popup");
        homePage.closePopup();

        // Send "action" to search box
        Allure.step("Send action to search box");
        homePage.inputToSearchBox(searchKey);

        // Press Enter
        Allure.step("Press Enter");
        resultPage = homePage.pressEnter();

        // Click "DEMO"
        Allure.step("Click DEMO");
        resultPage.clickDemoGameType();

        // Select Price = 0
        Allure.step("Select Price = 0");
        resultPage.scrollPriceBarToZero();

        // Verify number at "Search Result" with total number of item from each page
        Allure.step("Verify number at Search Result with total number of item from each page");
        verifyTrue(resultPage.isTotalItemsEqualSearchResult());
    }

    // 5. Test case: Open a product via search result
    @Test
    public void TC_02_Check_Game_Item_Display(){
        String searchKey = "Love of";
        String gameName = "Pulse of Love";

        Allure.step("Click to Home menu");
        homePage.clickToHomeMenu();

        Allure.step("Verify slide banner is displayed");
        verifyTrue(homePage.isSlideHomePageVisible());

        Allure.step("Send Love of to search box");
        homePage.inputToSearchBox(searchKey);

        Allure.step("Press Enter");
        resultPage = homePage.pressEnter();

        Allure.step("Change the view to Grid view");
        resultPage.changeToGridView();

        Allure.step("Hove to game Pulse of Love");
        resultPage.hoveToGameName(gameName);

        Allure.step("Verify cart icon is displayed");
        verifyTrue(resultPage.isCartIconByGameNameVisible(gameName));

        Allure.step("Verify heart icon is displayed");
        verifyTrue(resultPage.isHeartIconByGameNameVisible(gameName));

    }
}

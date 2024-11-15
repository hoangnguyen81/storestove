package testcases.homepage;

import commons.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import org.testng.annotations.*;

@Feature("Pop up")
public class PopUpHomePage extends BaseTest{

    // 2: Test case: Close popup
    @Test
    public void TC_01_Close_Popup(){
        Allure.step("Close popup if it is displayed");
        homePage.closePopup();

        Allure.step("Verify pop up is not displayed");
        verifyTrue(homePage.isPopupIsUnDisplayed());

        Allure.step("Refresh page");
        homePage.refreshHomePage();

        Allure.step("Verify pop up is not displayed");
        homePage.isPopupIsUnDisplayed();
    }

}

package testcases.homepage;

import commons.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

public class DropdownListHomePage extends BaseTest{

    // 3. Test case: Check item in dropdown list
    @Test
    public void TC_01_Check_Item_In_Dropdown_List(){
        Allure.step("Close popup if it is displayed");
        homePage.closePopup();

        Allure.step("Send lord to search box");
        homePage.inputToSearchBox("lord");

        Allure.step("Verify game Lord Nine is displayed");
        verifyTrue(homePage.isGameLordNineDisplayed());
    }

}

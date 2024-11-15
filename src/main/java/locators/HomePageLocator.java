package locators;

public class HomePageLocator {

    public static String POPUP_LOCATOR = "xpath=//div[@id = 'commpon-popup-front-body']";

    public static String POPUP_CHECKBOX_DO_NOT_DISPLAY_LOCATOR = "xpath=//input[@id = 'common-popup-checkbox-skip-0']";

    public static String SEARCH_BOX_LOCATOR = "xpath=//input[@placeholder = 'Search Games']";

    public static String ITEM_GAME_BY_NAME_DYNAMIC_LOCATOR = "xpath=//p[contains(string(),'%s')]";

    public static String HOME_MENU_LOCATOR = "xpath=//a[contains(text(), 'HOME')]";

    public static String SLIDE_HOME_PAGE_LOCATOR = "xpath=//div[contains(@class, 'indie-1-cols-banner-c-type-component')]//div[@class = 'indie-swiper']";
}

package locators;

public class ResultPageLocator {

    public static String GAME_TYPE_BY_NAME_DYNAMIC_LOCATOR = "xpath=//span[contains(text(), ' Game Type ')]/parent::button/following-sibling::div//span[contains(text(), '%s')]/parent::label/preceding-sibling::input/parent::div";

    public static String GAME_PRICE_SCROLL_BAR_LOCATOR = "xpath=//span[contains(text(), 'Game Price')]/parent::button/following-sibling::div//div[contains(@class, 'rounded-full')]";

    public static String TOTAL_NUMBER_GAME_ITEMS_SEARCH_RESULT_LOCATOR = "xpath=//span[contains(text(), 'Search Result')]/following-sibling::em";

    public static String GAME_ITEM_LOCATOR = "xpath=//div[@class = 'inds-category-a-type-item']";

    public static String BUTTON_NAME_PAGE_DYNAMIC_LOCATOR = "xpath=//button[contains(text(), '%s')]";

    public static String TOTAL_BUTTON_PAGE_LOCATOR = "xpath=//span[contains(text(), '다음')]/parent::i/parent::button/preceding-sibling::span//button";

    public static String BUTTON_CARD_TYPE_LOCATOR = "xpath=//span[text() = '카드형']/parent::i/parent::button";

    public static String GAME_ITEM_BY_NAME_LOCATOR = "xpath=//p[@class = 'inds-product-card-hover-title' and contains(text(), '%s')]//ancestor::div[contains(@class, 'inds-product-relate-games-card-box')]";

    public static String ICON_CART_BY_GAME_ITEM_DYNAMIC_LOCATOR = "xpath=//p[@class = 'inds-product-card-hover-title' and contains(text(), '%s')]//ancestor::div[contains(@class, 'inds-product-relate-games-card-box')]//i[contains(@class, 'cart-fill')]";

    public static String ICON_HEART_BY_GAME_ITEM_DYNAMIC_LOCATOR = "xpath=//p[@class = 'inds-product-card-hover-title' and contains(text(), '%s')]//ancestor::div[contains(@class, 'inds-product-relate-games-card-box')]//i[contains(@class, 'community-like-fill')]";

}

package factoryBrowsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager implements  BrowserFactory{
    @Override
    public WebDriver getBrowserDriver() {
        return new ChromeDriver();
    }
}

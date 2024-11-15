package factoryBrowsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverManager implements BrowserFactory{
    @Override
    public WebDriver getBrowserDriver() {
        return new EdgeDriver();
    }
}

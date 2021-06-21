package pages.base;

import com.paulhammant.ngwebdriver.NgWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.WebEventListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseClass {

    public static WebDriver webDriver;
    public static Properties properties;
    public static NgWebDriver ngWebDriver;
    public static JavascriptExecutor javascriptExecutor;
    public static EventFiringWebDriver edriver;
    public static WebEventListener webEventListener;

    public BaseClass() throws IOException {
        InputStream fileInputStream = BaseClass.class.getResourceAsStream("/data/testConfig.properties");
        properties = new Properties();
        properties.load(fileInputStream);
    }

    public void init() throws IOException {
        String browserName = properties.getProperty("browser");
        switch (browserName.toUpperCase().trim()) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;

            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;

            case "EDGE":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;

        }

        edriver = new EventFiringWebDriver(webDriver);
        webEventListener = new WebEventListener();
        edriver.register(webEventListener);
        webDriver = edriver;

        javascriptExecutor = (JavascriptExecutor) webDriver;
        ngWebDriver = new NgWebDriver(javascriptExecutor);
        String url = properties.getProperty("url");

        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
        webDriver.get(url);



    }
}

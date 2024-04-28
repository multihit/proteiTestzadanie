package base;

import factory.DriverFactory;
import model.MainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public abstract class BaseTest {
    private Logger logger = (Logger) LogManager.getLogger(BaseTest.class);
    private final String URL = System.getProperty("base.url");
    private final String login = System.getProperty("login");
    private final String password = System.getProperty("password");
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public WebDriver getDriver() {
        return driver;
    }

    public MainPage openURL() {
        driver.get(URL);
        return new MainPage(getDriver());
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @BeforeEach
    public void init() {
        driver = new DriverFactory("--start-fullscreen").create();
        driver.get(URL);
        logger.info("Start driver");
    }

    @AfterEach
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("Stop driver");
    }

    protected WebDriverWait getWait() {
        if (webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }
        return webDriverWait;
    }
}

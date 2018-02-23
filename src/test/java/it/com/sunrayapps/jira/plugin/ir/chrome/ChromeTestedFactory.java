package it.com.sunrayapps.jira.plugin.ir.chrome;

import com.atlassian.pageobjects.TestedProductFactory;
import com.atlassian.pageobjects.browser.Browser;
import com.atlassian.webdriver.DefaultAtlassianWebDriver;
import com.atlassian.webdriver.pageobjects.DefaultWebDriverTester;
import com.atlassian.webdriver.pageobjects.WebDriverTester;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeTestedFactory implements TestedProductFactory.TesterFactory<WebDriverTester> {
    private final boolean headless;

    public ChromeTestedFactory() {
        this(true);
    }

    public ChromeTestedFactory(boolean headless) {
        this.headless = headless;
    }

    @Override
    public WebDriverTester create() {
        new ChromeDriverInstaller().setup();
        final ChromeOptions options = new ChromeOptions();
        options.addArguments(
            "--no-sandbox",
            "--disable-infobars"
        );
        if (headless) {
            options.addArguments("--headless");
        }
        final ChromeDriver driver = new ChromeDriver(options);
        final DefaultAtlassianWebDriver atlassianWebDriver = new DefaultAtlassianWebDriver(driver, Browser.CHROME);
        return new DefaultWebDriverTester(atlassianWebDriver);
    }
}

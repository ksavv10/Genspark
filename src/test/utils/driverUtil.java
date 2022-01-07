package test.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class driverUtil {
    static WebDriver driver;

    private driverUtil() {

    }

    public static WebDriver getDriver() {
        String browser = LoadProperties.getProperty("browser");

        try {
            if (driver == null) {
                if (browser.contains("chrome")) {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
// chromeOptions.setHeadless(true);
// chromeOptions.addArguments("disable-infobars");
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles");
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    driver = new ChromeDriver(chromeOptions);
                }
            if(browser.contains("firefox")){
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }}

            } catch(Exception e){
                e.printStackTrace();
            }
//
            return driver;
        }

    }

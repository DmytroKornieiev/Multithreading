package com.epam.task.cucumber.driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Optional;

import static org.openqa.selenium.chrome.ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY;
import static org.openqa.selenium.firefox.GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY;

public class DriverManager {

    private final static String CHROME_DRIVER_PATH = "C:\\Users\\Dmytro_Kornieiev\\Desktop\\Course EPAM Middle\\Core\\src\\main\\resources\\chromedriver.exe";
    private final static String GECKO_DRIVER_PATH = "C:\\Users\\Dmytro_Kornieiev\\Desktop\\Course EPAM Middle\\Core\\src\\main\\resources\\geckodriver.exe";
    private final static int IMPLICITLY_WAIT_TIMEOUT = 5;
    private final static int WAIT_TIMEOUT_FOR_PAGE_LOAD = 20;
    private final static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void setUpDriver() throws MalformedURLException {
        System.setProperty(CHROME_DRIVER_EXE_PROPERTY, CHROME_DRIVER_PATH);
        WebDriver driver;
        String url = "http://localhost:4444/wd/hub";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("firefox");
        if (desiredCapabilities.getBrowserName().equals("chrome")) {
            System.setProperty(CHROME_DRIVER_EXE_PROPERTY, CHROME_DRIVER_PATH);
        } else if (desiredCapabilities.getBrowserName().equals("firefox")) {
            System.setProperty(GECKO_DRIVER_EXE_PROPERTY, GECKO_DRIVER_PATH);
        }
        desiredCapabilities.setPlatform(Platform.WIN10);
        driver = new RemoteWebDriver((new URL(url)), desiredCapabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(WAIT_TIMEOUT_FOR_PAGE_LOAD));
        webDriverThreadLocal.set(driver);
    }

    public static WebDriver getDriver() {
        return webDriverThreadLocal.get();
    }

    public static void quitDriver(){
        Optional.ofNullable(getDriver()).ifPresent(webDriver ->{
            webDriver.quit();
            webDriverThreadLocal.remove();
        });
    }
}

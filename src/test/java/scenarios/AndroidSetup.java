package scenarios;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class AndroidSetup {
    protected static AndroidDriver driver;

    WebDriverWait wait;

    //кнопка в левом верхнем углу ("меню" или "назад")
    By menuBtn = By.xpath("//android.widget.ImageButton[contains(@index, '0')]");
    //кнопка "Любимые"
    By favBtn = By.xpath("//android.widget.ImageView[contains(@index, '0')]");

    String activityMain = ".ui.activity.MainActivity";

    protected static void prepareAndroidForAppium() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium-version", "1.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("deviceName", "Emu01");
        capabilities.setCapability("app", "/home/skaz17/myGitRepos/appiumTest/Appium-test-Incubator/apps/info-goodline-btv.391.apk");
        capabilities.setCapability("appPackage", "info.goodline.btv");
        capabilities.setCapability("appActivity", "info.goodline.btv.ui.activity.AuthActivity");
        //capabilities.setCapability("appActivity", "info.goodline.btv.ui.activity.MainActivity");
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @BeforeClass
    public static void setUp() throws Exception {
        prepareAndroidForAppium();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void helloTest()
    {
        //By password = By.id(app_package_name + "user_password");
        /*driver.findElement(userId).sendKeys("someone@testvagrant.com");
        driver.findElement(password).sendKeys("testvagrant123");*/

        waitForActivity(activityMain, 10);
        assertEquals(activityMain, driver.currentActivity());

        driver.findElement(menuBtn).click();

        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(favBtn));
        driver.findElement(favBtn).click();

        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(menuBtn));
        assertEquals(".ui.activity.SearchBarActivity", driver.currentActivity());

        driver.findElement(menuBtn).click();
    }

    public void waitForActivity(String activityName, int timeout)
    {
        int counter = 0;
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
        } while(!(driver.currentActivity().contains(activityName)) && (counter<=timeout));
    }

    /*public void reportCurrentActivity()
    {
        System.out.println("Current activity: " + driver.currentActivity());
    }*/
}

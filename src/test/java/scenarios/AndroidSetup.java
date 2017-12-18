package scenarios;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class AndroidSetup {
    protected static AndroidDriver driver;

    protected static void prepareAndroidForAppium() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium-version", "1.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("deviceName", "Emu01");
        capabilities.setCapability("app", "/home/skaz17/myGitRepos/appiumTest/Appium-test-Incubator/apps/info-goodline-btv.391.apk");
        capabilities.setCapability("appPackage", "info.goodline.btv");
        capabilities.setCapability("appActivity", "info.goodline.btv.ui.activity.AuthActivity");
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
        String app_package_name = "info.goodline.btv";
        /*By userId = By.id(app_package_name + "user_id");
        By password = By.id(app_package_name + "user_password");
        By showPassword = By.id(app_package_name + "show_password");
        By login_Button = By.id(app_package_name + "btn_login");*/

        /*driver.findElement(userId).sendKeys("someone@testvagrant.com");
        driver.findElement(password).sendKeys("testvagrant123");
        driver.findElement(showPassword).click();
        driver.findElement(login_Button).click();*/

        System.out.println(app_package_name);
    }
}

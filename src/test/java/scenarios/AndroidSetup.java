package scenarios;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import pages.BTV_common;
import pages.BTV_fav_page;
import pages.BTV_main_page;

public class AndroidSetup {
    protected static AndroidDriver driver;

    protected BTV_common pageCommon;
    protected BTV_main_page pageMain;
    protected BTV_fav_page pageFav;

    //
    //private By showMore = By.id("info.goodline.btv:id/ibControl");

    protected static void prepareAndroidForAppium() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium-version", "1.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("deviceName", "Emu01");
        capabilities.setCapability("newCommandTimeout", 200);
        capabilities.setCapability("app", System.getProperty("user.dir") + "/apps/info-goodline-btv.391.apk");
        capabilities.setCapability("appPackage", "info.goodline.btv");
        capabilities.setCapability("appActivity", "info.goodline.btv.ui.activity.AuthActivity");
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    protected void initPages()
    {
        pageCommon = new BTV_common(driver);
        pageMain = new BTV_main_page(driver);
        pageFav = new BTV_fav_page(driver);
    }
}

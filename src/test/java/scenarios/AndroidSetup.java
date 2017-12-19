package scenarios;

import io.appium.java_client.android.AndroidDriver;

import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

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
    By favMenuBtn = By.xpath("//android.widget.ImageView[contains(@index, '0')]");
    //первая запись в списке любимых
    By favEl = By.xpath("//android.widget.FrameLayout[contains(@bounds, '[0,208][768,470]')]");
    //кнопка "В любимые"
    //By favBtn = By.xpath("//android.widget.ImageView[@resource-id='info.goodline.btv:id/ivTriggerButtonContainer'");
    By favBtn = By.xpath("//android.widget.ImageView[contains(@resource-id, 'info.goodline.btv:id/ivTriggerButtonContainer')]");

    String activityMain = ".ui.activity.MainActivity";
    String activityFav = ".ui.activity.SearchBarActivity";

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

    @Before
    public void clearFavsTest()
    {
        //By password = By.id(app_package_name + "user_password");
        /*driver.findElement(userId).sendKeys("someone@testvagrant.com");
        driver.findElement(password).sendKeys("testvagrant123");*/

        waitForActivity(activityMain, 10);
        assertCurrentActivity(activityMain);

        driver.findElement(menuBtn).click();

        waitElement(favMenuBtn);
        driver.findElement(favMenuBtn).click();

        waitElement(menuBtn);
        assertCurrentActivity(activityFav);

        //WebElement fav;
        for (int i = 0; i < 2; i++) {
            driver.findElement(favEl).click();
            waitElement(favBtn);
            driver.findElement(favBtn).click();
            driver.findElement(menuBtn).click();
            waitElement(menuBtn);
            refresh();
        }

        driver.findElement(menuBtn).click();
    }

    @Test
    public void openFavTest()
    {
        //By password = By.id(app_package_name + "user_password");
        /*driver.findElement(userId).sendKeys("someone@testvagrant.com");
        driver.findElement(password).sendKeys("testvagrant123");*/

        //waitForActivity(activityMain, 10);
        assertCurrentActivity(activityMain);

        driver.findElement(menuBtn).click();

        waitElement(favMenuBtn);
        driver.findElement(favMenuBtn).click();

        waitElement(menuBtn);
        assertCurrentActivity(activityFav);

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

    public void assertCurrentActivity(String desiredActivity)
    {
        assertEquals(desiredActivity, driver.currentActivity());
    }

    public void waitElement(By element)
    {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void refresh()
    {
        int starty = (int) (driver.manage().window().getSize().height * 0.90);
        int endy = (int) (driver.manage().window().getSize().height * 0.20);
        int startx = driver.manage().window().getSize().width / 2;
        //System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);
        //Thread.sleep(10000);
        driver.swipe(startx, endy, startx, starty, 300);
    }
}

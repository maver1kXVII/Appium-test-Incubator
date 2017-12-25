package scenarios;

import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.AndroidKeyCode;
import org.junit.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class AndroidSetup {
    protected static AndroidDriver driver;

    private WebDriverWait wait;

    //кнопка в левом верхнем углу ("меню" или "назад")
    private By menuBtn = By.xpath("//android.widget.ImageButton[contains(@index, '0')]");
    //меню
    private By menuWidget = By.id("info.goodline.btv:id/rvDrawerMenu");
    //
    private By showMore = By.id("info.goodline.btv:id/ibControl");
    //кнопка "Любимые"
    private By favMenuBtn = By.xpath("//android.widget.TextView[contains(@text, 'Любимое')]");
    //первая запись в списке любимых
    private By favEl = By.xpath("//android.widget.FrameLayout[contains(@bounds, '[0,208][768,470]')]");
    //кнопка "В любимые"
    private By favBtn = By.xpath("//android.widget.ImageView[contains(@resource-id, 'info.goodline.btv:id/ivTriggerButtonContainer')]");
    //виджет списка фильмов в избранном
    private By favWidget = By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id, 'info.goodline.btv:id/recyclerView')]");
    //название фильма
    private By movieTitle = By.xpath("//android.widget.TextView[contains(@resource-id, 'info.goodline.btv:id/tvTitle')]");
    //фильма для добавления в избранное
    private By movie = By.xpath("//android.widget.ImageView[contains(@resource-id, 'info.goodline.btv:id/ivPoster')]");
    //фильмы в избранном
    private By favMovies = By.id("info.goodline.btv:id/ivEcPicture");

    private String activityMain = ".ui.activity.MainActivity";
    private String activityFav = ".ui.activity.SearchBarActivity";

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

    public void waitForMain(int timeout)
    {
        waitForActivity(activityMain, 10);
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

    /**
     * Performs a bottom to top screen swipe.
     */
    public void refresh()
    {
        int starty = (int) (driver.manage().window().getSize().height * 0.90);
        int endy = (int) (driver.manage().window().getSize().height * 0.20);
        int startx = driver.manage().window().getSize().width / 2;
        driver.swipe(startx, endy, startx, starty, 300);
    }

    /**
     * Opens the Favourites menu. Use in the main menu.
     */
    public void openFavsMenu()
    {
        waitElement(menuBtn);
        assertCurrentActivity(activityMain);

        driver.findElement(menuBtn).click();

        waitElement(menuWidget);
        driver.findElement(favMenuBtn).click();

        waitElement(menuBtn);
        assertCurrentActivity(activityFav);
    }

    /**
     * Clears the list of favourites. Use when the favourites menu is already opened.
     */
    public void clearFavList()
    {
        assertCurrentActivity(activityFav);
        List<WebElement> favs = driver.findElements(favMovies);
        for(int i = 0; i < favs.size(); i++)
        {
            favs.get(i).click();
            toggleFavStatus();
            driver.findElement(menuBtn).click();
            waitElement(favWidget);
        }
    }

    /**
     * Add or removes a movie from favourites depending on its current status.
     */
    public void toggleFavStatus()
    {
        waitElement(favBtn);
        driver.findElement(favBtn).click();
    }

    public void backToMenu()
    {
        driver.findElement(menuBtn).click();
        waitElement(menuBtn);
        assertCurrentActivity(activityMain);
    }

    public String addToFavs()
    {
        String title = "";
        driver.findElement(movie).click();
        toggleFavStatus();
        title = driver.findElement(movieTitle).getText();
        driver.findElement(menuBtn).click();
        return title;
    }

    public String getFirstFavTitle()
    {
        String title = "";
        driver.findElement(favEl).click();
        waitElement(favBtn);
        title = driver.findElement(movieTitle).getText();
        driver.findElement(menuBtn).click();
        waitElement(menuBtn);
        return title;
    }
}

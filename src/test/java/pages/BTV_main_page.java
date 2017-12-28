package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class BTV_main_page extends BTV_common {

    //меню
    private By menuWidget = By.id("info.goodline.btv:id/rvDrawerMenu");
    //кнопка "Любимые"
    private By favMenuBtn = By.xpath("//android.widget.TextView[contains(@text, 'Любимое')]");

    private String activityMain = ".ui.activity.MainActivity";

    public BTV_main_page(AndroidDriver driver01) {
        super(driver01);
    }

    private void waitMain()
    {
        waitForActivity(activityMain, 10);
    }

    private void openMenu()
    {
        waitElement(menuBtn);
        assertCurrentActivity(activityMain);

        driver.findElement(menuBtn).click();
    }

    private void clickFavMenu()
    {
        waitElement(menuWidget);
        driver.findElement(favMenuBtn).click();

        waitElement(menuBtn);
    }

    /**
     * Opens the Favourites menu.
     */
    public void openFavsMenu()
    {
        openMenu();
        clickFavMenu();
    }

    public void waitForMain(int timeout)
    {
        waitMain();
    }
}

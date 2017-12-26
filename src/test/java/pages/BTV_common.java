package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class BTV_common {

    protected AndroidDriver driver;
    private WebDriverWait wait;

    //кнопка в левом верхнем углу ("меню" или "назад")
    protected By menuBtn = By.xpath("//android.widget.ImageButton[contains(@index, '0')]");

    public BTV_common(AndroidDriver driver01)
    {
        driver = driver01;
    }

    protected void assertCurrentActivity(String desiredActivity)
    {
        assertEquals(desiredActivity, driver.currentActivity());
    }

    protected void waitElement(By element)
    {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForActivity(String activityName, int timeout)
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

    public void backToMenu()
    {
        driver.findElement(menuBtn).click();
        waitElement(menuBtn);
    }
}

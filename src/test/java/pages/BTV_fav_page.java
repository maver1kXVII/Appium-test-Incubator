package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BTV_fav_page extends BTV_common {
    private String activityFav = ".ui.activity.SearchBarActivity";

    //фильмы в избранном
    private By favMovies = By.id("info.goodline.btv:id/ivEcPicture");
    //виджет списка фильмов в избранном
    private By favWidget = By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id, 'info.goodline.btv:id/recyclerView')]");
    //первая запись в списке любимых
    private By favEl = By.xpath("//android.widget.FrameLayout[contains(@bounds, '[0,208][768,470]')]");
    //кнопка "В любимые"
    private By favBtn = By.xpath("//android.widget.ImageView[contains(@resource-id, 'info.goodline.btv:id/ivTriggerButtonContainer')]");

    //название фильма
    private By movieTitle = By.xpath("//android.widget.TextView[contains(@resource-id, 'info.goodline.btv:id/tvTitle')]");
    //фильма для добавления в избранное
    private By movie = By.xpath("//android.widget.ImageView[contains(@resource-id, 'info.goodline.btv:id/ivPoster')]");

    public BTV_fav_page(AndroidDriver driver01) {
        super(driver01);
    }

    /**
     * Add or removes a movie from favourites depending on its current status.
     */
    private void toggleFavStatus()
    {
        waitElement(favBtn);
        driver.findElement(favBtn).click();
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

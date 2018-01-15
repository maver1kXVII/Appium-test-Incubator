package scenarios;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BTV_add_to_favourites_test extends AndroidSetup {

    private String favTitleInMain = "";
    private String favTitleInFav = "";

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
        initPages();

        pageMain.waitForMain(10);
        pageMain.openFavsMenu();
        pageFav.clearFavList();
        pageCommon.backToMenu();
    }

    @Test
    public void addFavTest()
    {
        favTitleInMain = pageFav.addToFavs();
        pageMain.openFavsMenu();
        pageCommon.refresh();
        favTitleInFav = pageFav.getFirstFavTitle();

        assertEquals(favTitleInMain, favTitleInFav);
    }
}

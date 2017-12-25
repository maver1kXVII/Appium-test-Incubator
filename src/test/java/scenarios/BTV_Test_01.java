package scenarios;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BTV_Test_01 extends AndroidSetup {

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
        waitForMain(10);
        openFavsMenu();
        clearFavList();
        backToMenu();
    }

    @Test
    public void addFavTest()
    {
        favTitleInMain = addToFavs();
        openFavsMenu();
        refresh();
        favTitleInFav = getFirstFavTitle();

        assertEquals(favTitleInMain, favTitleInFav);
    }
}

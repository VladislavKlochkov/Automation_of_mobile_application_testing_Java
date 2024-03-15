package HomeTest6;

import HW6.factory.DriverFactory;
import HW6.factory.Platform;
import HW6.factory.action.Direction;
import HW6.factory.action.SwipeHelper;
import HW6.factory.screens.CatalogScreen;
import HW6.factory.screens.FilterScreen;
import HW6.factory.screens.Tabbar;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.net.MalformedURLException;
import java.time.Duration;

public class Factory {
    private final DriverFactory driverFactory = new DriverFactory();
    private AppiumDriver<?> driver;

    @Before
    public void setDriver() throws MalformedURLException {
        driver = driverFactory.setUp(Platform.ANDROID);
    }

    @Test
    public void factory() {
        Tabbar tabbar = new Tabbar(driver);

        Assert.assertFalse(tabbar.isCatalogSelected());

        tabbar.clickCatalog();

        Assert.assertTrue(tabbar.isCatalogSelected());

        SwipeHelper swipeHelper = new SwipeHelper(driver);
        swipeHelper.swipe(Direction.UP);
        swipeHelper.swipe(Direction.DOWN);

        CatalogScreen catalogScreen = new CatalogScreen(driver);
        String television = "Телевизор";
        catalogScreen.search(television);
        ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

        String foundTitleText = catalogScreen.getFoundTitleText();

        catalogScreen.clickFilters();

        FilterScreen filterScreen = new FilterScreen(driver);
        filterScreen.turnOnDiscountedGoods();
        filterScreen.applyFilters();

        String foundSaleTitleText = catalogScreen.getFoundTitleText();

        Assert.assertNotEquals(foundTitleText, foundSaleTitleText);

        ((AndroidDriver<?>) driver).lockDevice(Duration.ofSeconds(3));

        Assert.assertEquals(television, catalogScreen.getSearchText());

        catalogScreen.swipeSpecifyCategoryPager();
    }
}

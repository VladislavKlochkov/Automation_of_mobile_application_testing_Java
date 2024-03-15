package HomeTest6;

import HW6.strategy.DriverFactory;
import HW6.strategy.SearchBarStrategy;
import HW6.strategy.action.Direction;
import HW6.strategy.action.SwipeHelper;
import HW6.strategy.screens.CatalogScreen;
import HW6.strategy.screens.FilterScreen;
import HW6.strategy.screens.Tabbar;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.net.MalformedURLException;
import java.time.Duration;

public class Strategy {
    private final DriverFactory driverFactory = new DriverFactory();
    private AppiumDriver<?> driver;

    @Before

    public void setDriver() throws MalformedURLException {
        driver = driverFactory.setUp();
    }

    @Test
    public void strategy() {
        Tabbar tabbar = new Tabbar(driver);

        Assert.assertFalse(tabbar.isCatalogSelected());

        tabbar.clickCatalog();

        Assert.assertTrue(tabbar.isCatalogSelected());

        SwipeHelper swipeHelper = new SwipeHelper(driver);
        swipeHelper.swipe(Direction.UP);
        swipeHelper.swipe(Direction.DOWN);

        CatalogScreen catalogScreen = new CatalogScreen(driver);
        String television = "Телевизор";
        tabbar.clickCatalog().searchByStrategy(new SearchBarStrategy(driver));

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

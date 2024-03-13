package HW6.strategy;

import io.appium.java_client.AppiumDriver;
import HW6.strategy.screens.CatalogScreen;

public class SearchBarStrategy implements SearchStrategy {
    AppiumDriver driver;

    public SearchBarStrategy(AppiumDriver driver) {
        this.driver = driver;
    }

    @Override
    public void search() {
        CatalogScreen catalogScreen = new CatalogScreen(driver);
        catalogScreen.search("Телевизор");
    }
}

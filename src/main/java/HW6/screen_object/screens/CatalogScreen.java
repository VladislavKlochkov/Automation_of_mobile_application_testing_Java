package HW6.screen_object.screens;

import HW6.screen_object.action.Direction;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class CatalogScreen extends Tabbar {

    @AndroidFindBy(id = "search_title")
    MobileElement searchTitle;

    @AndroidFindBy(id = "search_src_text")
    MobileElement searchText;

    @AndroidFindBy(uiAutomator = "resourceIdMatches(\".*id/title\").textContains(\"товаров\")")
    MobileElement foundTitle;

    @AndroidFindBy(id = "filter_label")
    MobileElement filterLabel;

    public CatalogScreen swipeSpecifyCategoryPager() {
        swipeHelper.swipe(Direction.LEFT, specifyCategoryPager);
        return this;
    }

    @AndroidFindBy(id = "specify_category_pager")
    MobileElement specifyCategoryPager;

    public CatalogScreen(AndroidDriver<?> driver) {
        super(driver);
    }

    public String getFoundTitleText() {
        return foundTitle.getText();
    }

    public void search(String text) {
        searchTitle.click();
        searchText.sendKeys(text);
    }

    public void clickFilters() {
        filterLabel.click();
    }

    public String getSearchText() {
        return searchText.getText();
    }
}

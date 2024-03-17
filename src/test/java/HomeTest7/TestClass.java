package HomeTest7;

import HW7.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.util.Set;

public class TestClass {
    @AndroidFindBy(accessibility = "www.e-katalog")
    MobileElement title;

    @AndroidFindBy(xpath = "//div[@id=\"owl-slider\"]//a[text()=\"Мобильные\"]")
    MobileElement mobiles;

    @FindBy(xpath = "//a[text()=\"Apple\"]")
    MobileElement apple;

    @AndroidFindBy(xpath = "android.widget.TextView[@text=\"по популярности\"]/parent::android.view.View")
    MobileElement filterByPopularity;

    @AndroidFindBy(accessibility = "от дешевых к дорогим")
    MobileElement filterFromCheapToExpensive;

    @AndroidFindBy(uiAutomator = "new UiScrollable(scrollable(true)).flingToEnd(3)")
    MobileElement maxScroll;

    private final DriverFactory driverFactory = new DriverFactory();
    private AndroidDriver<?> driver;

    @Before

    public void setDriver() throws MalformedURLException {
        driver = driverFactory.setUp();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Test
    public void test() {
        title.isDisplayed();
        changeDriverContextToWebView(driver);
        mobiles.click();
        apple.click();
        changeDriverContextToNative(driver);
        filterByPopularity.click();
        filterFromCheapToExpensive.click();
        maxScroll.isDisplayed();
    }

    public void changeDriverContextToWebView(AppiumDriver<?> driver) {
        Set<String> contextHandles = driver.getContextHandles();
        for (String name : contextHandles) {
            if (name.contains("WEBVIEW")) driver.context(name);
        }
    }

    public void changeDriverContextToNative(AppiumDriver<?> driver) {
        Set<String> contextHandles = driver.getContextHandles();
        for (String name : contextHandles) {
            if (name.contains("NATIVE_APP")) driver.context(name);
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

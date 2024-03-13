package HW6.screen_object.screens;

import HW6.screen_object.action.SwipeHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;


public class Screen {

    SwipeHelper swipeHelper;

    Screen(AndroidDriver<?> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        swipeHelper = new SwipeHelper(driver);
    }

}

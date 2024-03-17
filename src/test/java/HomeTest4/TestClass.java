package HomeTest4;

import HW4.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class TestClass {
    @AndroidFindBy(id = "btnJoinConf")
    MobileElement btnJoinConf;
    @AndroidFindBy(id = "btnBack")
    MobileElement btnBack;
    @AndroidFindBy(id = "txtTitle")
    MobileElement txtTitle;
    @AndroidFindBy(id = "panelConfNumber")
    MobileElement panelConfNumber;
    @AndroidFindBy(id = "btnGotoVanityUrl")
    MobileElement btnGotoVanityUrl;
    @AndroidFindBy(id = "edtScreenName")
    MobileElement edtScreenName;

    private final DriverFactory driverFactory = new DriverFactory();
    private AndroidDriver<?> driver;

    @Before

    public void setDriver() throws MalformedURLException {
        driver = driverFactory.setUp();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Test
    public void test() throws InterruptedException {
        Thread.sleep(Long.parseLong("50000"));
        Assertions.assertTrue(btnJoinConf.isDisplayed());
        Assertions.assertTrue(btnJoinConf.isEnabled());
        Assertions.assertFalse(btnJoinConf.isSelected());
        btnJoinConf.click();

        Thread.sleep(Long.parseLong("5000"));

        btnBack.isDisplayed();
        txtTitle.isDisplayed();
        panelConfNumber.isDisplayed();
        btnGotoVanityUrl.isDisplayed();
        edtScreenName.isDisplayed();

        Assertions.assertEquals("Join a Meeting", txtTitle.getText());

        MobileElement buttonJoin = (MobileElement) driver.findElementById("btnJoin");
        MobileElement textUnderJoin = (MobileElement) driver.findElementByAndroidUIAutomator(
                "textContains(If you received an invitation link," +
                        " tap on the link to join the meeting)");
        MobileElement titleJoinOptions = (MobileElement) driver.findElementByAndroidUIAutomator(
                "textContains(JOIN OPTIONS)");
        MobileElement textDoNotConnectToAudio = (MobileElement) driver.findElementByAndroidUIAutomator(
                "textContains(Don't Connect To Audio)");
        MobileElement chkNoAudio = (MobileElement) driver.findElementById("chkNoAudio");
        MobileElement textTurnOffMyVideo = (MobileElement) driver.findElementByAndroidUIAutomator(
                "textContains(Turn Off My Video)");
        MobileElement chkNoVideo = (MobileElement) driver.findElementById("chkNoVideo");

        buttonJoin.isDisplayed();
        textUnderJoin.isDisplayed();
        titleJoinOptions.isDisplayed();
        textDoNotConnectToAudio.isDisplayed();
        chkNoAudio.isDisplayed();
        textTurnOffMyVideo.isDisplayed();
        chkNoVideo.isDisplayed();

        Assertions.assertFalse(buttonJoin.isEnabled());

        String text = edtScreenName.getText();
        edtScreenName.clear();
        Assertions.assertNotEquals(text, edtScreenName.getText());
        edtScreenName.sendKeys(text);
        Assertions.assertEquals(text, edtScreenName.getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
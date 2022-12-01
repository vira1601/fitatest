package flutter;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;

@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(Lifecycle.PER_CLASS)

public class shrine {
    private AndroidDriver driver;
    // Annotated method should be executed before all tests in the current test class
    // Open apps
    @BeforeAll
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:platformVersion", "9");
        // replace emulator-5554 with your actual device emulator or real
        desiredCapabilities.setCapability("appium:deviceName", "emulator-5554");
        desiredCapabilities.setCapability("appium:automationName", "UIAutomator2");
        // apps capability
        desiredCapabilities.setCapability("appium:appPackage", "io.flutter.demo.gallery");
        desiredCapabilities.setCapability("appium:appActivity", "io.flutter.demo.gallery.MainActivity");

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub/");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    @DisplayName("Opening the Shrine Gallery")
    public void step1() {
        // Act
        driver.findElementByAccessibilityId("Reply\n" +
                "An efficient, focused email app").isDisplayed();
        // Swipe one card
        new TouchAction(driver).
                press(PointOption.point(1000,500)).
                waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).
                moveTo(PointOption.point(100,480)).
                release().
                perform();
        driver.findElementByAccessibilityId("Shrine\n" +
                "A fashionable retail app").isDisplayed();
        driver.findElementByAccessibilityId("Shrine\n" +
                "A fashionable retail app").click();
        // Input username and password
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElementByXPath("//*[@text='Username']").sendKeys("Vira");
        driver.findElementByXPath("//*[@text='Password']").sendKeys("Password123");
        driver.findElementByAccessibilityId("NEXT").click();
        // Assert after open Shrine
        Assertions.assertTrue(driver.findElementByAccessibilityId("SHRINE").getAttribute("content-desc").equals("SHRINE"));
    }

    @Test
    @DisplayName("Adding the Walter henley (white) shirt to cart after going to the Clothing filter")
     public void step2() {
        // Act
        driver.findElementByAccessibilityId("Open menu").click();
        driver.findElementByAccessibilityId("CLOTHING").isDisplayed();
        driver.findElementByAccessibilityId("CLOTHING").click();
        driver.findElementByAccessibilityId("Shopping cart, no items").click();
        driver.findElementByAccessibilityId("TOTAL\n" +
                "$0.00").isDisplayed();
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        driver.findElementByAccessibilityId("Sea tunic\n" +
                "$45, Add to cart").isDisplayed();
        // Swipe page
        for(int i=1; i<=2; i++) {
            new TouchAction(driver).
                    press(PointOption.point(1000,500)).
                    waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).
                    moveTo(PointOption.point(250,750)).
                    release().
                    perform();
        }
        // Find walter henley
        driver.findElementByAccessibilityId("Walter henley (white)\n" +
                "$38, Add to cart").isDisplayed();
        // Add cart walter henley
        driver.findElementByAccessibilityId("Walter henley (white)\n" +
                "$38, Add to cart").click();
        driver.findElementByAccessibilityId("Shopping cart, 1 item").click();
        // Assert walter henley successfully added to cart
        Assertions.assertTrue(driver.findElementByAccessibilityId("Walter henley (white)\n" +
                "Quantity: 1\n" +
                "x $38").getAttribute("content-desc").equals("Walter henley (white)\n" +
                "Quantity: 1\n" +
                "x $38"));
        Assertions.assertTrue(driver.findElementByAccessibilityId("TOTAL\n" +
                "$47.28").getAttribute("content-desc").equals("TOTAL\n" +
                "$47.28"));
    }

    @Test
    @DisplayName("Adding the Shrug bag after using the Accessories filter")
    public void step3() {
        // Act
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        driver.findElementByAccessibilityId("Open menu").click();
        driver.findElementByAccessibilityId("ACCESSORIES").isDisplayed();
        driver.findElementByAccessibilityId("ACCESSORIES").click();
        driver.findElementByAccessibilityId("Shopping cart, 1 item").click();
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        // Add cart shrug bag
        driver.findElementByAccessibilityId("Shrug bag\n" +
                "$198, Add to cart").click();
        driver.findElementByAccessibilityId("Shopping cart, 2 items").click();
        // Assert walter henley still in cart
        // Assert shrug bag successfully added to cart
        Assertions.assertTrue(driver.findElementByAccessibilityId("Walter henley (white)\n" +
                "Quantity: 1\n" +
                "x $38").getAttribute("content-desc").equals("Walter henley (white)\n" +
                "Quantity: 1\n" +
                "x $38"));
        Assertions.assertTrue(driver.findElementByAccessibilityId("Shrug bag\n" +
                "Quantity: 1\n" +
                "x $198").getAttribute("content-desc").equals("Shrug bag\n" +
                "Quantity: 1\n" +
                "x $198"));
    }

    @Test
    @DisplayName("Checking the total of the shopping cart")
    public void step4() {
        String priceWalter = "38";
        String priceShrug = "198";
        String priceShipping = "14";
        String priceTax = "14.16";
        int priceSubTotal = Integer.parseInt(priceWalter) + Integer.parseInt(priceShrug);
        float priceAdded = Float.parseFloat(priceShipping) + Float.parseFloat(priceTax);
        Assertions.assertTrue(driver.findElementByAccessibilityId("Subtotal:\n" +
                "$236.00").getAttribute("content-desc").equals("Subtotal:\n" +
                "$"+priceSubTotal+".00"));
        Assertions.assertTrue(driver.findElementByAccessibilityId("TOTAL\n" +
                "$264.16").getAttribute("content-desc").equals("TOTAL\n" +
                "$"+Float.sum(priceSubTotal,priceAdded)));
    }

    @Test
    @DisplayName("Clearing the shopping cart")
    public void step5() {
        // Act
        driver.findElementByAccessibilityId("CLEAR CART").click();
        driver.findElementByAccessibilityId("Shopping cart, no items").click();
        // Assert price total $0.00
        Assertions.assertTrue(driver.findElementByAccessibilityId("TOTAL\n" +
                "$0.00").isDisplayed());
        // Assert items cleared successfully
        try{
            driver.findElementByAccessibilityId("Walter henley (white)\n" +
                    "Quantity: 1\n" +
                    "x $38");
        } catch(NoSuchElementException e) {
            return;
        } try {
            driver.findElementByAccessibilityId("Shrug bag\n" +
                    "Quantity: 1\n" +
                    "x $198");
        } catch(NoSuchElementException e) {
            return;
        }
    }

    // Annotated method should be executed after all tests in the current test class
    @AfterAll
    public void quit() {
        // Close and clear apps data
        driver.quit();
    }
}

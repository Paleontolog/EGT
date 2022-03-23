package com.qa.shop;


import com.qa.shop.webdriver.BaseSelenium;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenCreator {

    private static final WebDriver driver = BaseSelenium.getWebDriver();
    private static final String path = BaseSelenium.getPath();

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] saveAllureScreenshot(String name) {
        return ((TakesScreenshot) (new Augmenter().augment(driver)))
                .getScreenshotAs(OutputType.BYTES);
    }

    static void saveScreenToFolder(String name) {
        File screenshot = ((TakesScreenshot) (new Augmenter().augment(BaseSelenium.getWebDriver())))
                .getScreenshotAs(OutputType.FILE);
        try {
            BufferedImage img = ImageIO.read(screenshot);
            File to = new File(path + "\\" + name + ".png");
            ImageIO.write(img, "png", to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package com.qa.shop.tests;

import com.qa.shop.ScreenListener;
import com.qa.shop.webdriver.BaseSelenium;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners({ScreenListener.class})
public class AbstractTest {
    @BeforeSuite
    public static void initSelenium() {
        BaseSelenium.setup();
    }

    @AfterSuite
    public static void closeSelenium() {
        BaseSelenium.close();
    }
}
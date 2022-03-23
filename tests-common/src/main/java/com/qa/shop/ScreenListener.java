package com.qa.shop;

import com.qa.shop.webdriver.BaseSelenium;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenListener implements ITestListener, StepLifecycleListener {
    private static final Logger log = LoggerFactory.getLogger(ScreenListener.class);
    private static final DateFormat formatForDateNow = new SimpleDateFormat("yyyy-mm-dd hh.mm.ss");
    private static final String FILE_PATH = BaseSelenium.getPath();

    private static final String testFailedMessage = "Failed test '%s' at %s";
    private static final String beforeStepMessage = "Before step '%s' at %s";
    private static final String afterStepMessage = "After step '%s' at %s";

    private String getFormattedMessage(String formatString, String message) {
        Date currentDate = new Date();
        String dateString = formatForDateNow.format(currentDate);
        return String.format(formatString, message, dateString);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ScreenCreator.saveAllureScreenshot(getFormattedMessage(testFailedMessage, result.getName()));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    private void addAttachment(final StepResult result, String format) {

        String name = getFormattedMessage(format, result.getName());

        ScreenCreator.saveAllureScreenshot(name);

//        Attachment att = new Attachment();
//        att.setType("image/png");
//
//        att.setSource(FILE_PATH + "\\" + dateString + ".png");
//        result.withAttachments(att);
    }

    @Override
    public void beforeStepStart(final StepResult result) {
//        addAttachment(result, beforeStepMessage);
    }

    @Override
    public void beforeStepStop(final StepResult result) {
//        addAttachment(result, afterStepMessage);
    }
}

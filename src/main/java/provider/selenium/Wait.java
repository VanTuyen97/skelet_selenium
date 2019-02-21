/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.selenium;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author vantuyen361
 */
public class Wait extends WebDriverWait {

    protected WebDriver driver;
    private final long timeOut;

    public Wait(WebDriver driver, long timeOut) {
        super(driver, timeOut);
        this.timeOut = timeOut;
        this.driver = driver;
    }

    /**
     * wait until load page success
     */
    public void untilPageLoadComplete() {
        this.until((d) -> {
            Boolean isPageLoaded = (Boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            if (!isPageLoaded) {
                System.out.println("Document is loading");
            }
            return isPageLoaded;
        });
    }

    public <V extends Object> V until(Function<? super WebDriver, V> isTrue, long timeOutInSeconds) {
        try {
            this.withTimeout(timeOutInSeconds, TimeUnit.SECONDS);
            return until(isTrue);
        } finally {
            this.withTimeout(timeOut, TimeUnit.SECONDS);
        }
    }

    @Override
    protected RuntimeException timeoutException(String message, Throwable lastException) {
        WebDriver exceptionDriver = driver;
        TimeoutException ex = new TimeoutException(message, lastException);
        throw ex;
    }
}

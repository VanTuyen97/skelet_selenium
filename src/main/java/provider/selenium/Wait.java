/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author vantuyen361
 */
public class Wait extends WebDriverWait {

    protected WebDriver driver;

    public Wait(WebDriver driver, long timeout) {
        super(driver, timeout);
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
}

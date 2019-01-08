/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.selenium;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 *
 * @author vantuyen361
 */
public abstract class CommonPage {
    protected WebDriver driver;
    protected Wait wait;

    public CommonPage() {
    }

    public void init(WebDriver driver, int waitImplicitly){
        this.driver = driver;
        this.wait = new Wait(driver, waitImplicitly);
    }

    public WebElement findElement(By by) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return this.driver.findElement(by);
    }

    public List<WebElement> findElements(By by) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return this.driver.findElements(by);
    }

    public void clickComponent(By by) {
        this.scrollToElement(by);
        WebElement e = this.findElement(by);
        this.wait.until(ExpectedConditions.elementToBeClickable(by));
        e.click();
    }

    public void enterText(By by, String content) {
        WebElement e = this.findElement(by);
        e.clear();
        e.sendKeys(content);
    }

    public void scrollToElement(By by) {
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-200)");
    }

    public void quit() {
        this.driver.quit();
    }
}
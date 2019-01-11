/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.selenium;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import provider.util.Directory;

/**
 *
 * @author vantuyen361
 */
public abstract class CommonPage {

    protected WebDriver driver;
    protected Wait wait;

    public CommonPage() {
    }

    public void init(WebDriver driver, int waitImplicitly) {
        this.driver = driver;
        this.wait = new Wait(driver, waitImplicitly);
    }

    public WebElement findElement(Xpath xpath) {
        this.wait.visibilityOfElementLocated(xpath);
        By by = By.xpath(xpath.toString());
        return this.driver.findElement(by);
    }

    public List<WebElement> findElements(Xpath xpath) {
        this.wait.visibilityOfElementLocated(xpath);
        By by = By.xpath(xpath.toString());
        return this.driver.findElements(by);
    }

    public void clickComponent(Xpath xpath) {
        this.scrollToElement(xpath);
        WebElement e = this.findElement(xpath);
        this.wait.elementToBeClickable(xpath);
        e.click();
    }

    public void enterText(Xpath xpath, String content) {
        WebElement e = this.findElement(xpath);
        e.clear();
        e.sendKeys(content);
    }

    public void scrollToElement(Xpath xpath) {
        WebElement element = this.findElement(xpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-200)");
    }

    /**
     * Capture screen browser (.jpg format)
     * @return path of image.
     * @throws IOException
     */
    public String captureScreen() throws IOException {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        
        StringBuilder path = new StringBuilder();
        path.append(Directory.convertPathFromProjectFolder("Screen"));
        path.append("\\Capture Screen ");
        path.append(format.format(time));
        path.append(".jpg");
        
        File srcFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);  // Take screen shot
        FileUtils.copyFile(srcFile, new File(path.toString()));
        return path.toString();
    }

    public void quit() {
        this.driver.quit();
    }
}

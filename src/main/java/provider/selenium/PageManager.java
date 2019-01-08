/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.selenium;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author vantuyen361
 */
public class PageManager {
    WebDriver driver;
    int waitImplicitly;
    
    public PageManager(){
    }
    /**
     * initialization page manager
     * @param driver
     * @param waitImplicitly 
     */
    public void init(WebDriver driver, int waitImplicitly){
        this.driver = driver;
        this.waitImplicitly = waitImplicitly;
    }

    public void setWaitImplicitly(int waitImplicitly) {
        this.waitImplicitly = waitImplicitly;
    }
    
    /**
     * get instance page.
     * @param <T> Class must extends from {@link provider.selenium.CommonPage}
     * @param typePage type page to create
     * @return instance of page.
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public <T extends CommonPage> T fetchPage(Class<T> typePage) throws InstantiationException, IllegalAccessException{
        T page = typePage.newInstance();
        page.init(driver, waitImplicitly);
        return page;
    }
    
    /**
     * close browser
     */
    public void quit(){
        driver.quit();
    }
}

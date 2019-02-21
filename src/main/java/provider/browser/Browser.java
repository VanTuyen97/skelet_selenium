/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.browser;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import provider.selenium.Window;

/**
 *
 * @author vantuyen361
 */
public abstract class Browser {
    public static String DRIVER_WAIT_IMPLICITLY = "driver.wait.implicitly";
    public static String WAIT_IMPLICITLY = "wait.implicitly";
            

    protected Map<String, String> browserProps = new HashMap();
    protected String path;
    public String driverProperty;
    public int driverWaitImplicitly;
    public int waitImplicitly;
    
    /**
     * Set property or argument from browserProps file for browser.
     */
    public abstract void addProperties(); 
    public abstract WebDriver openWindow();
    
    /**
     * Set path of driver to system.
     */
    protected void addPathDriver(){
        if (path != null){
            System.setProperty(this.driverProperty, path);
        }
    }
    
    /**
     * build a browser.
     * @return instance of Window manager pages class.
     */
    public Window buildWindow(){
        addPathDriver();
        WebDriver driver = openWindow();
        Window manager = new Window();
        manager.init(driver, waitImplicitly);
        return manager;
    }
    
    /**
     * Add property or argument for browser.
     * @param key
     * @param value
     */
    public void addBrowserProp(String key, String value){
        browserProps.put(key, value);
    }

    public void setDriverWaitImplicitly(int driverWaitImplicitly) {
        this.driverWaitImplicitly = driverWaitImplicitly;
    }

    public void setWaitImplicitly(int waitImplicitly) {
        this.waitImplicitly = waitImplicitly;
    }

    public int getDriverWaitImplicitly() {
        return driverWaitImplicitly;
    }

    public int getWaitImplicitly() {
        return waitImplicitly;
    }
    
}

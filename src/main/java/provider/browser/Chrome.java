/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.browser;

import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.env.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;
import provider.util.Directory;

/**
 *
 * @author vantuyen361
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@org.springframework.context.annotation.PropertySource(value = "classpath:chrome.properties", ignoreResourceNotFound = true)//placeholder file browserProps
public class Chrome extends Browser {

    public Chrome() {
        driverProperty = "webdriver.chrome.driver";
    }

    @Autowired
    private Environment env; //get value from chrome.browserProps file

    @Override
    public WebDriver openWindow() {
        ChromeOptions ops = new ChromeOptions();
        browserProps.forEach((key, value) -> {
            ops.addArguments(value);
        });
         WebDriver driver = (WebDriver) new ChromeDriver(ops);
         driver.manage().timeouts().implicitlyWait(driverWaitImplicitly, TimeUnit.SECONDS);
         return driver;
    }

    @Override
    @PostConstruct
    public void addProperties() {
        Directory dir = new Directory();
        for (PropertySource propertySource : ((AbstractEnvironment) env).getPropertySources()) {
            if (propertySource.getName().contains("chrome.properties")) {
                String[] keys = ((MapPropertySource) propertySource).getPropertyNames();
                String value;

                if ((value = (String) propertySource.getProperty(WAIT_IMPLICITLY)) != null) {
                    int wait = Integer.valueOf(value);
                    this.waitImplicitly = wait;
                } else {
                    waitImplicitly = 30;
                }
                
                if ((value = (String) propertySource.getProperty(DRIVER_WAIT_IMPLICITLY)) != null) {
                    int wait = Integer.valueOf(value);
                    this.driverWaitImplicitly = wait;
                }else {
                    this.driverWaitImplicitly = 30;
                }

                if ((value = (String) propertySource.getProperty(driverProperty)) != null) {
                    this.path = dir.convertPathFromProjectFolder(value);
                }

                for (String key : keys) {
                    if ((key.length() > 7) && key.substring(0, 7).equals("option.")) {
                        this.browserProps.put(key, (String) propertySource.getProperty(key));
                    }
                }
            }
        }
    }

}

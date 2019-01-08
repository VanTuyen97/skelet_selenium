/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.browser;

import javax.annotation.PostConstruct;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;
import provider.util.Directory;

/**
 *
 * @author vantuyen361
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@org.springframework.context.annotation.PropertySource(value = "classpath:firefox.properties", ignoreResourceNotFound = true)//placeholder file browserProps
public class Firefox extends Browser {

    public Firefox() {
        driverProperty = "webdriver.gecko.driver";
    }

    @Autowired
    private Environment env; //get value from chrome.browserProps file

    @Override
    public void openBrowser() {
        FirefoxProfile profile = new FirefoxProfile();
        browserProps.forEach((key, value) -> {
            boolean flag = false;

            try {
                int valueInt = Integer.valueOf(value);
                profile.setPreference(key, valueInt);
                flag = true;
            } catch (Exception e) {
            }

            try {
                boolean valueBoolean = Boolean.valueOf(value);
                profile.setPreference(key, valueBoolean);
                flag = true;
            } catch (Exception e) {
            }

            if (flag == false) {
                profile.setPreference(key, value);
            }
        });
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setCapability("firefox_profile", profile);
        driver = new FirefoxDriver(caps);
    }

    @Override
    @PostConstruct
    public void addProperties() {
        Directory dir = new Directory();
        for (PropertySource propertySource : ((AbstractEnvironment) env).getPropertySources()) {
            if (propertySource.getName().contains("firefox.properties")) {
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
                } else {
                    this.driverWaitImplicitly = 30;
                }

                if ((value = (String) propertySource.getProperty(driverProperty)) != null) {
                    this.path = dir.convertPathFromProjectFolder(value);
                }

                for (String key : keys) {
                    this.browserProps.put(key, (String) propertySource.getProperty(key));
                }

                this.browserProps.remove(WAIT_IMPLICITLY);
                this.browserProps.remove(DRIVER_WAIT_IMPLICITLY);
                this.browserProps.remove(driverProperty);
            }
        }
    }
}

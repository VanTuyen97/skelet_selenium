/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import provider.browser.Chrome;
import provider.browser.Firefox;
import provider.selenium.Window;

/**
 *
 * @author vantuyen361
 */
@Configuration
@ComponentScan(basePackages = {"provider.browser"})
@Lazy
public class Context {
    
    @Bean(name = "windowOfChrome")
    public Window windowOfChrome(@Autowired Chrome chrome){
        return chrome.buildWindow();
    }
    
    @Bean(name = "windowOfFirefox")
    public Window windowOfFirefox(@Autowired Firefox firefox){
        return firefox.buildWindow();
    }
    
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ChromeDriver chromedriver(@Autowired Chrome chrome){
        return (ChromeDriver)chrome.openWindow();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public FirefoxDriver firefoxDriver(@Autowired Firefox firefox){
        return (FirefoxDriver)firefox.openWindow();
    }
}

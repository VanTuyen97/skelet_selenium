/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import provider.browser.Chrome;
import provider.browser.Firefox;
import provider.selenium.PageManager;

/**
 *
 * @author vantuyen361
 */
@Configuration
@ComponentScan(basePackages = {"provider.browser"})
@Lazy
public class Context {

    @Bean(name = "PageManagerOfChrome")
    public PageManager pageManager(@Autowired Chrome chrome){
        return chrome.buildPageManager();
    }

    @Bean(name = "PageManagerOfFirefox")
    public PageManager pageManager(@Autowired Firefox firefox){
        return firefox.buildPageManager();
    }
}

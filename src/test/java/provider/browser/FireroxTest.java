/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.browser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.context.junit4.SpringRunner;
import provider.Factory;

/**
 *
 * @author vantuyen361
 */
@RunWith(SpringRunner.class)
public class FireroxTest {

    @Test
    public void test() {
        FirefoxDriver firefox = Factory.buildObject(FirefoxDriver.class);
    }
    
}

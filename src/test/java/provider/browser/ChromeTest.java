/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.browser;

import org.junit.Test;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author vantuyen361
 */
public class ChromeTest {
    ApplicationContext context;
    
    @Before
    public void before() {
        context = new AnnotationConfigApplicationContext(provider.Context.class);
    }

    @Test
    public void test() {
        Chrome chrome = (Chrome)context.getBean(Chrome.class);
    }
    
}

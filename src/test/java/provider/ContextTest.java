/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import provider.selenium.PageManager;

/**
 *
 * @author vantuyen361
 */
@RunWith(SpringRunner.class)
public class ContextTest {
    ApplicationContext context;
    
    @Before
    public void before() {
        context = new AnnotationConfigApplicationContext(provider.Context.class);
    }
    
    @Test
    public void test() {
        PageManager pm = (PageManager)context.getBean("PageManagerOfFirefox");
    }
    
}

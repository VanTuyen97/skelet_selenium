/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.browser;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author vantuyen361
 */
@RunWith(SpringRunner.class)
public class FireroxTest {
    ApplicationContext context;
    
    @Before
    public void before() {
        context = new AnnotationConfigApplicationContext(provider.Context.class);
    }

    @Test
    public void test() {
        Firefox firefox = (Firefox)context.getBean(Firefox.class);
    }
    
}

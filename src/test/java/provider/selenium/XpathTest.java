/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider.selenium;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import xpath.XpathPage1;

/**
 *
 * @author vantuyen361
 */
@RunWith(SpringRunner.class)
public class XpathTest {
    @Test
    public void test() {
        Xpath xpath = XpathPage1.TUYEN_AGE;
        System.out.println(xpath.toString());
        System.out.println(xpath.nameElement());
    }
}

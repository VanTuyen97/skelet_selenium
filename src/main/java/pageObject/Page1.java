/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pageObject;

import provider.selenium.CommonPage;
import xpath.XpathPage1;

/**
 *
 * @author vantuyen361
 */
public class Page1 extends CommonPage{
    public void login(){
        this.enterText(XpathPage1.TUYEN_AGE, "helo");
    }
}

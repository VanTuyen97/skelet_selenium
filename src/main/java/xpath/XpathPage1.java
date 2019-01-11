/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xpath;

import provider.selenium.Xpath;

/**
 *
 * @author vantuyen361
 */
public enum XpathPage1 implements Xpath{
    TUYEN_AGE("97");
    
    private static String page = "Page1 - ";
    private String xpath;
    
    private XpathPage1(String xpath){
        this.xpath = xpath;
    }

    @Override
    public String toString() {
        return xpath;
    }

    @Override
    public String nameElement() {
        return page + this.name().replace("_", " ").toLowerCase();
    }
}

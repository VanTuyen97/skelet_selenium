/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author vantuyen361
 */
public class Factory {
    private static ApplicationContext context;
    static{
        context = new AnnotationConfigApplicationContext(provider.Context.class);
    }
    
    public static <T>  T buildObject(Class<T> type){
        return (T)context.getBean(type);
    }
}

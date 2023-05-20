package org.buildyourown.timispring.test;

import org.buildyourown.timispring.beans.NoSuchBeanDefinitionException;
import org.buildyourown.timispring.context.ClassPathXmlApplicationContext;

public class Test1 {
    public static void main(String[] args) throws NoSuchBeanDefinitionException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        AService aService = (AService) ctx.getBean("aservice");
        aService.sayHello();
    }
}

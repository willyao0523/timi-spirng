package org.buildyourown.timispring.beans.factory;

import org.buildyourown.timispring.beans.BeansException;

public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;
    boolean containsBean(String name);
    boolean isSingleton(String name);
    boolean isPrototype(String name);
    Class<?> getType(String name);

}

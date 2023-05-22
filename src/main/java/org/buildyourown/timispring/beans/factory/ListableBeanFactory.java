package org.buildyourown.timispring.beans.factory;

import org.buildyourown.timispring.beans.BeansException;
import org.buildyourown.timispring.beans.factory.BeanFactory;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {
    boolean containsBeanDefinition(String beanName);
    int getBeanDefinitionCount();
    String[] getBeanDefinitionNames();
    String[] getBeanNamesForType(Class<?> type);
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;
}

package org.buildyourown.timispring.beans.factory.config;

import org.buildyourown.timispring.beans.BeansException;
import org.buildyourown.timispring.beans.factory.BeanFactory;

public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

    void setBeanFactory(BeanFactory beanFactory);
}

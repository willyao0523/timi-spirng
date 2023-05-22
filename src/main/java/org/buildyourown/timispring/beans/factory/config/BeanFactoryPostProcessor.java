package org.buildyourown.timispring.beans.factory.config;

import org.buildyourown.timispring.beans.BeansException;
import org.buildyourown.timispring.beans.factory.BeanFactory;

public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;
}

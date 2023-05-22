package org.buildyourown.timispring.context;

import org.buildyourown.timispring.beans.BeansException;
import org.buildyourown.timispring.beans.factory.ListableBeanFactory;
import org.buildyourown.timispring.beans.factory.config.BeanFactoryPostProcessor;
import org.buildyourown.timispring.beans.factory.config.ConfigurableBeanFactory;
import org.buildyourown.timispring.beans.factory.config.ConfigurableListableBeanFactory;
import org.buildyourown.timispring.core.env.Environment;
import org.buildyourown.timispring.core.env.EnvironmentCapable;

public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, ConfigurableBeanFactory, ApplicationEventPublisher {
    String getApplicationName();
    long getStartupDate();
    ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;
    void setEnvironment(Environment environment);
    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor);
    void refresh() throws BeansException, IllegalStateException;
    void close();
    boolean isActive();
}

package org.buildyourown.timispring.beans.factory.config;

public interface SingleBeanRegistry {

    void registerSingleton(String beanName, Object singletionObject);

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);

    String[] getSingletonNames();
}

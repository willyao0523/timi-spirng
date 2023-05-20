package org.buildyourown.timispring.beans;

public interface BeanFactory {
    /**
     * get bean instance from bean factory
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object getBean(String beanName) throws NoSuchBeanDefinitionException;

    /**
     * register bean information
     * @param beanDefinition
     */
    void registerBeanDefinition(BeanDefinition beanDefinition);
}

package org.buildyourown.timispring.context;

import org.buildyourown.timispring.beans.*;
import org.buildyourown.timispring.beans.factory.BeanFactory;
import org.buildyourown.timispring.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.buildyourown.timispring.beans.factory.config.AutowireCapableBeanFactory;
import org.buildyourown.timispring.beans.factory.config.BeanFactoryPostProcessor;
import org.buildyourown.timispring.beans.factory.xml.XmlBeanDefinitionReader;
import org.buildyourown.timispring.core.ClassPathXmlResource;
import org.buildyourown.timispring.core.Resource;

import java.util.ArrayList;
import java.util.List;

public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {
    AutowireCapableBeanFactory beanFactory;
    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<>();

    public ClassPathXmlApplicationContext(String filename) {
        this(filename, true);
    }

    public ClassPathXmlApplicationContext(String filename, boolean isRefresh) {
        Resource resource = new ClassPathXmlResource(filename);
        AutowireCapableBeanFactory beanFactory = new AutowireCapableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;

        if (isRefresh) {
            refresh();
        }
    }

    public void refresh() {
        registerBeanPostProcessor(this.beanFactory);

        onRefresh();
    }

    private void registerBeanPostProcessor(AutowireCapableBeanFactory beanFactory) {
        beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    private void onRefresh() {
        this.beanFactory.refresh();
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    @Override
    public boolean containsBean(String name) {
        return this.beanFactory.containsBean(name);
    }

    public void registerBean(String beanName, Object obj) {
        this.beanFactory.registerBean(beanName, obj);
    }

    public void publishEvent(ApplicationEvent event) {

    }

    public boolean isSingleton(String name) {
        return false;
    }

    public boolean isPrototype(String name) {
        return false;
    }

    public Class<?> getType(String name) {
        return null;
    }
}

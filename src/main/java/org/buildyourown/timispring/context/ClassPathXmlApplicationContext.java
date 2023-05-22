package org.buildyourown.timispring.context;

import org.buildyourown.timispring.beans.*;
import org.buildyourown.timispring.beans.factory.BeanFactory;
import org.buildyourown.timispring.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.buildyourown.timispring.beans.factory.config.AutowireCapableBeanFactory;
import org.buildyourown.timispring.beans.factory.config.BeanFactoryPostProcessor;
import org.buildyourown.timispring.beans.factory.config.ConfigurableListableBeanFactory;
import org.buildyourown.timispring.beans.factory.support.DefaultListableBeanFactory;
import org.buildyourown.timispring.beans.factory.xml.XmlBeanDefinitionReader;
import org.buildyourown.timispring.core.ClassPathXmlResource;
import org.buildyourown.timispring.core.Resource;

import java.util.ArrayList;
import java.util.List;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    DefaultListableBeanFactory beanFactory;
    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<>();

    public ClassPathXmlApplicationContext(String filename) {
        this(filename, true);
    }

    public ClassPathXmlApplicationContext(String filename, boolean isRefresh) {
        Resource resource = new ClassPathXmlResource(filename);
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;

        if (isRefresh) {
            try {
                refresh();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    void registerListeners() {
        ApplicationListener listener = new ApplicationListener();
        this.getApplicationEventPublisher().addApplicationListener(listener);
    }

    @Override
    void initApplicationEventPublisher() {
        ApplicationEventPublisher aep = new SimpleApplicationEventPublisher();
        this.setApplicationEventPublisher(aep);
    }

    @Override
    void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {
    }

    @Override
    void registerBeanPostProcessors(ConfigurableListableBeanFactory bf) {
        this.beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    @Override
    void onRefresh() {
        this.beanFactory.refresh();
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.getApplicationEventPublisher().addApplicationListener(listener);

    }

    @Override
    void finishRefresh() {
        publishEvent(new ContextRefreshEvent("Context Refreshed..."));

    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.getApplicationEventPublisher().publishEvent(event);

    }
}

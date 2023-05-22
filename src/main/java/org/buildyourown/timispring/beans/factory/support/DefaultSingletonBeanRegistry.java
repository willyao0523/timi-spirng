package org.buildyourown.timispring.beans.factory.support;

import org.buildyourown.timispring.beans.factory.config.SingletonBeanRegistry;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    protected List<String> beanNames = new ArrayList<>();
    protected Map<String, Object> singletons = new ConcurrentHashMap<>(256);
    protected Map<String, Set<String>> dependentBeanMap = new ConcurrentHashMap<>(256);
    protected Map<String, Set<String>> dependenciesForBeanMap = new ConcurrentHashMap<>(256);

    @Override
    public void registerSingleton(String beanName, Object singletionObject) {
        synchronized (this.singletons) {
            Object oldObject = this.singletons.get(beanName);
            if (oldObject != null) {
                throw new IllegalStateException("Could not register object [" + singletons +
                        "] under bean name '" + beanName + "': there is already object [" + oldObject + "] bound");
            }

            this.singletons.put(beanName, singletionObject);
            this.beanNames.add(beanName);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletons.get(beanName);
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return this.singletons.containsKey(beanName);
    }

    @Override
    public String[] getSingletonNames() {
        return (String[]) this.beanNames.toArray();
    }

    protected void removeSingleton(String beanName) {
        synchronized (this.singletons) {
            this.beanNames.remove(beanName);
            this.singletons.remove(beanName);
        }
    }

    public void registerDependentBean(String beanName, String dependentBeanName) {
        Set<String> dependentBeans = this.dependentBeanMap.get(beanName);
        if (dependentBeans != null && dependentBeans.contains(dependentBeanName)) {
            return;
        }
        synchronized (this.dependentBeanMap) {
            dependentBeans = this.dependentBeanMap.get(beanName);
            if (dependentBeans == null) {
                dependentBeans = new LinkedHashSet<>(8);
                this.dependentBeanMap.put(beanName, dependentBeans);
            }
            dependentBeans.add(dependentBeanName);
        }
        synchronized (this.dependenciesForBeanMap) {
            Set<String> dependenciesForBean = this.dependenciesForBeanMap.get(beanName);
            if (dependenciesForBean == null) {
                dependenciesForBean = new LinkedHashSet<>(8);
                this.dependenciesForBeanMap.put(dependentBeanName, dependenciesForBean);
            }
            dependenciesForBean.add(beanName);
        }
    }

    public boolean hasDependentBean(String beanName) {
        return this.dependenciesForBeanMap.containsKey(beanName);
    }

    public String[] getDependentBeans(String beanName) {
        Set<String> dependentBeans = this.dependentBeanMap.get(beanName);
        if (dependentBeans == null) {
            return new String[0];
        }
        return (String[]) dependentBeans.toArray();

    }

    public String[] getDependenciesForBean(String beanName) {
        Set<String> dependenciesForBean = this.dependenciesForBeanMap.get(beanName);
        if (dependenciesForBean == null) {
            return new String[0];
        }
        return (String[]) dependenciesForBean.toArray();
    }
}

package org.buildyourown.timispring.beans.factory.xml;

import org.buildyourown.timispring.beans.*;
import org.buildyourown.timispring.beans.factory.config.AutowireCapableBeanFactory;
import org.buildyourown.timispring.beans.factory.config.BeanDefinition;
import org.buildyourown.timispring.beans.factory.config.ConstructorArgumentValue;
import org.buildyourown.timispring.beans.factory.config.ConstructorArgumentValues;
import org.buildyourown.timispring.core.Resource;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;


public class XmlBeanDefinitionReader {

    AutowireCapableBeanFactory beanFactory;

    public XmlBeanDefinitionReader(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            String initMethodName = element.attributeValue("init-method");
            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);

            List<Element> constructorElements = element.elements("constructor-arg");
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            for (Element constructorElement : constructorElements) {
                String pType = constructorElement.attributeValue("type");
                String pName = constructorElement.attributeValue("name");
                String pValue = constructorElement.attributeValue("value");
                constructorArgumentValues.addArgumentValue(new ConstructorArgumentValue(pType, pName, pValue));
            }
            beanDefinition.setConstructorArgumentValues(constructorArgumentValues);

            List<Element> propertyElements = element.elements("property");
            PropertyValues propertyValues = new PropertyValues();
            List<String> refs = new ArrayList<>();
            for (Element propertyElement : propertyElements) {
                String pType = propertyElement.attributeValue("type");
                String pName = propertyElement.attributeValue("name");
                String pValue = propertyElement.attributeValue("value");
                String pRef = propertyElement.attributeValue("ref");
                String pV = "";
                boolean isRef = false;
                if (pValue != null && !pValue.equals("")) {
                    isRef = false;
                    pV = pValue;
                } else if (pRef != null && !pRef.equals("")) {
                    isRef = true;
                    pV = pRef;
                    refs.add(pRef);
                }
                propertyValues.addPropertyValue(new PropertyValue(pType, pName, pV, isRef));
            }
            beanDefinition.setPropertyValues(propertyValues);

            String[] refArray = refs.toArray(new String[0]);
            beanDefinition.setDependsOn(refArray);

            beanDefinition.setInitMethodName(initMethodName);
            this.beanFactory.registerBeanDefinition(beanId, beanDefinition);
        }
    }
}

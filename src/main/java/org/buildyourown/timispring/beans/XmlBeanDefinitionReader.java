package org.buildyourown.timispring.beans;

import org.buildyourown.timispring.core.Resource;
import org.dom4j.Element;

import java.util.List;


public class XmlBeanDefinitionReader {

    SimpleBeanFactory beanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.beanFactory = simpleBeanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");

            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);

            List<Element> propertyElements = element.elements("property");
            PropertyValues propertyValues = new PropertyValues();
            for (Element propertyElement : propertyElements) {
                String pType = propertyElement.attributeValue("type");
                String pName = propertyElement.attributeValue("name");
                String pValue = propertyElement.attributeValue("value");
                propertyValues.addPropertyValue(new PropertyValue(pType, pName, pValue));
            }
            beanDefinition.setPropertyValues(propertyValues);

            List<Element> constructorElements = element.elements("constructor-arg");
            ArgumentValues argumentValues = new ArgumentValues();
            for (Element constructorElement : constructorElements) {
                String pType = constructorElement.attributeValue("type");
                String pName = constructorElement.attributeValue("name");
                String pValue = constructorElement.attributeValue("value");
                argumentValues.addArgumentValue(new ArgumentValue(pType, pName, pValue));
            }
            beanDefinition.setConstructorArgumentValues(argumentValues);

            this.beanFactory.registerBeanDefinition(beanId, beanDefinition);
        }
    }
}

package org.buildyourown.timispring.context;

import org.buildyourown.timispring.beans.BeanDefinition;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.swing.event.DocumentEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPathXmlApplicationContext {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private Map<String, Object> singletons = new HashMap<>();

    public ClassPathXmlApplicationContext(String filename) {
        this.readXml(filename);
        this.instanceBeans();
    }

    private void readXml(String filename) {
        SAXReader saxReader = new SAXReader();
        try {
            URL xmlPath = this.getClass().getClassLoader().getResource(filename);
            Document document = saxReader.read(xmlPath);
            Element rootElement = document.getRootElement();
            for (Element element : (List<Element>) rootElement.elements()) {
                String beanId = element.attributeValue("id");
                String beanClassName = element.attributeValue("class");
                BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);
                beanDefinitions.add(beanDefinition);
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    private void instanceBeans() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                singletons.put(
                        beanDefinition.getId(),
                        Class.forName(beanDefinition.getClassName()).newInstance()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Object getBean(String beanName) {
        return singletons.get(beanName);
    }
}

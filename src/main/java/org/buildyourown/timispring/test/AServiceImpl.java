package org.buildyourown.timispring.test;

public class AServiceImpl implements AService {

    private String name;
    private int level;
    private String property1;
    private String property2;

    public AServiceImpl(String name, int level) {
        this.name = name;
        this.level = level;
        System.out.println("Name: " + this.name + " Level: " + this.level);
    }

    public AServiceImpl() {
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    @Override
    public void sayHello() {
        System.out.println("Property1: " + this.property1 + " Property2: " + this.property2);
    }
}

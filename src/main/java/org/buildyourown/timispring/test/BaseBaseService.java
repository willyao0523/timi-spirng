package org.buildyourown.timispring.test;

public class BaseBaseService {
    private AServiceImpl as;

    public BaseBaseService() {
    }

    public AServiceImpl getAs() {
        return as;
    }

    public void setAs(AServiceImpl as) {
        this.as = as;
    }

    public void sayHello() {
        System.out.println("Base Base Service says hello");
    }
}

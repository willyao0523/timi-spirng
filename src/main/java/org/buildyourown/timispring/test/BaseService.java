package org.buildyourown.timispring.test;

import org.buildyourown.timispring.beans.factory.annotation.Autowired;

public class BaseService {
    @Autowired
    private BaseBaseService bbs;

    public BaseService() {
    }

    public BaseBaseService getBbs() {
        return bbs;
    }

    public void setBbs(BaseBaseService bbs) {
        this.bbs = bbs;
    }

    public void sayHello() {
        System.out.print("Base Service says hello");
        bbs.sayHello();
    }

    public void init() {
        System.out.print("Base Service init method.");
    }
}

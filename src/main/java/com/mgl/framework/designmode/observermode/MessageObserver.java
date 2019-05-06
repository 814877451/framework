package com.mgl.framework.designmode.observermode;

public class MessageObserver implements Observer {

    private String name;

    MessageObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + ":::" + message);
    }
}

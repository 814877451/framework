package com.mgl.framework.designmode.observermode;

public class Demo {

    public static void main(String[] args) {
        Observer observerA = new MessageObserver("观察者A");
        Observer observerB = new MessageObserver("观察者B");
        Observer observerC = new MessageObserver("观察者C");
        Observer observerD = new MessageObserver("观察者D");

        MessageSubject subject = new MessageSubject();
        //订阅
        subject.addObserver(observerA);
        subject.addObserver(observerB);
        subject.addObserver(observerC);
        subject.addObserver(observerD);

        //发版
        subject.setMessage("hello world!");
        System.out.println("--------------------------------------");
        subject.removeObserver(observerA);
        subject.setMessage("你好世界!");
        System.out.println("--------------------------------------");
        subject.addObserver(observerA);
        subject.setMessage("欢迎来到中国!");
    }
}

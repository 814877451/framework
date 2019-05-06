package com.mgl.framework.designmode.observermode;

import org.assertj.core.util.Lists;

import java.util.List;

/**
 *@Description: 被观察者接口
 *
 *@Author: magla
 *@Date: 2019/4/4_16:20
 *@since: v1.0.0
 */
public interface Subject {

    List<Observer> observers = Lists.newArrayList();

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver();
}

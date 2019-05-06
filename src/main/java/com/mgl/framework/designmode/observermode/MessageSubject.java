package com.mgl.framework.designmode.observermode;

public class MessageSubject implements Subject {

    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyObserver();
    }

    @Override
    public void addObserver(Observer observer) {
        if (null != observer) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (null != observer) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        for(Observer observer : observers) {
            observer.update(message);
        }
    }
}

package com.mgl.framework.utils;

import java.util.Optional;

public class Log {
    private StringBuilder message;
    private Log(String op) {
        message = new StringBuilder();
        message.append("[").append("函数名称").append(":::").append(op).append("]");
    }

    public static Log op(String op) {
        return new Log(op);
    }

    public Log msg(String msg) {
        message.append("[").append("提示消息").append(":::").append(msg).append("]");
        return this;
    }

    public Log kv(String key, Object value) {
        message.append("[").append(key).append(":::").append(value).append("]");
        return this;
    }

    public String toString() {
        return this.message.toString();
    }

    public static void main(String[] args) {
        System.out.println("-------------------");
        System.out.println(Log.op("demo").msg("测试").kv("userId", "123456").kv("wod", "eew").toString());
        System.out.println(Log.op("demo").msg("测试").kv("userId", "123456").kv("wod", "eew").toString());
    }
}

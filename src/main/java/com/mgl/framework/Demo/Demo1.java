package com.mgl.framework.Demo;

import com.mgl.framework.po.OpRequest;

public class Demo1 {

    public static void main(String[] args) {
        OpRequest opRequest = new OpRequest();
        opRequest.setSign("从我开始");
        System.out.println(opRequest.getSign());
        changevalue(opRequest);
        System.out.println(opRequest.getSign());

        System.out.println("======================");
        OpRequest opRequest1 = new OpRequest();
        opRequest1.setSign("我是1号");
        System.out.println(opRequest1.getSign());
        changeObject(opRequest1);
        System.out.println(opRequest1.getSign());
    }

    private static OpRequest changevalue(OpRequest opRequest) {
        opRequest.setSign("我改变了");
        System.out.println(opRequest.getSign());
        return  opRequest;
    }

    private static OpRequest changeObject(OpRequest opRequest) {
        opRequest = new OpRequest();
        opRequest.setSign("我是新对象");
        System.out.println(opRequest.getSign());
        return  opRequest;
    }
}

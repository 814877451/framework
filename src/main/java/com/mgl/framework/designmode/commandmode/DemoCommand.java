package com.mgl.framework.designmode.commandmode;

public class DemoCommand implements Command {

    private Receiver receiver;

    DemoCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {

    }
}

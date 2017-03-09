package com.server.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StopTimerTask extends AbstractTask {

    @Autowired
    private TestTimerTask testTimerTask;

    @Override
    public Object run() {
        testTimerTask.setStart(false);
        return "Wyłączono timer";
    }
}

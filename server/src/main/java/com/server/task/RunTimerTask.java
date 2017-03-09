package com.server.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RunTimerTask extends AbstractTask {

    @Autowired
    private TestTimerTask testTimerTask;

    @Override
    public Object run() {
        testTimerTask.setStart(true);
        return "Włączono timer";
    }
}

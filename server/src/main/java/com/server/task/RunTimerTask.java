package com.server.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class RunTimerTask extends AbstractTask {

    @Autowired
    private TestTimerTask testTimerTask;

    @Override
    @RequestMapping("/start")
    public Object run() {
        testTimerTask.setStart(true);
        return "Włączono timer";
    }
}

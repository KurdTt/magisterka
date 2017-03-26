package com.server.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class StopTimerTask extends AbstractTask {

    @Autowired
    private TestTimerTask testTimerTask;

    @Override
    @RequestMapping("/stop")
    public Object run() {
        testTimerTask.setStart(false);
        return "Wyłączono timer";
    }
}

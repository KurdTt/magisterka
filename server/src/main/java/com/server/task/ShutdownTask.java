package com.server.task;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Przemek on 2016-12-14.
 */
@Component
@RestController
public class ShutdownTask extends AbstractTask {
    @RequestMapping("/shutdown")
    @Override
    public Object run() {
        System.exit(0);
        return "Spark com.server stopped";
    }
}

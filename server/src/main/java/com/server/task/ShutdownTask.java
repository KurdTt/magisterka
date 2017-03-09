package com.server.task;

import org.springframework.stereotype.Component;

/**
 * Created by Przemek on 2016-12-14.
 */
@Component
public class ShutdownTask extends AbstractTask {
    @Override
    public Object run() {
        System.exit(0);
        return "Spark com.server stopped";
    }
}

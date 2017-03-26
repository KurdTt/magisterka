package com.server.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

/**
 * Created by Przemysław Książek
 * on 2016-12-14.
 */

@ImportResource("applicationContext.xml")
@SpringBootApplication
public class ServerMainClass implements CommandLineRunner {

    private static final Logger LOGGER = Logger.getLogger(ServerMainClass.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(ServerMainClass.class, args);
    }

    @Value("${server.port}")
    private int port;

    @Override
    public void run(String... args) {
        init();
    }

    private void init() {
        showInfo();
    }


    private void showInfo() {
        try {
            LOGGER.info(String.format("IP: %s:%d", InetAddress.getLocalHost().getHostAddress(), port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

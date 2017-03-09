package com.server.main;

import com.server.task.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import spark.Spark;

import static com.server.utils.TaskUtils.doGet;
import static com.server.utils.TaskUtils.doGetDownloadFile;

/**
 * Created by Przemek on 2016-12-14.
 */

@ImportResource("applicationContext.xml")
@SpringBootApplication
public class SparkServer implements CommandLineRunner {

    @Autowired
    private DeleteAllDataTask deleteAllDataTask;
    @Autowired
    private ShowDataTask showDataTask;
    @Autowired
    private InsertTestDataTask insertTestDataTask;
    @Autowired
    private ShutdownTask shutdownTask;
    @Autowired
    private RunTimerTask runTimerTask;
    @Autowired
    private StopTimerTask stopTimerTask;
    @Autowired
    private SendDataTask sendDataTask;

    @Value("${spark.port}")
    private int sparkPort;

    private static final String TEST_FILE_PATH_PDF = "C:\\Users\\Przemek\\Downloads\\400.pdf";
    private static final String TEST_FILE_PATH_PNG = "C:\\Users\\Przemek\\Downloads\\tabela.png";

    public static void main(String[] args) {
        SpringApplication.run(SparkServer.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        init();
    }

    private void init(){
        Spark.port(sparkPort);
        setTasks();
    }

    private void setTasks(){
        //Tasks by GET
        doGetDownloadFile("/download_pdf", TEST_FILE_PATH_PDF);
        doGetDownloadFile("/download_png", TEST_FILE_PATH_PNG);
        doGet("/shutdown", shutdownTask);
        doGet("/insert", insertTestDataTask);
        doGet("/delete", deleteAllDataTask);
        doGet("/send", sendDataTask);
        doGet("/show", showDataTask);
        doGet("/run", runTimerTask);
        doGet("/stop", stopTimerTask);
    }
}

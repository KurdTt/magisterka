package com.server.utils;

import com.server.task.AbstractTask;
import com.server.task.DownloadTask;
import spark.Spark;

/**
 * Created by Przemek on 2016-12-15.
 */
public class TaskUtils {
    public static void doGetDownloadFile(String path, String filePath){
        Spark.get(path, (req, res) -> new DownloadTask(res, filePath).run());
    }

    public static void doGet(String path, AbstractTask task){
        Spark.get(path, (req, res) -> task.run());
    }
}

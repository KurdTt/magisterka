package com.server.task;

import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Przemek on 2016-12-14.
 */
public class DownloadTask extends AbstractTask {

    private static final String MIME_DOWNLOAD = "application/x-msdownload";
    private static final String HEADER_NAME = "Content-disposition";
    private static final String HEADER_VALUE = "attachment; filename=%s";

    private Response response;
    private String filePath;

    private String getFileExtension(String fileName){
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i >= 0) {
            extension = fileName.substring(i+1);
        }
        return extension.toLowerCase();
    }

    public DownloadTask(Response response, String filePath) {
        this.response = response;
        this.filePath = filePath;
    }

    @Override
    public Object run() {
        Path path = Paths.get(filePath);

        if(!Files.exists(path)){
            return String.format("File: %s not found on com.server.", filePath);
        } else {
            try {
                byte[] bytes = Files.readAllBytes(path);
                HttpServletResponse raw = response.raw();
                //Set content
                raw.setContentType(MIME_DOWNLOAD);
                raw.setHeader(HEADER_NAME, String.format(HEADER_VALUE, path.getFileName()));
                //Write bytes to stream with flush
                raw.getOutputStream().write(bytes);
                raw.getOutputStream().flush();
                raw.getOutputStream().close();
                return response.raw();
            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }
}

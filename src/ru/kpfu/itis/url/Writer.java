package ru.kpfu.itis.url;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Ilnur
 * Date: 11/9/13
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */
class Writer {
    private File file =null;

    public Writer(String path) {
        file = new File(path);
        /*if(!file.exists()){
            System.out.print(path + " is not create.");
        }*/
    }

    public void write(String text){
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

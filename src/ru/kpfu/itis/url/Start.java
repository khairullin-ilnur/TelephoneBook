package ru.kpfu.itis.url;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Ilnur
 * Date: 11/9/13
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Start {
    public static void main(String[] args) {
        Thread thread;
        URL url;
        for (int i = 0; i < args.length - 1 && args.length > 1; i++) {
            try {
                url = new URL(args[i]);
                File file = new File(args[args.length - 1] + "/" + url.getHost().toString() + "/" + url.getPath().toString());
                //crePath(file);
                file.getParentFile().mkdirs();
                file.mkdir();
                thread = new Thread(new Dowlander(url, file.getPath() + "//" + i + ".txt"));
                thread.start();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void crePath(File file){
         if(!file.exists()){
             if(!file.getParentFile().exists()){
                 crePath(file.getParentFile());
             }
             file.mkdir();
         }
    }
    //1 юрл wget в теме письма номер группа.
}

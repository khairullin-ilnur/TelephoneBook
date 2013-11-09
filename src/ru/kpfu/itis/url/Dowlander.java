package ru.kpfu.itis.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Ilnur
 * Date: 11/9/13
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
class Dowlander implements Runnable {
    private URL url = null;
    private Writer writer = null;
    private String text = "";

    public Dowlander(URL url, String path) {
        this.url = url;
        this.writer = new Writer(path);
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String row = null;
            while((row = bufferedReader.readLine())!=null){
                 text += row;
            }
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

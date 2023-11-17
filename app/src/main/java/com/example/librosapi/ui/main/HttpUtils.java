package com.example.librosapi.ui.main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    public static String get(String dataURL) throws IOException {
        URL url = new URL(dataURL);
        String response = null;

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            response = readStream(in);

        }finally {
            urlConnection.disconnect();
        }
        return response;
    }

    public static String readStream(InputStream in) throws IOException{

        InputStreamReader is = new InputStreamReader(in);
        BufferedReader breader = new BufferedReader(is);
        String line;
        StringBuilder response = new StringBuilder();
        while((line = breader.readLine()) !=null){
            response.append(line);
            response.append('\r');
        }
        breader.close();
        return response.toString();

    }

}

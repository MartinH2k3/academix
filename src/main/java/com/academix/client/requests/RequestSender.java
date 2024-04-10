package com.academix.client.requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestSender {
    private static final RequestSender instance = new RequestSender();

    private String domain;
    private RequestSender() {
        domain ="http://localhost:8080/test";
    }

    public static RequestSender getInstance() {
        if (instance == null) {
            return new RequestSender();
        }
        return instance;
    }

    public String sendRequest(String parameters) throws IOException {
        URL url = new URL(domain);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();
        return content.toString();
    }
}

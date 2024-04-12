package com.academix.client.requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class RequestSender {
    private static final RequestSender instance = null;

    private String domain;

    private RequestSender() {
        domain ="http://localhost:8080";
    }

    public static RequestSender getInstance() {
        if (instance == null) {
            return new RequestSender();
        }
        return instance;
    }


    private String getResponse(HttpURLConnection conn) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }

    public String sendRequest(String request, String requestMethod) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(domain + request);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(requestMethod.toUpperCase(Locale.ROOT));
            return getResponse(conn);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error sending request";
    }

}
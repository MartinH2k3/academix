package com.academix.client.requests;

import server.logging.Logging;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class RequestSender {
    private static RequestSender instance = null;

    private String domain;

    private RequestSender() {
        domain ="http://localhost:8080";
    }

    public static RequestSender getInstance() {
        if (instance == null) {
            instance = new RequestSender();
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
            Logging.getInstance().logException(e,"Chyba pri parsovaní URL.");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Pri posielaní requestu došlo k chybe.");
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return "Error sending request";
    }

    public String sendRequest(String request, String body, String requestMethod){
        HttpURLConnection conn = null;
        try {
            URL url = new URL(domain + request);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(requestMethod.toUpperCase(Locale.ROOT));
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream(); OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8")) {
                osw.write(body); // Write the body of the request to the output stream
                osw.flush(); // Ensure all data is sent
            }
            return getResponse(conn);
        } catch (MalformedURLException e) {
            Logging.getInstance().logException(e,"Chyba pri parsovaní URL.");
        } catch (IOException e) {
            Logging.getInstance().logException(e, "Pri posielaní requestu došlo k chybe.");
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return "Error sending request";
    }
}

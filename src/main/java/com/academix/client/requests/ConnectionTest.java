package com.academix.client.requests;

public class ConnectionTest {
    public static void main(String[] args) {
        RequestSender requestSender = RequestSender.getInstance();
        //requestSender.sendRequest("/register?username=username&password=password&type=student", "POST);
        System.out.println(requestSender.sendRequest("/login?username=username&password=password", "POST"));
    }
}

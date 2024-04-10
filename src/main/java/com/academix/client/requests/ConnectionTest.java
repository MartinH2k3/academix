package com.academix.client.requests;

public class ConnectionTest {
    public static void main(String[] args) {
        RequestSender requestSender = RequestSender.getInstance();
        requestSender.sendRequest("/register?name=name&password=password&type=student", RequestSender.RequestMethod.POST);
    }
}

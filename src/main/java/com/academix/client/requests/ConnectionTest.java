package com.academix.client.requests;

public class ConnectionTest {
    public static void main(String[] args) {
        RequestSender requestSender = RequestSender.getInstance();
        // requestSender.sendRequest("/register?username=karol&password=password&type=student", "POST");
        // System.out.println(requestSender.sendRequest("/login?username=karol&password=password", "POST"));
        // /account/update/{username}?x=y... would be more fitting, but without using Spring (or something similar), this is more scalable
        requestSender.sendRequest("/account/update?username=karol&password=password&email=bob@bob.com&first_name=Karol&last_name=Kowalski&phone_number=123456789", "POST");
    }
}

package com.academix.client.requests;

public class ConnectionTest {
    public static void main(String[] args) {
        RequestSender requestSender = RequestSender.getInstance();
        // register
        // requestSender.sendRequest("/register?username=karolko&password=password&type=facultyRepresentative", "POST");

        // login
        // System.out.println(requestSender.sendRequest("/login?username=karolko&password=password", "POST"));

        // update account info /account/update/{username}?x=y... would be more fitting, but without using Spring (or something similar), this is more scalable
        // requestSender.sendRequest("/account/update?username=karol&password=password&email=bob@bob.com&first_name=Karol&last_name=Kowalski&phone_number=123456789", "POST");

        // reset password
        // System.out.println(requestSender.sendRequest("/account/reset_password?username=karolko&old_password=pass&new_password=password", "POST"));
    }
}

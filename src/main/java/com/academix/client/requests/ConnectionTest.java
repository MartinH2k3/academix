package com.academix.client.requests;

import common.Base64EncoderDecoder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConnectionTest {
    public static void main(String[] args) {
        RequestSender requestSender = RequestSender.getInstance();
        // register faculty representative
        // System.out.println(requestSender.sendRequest("/register?username=karol1&password=password&type=faculty_representative", "POST"));

        // register student
        // requestSender.sendRequest("/register?username=karol2&password=password&type=student", "POST");

        // login
        // System.out.println(requestSender.sendRequest("/login?username=karol1&password=password", "POST"));

        // update account info /account/update/{username}?x=y... would be more fitting, but without using Spring (or something similar), this is more scalable
        // requestSender.sendRequest("/account/update?username=karol1&password=password&email=bob@bob.com&first_name=Karol&last_name=Kowalski&phone_number=123456789", "POST");

        // reset password
        // System.out.println(requestSender.sendRequest("/account/reset_password?username=karolko&old_password=pass&new_password=password", "POST"));

        // submit question
        // System.out.println(requestSender.sendRequest("/submit_question?username=karolko&question="+ Base64EncoderDecoder.encode("What & Why Jesus Chris?:\n?"), "POST"));

        // answer question
        // System.out.println(requestSender.sendRequest("/answer_question?question_id=9&answer="+ Base64EncoderDecoder.encode("Because he is the son of God"), "POST"));

        // approve faculty representative
        // System.out.println(requestSender.sendRequest("/answer_request?request_id=1&decision=accepted", "POST"));
    }
}

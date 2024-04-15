package com.academix.client.requests;

public class RequesterAdmin {
    private RequestSender requestSender;

    RequesterAdmin() {
        requestSender = RequestSender.getInstance();
    }


}

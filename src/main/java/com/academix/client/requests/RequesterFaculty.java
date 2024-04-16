package com.academix.client.requests;

public class RequesterFaculty {
    private RequesterFaculty requesterFaculty = null;
    private RequestSender requestSender;

    private RequesterFaculty() {
        requestSender = RequestSender.getInstance();
    }

    public RequesterFaculty getInstance() {
        if (requesterFaculty == null) {
            requesterFaculty = new RequesterFaculty();
        }
        return requesterFaculty;
    }


}

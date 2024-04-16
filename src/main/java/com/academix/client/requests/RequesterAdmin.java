package com.academix.client.requests;

public class RequesterAdmin {
    private RequesterAdmin requesterAdmin = null;
    private RequestSender requestSender;

    private RequesterAdmin() {
        requestSender = RequestSender.getInstance();
    }

    public RequesterAdmin getInstance() {
        if (requesterAdmin == null) {
            requesterAdmin = new RequesterAdmin();
        }
        return requesterAdmin;
    }

    private String evaluateRequest(Long requestId, String decision) {
        return requestSender.sendRequest("/answer_request?" + "request_id=" + requestId + "&decision=" + decision, "POST");
    }

    public void acceptRequest(Long requestId) {
        evaluateRequest(requestId, "accepted");
    }

    public void rejectRequest(Long requestId) {
        evaluateRequest(requestId, "rejected");
    }

}

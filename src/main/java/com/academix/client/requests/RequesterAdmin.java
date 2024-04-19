package com.academix.client.requests;

import com.google.gson.Gson;
import common.Base64EncoderDecoder;

import java.util.Map;

public class RequesterAdmin {
    private static RequesterAdmin requesterAdmin = null;
    private final RequestSender requestSender;

    private RequesterAdmin() {
        requestSender = RequestSender.getInstance();
    }

    public static RequesterAdmin getInstance() {
        if (requesterAdmin == null) {
            requesterAdmin = new RequesterAdmin();
        }
        return requesterAdmin;
    }

    /**
     * Sends a request for all pending questions, that haven't been answered by an admin yet
     * @return a map of pending questions
     */
    public Map<Long, String> getPendingQuestions() {
        String response =  requestSender.sendRequest("/pending_questions", "GET");
        String json = Base64EncoderDecoder.decode(response);
        Gson gson = new Gson();
        return gson.fromJson(json, Map.class);
    }

    /**
     * Evaluates a request, either accepting or rejecting it
     * @param requestId the id of the request
     * @param decision the decision to be made
     * @return the response from the server
     */
    private String evaluateRequest(Long requestId, String decision) {
        return requestSender.sendRequest("/answer_request?" + "request_id=" + requestId + "&decision=" + decision, "POST");
    }

    public String acceptRequest(Long requestId) {
        return evaluateRequest(requestId, "accepted");
    }

    public String rejectRequest(Long requestId) {
        return evaluateRequest(requestId, "rejected");
    }

    public String answerQuestion(Long questionId, String answer) {
        return requestSender.sendRequest("/answer_question?question_id=" + questionId, answer, "POST");
    }

}

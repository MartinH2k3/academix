package server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.Base64EncoderDecoder;
import server.database.support.Helpline;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class PendingQuestionsHandler implements HttpHandler {
    private static PendingQuestionsHandler instance = null;

    private PendingQuestionsHandler() {
    }

    public static PendingQuestionsHandler getInstance() {
        if (instance == null) {
            instance = new PendingQuestionsHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            Map<Long, String> questions = Helpline.getQuestions();
            Gson gson = new Gson();
            response = gson.toJson(questions);
            response = Base64EncoderDecoder.encode(response);
        } else {
            response = "Wrong request method";
            // TODO log here
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

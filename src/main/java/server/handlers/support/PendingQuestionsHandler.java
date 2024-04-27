package server.handlers.support;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.dto.QuestionDTO;
import server.database.support.Helpline;
import server.logging.Logging;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
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
            Map<Long, QuestionDTO> questions = Helpline.getQuestions();
            Gson gson = new Gson();
            response = gson.toJson(questions);
        } else {
            response = "Wrong request method";
            Logging.getInstance().logServerWarning(response);
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

package server.handlers.support;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.dto.QuestionDTO;
import server.database.support.Helpline;
import server.handlers.util.HttpStreamManager;
import server.handlers.util.ParamParser;
import server.logging.Logging;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class HelplineQuestionHandler implements HttpHandler {
    private static HelplineQuestionHandler instance = null;

    private HelplineQuestionHandler() {
    }

    public static HelplineQuestionHandler getInstance() {
        if (instance == null) {
            instance = new HelplineQuestionHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            String json = HttpStreamManager.readRequestBody(exchange);
            Gson gson = new Gson();
            QuestionDTO dto = gson.fromJson(json, QuestionDTO.class);
            response = Helpline.submitQuestion(dto.username, dto.subject, dto.question);
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

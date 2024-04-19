package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.handlers.util.HttpStreamManager;
import server.handlers.util.ParamParser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import server.database.support.Helpline;

public class HelplineAnswerHandler implements HttpHandler {
    private static HelplineAnswerHandler instance;

    private HelplineAnswerHandler() {
    }

    public static HelplineAnswerHandler getInstance() {
        if (instance == null) {
            instance = new HelplineAnswerHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            String answer = HttpStreamManager.readRequestBody(exchange);
            response = Helpline.answerQuestion(Long.parseLong(params.get("question_id")), answer);
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

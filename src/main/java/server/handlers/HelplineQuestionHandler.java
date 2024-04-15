package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.Base64EncoderDecoder;
import server.database.support.Helpline;
import server.handlers.util.ParamParser;

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
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            String question = Base64EncoderDecoder.decode(params.get("question"));
            response = Helpline.submitQuestion(params.get("username"), question);
        } else {
            response = "Wrong request method";
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

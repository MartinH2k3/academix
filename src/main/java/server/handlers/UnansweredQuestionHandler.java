package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class UnansweredQuestionHandler implements HttpHandler {
    private static UnansweredQuestionHandler instance = null;

    private UnansweredQuestionHandler() {
    }

    public static UnansweredQuestionHandler getInstance() {
        if (instance == null) {
            instance = new UnansweredQuestionHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            response = "bob";
        } else {
            response = "Wrong request method";
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.database.support.Requests;
import server.handlers.util.ParamParser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class AcceptRejectHandler implements HttpHandler {
    private static AcceptRejectHandler instance = null;

    private AcceptRejectHandler() {
    }

    public static AcceptRejectHandler getInstance() {
        if (instance == null) {
            instance = new AcceptRejectHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            response = Requests.answerRequest(Long.parseLong(params.get("request_id")), params.get("decision").equalsIgnoreCase("accepted"));
        } else {
            response = "Wrong request method";
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

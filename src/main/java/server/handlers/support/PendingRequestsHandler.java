package server.handlers.support;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.database.support.Requests;
import server.logging.Logging;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class PendingRequestsHandler implements HttpHandler {
    private static PendingRequestsHandler instance = null;

    private PendingRequestsHandler() {
    }

    public static PendingRequestsHandler getInstance() {
        if (instance == null) {
            instance = new PendingRequestsHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            Map<Long, String> requests = Requests.getRequests();
            Gson gson = new Gson();
            response = gson.toJson(requests);
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

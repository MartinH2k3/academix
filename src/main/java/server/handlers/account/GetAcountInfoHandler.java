package server.handlers.account;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.database.user.AccountInfoUpdate;
import server.handlers.util.ParamParser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class GetAcountInfoHandler implements HttpHandler {
    private static GetAcountInfoHandler instance = null;

    private GetAcountInfoHandler() {
    }

    public static GetAcountInfoHandler getInstance() {
        if (instance == null) {
            instance = new GetAcountInfoHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        String username;
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            username = params.get("username");
            response = AccountInfoUpdate.getAccountInfo(username);
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } else {
            response = "Invalid request method";
            exchange.sendResponseHeaders(405, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}

package server.handlers.account;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.handlers.util.ParamParser;
import server.logging.Logging;
import server.database.user.Auth;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;


public class AccountDeletionHandler implements HttpHandler {
    private static AccountDeletionHandler instance;

    private AccountDeletionHandler() {
    }

    public static AccountDeletionHandler getInstance() {
        if (instance == null) {
            instance = new AccountDeletionHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            String username = params.get("username");
            response = Auth.deleteAccount(username);
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

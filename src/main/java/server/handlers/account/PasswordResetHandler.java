package server.handlers.account;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.database.user.Auth;
import server.handlers.util.ParamParser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class PasswordResetHandler implements HttpHandler {
    private static PasswordResetHandler instance = null;

    private PasswordResetHandler() {
    }

    public static PasswordResetHandler getInstance() {
        if (instance == null) {
            instance = new PasswordResetHandler();
        }
        return instance;
    }

    /**
     * Handle the password reset request. Parse the parameters and send it to service that connects to database and does its thing.
     * @param exchange HttpExchange (username, old_password, new_password)
     * @return String
     * @throws IOException
     */
    public void handle(HttpExchange exchange) throws IOException {
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            response = Auth.resetPassword(params.get("username"), params.get("old_password"), params.get("new_password"));
        } else {
            response = "Wrong request method";
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.database.Auth;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;

public class LoginHandler implements HttpHandler {
    public static final LoginHandler instance = null;

    private LoginHandler() {
    }

    public static LoginHandler getInstance() {
        if (instance == null) {
            return new LoginHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            try {
                Auth.login(params.get("username"), params.get("password"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

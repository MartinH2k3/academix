package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.database.Auth;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class RegisterHandler implements HttpHandler {
    public static final RegisterHandler instance = null;
    /**
     * RegisterHandler constructor
     */
    private RegisterHandler() {
    }
    /**
     * Get instance of RegisterHandler
     * @return RegisterHandler instance
     */
    public static RegisterHandler getInstance() {
        if (instance == null) {
            return new RegisterHandler();
        }
        return instance;
    }

    /**
     * Handle the registration request
     * @param exchange HttpExchange (username, password, type)
     * @return String
     * @throws IOException
     * @throws SQLException
     */
    public void handle(HttpExchange exchange) throws IOException{
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            Auth.register(params.get("username"), params.get("password"), params.get("type"));
        }
    }
}

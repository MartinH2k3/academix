package server.handlers.account;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.dto.LoginCredentialsDTO;
import server.database.user.Auth;
import server.handlers.util.HttpStreamManager;
import server.handlers.util.ParamParser;
import server.logging.Logging;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;

public class RegisterHandler implements HttpHandler {
    public static RegisterHandler instance = null;
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
            instance = new RegisterHandler();
        }
        return instance;
    }

    /**
     * Handle the registration request
     * @param exchange HttpExchange (username, password, type)
     * @return String response
     * @throws IOException
     * @throws SQLException if there is a problem with a query
     */
    public void handle(HttpExchange exchange) throws IOException{
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            String json = HttpStreamManager.readRequestBody(exchange);
            Gson gson = new Gson();
            LoginCredentialsDTO dto = gson.fromJson(json, LoginCredentialsDTO.class);
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            response = Auth.register(dto.username, dto.password, params.get("type"));
        }
        else {
            response = "Wrong request method";
            Logging.getInstance().logServerWarning(response);
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

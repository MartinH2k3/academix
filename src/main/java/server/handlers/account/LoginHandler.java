package server.handlers.account;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.dto.LoginCredentialsDTO;
import server.database.user.Auth;
import server.handlers.util.HttpStreamManager;
import server.logging.Logging;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class LoginHandler implements HttpHandler {
    public static LoginHandler instance = null;

    private LoginHandler() {
    }

    /**
     * Used for creating a singleton instance of LoginHandler
     * @return LoginHandler instance
     */
    public static LoginHandler getInstance() {
        if (instance == null) {
            instance = new LoginHandler();
        }
        return instance;
    }


    /**
     * Handle the login request. If parameters are correct, user is logged in.
     * @param exchange HttpExchange (username, password)
     * @return String user type
     * @throws IOException
     */
    public void handle(HttpExchange exchange) throws IOException{
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            String json = HttpStreamManager.readRequestBody(exchange);
            Gson gson = new Gson();
            LoginCredentialsDTO dto = gson.fromJson(json, LoginCredentialsDTO.class);
            try {
                response = Auth.login(dto.username, dto.password);
            } catch (SQLException e) {
                Logging.getInstance().logException(e, "SQL Exception");
                throw new RuntimeException(e);
            }
        }
        else {
            response = "Wrong request method";
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

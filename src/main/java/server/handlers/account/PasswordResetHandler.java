package server.handlers.account;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.dto.ChangePasswordDTO;
import server.database.user.Auth;
import server.handlers.util.HttpStreamManager;
import server.logging.Logging;

import java.io.IOException;
import java.io.OutputStream;

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
            String json = HttpStreamManager.readRequestBody(exchange);
            Gson gson = new Gson();
            ChangePasswordDTO dto = gson.fromJson(json, ChangePasswordDTO.class);
            response = Auth.resetPassword(dto.username, dto.oldPassword, dto.newPassword);
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

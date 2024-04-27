package server.handlers.account;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.dto.AccountInfoDTO;
import server.database.user.AccountInfoUpdate;
import server.handlers.util.HttpStreamManager;
import server.handlers.util.ParamParser;
import server.logging.Logging;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class AccountInfoHandler implements HttpHandler {
    public static AccountInfoHandler instance = null;

    private AccountInfoHandler() {
    }

    public static AccountInfoHandler getInstance() {
        if (instance == null) {
            instance = new AccountInfoHandler();
        }
        return instance;
    }

    /**
     * Handle the account update request. It updates the user's account information, which consists of
     * first name, last name, email, or phone number. All parameters are optional.
     * @param exchange HttpExchange (username, first_name, last_name, email, phone_number)
     * @return String
     * @throws IOException
     */
    public void handle(HttpExchange exchange) throws IOException {
        String response;
        String username;
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) { // PUT more correct, but POST, so it's more uniform
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            String input = HttpStreamManager.readRequestBody(exchange);
            Gson gson = new Gson();
            AccountInfoDTO dto = gson.fromJson(input, AccountInfoDTO.class);
            // check what parameters are present and call corresponding method
            response = "";

            if (!params.containsKey("username")){
                response = "Username not provided";
                exchange.sendResponseHeaders(400, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                return;
            }

            username = params.get("username");

            if (dto.email != null) {
                response += AccountInfoUpdate.updateEmail(username, dto.email);
            }

            if (dto.firstName != null) {
                response += AccountInfoUpdate.updateFirstName(username, dto.firstName);
            }

            if (dto.lastName != null) {
                response += AccountInfoUpdate.updateLastName(username, dto.lastName);
            }

            if (dto.phoneNumber != null) {
                response += AccountInfoUpdate.updatePhoneNumber(username, dto.phoneNumber);
            }

            if (response.equals("")) {
                response = "No parameters provided";
            }

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

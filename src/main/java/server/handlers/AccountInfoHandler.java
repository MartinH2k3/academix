package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.database.user.AccountInfoUpdate;
import server.database.user.Auth;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;

public class AccountInfoHandler implements HttpHandler {
    public static final AccountInfoHandler instance = null;

    private AccountInfoHandler() {
    }

    public static AccountInfoHandler getInstance() {
        if (instance == null) {
            return new AccountInfoHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        String username;
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) { // PUT more correct, but POST, so it's more uniform
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            // possible parameters: first_name, last_name, email, phone_number
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

            if (params.containsKey("first_name")) {
                AccountInfoUpdate.updateFirstName(username, params.get("first_name"));
                response += "First name updated\n";
            }

            if (params.containsKey("last_name")) {
                AccountInfoUpdate.updateLastName(username, params.get("last_name"));
                response += "Last name updated\n";
            }

            if (params.containsKey("email")) {
                AccountInfoUpdate.updateEmail(username, params.get("email"));
                response += "Email updated\n";
            }

            if (params.containsKey("phone_number")) {
                AccountInfoUpdate.updatePhoneNumber(username, params.get("phone_number"));
                response += "Phone number updated\n";
            }

            if (response.equals("")) {
                response = "No parameters provided";
            }

        } else {
            response = "Wrong request method";
        }

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

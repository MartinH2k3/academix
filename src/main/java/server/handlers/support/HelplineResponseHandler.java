package server.handlers.support;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.dto.QnADTO;
import server.database.support.Helpline;
import server.handlers.util.ParamParser;
import server.logging.Logging;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class HelplineResponseHandler implements HttpHandler{
    private static HelplineResponseHandler instance = null;

    private HelplineResponseHandler() {
    }

    public static HelplineResponseHandler getInstance() {
        if (instance == null) {
            instance = new HelplineResponseHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            if (params.containsKey("username")) {
                String username = params.get("username");
                List<QnADTO> dto = Helpline.getAnswers(username);
                Gson gson = new Gson();
                response = gson.toJson(dto);
            } else {
                response = "Missing username parameter";
                Logging.getInstance().logServerWarning(response);
            }
        } else {
            System.out.println(exchange.getRequestMethod());
            response = "Wrong request method";
            Logging.getInstance().logServerWarning(response);
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

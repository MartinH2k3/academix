package server.handlers.util;

import com.sun.net.httpserver.HttpExchange;
import server.logging.Logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpStreamManager {
    public static String readRequestBody(HttpExchange exchange) {
        try {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                json.append(line);
            }
            br.close();
            return json.toString();
        } catch (IOException e) {
            Logging.getInstance().logException(e, "Pri načítavaní tela requestu prišlo k chybe.");
            return null;
        }
    }
}

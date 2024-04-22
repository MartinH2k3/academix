package server.handlers.faculty;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.Base64EncoderDecoder;
import common.dto.FacultyDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import server.database.faculty.FacultyGetter;

public class GetFacultiesHandler implements HttpHandler {
    private static GetFacultiesHandler instance = null;

    private GetFacultiesHandler() {
    }

    public static GetFacultiesHandler getInstance() {
        if (instance == null) {
            instance = new GetFacultiesHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            List<FacultyDTO> faculties = FacultyGetter.getAllFaculties();
            Gson gson = new Gson();
            response = gson.toJson(faculties);
        } else {
            response = "Wrong request method";
            // TODO log here
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

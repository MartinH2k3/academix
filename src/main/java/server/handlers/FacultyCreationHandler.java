package server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.dto.FacultyDTO;
import server.database.faculty.FacultyCreator;
import server.handlers.util.HttpStreamManager;
import server.handlers.util.ParamParser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class FacultyCreationHandler implements HttpHandler {
    private static FacultyCreationHandler instance = null;

    private FacultyCreationHandler() {
    }

    public static FacultyCreationHandler getInstance() {
        if (instance == null) {
            instance = new FacultyCreationHandler();
        }
        return instance;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String response;
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> params = ParamParser.paramsToMap(exchange.getRequestURI().getQuery());
            String json = HttpStreamManager.readRequestBody(exchange);
            Gson gson = new Gson();
            FacultyDTO facultyDTO = gson.fromJson(json, FacultyDTO.class);
            response = FacultyCreator.addFaculty(params.get("username"), facultyDTO.university_name, facultyDTO.faculty_name, facultyDTO.description, facultyDTO.field, facultyDTO.minimal_grade, facultyDTO.website_url, facultyDTO.title_image_url);
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

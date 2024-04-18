package server;

import com.sun.net.httpserver.HttpServer;
import server.handlers.*;
import server.handlers.account.AccountInfoHandler;
import server.handlers.account.LoginHandler;
import server.handlers.account.PasswordResetHandler;
import server.handlers.account.RegisterHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class server {
public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/register", RegisterHandler.getInstance());
        server.createContext("/login", LoginHandler.getInstance());
        server.createContext("/account/update", AccountInfoHandler.getInstance());
        server.createContext("/account/reset_password", PasswordResetHandler.getInstance());
        server.createContext("/submit_question", HelplineQuestionHandler.getInstance());
        server.createContext("/answer_question", HelplineAnswerHandler.getInstance());
        server.createContext("/answer_request", AcceptRejectHandler.getInstance());
        server.createContext("/create_faculty", FacultyCreationHandler.getInstance());
        server.createContext("/pending_questions", PendingQuestionsHandler.getInstance());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}

package server;

import com.sun.net.httpserver.HttpServer;
import server.handlers.AccountInfoHandler;
import server.handlers.LoginHandler;
import server.handlers.RegisterHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class server {
public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/register", RegisterHandler.getInstance());
        server.createContext("/login", LoginHandler.getInstance());
        server.createContext("/account/update", AccountInfoHandler.getInstance());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}

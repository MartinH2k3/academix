package server.handlers.util;

import com.google.gson.Gson;

public class Serializer {
    public static String serialize(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}

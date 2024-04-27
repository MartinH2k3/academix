package server.handlers.util;

import java.util.HashMap;
import java.util.Map;

public class ParamParser {
    private ParamParser() {}
    /**
     * Convert a map of parameters to a string
     * @param params Map (key, value)
     * @return String with http request parameters (key=value&key=value&...)
     */
    public static String paramsToString(Map<String, String> params){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String result = sb.toString();
        return result.length() > 0 ? result.substring(0, result.length() - 1) : result; // removes the trailing &
    }

    /**
     * Convert a string of parameters to a map
     * @param params String with http request parameters (key=value)
     * @return Map (key, value)
     */
    public static Map<String, String> paramsToMap(String params){
        Map<String, String> result = new HashMap<>();
        if (params == null || params.isEmpty()) return result;
        for (String param : params.split("&")) {
            String[] pair = param.split("=");
            if (pair.length == 2) {
                result.put(pair[0], pair[1]);
            }
        }
        return result;
    }
}


package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@SpringBootApplication
public class JsonReadingApplication {
	
	 public static List<Map<String, Object>> parseJson(String jsonString) {
        List<Map<String, Object>> result = new ArrayList<>();

        // Remove any leading/trailing whitespace and brackets [ ]
        jsonString = jsonString.trim();
        if (jsonString.startsWith("[")) {
            jsonString = jsonString.substring(1);
        }
        if (jsonString.endsWith("]")) {
            jsonString = jsonString.substring(0, jsonString.length() - 1);
        }

        String[] items = jsonString.split("\\},\\{");
        for (String item : items) {
            Map<String, Object> objMap = new LinkedHashMap<>();

            // Remove leading/trailing brackets { }
            item = item.trim();
            if (item.startsWith("{")) {
                item = item.substring(1);
            }
            if (item.endsWith("}")) {
                item = item.substring(0, item.length() - 1);
            }

            String[] keyValuePairs = item.split(",");
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split(":");
                String key = keyValue[0].trim().replaceAll("\"", "");
                String value = keyValue[1].trim();

                if (value.startsWith("\"") && value.endsWith("\"")) {
                    // String value
                    value = value.replaceAll("\"", "");
                    objMap.put(key, value);
                } else if (value.contains(".")) {
                    // Floating-point number
                    BigDecimal decimalValue = new BigDecimal(value);
                    objMap.put(key, decimalValue);
                } else {
                    // Integer number
                    long longValue = Long.parseLong(value);
                    objMap.put(key, longValue);
                }
            }

            result.add(objMap);
        }

        return result;
    }
	public static void main(String[] args) {
		String jsonString = "[{\"name\":\"Ankur\",\"age\":27,\"salary\":1000.50},{\"name\":\"Anuj\",\"age\":25,\"salary\":2000.75}]";

        List<Map<String, Object>> resultList = parseJson(jsonString);
        for (Map<String, Object> objMap : resultList) {
            System.out.println(objMap);
        }
	}

}

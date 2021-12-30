package com.challenge.application.util;

import com.challenge.application.domain.Product;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    public List<Product> deserializeFile() {
        File input = new File("src/main/resources/json/input.json");
        List<Product> productList = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray itemsList = fileObject.get("items").getAsJsonArray();
            for (JsonElement item : itemsList) {
                JsonObject itemJsonObject = item.getAsJsonObject();

                String name = itemJsonObject.get("name").getAsString();
                int amount = itemJsonObject.get("amount").getAsInt();
                double price = itemJsonObject.get("price").getAsDouble();

                Product product = new Product(name, amount, price);
                productList.add(product);
                return productList;
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        return productList;
    }
}

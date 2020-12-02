package com.safari1006.assignment;

import org.json.JSONObject;

public class ProductModel extends JSONObject {
    String id, name, price, description, image, expirationDate;

    public ProductModel(String id, String name, String price, String description, String image, String expirationDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.expirationDate = expirationDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
}


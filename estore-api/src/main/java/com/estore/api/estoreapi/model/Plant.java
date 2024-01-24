package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Plant {

    public static final String STRING_FORMAT = "Plant [id=%d, name=%s, price=%d, quantity=%d]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("price") private int price;
    @JsonProperty("quantity") private int quantity;
    public Plant(@JsonProperty("id") int id, @JsonProperty("name") String name,@JsonProperty("prices") int prices, @JsonProperty("quantity") int quantity ){
        this.price = prices;
        this.quantity = quantity;
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getprice() {
        return this.price;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,price,quantity);
    }
}

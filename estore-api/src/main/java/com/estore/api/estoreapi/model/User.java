package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class User {

    public static final String STRING_FORMAT = "User [id=%d, username=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("username") private String username;
    @JsonProperty("password") private String password;
    @JsonProperty("cart") private List<Plant> cart;
    public User(@JsonProperty("id") int id, @JsonProperty("username") String username, @JsonProperty("password") String password,@JsonProperty("cart") List<Plant> cart){

        this.id = id;
        this.username = username;
        this.password = password;
        if(cart == null){
            this.cart = new ArrayList<Plant>();
        }
        this.cart = cart;
        
    }

    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Plant> getCart() {
        return cart;
    }
    
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,username);
    }
    
}

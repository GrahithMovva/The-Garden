package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Plant;
import com.estore.api.estoreapi.model.User;

public interface UserDAO {
    User[] getUsers() throws IOException;

    User[] findUsers(String text) throws IOException;

    User getUser(int id) throws IOException;

    User createUser(User user) throws IOException;

    User updateUser(User user) throws IOException;

    boolean deleteUsers(int id) throws IOException;

    Plant[] addtocart(int id, Plant plant) throws IOException;

    Plant[] removefromcart(int id, Plant plant) throws IOException;;

    Plant[] checkout(int id) throws IOException;;

    
}

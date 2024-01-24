package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Plant;
import com.estore.api.estoreapi.model.User;

public interface CurrentDAO {

    User getCurrentUser() throws IOException;
    
    User setCurrentUser(User user) throws IOException;
    
    Plant[] AddtoCart(Plant plant) throws IOException;
    
    Plant[] RemoveFromCart(Plant plant) throws IOException;
}

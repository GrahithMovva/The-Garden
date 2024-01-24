package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.model.Plant;
import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.CurrentDAO;
import com.estore.api.estoreapi.persistence.CurrentFileDAO;

@Tag("Controller-tier")
public class CurrentControllerTest {

    CurrentController currentController;
    CurrentDAO currentDAO;
    List<Plant> cart;

    @BeforeEach
    public void setup() throws IOException {
        this.currentDAO = mock(CurrentDAO.class);
        this.currentController = new CurrentController(currentDAO);
        this.cart = new ArrayList<Plant>();
        cart.add(new Plant(0, "test_plant", 10, 10));
        
    }

    @Test
    public void testgetcurrentOK() throws IOException{
        User user = new User(1, "test_user", "123", cart);
        when(currentDAO.getCurrentUser()).thenReturn(user);
        ResponseEntity<User> response = currentController.getcurrent();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testgetcurrentInternalServerError() throws IOException{
        when(currentDAO.getCurrentUser()).thenThrow(IOException.class);
        ResponseEntity<User> response = currentController.getcurrent();
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testsetCurrentUserOK() throws IOException{
        User user = new User(1, "test_user", "123", cart);
        when(currentDAO.getCurrentUser()).thenReturn(user);
        ResponseEntity<User> response = currentController.setCurrentUser(user);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testsetCurrentUserInternalServerError() throws IOException{
        User user = new User(1, "test_user", "123", cart);
        when(currentDAO.getCurrentUser()).thenThrow(IOException.class);
        ResponseEntity<User> response = currentController.setCurrentUser(user);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

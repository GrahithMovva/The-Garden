package com.estore.api.estoreapi.persistence;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.model.Plant;
import com.estore.api.estoreapi.model.User;

@Tag("Persistence-tier")
public class CurrentFileDAOTest {

    User user;
    CurrentFileDAO currentFileDAO;
    PlantDAO plantDAO;
    String filename;

    @BeforeEach
    public void setup() throws IOException {
        this.currentFileDAO = mock(CurrentFileDAO.class);
        this.plantDAO = mock(PlantDAO.class);
        this.filename = "test.json";
        this.user = mock(User.class);
    }

    @Test
    public void testGetCurrentUser() throws IOException {
        when(currentFileDAO.getCurrentUser()).thenReturn(user);
        assertEquals(user, currentFileDAO.getCurrentUser());
    }

    @Test
    public void testSetCurrentUser() throws IOException {
        when(currentFileDAO.setCurrentUser(user)).thenReturn(user);
        assertEquals(user, currentFileDAO.setCurrentUser(user));
    }

    @Test
    public void testAddtoCart() throws IOException {
        Plant plant = mock(Plant.class);
        List<Plant> cart = new ArrayList<Plant>();
        cart.add(plant);
        when(user.getCart()).thenReturn(cart);
        when(currentFileDAO.AddtoCart(plant)).thenReturn(cart.toArray(new Plant[cart.size()]));

        assertTrue(cart.contains(plant));
    }

    @Test
    public void testRemoveFromCart() throws IOException {
        Plant plant = mock(Plant.class);
        List<Plant> cart = new ArrayList<Plant>();
        cart.add(plant);
        when(user.getCart()).thenReturn(cart);
        when(currentFileDAO.RemoveFromCart(plant)).thenReturn(cart.toArray(new Plant[cart.size()]));

        assertFalse(!cart.contains(plant));
    }    
}

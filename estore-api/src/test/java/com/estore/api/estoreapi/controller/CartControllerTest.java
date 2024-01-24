package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.model.Plant;
import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.CurrentDAO;
import com.estore.api.estoreapi.persistence.UserDAO;

@Tag("Controller-tier")
public class CartControllerTest {
    private CartController cartController;
    private User mockUser;
    private Plant mockPlant;
    private UserDAO mockUserDAO;;
    private CurrentDAO mockCurrentDAO;

    /**
     * Before each test, create a new inventoryController object and inject
     * a mock Plant DAO
     */
    @BeforeEach
    public void setupinventoryController() {
        mockPlant = mock(Plant.class);
        mockCurrentDAO = mock(CurrentDAO.class);
        cartController = new CartController( mockUserDAO, mockCurrentDAO);
        mockUser = mock(User.class);
        when(mockUser.getUsername()).thenReturn("mock");
        when(mockPlant.getId()).thenReturn(1);
        when(mockPlant.getQuantity()).thenReturn(5);
    }

    @Test
    public void testGetCartSuccess() throws IOException {

        when(mockCurrentDAO.getCurrentUser()).thenReturn(mockUser);
        when(mockUser.getCart()).thenReturn(List.of(mockPlant));


        ResponseEntity<Plant[]> response = cartController.getCart(mockUser.getId());

        when(mockCurrentDAO.getCurrentUser()).thenReturn(mockUser);
        when(mockUser.getCart()).thenReturn(List.of(mockPlant));

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetCartFail() throws IOException {
        // when(mockCartDAO(mockUser.getUsername())).thenReturn(null);
        when(mockUser.getCart()).thenReturn(null);

        ResponseEntity<Plant[]> response = cartController.getCart(mockUser.getId());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddToCartSuccess() throws IOException {
        when(mockCurrentDAO.getCurrentUser()).thenReturn(mockUser);
        when(mockUser.getCart()).thenReturn(List.of(mockPlant));

        ResponseEntity<Plant[]> response = cartController.addToCart(mockPlant,mockPlant.getId());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testDeletePlantSuccess() throws IOException {

        when(mockCurrentDAO.getCurrentUser()).thenReturn(mockUser);
        when(mockUser.getCart()).thenReturn(List.of(mockPlant));

        ResponseEntity<Plant[]> response = cartController.removeFromCart(mockPlant,mockPlant.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void testAddPlantFail() throws IOException {
        when(mockCurrentDAO.getCurrentUser()).thenReturn(mockUser);
        when(mockUser.getCart()).thenReturn(List.of(mockPlant));

        ResponseEntity<Plant[]> result = cartController.addToCart(mockPlant, mockPlant.getId());

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotEquals(mockPlant.getId(), result.getBody());
    }

    @Test
    public void testcheckoutFail() throws IOException {
        when(mockCurrentDAO.getCurrentUser()).thenReturn(mockUser);
        when(mockUser.getCart()).thenReturn(List.of(mockPlant));

        ResponseEntity<Plant[]> response = cartController.checkout(mockUser.getId());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    // @Test
    public void testcheckoutOK() throws IOException {
        when(mockCurrentDAO.getCurrentUser()).thenReturn(mockUser);
        when(mockUser.getCart()).thenReturn(List.of(mockPlant));

        ResponseEntity<Plant[]> response = cartController.checkout(mockUser.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
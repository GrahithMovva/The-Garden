package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.UserDAO;

@Tag("Controller-tier")
public class UserControllerTest {
    private UserController userController;
    private UserDAO mockUserDAO;
    private User mockUser;

    /**
     * Before each test, create a new inventoryController object and inject
     * a mock Product DAO
     */
    
    @BeforeEach
    public void setupUserController() {
       this.mockUserDAO = mock(UserDAO.class);
        this.userController = new UserController(mockUserDAO);
        this.mockUser = mock(User.class);
    }

    @Test
    public void testGetUser() throws IOException { // getProduct may throw IOException
        // Setup
        User user = new User(1, "Shreyes", "1234",new ArrayList<>());
        when(mockUserDAO.getUser(1)).thenReturn(user);
        // When the same id is passed in, our mock Product DAO will return the Product
        // object
        when(mockUserDAO.getUser(user.getId())).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = userController.getUser(user.getId());

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testFailGetUser() throws IOException {
        User user = new User(1, "Shreyes", "1234",new ArrayList<>());
        this.userController = new UserController(mockUserDAO);
        ResponseEntity<User> actualGetUserResult = userController.getUser(user.getId());
        assertEquals(HttpStatus.NOT_FOUND, actualGetUserResult.getStatusCode());
    }

    @Test
    public void testCreateUser() throws IOException { // getProduct may throw IOException
        // Setup
        User user = new User(1, "Shreyes", "1234",new ArrayList<>());
        when(mockUserDAO.createUser(user)).thenReturn(user);
        // When the same id is passed in, our mock Product DAO will return the Product
        // object
        when(mockUserDAO.createUser(user)).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = userController.createUser(user);

        // Analyze
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testFailCreateUser() throws IOException {
        User user = new User(1, "Shreyes", "1234",new ArrayList<>());
        this.userController = new UserController(mockUserDAO);
        ResponseEntity<User> actualCreateUserResult = userController.createUser(user);
        assertEquals(HttpStatus.CONFLICT, actualCreateUserResult.getStatusCode());
    }

    @Test
    public void testUpdateUser() throws IOException { // getProduct may throw IOException
        // Setup
        User user = new User(1, "jake12", "1234",new ArrayList<>());
        User user1 = mockUserDAO.getUser(user.getId());
        when(mockUserDAO.updateUser(user1)).thenReturn(user1);
        
        ResponseEntity<User> response = userController.updateUser(user);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testFailUpdateUser() throws IOException {
        User user = new User(1, "jake12s", "1234",new ArrayList<>());
        this.userController = new UserController(mockUserDAO);
        ResponseEntity<User> actualUpdateUserResult = userController.updateUser(user);
        assertEquals(HttpStatus.NOT_FOUND, actualUpdateUserResult.getStatusCode());
    }

    @Test
    public void testSearchUsers() throws IOException {
        // Setup
        User user = new User(1, "jadin", "1234",new ArrayList<>());
        when(mockUserDAO.getUser(1)).thenReturn(user);
        when(mockUserDAO.getUser(user.getId())).thenReturn(user);

        // Invoke
        ResponseEntity<com.estore.api.estoreapi.model.User> response = userController.getUser(user.getId());

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testFailSearchUsers() throws IOException {
        User user = new User(1, "jake12s", "1234",new ArrayList<>());
        this.userController = new UserController(mockUserDAO);
        ResponseEntity<User> actualSearchUsersResult = userController.getUser(user.getId());
        assertEquals(HttpStatus.NOT_FOUND, actualSearchUsersResult.getStatusCode());
    }

    @Test
    public void testDeleteUser() throws IOException { // getProduct may throw IOException
        // Setup
        User user = new User(1, "jadin", "1234",new ArrayList<>());
        when(mockUserDAO.deleteUsers(user.getId())).thenReturn(false);

        // Invoke
        ResponseEntity<User> response = userController.deleteUser(user.getId());

        // Analyze
        assertEquals(null, response.getBody());
    }

    @Test
    public void testFailDeleteUser() throws IOException {
        User user = new User(1, "jake12s", "1234",new ArrayList<>());
        this.userController = new UserController(mockUserDAO);
        ResponseEntity<User> actualDeleteUserResult = userController.getUser(user.getId());
        assertEquals(HttpStatus.NOT_FOUND, actualDeleteUserResult.getStatusCode());
    }

    @Test
    public void testGetUserInternalServerError() throws IOException {
        User user = new User(1, "jake12s", "1234",new ArrayList<>());
        userController = new UserController(mockUserDAO);
        ResponseEntity<User> actualGetUserInternalServerErrorResult = userController.getUser(user.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualGetUserInternalServerErrorResult.getStatusCode());
    }
}
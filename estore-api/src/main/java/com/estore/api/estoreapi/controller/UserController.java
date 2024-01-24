package com.estore.api.estoreapi.controller;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.UserDAO;
@RestController
@RequestMapping("users")
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    private UserDAO userDAO;

    public UserController(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        LOG.info("GET /users/" + id);

        try{
            User user = userDAO.getUser(id);
            if(user != null)
                return new ResponseEntity<User>(user,HttpStatus.OK);
            
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);    
        }
        catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user){
        LOG.info("POST /users " + user);
        try{
            User newUser = userDAO.createUser(user);
            if(newUser != null)
                return new ResponseEntity<User>(newUser,HttpStatus.CREATED);  
            else
                return new ResponseEntity<User>(newUser,HttpStatus.CONFLICT);    
        }
        catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<User[]> searchUsers(@RequestParam String name){
        LOG.info("GET /users/?name=" + name);

        try{
            return new ResponseEntity<User[]>(userDAO.findUsers(name),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        LOG.info("DELETE /users/" + id);
        try {
            if (userDAO.getUser(id) != null) {
                userDAO.deleteUsers(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        LOG.info("PUT /users " + user);
        try {
            if (userDAO.getUser(user.getId()) != null) {
                return new ResponseEntity<User>(userDAO.updateUser(user), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<User[]> getUsers() {
        LOG.info("GET /users");
        try {
            User[] users = userDAO.getUsers();
            return new ResponseEntity<User[]>(users,HttpStatus.OK);    
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


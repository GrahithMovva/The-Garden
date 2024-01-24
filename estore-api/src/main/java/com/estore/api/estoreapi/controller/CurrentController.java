package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.CurrentDAO;



@RestController
@RequestMapping("current")
public class CurrentController {
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    private CurrentDAO currentDAO;


    public CurrentController(CurrentDAO currentDAO){
        this.currentDAO = currentDAO;
    }
    @GetMapping("")
    public ResponseEntity<User> getcurrent() {
        LOG.info("GET /current");
        try {
            User users = currentDAO.getCurrentUser();
            return new ResponseEntity<User>(users,HttpStatus.OK);    
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<User> setCurrentUser(@RequestBody User user) {
        LOG.info("PUT /current " + user);
        try {
            
            currentDAO.setCurrentUser(user);
            return new ResponseEntity<User>(currentDAO.getCurrentUser(), HttpStatus.OK);
            
            
        }
        catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

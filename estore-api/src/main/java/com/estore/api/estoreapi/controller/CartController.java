
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
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.Plant;
import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.CurrentDAO;
import com.estore.api.estoreapi.persistence.UserDAO;
@RestController
@RequestMapping("cart")
public class CartController {
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    private UserDAO userDAO;
    private CurrentDAO current;

    public CartController(UserDAO userDAO,CurrentDAO current){
        this.userDAO = userDAO;
        this.current = current;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant[]> getCart(@PathVariable int id){
        LOG.info("GET /cart" + id );

        try{
            User user = current.getCurrentUser();
            if(user != null)
                
                return new ResponseEntity<Plant[]>(user.getCart().toArray(new Plant[user.getCart().size()]),HttpStatus.OK);
            
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);    
        }
        catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Plant[]> checkout(@PathVariable int id){
        LOG.info("DELETE /cart/" + id );
        try {
            if (userDAO.getUser(id) != null) {
                userDAO.checkout(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Plant[]> addToCart(@RequestBody Plant plant,@PathVariable int id){
        LOG.info("POST /cart " + id + plant );
        try{
            User newUser = current.getCurrentUser();
            
            if(newUser != null){
                Plant[] newCart = current.AddtoCart(plant);
                return new ResponseEntity<Plant[]>(newCart,HttpStatus.CREATED);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
              
        }
        catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plant[]> removeFromCart(@RequestBody Plant plant,@PathVariable int id) {
        LOG.info("PUT /cart " + id + plant);
        try {
            User newuser = current.getCurrentUser();
            if (newuser != null) {
                Plant[] newCart = current.RemoveFromCart(plant);
                return new ResponseEntity<Plant[]>(newCart, HttpStatus.OK);
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
    
}

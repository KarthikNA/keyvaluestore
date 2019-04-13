package com.assignment.keyvaluestore.udaan.controller;

import com.assignment.keyvaluestore.udaan.model.PendingAmount;
import com.assignment.keyvaluestore.udaan.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // creates new user
        return new ResponseEntity<User>(HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<PendingAmount> getUserPendingAmount(Integer id) {
        // returns the amount the person owes and how much others owe
        return new ResponseEntity<PendingAmount>(HttpStatus.OK);
    }


}

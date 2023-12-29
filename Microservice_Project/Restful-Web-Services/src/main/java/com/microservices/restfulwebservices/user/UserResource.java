package com.microservices.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    public UserResource(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
        if(user == null){
            throw new UserNotFoundException("id : " + id);
        }

        return user;
    }

    //When binding happens the validations which are defined on your object are automatically invoked
    @PostMapping("/users")
    public ResponseEntity<User> CreateUser(@Valid @RequestBody User user){
        User savedUser = service.saveUser(user);
        // To Return URL of Created Resource use quick HTTP header location
        URI location = ServletUriComponentsBuilder    // To Uri of Current Request ---> /users
                .fromCurrentRequest()
                .path("/{id}")     // Add a Path /{id}
                .buildAndExpand(savedUser.getId()) // Replace the variable present here with id of the created User
                .toUri();        // Convert it to URI and return it to the location specified
        return ResponseEntity.created(location).build();  // Response Status is Created at URI /users/4/ : 201
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        service.deleteById(id);
    }


}

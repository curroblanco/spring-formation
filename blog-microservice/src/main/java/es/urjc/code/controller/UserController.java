package es.urjc.code.controller;

import es.urjc.code.entity.AppUser;
import es.urjc.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<AppUser> getAll() {

        return userService.findAll();
    }

    @GetMapping("/{id}")
    public AppUser findOne(@PathVariable long id) {

        return userService.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long insertOne(@RequestBody AppUser appUser) {

        return userService.insertOne(appUser);
    }

    @PutMapping("/{id}")
    public AppUser updateOne(@PathVariable long id, @RequestBody AppUser appUser) {

        return userService.updateOne(id, appUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable long id) {

        userService.deleteOne(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

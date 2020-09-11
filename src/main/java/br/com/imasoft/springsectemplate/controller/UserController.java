package br.com.imasoft.springsectemplate.controller;

import br.com.imasoft.springsectemplate.model.User;
import br.com.imasoft.springsectemplate.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author brunocarneiro
 */
@RestController
@RequestMapping(value = "/api/v1/service/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) throws Exception {
        return Optional.of(new ResponseEntity<>(this.userService.save(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping
    public ResponseEntity<List<User>> list() throws Exception {
        return Optional.of(new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Integer id) {
        return Optional.of(new ResponseEntity<>(this.userService.findById(id), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) throws Exception {
        return Optional.of(new ResponseEntity<>(this.userService.update(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Integer id) {
        return Optional.of(new ResponseEntity<>(this.userService.deleteById(id), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }
}

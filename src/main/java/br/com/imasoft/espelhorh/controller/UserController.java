package br.com.imasoft.espelhorh.controller;

import br.com.imasoft.espelhorh.model.User;
import br.com.imasoft.espelhorh.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * @author brunocarneiro
 */
@CrossOrigin
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

    @PostMapping("/avatar")
    public ResponseEntity<User> uploadImage(@RequestParam("file") MultipartFile image, Principal principal) throws Exception {

        User user = this.userService.findUserByEmail("bruno.carneiro312@gmail.com");
        user.setAvatar(image.getBytes());

        return Optional.of(new ResponseEntity<>(this.userService.update(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }
}

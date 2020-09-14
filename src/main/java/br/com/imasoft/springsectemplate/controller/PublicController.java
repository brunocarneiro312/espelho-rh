package br.com.imasoft.springsectemplate.controller;

import br.com.imasoft.springsectemplate.model.User;
import br.com.imasoft.springsectemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author brunocarneiro
 */
@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    private final UserService userService;

    @Autowired
    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> signUp(@RequestBody User user) {
        try {
            return new ResponseEntity<>(this.userService.save(user), HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Public API", HttpStatus.OK);
    }

}

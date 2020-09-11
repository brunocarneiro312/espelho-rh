package br.com.imasoft.springsectemplate.controller;

import br.com.imasoft.springsectemplate.model.User;
import br.com.imasoft.springsectemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Hello Admin!", HttpStatus.OK);
    }
}

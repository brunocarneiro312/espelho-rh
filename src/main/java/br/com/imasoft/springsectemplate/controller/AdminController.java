package br.com.imasoft.springsectemplate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @GetMapping
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Hello Admin!", HttpStatus.OK);
    }
}

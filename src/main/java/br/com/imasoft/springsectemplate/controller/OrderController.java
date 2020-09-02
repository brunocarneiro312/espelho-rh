package br.com.imasoft.springsectemplate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/order")
public class OrderController {

    @GetMapping
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Welcome to Order!", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('COMMON', 'ADMIN')")
    @GetMapping("/register")
    public ResponseEntity<String> register() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}

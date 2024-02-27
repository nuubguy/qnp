package com.example.assignment.controller;

import com.example.assignment.annotation.RateLimited;
import com.example.assignment.dto.response.Response;
import com.example.assignment.entity.Contact;
import com.example.assignment.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RateLimited(key = "getContactById", tokens = 10, timeWindowInSeconds = 60)
    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable Long id) {
        Contact contact = contactService.getContactById(id);
        if (contact == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("id not found"));
        return ResponseEntity.status(HttpStatus.OK).body(contact);
    }

    @RateLimited(key = "createContact", tokens = 10, timeWindowInSeconds = 60)
    @PostMapping
    public ResponseEntity<?> createContact(@RequestBody Contact contact) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.createContact(contact));
    }

    @RateLimited(key = "updateContact", tokens = 10, timeWindowInSeconds = 60)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
        return ResponseEntity.status(HttpStatus.OK).body(contactService.updateContact(id, updatedContact));
    }

    @RateLimited(key = "deleteContact", tokens = 10, timeWindowInSeconds = 60)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}

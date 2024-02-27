package com.example.assignment.service;

import com.example.assignment.entity.Contact;

public interface ContactService {
    Contact getContactById(Long id);
    Contact createContact(Contact contact);
    Contact updateContact(Long id, Contact updatedContact);
    void deleteContact(Long id);
}

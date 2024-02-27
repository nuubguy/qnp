package com.example.assignment.service.implementation;

import com.example.assignment.entity.Contact;
import com.example.assignment.repository.ContactRepository;
import com.example.assignment.service.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Long id, Contact updatedContact) {
        Contact existingContact = contactRepository.findById(id).orElse(null);
        if (existingContact != null) {
            existingContact.setName(updatedContact.getName());
            existingContact.setEmail(updatedContact.getEmail());
            existingContact.setPhone(updatedContact.getPhone());
            existingContact.setAddress(updatedContact.getAddress());
            return contactRepository.save(existingContact);
        }
        return null;
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}

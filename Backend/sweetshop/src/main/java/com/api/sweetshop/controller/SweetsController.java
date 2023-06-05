package com.api.sweetshop.controller;

import java.util.List;

import com.api.sweetshop.model.Sweets;
import com.api.sweetshop.repository.SweetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SweetShop")
public class SweetsController {

    @Autowired
    SweetsRepository sweetsRepository;

    @GetMapping(value = { "/sweets" })
    public List<Sweets> getSweets() {
        return this.sweetsRepository.findAll();
    }

    @PostMapping(value = { "/addSweet" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addSweet(@RequestBody Sweets sweet) {
        sweetsRepository.save(sweet);
        return ResponseEntity.ok("Ok");
    }

    @DeleteMapping(value = { "/deleteSweet" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteSweet(@RequestBody Sweets sweet) {
        if (sweetsRepository.existsByName(sweet.getName())) {
            Sweets foundSweet = sweetsRepository.getByName(sweet.getName());
            sweetsRepository.delete(foundSweet);
            return ResponseEntity.ok("Sweet deleted");
        } else {
            return ResponseEntity.ok("Sweet not found");
        }
    }
}

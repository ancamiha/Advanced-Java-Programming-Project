package com.api.sweetshop.service;

import com.api.sweetshop.model.Sweets;

import java.util.List;

public interface Baking {
    Sweets addSweet(Sweets sweets);

    Sweets get(Long id);

    Sweets getSweetByName(String name);

    List<Sweets> getAll();
}

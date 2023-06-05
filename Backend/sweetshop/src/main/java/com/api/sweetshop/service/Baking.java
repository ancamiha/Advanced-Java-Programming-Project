package com.api.sweetshop.service;

import com.api.sweetshop.model.Sweets;
import com.api.sweetshop.model.UserProfile;

import java.util.List;
import java.util.Set;

public interface Baking {
    Sweets addSweet(Sweets sweets);

    Sweets get(Long id);

    Sweets getSweetByName(String name);

    List<Sweets> getAll();

    Set<UserProfile> getUsers();

    UserProfile getUser(int poz);

    void addUser(UserProfile user);
}

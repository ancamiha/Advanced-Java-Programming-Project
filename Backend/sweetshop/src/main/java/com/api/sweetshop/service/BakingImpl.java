package com.api.sweetshop.service;

import com.api.sweetshop.model.Sweets;
import com.api.sweetshop.model.UserProfile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BakingImpl implements Baking {

    private Map<Long, Sweets> data = new HashMap<>();
    private final Set<UserProfile> users = new LinkedHashSet<>();
//    private final EmailService emailService = new EmailService();

    private final UserProfile bankSystemAdmin = new UserProfile("System Admin", null, null, "1234");
    private List<UserProfile> toList = new ArrayList<>();

    private Long currentId = 0L;

    public BakingImpl() {

    }

    @Override
    public Sweets addSweet(Sweets sweet) {
        data.put(currentId, sweet);
        currentId++;

        return sweet;
    }

    @Override
    public Sweets get(Long id) {
        return data.get(id);
    }

    @Override
    public Sweets getSweetByName(String name) {
        return (Sweets) data.entrySet()
            .stream()
            .filter(entry -> entry.getValue().getName().equals(name));
    }

    @Override
    public List<Sweets> getAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Set<UserProfile> getUsers() {
        return Collections.unmodifiableSet(users);
    }

    @Override
    public UserProfile getUser(int poz) {
        int i = 0;
        for (UserProfile client : users) {
            if (i == poz)
                return client;
            i++;
        }
        return null;
    }

    @Override
    public void addUser(UserProfile user) {
        users.add(user);
    }
}

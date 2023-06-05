package com.api.sweetshop.service;

import com.api.sweetshop.model.Sweets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BakingImpl implements Baking {

    private Map<Long, Sweets> data;

    private Long currentId = 0L;

    public BakingImpl() {
        this.data = new HashMap<>();
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
        for (Map.Entry<Long, Sweets> entry : data.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public List<Sweets> getAll() {
        return new ArrayList<>(data.values());
    }
}

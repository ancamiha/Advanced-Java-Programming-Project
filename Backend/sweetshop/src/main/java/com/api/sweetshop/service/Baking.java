package com.api.sweetshop.service;

import java.util.Set;

import com.api.sweetshop.exceptions.UserExistsException;
import com.api.sweetshop.model.UserProfile;

public interface Baking {

    Set<UserProfile> getUsers();

    UserProfile getUser(int poz);

    void addUser(UserProfile user) throws UserExistsException;
}

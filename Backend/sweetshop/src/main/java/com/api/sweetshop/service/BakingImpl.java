package com.api.sweetshop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.api.sweetshop.exceptions.EmailException;
import com.api.sweetshop.exceptions.UserExistsException;
import com.api.sweetshop.model.Email;
import com.api.sweetshop.model.Gender;
import com.api.sweetshop.model.UserProfile;
import com.api.sweetshop.utils.UserRegistrationListener;

public class BakingImpl implements Baking {

    private final Set<UserProfile> users = new LinkedHashSet<>();
    private final EmailService emailService = new EmailService();
    private final List<UserRegistrationListener> listeners = new ArrayList<>();

    private final UserProfile bakingSystem = new UserProfile("Baking System", null, Gender.NaN);
    private final UserProfile bakingSystemAdmin = new UserProfile("System Admin", null, Gender.NaN);
    private List<UserProfile> toList = new ArrayList<>();

    private Long currentId = 0L;

    private int printedUsers = 0;
    private int emailedUsers = 0;

    public BakingImpl() {
        listeners.add(new PrintUserListener());
        listeners.add(new EmailNotificationListener());
        toList.add(bakingSystemAdmin);
    }

    public int getPrintedUsers() {
        return printedUsers;
    }

    public int getEmailedUsers() {
        return emailedUsers;
    }

    public void addUser(final UserProfile user) throws UserExistsException {
        if (users.contains(user)) {
            throw new UserExistsException("Client already exists into the bank");
        }

        users.add(user);
        notify(user);
    }

    public void closeEmailService() {
        emailService.close();
    }

    private void notify(UserProfile user) {
        for (UserRegistrationListener listener : listeners) {
            listener.onUserAdded(user);
        }
    }

    public class PrintUserListener implements UserRegistrationListener {
        @Override
        public void onUserAdded(UserProfile user) {
            System.out.println("User added: " + user.getName());
            printedUsers++;
        }
    }


    class EmailNotificationListener implements UserRegistrationListener {
        @Override
        public void onUserAdded(UserProfile user) {
            System.out.println("Notification email for client " + user.getName() + " to be sent");
            try {
                emailService.sendNotificationEmail(
                    new Email(user, bakingSystem, toList,
                        "New client has been added",
                        "New client " + user + " has been added in system"));
            } catch (EmailException e) {
                e.printStackTrace();
            }
            emailedUsers++;
        }
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
}

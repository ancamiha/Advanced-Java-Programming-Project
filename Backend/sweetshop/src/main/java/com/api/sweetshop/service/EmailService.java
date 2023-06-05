package com.api.sweetshop.service;

import com.api.sweetshop.exceptions.EmailException;
import com.api.sweetshop.model.Email;
import com.api.sweetshop.queue.Queue;

public class EmailService implements Runnable {
    private final Queue<Email> emailStorage = new Queue<>();
    private final Thread currentThread;
    private boolean isServiceClosed = false;
    private int sentEmails = 0;

    public EmailService() {
        currentThread = new Thread(this);
        currentThread.start();
    }

    @Override public void run() {
        while (true) {
            if (isServiceClosed) {
                return;
            }

            Email email = emailStorage.get();
            if (email != null) {
                consumeEmail(email);
            }
            synchronized (emailStorage) {
                try {
                    emailStorage.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendNotificationEmail(Email email) throws EmailException {
        if (!isServiceClosed) {
            emailStorage.add(email);
            synchronized (emailStorage) {
                emailStorage.notify();
            }
        } else {
            throw new EmailException("Email service is closed, mails cannot be sent!");
        }
    }

    public void close() {
        isServiceClosed = true;
        synchronized (emailStorage) {
            emailStorage.notify();
        }
        try {
            currentThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getSentEmails() {
        return sentEmails;
    }

    private void consumeEmail(Email email) {
        ++sentEmails;
        System.out.println("Email successfully sent!\n" + email);
    }
}

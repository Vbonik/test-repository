package com.issoft.log.mailer.model;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * {@link ConcurrentLinkedQueue} based storage for messages.
 *
 * @author: AS
 */
public class MailStorage {

    private static MailStorage instance = new MailStorage();
    private ConcurrentLinkedQueue<Mail> mailList = new ConcurrentLinkedQueue<Mail>();

    public static MailStorage getInstance() {
        return instance;
    }

    private MailStorage() {
    }

    public boolean addMail(Mail mail) {
        return mailList.add(mail);
    }

    public Mail getMail() {
        return mailList.remove();
    }

    public boolean isEmpty() {
        return mailList.isEmpty();
    }
}

package com.issoft.log.mailer;

import com.issoft.log.database.UserEntityDAO;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: AS
 */
public class MailService implements Runnable {

    Thread runner;

    private static final String EMAIL = "mail";
    private static final String PASSWORD = "password";
    private static final String SMTP_SERVER = "smtp_server";
    private static final String PORT = "port";

    private UserEntityDAO userEntityDAO;
    private String email;
    private String password;
    private String smtp_server;
    private String port;

    private String userName;
    private String action;
    private String filePath;

    MailService() {
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getResourceAsStream("/email-settings.properties"));
            email = properties.getProperty(EMAIL);
            password = properties.getProperty(PASSWORD);
            smtp_server = properties.getProperty(SMTP_SERVER);
            port = properties.getProperty(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEmail(String messageTo, String messageSubject, String messageBody) {

        Properties prop = new Properties();
        prop.put("mail.smtp.host", smtp_server);
        prop.put("mail.port.socketFactory.port", port);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", port);

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        /*
        * Authorized sucessfull? -> Create new message
        */
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(messageTo));
            message.setSubject(messageSubject);
            message.setText(messageBody);

            Transport.send(message);
            System.out.println("Your message has been sent");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts new Thread which sends email about file changes (upload, download) to the subscribed users.
     *
     * @param action
     * @param filePath
     * @param userName
     */
    public void mailFileChanges(String action, String filePath, String userName) {
        this.action = action;
        this.filePath = filePath;
        this.userName = userName;
        runner = new Thread(this);
        runner.start();
    }


    @Override
    public synchronized void run() {
        for (Object[] receiver : userEntityDAO.selectReceivers(action)) {
            String messageTo = receiver[0].toString();
            String messageSubject = "FTP delivery";
            String messageBody = "Hello, " + receiver[1].toString() + "!\n" +
                    "User: " + userName + "\n" + action + " file: " + filePath + "";
            sendEmail(messageTo, messageSubject, messageBody);
        }
    }

    public void setUserEntityDAO(UserEntityDAO userEntityDAO) {
        this.userEntityDAO = userEntityDAO;
    }
}
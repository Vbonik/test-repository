package com.issoft.log.mailer;

import com.issoft.log.database.UserEntityDAO;
import com.issoft.log.mailer.model.Mail;
import com.issoft.log.mailer.model.MailStorage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Mailing service.
 *
 * @author: AS
 */
public class MailService {

    private static final String EMAIL = "mail";
    private static final String PASSWORD = "password";
    private static final String SMTP_SERVER = "smtp_server";
    private static final String PORT = "port";

    private UserEntityDAO userEntityDAO;
    private String email;
    private String password;
    private String smtp_server;
    private String port;

    private Mail mail;
    private Session session;

    MailService() {
        try {
            //Load smtp-server configuration from properties
            Properties properties = new Properties();
            properties.load(this.getClass().getResourceAsStream("/email-settings.properties"));
            email = properties.getProperty(EMAIL);
            password = properties.getProperty(PASSWORD);
            smtp_server = properties.getProperty(SMTP_SERVER);
            port = properties.getProperty(PORT);

            //Configure and authentication on smtp-server
            Properties prop = new Properties();
            prop.put("mail.smtp.host", smtp_server);
            prop.put("mail.port.socketFactory.port", port);
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.port", port);

            session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email, password);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends e-mail to receivers.
     */
    public void sendEmail() {

        try {
            MailStorage mailStorage = MailStorage.getInstance();

            while (!mailStorage.isEmpty()) {
                mail = mailStorage.getMail();
                Transport.send(createMessage(mail));
                System.out.println("Your message has been sent");
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Composes message to send.
     */
    public Message createMessage(Mail mail) throws MessagingException {

        List<Object[]> receivers = userEntityDAO.selectReceivers(mail.getAction());
        StringBuffer receiversAddresses = new StringBuffer();

        //Selects receivers depending on committed action with file
        for (Object[] receiver : receivers) {
            receiversAddresses.append(receiver[0].toString() + ",");
        }

        //Creates message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiversAddresses.toString()));
        message.setSubject("FTP delivery");
        message.setText("User: " + mail.getUserName() + "\n" +
                mail.getAction() + " file: " + mail.getFilePath());
        return message;

    }

    public void setUserEntityDAO(UserEntityDAO userEntityDAO) {
        this.userEntityDAO = userEntityDAO;
    }
}
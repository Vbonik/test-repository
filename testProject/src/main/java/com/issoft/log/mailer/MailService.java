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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

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

    private Map<String, List<Mail>> mailsToSend;

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
    public void prepareMail() {

        mailsToSend = new ConcurrentHashMap<String, List<Mail>>();
        MailStorage mailStorage = MailStorage.getInstance();

        while (!mailStorage.isEmpty()) {
            mail = mailStorage.getMail();
            addMessageForSending(mail);
        }

        for (String email : mailsToSend.keySet()) {
            byte[] report = JasperReporter.generateJasperReport(mailsToSend.get(email));
            sendMessage(report, email);
        }
    }

    /**
     * Groups <code>Mail</code> objects by receivers for further sending.
     */
    public void addMessageForSending(Mail mail) {

        List<Object[]> receivers = userEntityDAO.selectReceivers(mail.getAction());

        //Select receivers depending on committed action with file
        for (Object[] receiver : receivers) {
            String receiverEmail = receiver[0].toString();
            if (mailsToSend.get(receiverEmail) != null) {
                mailsToSend.get(receiverEmail).add(mail);
            } else {
                mailsToSend.put(receiverEmail, new ArrayList<Mail>(Arrays.asList(mail)));
            }
        }
    }

    /**
     * Sends message with PDF report attached.
     *
     * @param attach
     * @param address
     */
    public void sendMessage(byte[] attach, String address) {
        try {
            //Creates message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setSubject("FTP delivery");
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
            message.setContent(attach, "application/pdf");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void setUserEntityDAO(UserEntityDAO userEntityDAO) {
        this.userEntityDAO = userEntityDAO;
    }
}
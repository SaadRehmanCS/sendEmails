package src.model;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailAuthorization {

    public static void sendEmail(String recipient, String subject, String body) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String emailAccount = "saadrehman2000@gmail.com";
        String emailPassword = "******";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAccount, emailPassword);
            }
        });

        Message message = prepareMessage(session, emailAccount, recipient, subject, body);

        Transport.send(message);
        System.out.println("Message sent successfully");


    }

    public static Message prepareMessage(Session session, String myEmailAccount, String recipientEmail,
                                         String subject, String body) throws AddressException, MessagingException {

        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(myEmailAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            String emailSignature = "\n\n\nSent from my computer,\nWith regards,\n\nSaad Rehman";
            message.setText(body + emailSignature);
            return message;
        } catch (AddressException ax) {
            Logger.getLogger(MailAuthorization.class.getName()).log(Level.SEVERE, null, ax);
        }
        return null;
    }
}



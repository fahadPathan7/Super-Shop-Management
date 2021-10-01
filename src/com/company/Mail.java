/*
Main use of this class:
1. to send email with password (password recovery/ new login)
*/

package com.company;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Scanner;

public class Mail {
    static String emailRecipient;
    static boolean sent = false;

    // to send password via email
    static void email() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter 1 to get pass or enter 0 to try again: ");
        String str = sc.nextLine();

        if (str.equals("1")) {
            System.out.print("Enter your email id: ");
            emailRecipient = sc.nextLine();

            try{
                sendEmail();
            } catch(Exception e) {
                System.out.println(UserControl.RED + "\nFailed to send email!\n" + UserControl.RESET);
            }
        }
        else if (str.equals("0")) {
            System.out.println();
            //loginForm();
        }
        else {
            System.out.println(UserControl.RED + "\nInvalid input! Please try again.\n" + UserControl.RESET);
            //welcomeMessage();
        }
    }

    // this part was taken from: https://netcorecloud.com/tutorials/send-email-in-java-using-gmail-smtp/
    static void sendEmail() {
        // Recipient's email ID needs to be mentioned.
        //String to = emailRecipient;
        String to = emailRecipient;

        // Sender's email ID needs to be mentioned
        String from = "fp137202@gmail.com";

        // Assuming you are sending email from through gmail's smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("fp137202@gmail.com", "#11905112");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Password");

            // Now set the actual message
            message.setText(UserControl.pass);

            //System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println(UserControl.GREEN + "\nEmail sent successfully....\n" +
                    "Please check spam folder if you didn't get email!\n"+ UserControl.RESET);
            sent = true;
        } catch (MessagingException mex) {
            System.out.println(UserControl.RED + "\nFailed to send email! Please try again.\n" +
                    "(check your email id and internet connection)\n" + UserControl.RESET);
        }
    }
}

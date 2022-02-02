package controller;

import datamanagement.JDBC;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;


@WebServlet(urlPatterns = "/sendInvitation")
public class SendInvitationController extends HttpServlet {
    static String host = "smtp.gmail.com";
    static String user = ""; // the Mail id
";
    JDBC db = new JDBC();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String invitationurl = req.getParameter("invitationUrl");
        HttpSession httpSession = req.getSession();
        String sender = req.getRemoteUser();
        String recipient = (String) httpSession.getAttribute("friendId");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        final String domainMail = ""; // The Mail
        final String password = ""; // The Password

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(domainMail, password);

            }
        });

        Message message = prepareMessage(session, recipient, sender, invitationurl);

        try {
            assert message != null;
            Transport.send(message);
            System.out.println("Success from mail"); // TODO invited notification - notification
            try {
                db.dml("insert into public.\"NOTIFICATION\" values('"+ sender +"','"+ recipient +"','invitation',now(),FALSE);");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            res.sendRedirect("app");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    private static Message prepareMessage(Session session, String recipient, String sender,  String invitationUrl){
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("mullairajan2000@gmail.com"));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                message.setSubject(sender + "has sent you a Invitation to user this App");
                message.setText(invitationUrl);
                return message;
            }catch (Exception ex){
                System.out.println("Error");
            }
            return null;
    }
}

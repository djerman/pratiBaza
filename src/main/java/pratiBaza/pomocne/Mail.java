package pratiBaza.pomocne;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Embeddable;

@Embeddable
public class Mail implements Serializable{
	
	private static final long serialVersionUID = 1L;

    // Get system properties
    private Properties properties;
    // Get the default Session object.
    private Session session;
    private String from;
    
	public Mail(String server, String port, String nalog, String lozinka) {
		from = nalog;
		properties = System.getProperties();
	    properties.put("mail.smtp.host", server);
	    properties.put("mail.smtp.socketFactory.port", port);
	    //properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.port", port);
	    properties.put("mail.smtp.ssl.enable", "true");
	    session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
	    	@Override
	    	protected PasswordAuthentication getPasswordAuthentication() {
	    		return new PasswordAuthentication(nalog, lozinka);
	    		}
	    	});
	}
	
	public void posaljiMail(String to, String subject, String text) {

	      try {
	    	  // Create a default MimeMessage object.
	    	  Message message = new MimeMessage(session);

	          // Set From: header field of the header.
	          message.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	          // Set Subject: header field
	          message.setSubject(subject);

	          // Now set the actual message
	          message.setText(text);

	          // Send message
	          Transport.send(message);

	       } catch (MessagingException mex) {
	    	   System.out.println("neuspešno... " + to + " ");
	    	   mex.printStackTrace();
	       }
	}
}

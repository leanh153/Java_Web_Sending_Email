package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import model.Email;
import model.User;

public class EmailHelper {
	
	public static boolean sendEmail(HttpServletRequest request) throws AddressException {
		User user =(User) request.getSession().getAttribute("logedinUser");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		// get list Addresses from request
		Address[] toAddressList= InternetAddress.parse(request.getParameter("to"));
		Address[] ccAddressList= InternetAddress.parse(request.getParameter("cc"));
		
		// set properties for sending email
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		// create session
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(user.getEmail(), user.getPassword());
			}
		});
		
		// create message from session and set it's values
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(user.getEmail()));
			message.setSubject(subject);
			message.setContent(content, "text/html; charset=utf-8");
			if (toAddressList!=null && toAddressList.length >0) {
				message.setRecipients(Message.RecipientType.TO, toAddressList);
			}
			
			if (ccAddressList!=null && ccAddressList.length >0) {
				message.setRecipients(Message.RecipientType.CC, ccAddressList);
			}
			Transport.send(message);
			System.out.println("done");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	public static List<Email> listEmail(HttpServletRequest request) throws MessagingException, IOException {
		List<Email> list=new ArrayList<>();
		User user =(User) request.getSession().getAttribute("logedinUser");
		
		Session session = Session.getDefaultInstance(new Properties());
		Store store = session.getStore("imaps");
		// connect store host "imap.googlemail.com", port 993, email and user password
		store.connect("imap.googlemail.com", 993, user.getEmail(), user.getPassword());
		// get in-box folder
		Folder emailFolder = store.getFolder("INBOX");
		// set open read only, other 
		emailFolder.open(Folder.READ_ONLY);
		Message[] msgs= emailFolder.getMessages();
		for (int i = 0; i < msgs.length; i++) {
			Email email = new Email();
			email.setSubject(msgs[i].getSubject());
			email.setFrom(InternetAddress.toString(msgs[i].getFrom()));
			email.setReceivedDate(msgs[i].getReceivedDate());
			Multipart multipart =(Multipart) msgs[i].getContent();
			
			for (int j = 0; j < multipart.getCount(); j++) {
				BodyPart bodyPart = multipart.getBodyPart(j);
				String disposition = bodyPart.getDisposition();
				if (disposition!= null && disposition.equalsIgnoreCase("ATTACHMENT")) {
					DataHandler handle = bodyPart.getDataHandler();
					email.setAttachmentName(handle.getName());
					
				} else {
					email.setContent(bodyPart.getContent().toString());
				}
			}
			
			list.add(email);
		}
		return list;
	}
	public static void main(String[] args) {
		
	}
	
}

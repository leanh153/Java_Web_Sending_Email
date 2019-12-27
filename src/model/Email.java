package model;

import java.io.Serializable;
import java.util.Date;

public class Email implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String subject;
	private String from;
	private String content;
	private String attachmentName;
	private Date receivedDate;
	
	public Email() {
		// TODO Auto-generated constructor stub
	}

	
	public Email(String subject, String from, String content) {
		super();
		this.subject = subject;
		this.from = from;
		this.content = content;
	}



	public Email(String subject, String from, String content, String attachmentName) {
		super();
		this.subject = subject;
		this.from = from;
		this.content = content;
		this.attachmentName = attachmentName;
	}



	public Email(long id, String subject, String from, String content, String attachmentName) {
		super();
		this.id = id;
		this.subject = subject;
		this.from = from;
		this.content = content;
		this.attachmentName = attachmentName;
	}



	


	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getFrom() {
		return from;
	}



	public void setFrom(String from) {
		this.from = from;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getAttachmentName() {
		return attachmentName;
	}



	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	


	public Date getReceivedDate() {
		return receivedDate;
	}


	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}


	
}

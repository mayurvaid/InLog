package com.inlog.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "email")
public class Email extends BaseDataObject {
	@Id
	private String id;
	private String toEmail;
	private String fromEmail;
	private String subject;
	private String body;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((fromEmail == null) ? 0 : fromEmail.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		if (fromEmail == null) {
			if (other.fromEmail != null)
				return false;
		} else if (!fromEmail.equals(other.fromEmail))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Email [toEmail=" + toEmail + ", fromEmail=" + fromEmail
				+ ", subject=" + subject + ", body=" + body
				+ ", getCreateUserId()=" + getCreateUserId()
				+ ", getUpdateUserId()=" + getUpdateUserId()
				+ ", getVersion()=" + getVersion() + ", getCreatedAt()="
				+ getCreatedAt() + ", getLastModified()=" + getLastModified()
				+ ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}

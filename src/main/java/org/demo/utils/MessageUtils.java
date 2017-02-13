package org.demo.utils;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public class MessageUtils {
	private FacesMessage.Severity severity;
	private String idUpdate;
	private String messagePrin;
	private String messageSecon;

	public MessageUtils(Severity severity, String idUpdate, String messagePrin, String messageSecon) {
		this.severity = severity;
		this.idUpdate = idUpdate;
		this.messagePrin = messagePrin;
		this.messageSecon = messageSecon;
	}

	public FacesMessage.Severity getSeverity() {
		return severity;
	}

	public void setSeverity(FacesMessage.Severity severity) {
		this.severity = severity;
	}

	public String getIdUpdate() {
		return idUpdate;
	}

	public void setIdUpdate(String idUpdate) {
		this.idUpdate = idUpdate;
	}

	public String getMessagePrin() {
		return messagePrin;
	}

	public void setMessagePrin(String messagePrin) {
		this.messagePrin = messagePrin;
	}

	public String getMessageSecon() {
		return messageSecon;
	}

	public void setMessageSecon(String messageSecon) {
		this.messageSecon = messageSecon;
	}

}

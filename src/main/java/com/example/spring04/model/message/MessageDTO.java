package com.example.spring04.model.message;

import java.util.Date;

public class MessageDTO {
	private int idx;
	private String receive_id;
	private String sender;
	private String message;
	private Date open_date;
	private Date send_date;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getReceive_id() {
		return receive_id;
	}

	public void setReceive_id(String receive_id) {
		this.receive_id = receive_id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getOpen_date() {
		return open_date;
	}

	public void setOpen_date(Date open_date) {
		this.open_date = open_date;
	}

	public Date getSend_date() {
		return send_date;
	}

	public void setSend_date(Date send_date) {
		this.send_date = send_date;
	}

	@Override
	public String toString() {
		return "MessageDTO [idx=" + idx + ", receive_id=" + receive_id + ", sender=" + sender + ", message=" + message
				+ ", open_date=" + open_date + ", send_date=" + send_date + "]";
	}

}

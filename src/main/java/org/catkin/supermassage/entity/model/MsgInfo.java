package org.catkin.supermassage.entity.model;

import java.util.Date;

import org.catkin.supermassage.service.jms.MsgType;

/**
 * 消息模型
 * 
 * @author Catkin_nice
 *
 */
public class MsgInfo<T> {
	
	private T body;				//消息内容
	private MsgType type;		//消息类型{@like MsgType}
	private Date sendTime;		//发送时间
	private String sender;		//信息发送者ID
	private String receiver;	//信息接收者ID
	
	public MsgInfo() {
		super();
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public MsgType getType() {
		return type;
	}

	public void setType(MsgType type) {
		this.type = type;
	}
	
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public void setSender(long sender) {
		this.sender = Long.toString(sender);
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public void setReceiver(long receiver) {
		this.receiver = Long.toString(receiver);
	}
	
}

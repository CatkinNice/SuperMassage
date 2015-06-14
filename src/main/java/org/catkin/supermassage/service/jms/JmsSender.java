package org.catkin.supermassage.service.jms;

import org.catkin.supermassage.entity.model.MsgInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Catkin_nice
 *
 */
@Component
public class JmsSender {
	
	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	private JmsAdmin jmsAdmin;
	
	@Scheduled(fixedDelay = 1000)
	public <T> void sendMessage(MsgInfo<T> msgInfo) {
		String queueName = "sm.";
		if (MsgType.store.equals(msgInfo.getType())) {
			queueName += "store.";
		} else if (MsgType.user.equals(msgInfo.getType())) {
			queueName += "user.";
		} else if (MsgType.storeExp.equals(msgInfo.getType())) {
			queueName += "storeExp.";
		} else {
			queueName += "test.";
		}
		queueName += msgInfo.getReceiver();
		
		template.setMessageConverter(new Jackson2JsonMessageConverter());
		template.convertAndSend(jmsAdmin.declareQueue(queueName, null), msgInfo.getBody());
	}
}

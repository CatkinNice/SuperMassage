package org.catkin.supermassage.service.jms;

import org.catkin.supermassage.entity.model.MsgInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	public <T> void sendMessage(MsgInfo<T> msgInfo) {
		String queueName = "sm.queue." + msgInfo.getReceiver();
		template.convertAndSend(jmsAdmin.declareQueue(queueName, null), msgInfo.getBody());
	}
}

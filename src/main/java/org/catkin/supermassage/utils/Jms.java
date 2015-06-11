package org.catkin.supermassage.utils;

import java.util.Map;

import javax.validation.constraints.Null;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Catkin_nice
 *
 */
@Component
public class Jms {
	
	@Autowired
	private static RabbitTemplate template;
	
	@Autowired
	private static AmqpAdmin amqpAdmin;
	
	public void sendMessage(String queue) {
		
		template.convertAndSend(queue, "hello Rabbit!");
	}
	
	public static void declareQueue(String name, @Null Map<String, Object> arguments) {
		Queue queue = new Queue(name, true, false, true, arguments);
		amqpAdmin.declareQueue(queue);
	}
}

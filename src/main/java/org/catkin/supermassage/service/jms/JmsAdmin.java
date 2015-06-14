package org.catkin.supermassage.service.jms;

import java.util.Map;

import javax.validation.constraints.Null;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Catkin_nice
 *
 */
@Component
public class JmsAdmin {

	@Autowired
	private AmqpAdmin amqpAdmin;
	
	public String declareQueue(String name, @Null Map<String, Object> arguments) {
		Queue queue = new Queue(name, true, false, true, arguments);
		return amqpAdmin.declareQueue(queue);
	}
	
}

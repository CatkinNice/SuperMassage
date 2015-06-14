package org.catkin.supermassage.service.jms;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Catkin_nice
 *
 */
@Component
public class JmsListener {
	@Autowired
	private JmsAdmin jmsAdmin;

	@Autowired
	private ConnectionFactory connectionFactory;
	
	
//	@Bean
	public SimpleMessageListenerContainer regListener() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setMessageConverter(new Jackson2JsonMessageConverter());
		
		//创建消息监听器(MessageListener|ChannelAwareMessageListener)
	    MessageListenerAdapter messageListener = new MessageListenerAdapter(new MessageListener(){
			@Override
			public void onMessage(Message message) {
				try {
					System.out.println(new String(message.getBody(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
	    });
	    
		String queueName = jmsAdmin.declareQueue("sm.queue.test", null);
		container.setMessageListener(messageListener);
		container.setQueueNames(queueName);
		return container;
	}
}

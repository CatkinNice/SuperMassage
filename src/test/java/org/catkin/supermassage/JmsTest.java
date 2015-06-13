package org.catkin.supermassage;

import org.junit.Test;
import org.catkin.supermassage.entity.model.MsgInfo;
import org.catkin.supermassage.service.jms.JmsSender;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Catkin_nice
 *
 */
public class JmsTest extends BaseTest {
	
	@Autowired
	private JmsSender sender;
	
	@Test
	public void sendMsg() throws InterruptedException {
		MsgInfo<String> info = new MsgInfo<String>();
		info.setBody("hello massage!");
		info.setReceiver("test");
		sender.sendMessage(info);
		Thread.sleep(10000);
	}

}

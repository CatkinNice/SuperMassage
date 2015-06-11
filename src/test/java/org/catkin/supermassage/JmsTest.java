package org.catkin.supermassage;

import org.catkin.supermassage.utils.Jms;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Catkin_nice
 *
 */
public class JmsTest extends BaseTest {
	
	@Autowired
	private Jms jms;
	
	@Test
	public void sendMsg() throws InterruptedException {
		jms.sendMessage("sm.quere.test");
		Thread.sleep(1000 * 8);
	}
	
	@Test
	public void consumer() throws InterruptedException {
		
	}
}

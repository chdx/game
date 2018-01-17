package com.qh.redis.config;

import java.util.concurrent.CountDownLatch;

import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @ClassName MessageListener
 * @Description 消息监听
 * @author chenyuezhi
 * @Date 2017年11月10日 下午5:53:26
 * @version 1.0.0
 * @param <M>
 */
public class MessageListenerRedis implements MessageListener{

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MessageListenerRedis.class);

	private CountDownLatch latch;
	
	
	/**
	 * @Description T
	 * @param latch
	 */
	public MessageListenerRedis(CountDownLatch latch) {
		this.latch = latch;
	}
	

	@Override
	public void onMessage(Message message, byte[] pattern) {
		String channel = new String(message.getChannel());
		String msgKey = new String(message.getBody());
		logger.info("MessageListenerRedis"+ ":" + channel + ":" + msgKey);
		latch.countDown();
	}

}

package com.qh.redis.config;

import java.util.concurrent.CountDownLatch;

import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @ClassName MessageNotifyRedis
 * @Description 通知消息
 * @author chenyuezhi
 * @Date 2017年11月30日 下午2:39:36
 * @version 1.0.0
 */
public class MessageNotifyRedis implements MessageListener{
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MessageNotifyRedis.class);

	private CountDownLatch latch;
	
	
	/**
	 * @Description T
	 * @param latch
	 */
	public MessageNotifyRedis(CountDownLatch latch) {
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

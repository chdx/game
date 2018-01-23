package com.qh.redis.service;

import org.redisson.api.RLock;

public class RedissonLockUtil {
	
    private static RedissonLocker redissLock;
    
    public static void setLocker(RedissonLocker locker) {
        redissLock = locker;
    }
    
    public static RLock getLock(String lockKey){
		return redissLock.getLock(lockKey);
	}
}
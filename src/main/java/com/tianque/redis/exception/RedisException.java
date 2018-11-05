package com.tianque.redis.exception;

/**
 * @author hzh
 *
 */
public class RedisException extends RuntimeException {
    public RedisException() {
        super();
    }

    public RedisException(String message) {
        super(message);
    }

    public RedisException(String message, Throwable cause) {
        super(message, cause);
    }
}
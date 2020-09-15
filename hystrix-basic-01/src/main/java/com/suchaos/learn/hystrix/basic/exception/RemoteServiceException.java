package com.suchaos.learn.hystrix.basic.exception;

/**
 * 远程服务调用异常
 *
 * @author suchao
 * @date 2020/9/14
 */
public class RemoteServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RemoteServiceException(String message) {
        super(message);
    }
}

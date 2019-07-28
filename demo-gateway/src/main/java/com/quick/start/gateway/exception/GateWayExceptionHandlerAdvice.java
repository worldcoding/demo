package com.quick.start.gateway.exception;

import com.quick.start.gateway.common.GateWayErrorCode;
import com.quick.start.gateway.common.HttpResult;
import io.netty.channel.ConnectTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Component
public class GateWayExceptionHandlerAdvice {

    private final static Logger LOGGER = LoggerFactory.getLogger(GateWayExceptionHandlerAdvice.class);

    @ExceptionHandler(value = {DemoException.class})
    @ResponseStatus(HttpStatus.OK)
    public HttpResult handle(DemoException e){
        HttpResult r = new HttpResult();
        r.setCode(e.getErrorCode());
        r.setMsg(e.toString());
        LOGGER.info("{}|{}", e.getErrorCode(), e.toString(),e);
        return r;
    }

    @ExceptionHandler(value = {ResponseStatusException.class})
    @ResponseStatus(HttpStatus.OK)
    public HttpResult handle(ResponseStatusException e) {
        HttpResult r = new HttpResult(GateWayErrorCode.GATEWAY_ERROR.getCode(),GateWayErrorCode.GATEWAY_ERROR.getMessage());
        LOGGER.error("{}|{}", GateWayErrorCode.GATEWAY_ERROR.getCode(),e.getStatus().getReasonPhrase() ,e);
        return r;
    }

    @ExceptionHandler(value = {ConnectTimeoutException.class})
    @ResponseStatus(HttpStatus.OK)
    public HttpResult handle(ConnectTimeoutException e) {
        HttpResult r = new HttpResult(GateWayErrorCode.GATEWAY_CONNECT_TIME_OUT.getCode(),GateWayErrorCode.GATEWAY_CONNECT_TIME_OUT.getMessage());
        LOGGER.error("{}|{}", GateWayErrorCode.GATEWAY_CONNECT_TIME_OUT.getCode(),e.getMessage() ,e);
        return r;
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpResult handle(NotFoundException ex) {
        HttpResult r = new HttpResult(GateWayErrorCode.GATEWAY_NOT_FOUND_SERVICE.getCode(),GateWayErrorCode.GATEWAY_CONNECT_TIME_OUT.getMessage());
        LOGGER.error("{}|{}", GateWayErrorCode.GATEWAY_NOT_FOUND_SERVICE.getCode(),ex.getMessage() ,ex);
        return r;
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResult handle(RuntimeException ex) {
        HttpResult r = new HttpResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), GateWayErrorCode.SYSTEM_ERROR.getMessage());
        LOGGER.error("{}|{}", GateWayErrorCode.SYSTEM_ERROR.getCode(), ex.getMessage(), ex);
        return r;
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public HttpResult handle(Exception ex) {
        HttpResult r = new HttpResult(GateWayErrorCode.SYSTEM_ERROR.getCode(),GateWayErrorCode.SYSTEM_ERROR.getMessage());
        LOGGER.error("{}|{}", GateWayErrorCode.SYSTEM_ERROR.getCode(),ex.getMessage() ,ex);
        return r;
    }

    @ExceptionHandler(value = {Throwable.class})
    public HttpResult handle(Throwable throwable) {
        HttpResult result = new HttpResult();
        if(throwable instanceof DemoException){
            result = handle((DemoException) throwable);
        } else if (throwable instanceof ResponseStatusException) {
            result = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            result = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof NotFoundException) {
            result = handle((NotFoundException) throwable);
        } else if (throwable instanceof RuntimeException) {
            result = handle((RuntimeException) throwable);
        } else if (throwable instanceof Exception) {
            result = handle((Exception) throwable);
        }
        return result;
    }
}

package com.hzdracom.core.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hzdracom.core.util.ResponseEntityUtil;
import com.hzdracom.core.util.ResultCode;
import com.hzdracom.core.util.ResultValue;


/**
 * 
* @ClassName: DefaultExceptionHandler 
* @Description:  统一异常处理的公共类
* @author 刘章 
* @date 2016年6月29日 下午4:56:10 
*
 */
@ControllerAdvice
public class DefaultExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> doResolveHandlerMethodException(Exception ex) {
        ResultValue resultValue = new ResultValue();
        if (ex instanceof IllegalArgumentException) {
        	resultValue.setCode(ResultCode.RC_1001);
        	resultValue.setResult(ex.getMessage());
        } else if (ex instanceof ClassCastException) {
        	resultValue.setCode(ResultCode.RC_3003);
        	resultValue.setResult("参数类型不匹配");
        } else if (ex instanceof ApiException) {
            ApiException apiException = (ApiException) ex;
            resultValue.setCode(apiException.getCode());
            resultValue.setResult(apiException.getResult());
        } else {
        	resultValue.setCode(ResultCode.RC_9999);
        	resultValue.setResult("系统异常");
        }
        log.error("doResolveHandlerMethodException", ex);
        return ResponseEntityUtil.successResponse(resultValue);
    }
}

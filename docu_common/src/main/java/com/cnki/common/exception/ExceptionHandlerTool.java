package com.cnki.common.exception;

import com.cnki.common.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一错误处理
 */
@ControllerAdvice
public class ExceptionHandlerTool {

    @ResponseBody
    @ExceptionHandler(DocuManaException.class)
    public Result error(DocuManaException e) {
        return Result.error(e.getCode(), e.getMsg());
    }

}

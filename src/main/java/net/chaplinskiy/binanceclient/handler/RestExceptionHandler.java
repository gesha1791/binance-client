package net.chaplinskiy.binanceclient.handler;

import net.chaplinskiy.binanceclient.dto.error.ErrorDetail;

import net.chaplinskiy.binanceclient.exception.BadGatewayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BadGatewayException.class)
    public ResponseEntity<?> handleBadGatewayException(BadGatewayException bfe, HttpServletRequest request){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(LocalDateTime.now());
        errorDetail.setStatus(HttpStatus.BAD_GATEWAY.value());
        errorDetail.setTitle("Unbelievable Buy");
        errorDetail.setDeveloperMessage(bfe.getClass().getName());

        return new ResponseEntity<Object>(errorDetail, null, HttpStatus.BAD_GATEWAY);
    }
}

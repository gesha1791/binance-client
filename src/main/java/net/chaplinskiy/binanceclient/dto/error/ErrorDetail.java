package net.chaplinskiy.binanceclient.dto.error;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ErrorDetail {
    private String title;
    private int status;
    private String detail;
    private LocalDateTime timeStamp;
    private String developerMessage;
}

package me.luraframework.logging.aspect;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Log {
    private Long id;
    private String logType;
    private String description;
    private String method;
    private String params;
    private Long time;
    private String browser;
    private String ip;


    public Log(String logType, Long time, String browser, String ip) {
        this.logType = logType;
        this.time = time;
        this.ip = ip;
        this.browser = browser;
    }
}

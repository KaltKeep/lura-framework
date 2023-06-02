package me.luraframework.logging.oprationlog;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: TODO
 * @author: LiuRan
 * @data: 10/25/22 15:50
 */
@Data
@NoArgsConstructor
public class OperationLog {
    private Long id;
    private String logType;
    private String description;
    private String method;
    private String params;
    private Long time;
    private String browser;
    private String ip;


    public OperationLog(String logType, Long time, String browser, String ip) {
        this.logType = logType;
        this.time = time;
        this.ip = ip;
        this.browser = browser;
    }
}

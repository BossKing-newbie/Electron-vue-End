package com.fosu.software.delivery.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.domain
 * @Author: Boss_king
 * @CreateTime: 2020-07-07 22:16
 * @Description: 物流信息时间戳
 */
public class DeliveryTimestamp implements Serializable {
    // 定义时间转换格式
    private static final String DDFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_ZONE = "GMT+8";

    private String content;
    @JsonFormat(pattern = DDFormat,timezone = TIME_ZONE)
    private Timestamp  timestamp;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "DeliveryTimestamp{" +
                "content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

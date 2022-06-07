package com.xsk.test.util;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SnowFlowUtil {

    private long workerId;

    @PostConstruct
    public void init() {
        try {
            // 将网络ip转换成long
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr()) % 32;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized String snowflakeId() {
        return String.valueOf(IdUtil.getSnowflake(workerId).nextIdStr());
    }
}

package com.ruoyi.code.config.snow;

import cn.hutool.core.net.NetUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

/**
 * @program: ruoyi
 * @description:
 * @author: zhendong.wu
 * @create: 2022-03-09 18:04
 **/
@Component
@ConfigurationProperties(prefix = "snowflake")
public class SnowFlakeConfig {
    private long workerId;

    @NotNull
    private long dataCenterId;

    @PostConstruct
    public void init() {
        try {
            String ipAddr = NetUtil.getLocalhost().getHostAddress();
            int[] ints = StringUtils.toCodePoints(ipAddr);
            int sums = 0;
            for (int b : ints) {
                sums += b;
            }
            //log.info("current ip :{}", ipAddr);
            if (!StringUtils.isEmpty(ipAddr)) {
                String[] ipStep = ipAddr.split("\\.");
                this.workerId = Long.valueOf( (sums % 32));
            } else {
                // log.warn("ip address parse error.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //   log.info("Snowflake:[workerId:{} from ip address tail, dataCenterId:{} from properties] ", workerId, dataCenterId);
    }

    public String loclHost(){
        return NetUtil.getLocalhost().getHostAddress();
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public long getDataCenterId() {
        return dataCenterId;
    }

    public void setDataCenterId(long dataCenterId) {
        this.dataCenterId = dataCenterId;
    }
}

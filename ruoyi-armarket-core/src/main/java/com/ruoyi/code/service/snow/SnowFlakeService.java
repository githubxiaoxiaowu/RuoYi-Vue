package com.ruoyi.code.service.snow;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.ruoyi.code.config.snow.SnowFlakeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @program: ruoyi
 * @description:
 * @author: zhendong.wu
 * @create: 2022-03-09 18:03
 **/
@Service
public class SnowFlakeService {
    @Autowired
    private SnowFlakeConfig snowFlakeConfig;

    private static Snowflake snowflake;

    @PostConstruct
    public void init() {
        long wokerid = snowFlakeConfig.getWorkerId();
        long dataCenterId = snowFlakeConfig.getDataCenterId();
        if(snowflake == null){
            snowflake = IdUtil.createSnowflake(wokerid, dataCenterId);
        }
    }

    public synchronized Long nextId() {
        return snowflake.nextId();
    }
}

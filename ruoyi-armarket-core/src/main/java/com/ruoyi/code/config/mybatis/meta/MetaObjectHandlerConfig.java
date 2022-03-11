package com.ruoyi.code.config.mybatis.meta;

import cn.hutool.core.net.NetUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.code.service.snow.SnowFlakeService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @program: ruoyi
 * @description:
 * @author: zhendong.wu
 * @create: 2022-03-09 18:03
 **/
@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {
    @Resource
    private SnowFlakeService snowFlakeService;
    @Value("${spring.profiles.active}")
    private String active;

    @Override
    public void insertFill(MetaObject metaObject) {

        Object createTime = getFieldValByName("createTime",metaObject);
        if(createTime==null){
            setFieldValByName("createTime", new Date(), metaObject);
        }
        Object optimistic = getFieldValByName("optimistic",metaObject);
        if(optimistic==null){
            setFieldValByName("optimistic", 0L, metaObject);
        }
        Object id = getFieldValByName("id",metaObject);

        if(id==null){
            setFieldValByName("id", snowFlakeService.nextId(), metaObject);
        }
        try{
            SysUser user = SecurityUtils.getLoginUser().getUser();
            Object userId = getFieldValByName("userId",metaObject);
            if(userId==null){
                setFieldValByName("userId", user.getUserId(), metaObject);
            }
        }catch (Exception e){
            if(!"dev".equals(active)){
                throw  e;
            }
            setFieldValByName("userId", 1L, metaObject);

        }




    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", new Date(), metaObject);
    }

}

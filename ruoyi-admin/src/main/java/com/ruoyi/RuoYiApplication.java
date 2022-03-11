package com.ruoyi;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import redis.embedded.RedisServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.io.IOException;
import java.util.Properties;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@Slf4j
@EnableSwagger2WebMvc
@MapperScan(basePackages = "com.ruoyi.**.mapper")
public class RuoYiApplication
{
    public static void main(String[] args) throws IOException {
     /*   Properties properties=  PropertiesLoaderUtils.loadProperties(new ClassPathResource("system.properties"));
        String active=properties.getProperty("redis.profiles.active");
        log.info("active={}", active);
        if ("dev".equals(active)) {
            //启动内嵌redis服务
            RedisServer redisServer = new RedisServer(Integer.valueOf(properties.getProperty("spring.redis.port")));
            redisServer.start();
        }*/
        SpringApplication.run(RuoYiApplication.class, args);

    }
}

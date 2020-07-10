package com.niu.springboot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 *
 * @version 1.0 [2020/07/09 15:11]
 * @author [nza]
 * @createTime [2020/07/09 15:11]
 */
@Configuration
@ComponentScan({"com.niu.springboot.*"})
public class MainConfig {
}

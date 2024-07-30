package com.my.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.my.project.**.mapper")
public class MybatisConfig {

}

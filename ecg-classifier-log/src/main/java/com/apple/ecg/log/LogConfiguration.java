package com.apple.ecg.log;

import com.apple.ecg.mybatis.MybatisConfiguration;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 04/03/2018 1:29 PM
 */
@SpringBootConfiguration
@Import({
        MybatisConfiguration.class
})
@ComponentScan("com.apple.ecg.log")
public class LogConfiguration {
}

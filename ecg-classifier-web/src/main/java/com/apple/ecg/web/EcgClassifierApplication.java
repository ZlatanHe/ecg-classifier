package com.apple.ecg.web;

import com.apple.ecg.log.LogConfiguration;
import com.apple.ecg.matlabcontrol.MatlabControlConfiguration;
import com.apple.ecg.mybatis.MybatisConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 04/03/2018 12:11 AM
 */
@SpringBootApplication
@Import({
        MybatisConfiguration.class,
        LogConfiguration.class,
        MatlabControlConfiguration.class
})
public class EcgClassifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcgClassifierApplication.class, args);
    }
}

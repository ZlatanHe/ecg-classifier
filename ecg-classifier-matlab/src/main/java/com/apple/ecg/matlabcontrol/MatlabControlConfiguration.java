package com.apple.ecg.matlabcontrol;

import com.apple.ecg.log.LogConfiguration;
import com.apple.ecg.matlabcontrol.property.MatlabControlProperties;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;
import matlabcontrol.extensions.MatlabTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 03/03/2018 10:31 PM
 */
@SpringBootConfiguration
@EnableConfigurationProperties
@Import({
        LogConfiguration.class
})
@ComponentScan("com.apple.ecg.matlabcontrol")
public class MatlabControlConfiguration {

    @Bean
    public MatlabProxyFactory matlabProxyFactory() {
        return new MatlabProxyFactory();
    }

    @Bean
    @Autowired
    public MatlabProxy matlabProxy(MatlabProxyFactory matlabProxyFactory) {
        MatlabProxy proxy = null;
        try {
            proxy = matlabProxyFactory.getProxy();
        } catch (MatlabConnectionException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            return proxy;
        }
    }

    @Bean
    @Autowired
    public MatlabTypeConverter matlabTypeConverter(MatlabProxy matlabProxy) {
        return new MatlabTypeConverter(matlabProxy);
    }

    @Bean
    public MatlabControlProperties matlabControlProperties() {
        return new MatlabControlProperties();
    }
}

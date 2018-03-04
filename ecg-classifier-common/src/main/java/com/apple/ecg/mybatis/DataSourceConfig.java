package com.apple.ecg.mybatis;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 04/03/2018 6:43 PM
 */
@ConfigurationProperties("spring.datasource")
public class DataSourceConfig {

    private String url;

    private String username;

    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

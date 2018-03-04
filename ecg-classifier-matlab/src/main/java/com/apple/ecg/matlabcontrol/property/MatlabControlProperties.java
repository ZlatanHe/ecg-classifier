package com.apple.ecg.matlabcontrol.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 03/03/2018 10:51 PM
 */
@ConfigurationProperties(prefix = "matlab.control")
@Component
public class MatlabControlProperties {

    private Detector detector;

    public Detector getDetector() {
        return detector;
    }

    public void setDetector(Detector detector) {
        this.detector = detector;
    }

    public static class Detector {

        private String path;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}

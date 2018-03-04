package com.apple.ecg.matlabcontrol.property;

import com.apple.ecg.matlabcontrol.MatlabControlConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 03/03/2018 11:49 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MatlabControlConfiguration.class)
public class MatlabControlPropertiesTest {

    @Autowired
    private MatlabControlProperties matlabControlProperties;

    @Test
    public void testDetectorPath() {
        Assert.assertEquals("TestMatlabControlDetectorPath", matlabControlProperties.getDetector().getPath());
    }
}

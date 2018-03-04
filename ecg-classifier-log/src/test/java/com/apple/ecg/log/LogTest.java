package com.apple.ecg.log;

import com.apple.ecg.log.constant.HeartbeatTypeEnum;
import com.apple.ecg.log.entity.HeartbeatLog;
import com.apple.ecg.log.entity.Patient;
import com.apple.ecg.log.service.LogService;
import com.apple.ecg.log.view.ViewConverter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 04/03/2018 4:32 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        LogConfiguration.class
})
public class LogTest {

    @Autowired
    LogService logService;

    @Autowired
    ViewConverter viewConverter;

    final String testPatientId = "550bb16ba58e46759bc5df4f78409fec";

    @Test
    public void testPatientQuery() {
        Patient patient = logService.getByPatientId(testPatientId);
        Assert.assertEquals(patient.getPatientName(), "Austin Rivers");
    }

    @Test
    public void testLogQuery() {
        List<HeartbeatLog> heartbeatLogList = logService.queryLog(testPatientId);
        Assert.assertTrue(!heartbeatLogList.isEmpty());
    }

    @Test
    public void testLogInsert() {
        logService.writeLog(HeartbeatTypeEnum.SUPRA_VENTRICULAR_ECTOPIC_BEAT.getCode(), testPatientId);
        List<HeartbeatLog> heartbeatLogList = logService.queryLog(testPatientId);
        Assert.assertTrue(!heartbeatLogList.isEmpty());
    }

    @Test
    public void testViewConverter() {
        Patient patient = logService.getByPatientId(testPatientId);
        System.out.println(viewConverter.convertHeartbeatLogList2Html(patient.getPatientName(), logService.queryLog(testPatientId)));
    }
}

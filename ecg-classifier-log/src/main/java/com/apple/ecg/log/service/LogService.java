package com.apple.ecg.log.service;

import com.apple.ecg.log.constant.HeartbeatTypeEnum;
import com.apple.ecg.log.entity.HeartbeatLog;
import com.apple.ecg.log.entity.Patient;
import com.apple.ecg.log.mapper.db.HeartbeatLogDbMapper;
import com.apple.ecg.log.mapper.db.PatientDbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 04/03/2018 3:19 PM
 */
@Component
public class LogService {

    @Autowired
    private PatientDbMapper patientDbMapper;

    @Autowired
    private HeartbeatLogDbMapper heartbeatLogDbMapper;

    public Patient getByPatientId(String patientId) {
        return patientDbMapper.getByPatientId(patientId);
    }

    public void writeLog(int typeCode,
                         String patientId) {
        Patient patient = patientDbMapper.getByPatientId(patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Invalid patient");
        }
        HeartbeatLog heartbeatLog = new HeartbeatLog();
        heartbeatLog.setPatientId(patientId);
        heartbeatLog.setPatientName(patient.getPatientName());
        heartbeatLog.setHeartBeatType(HeartbeatTypeEnum.findByCode(typeCode).name());
        heartbeatLogDbMapper.insert(heartbeatLog);
    }

    public List<HeartbeatLog> queryLog(String patientId) {
        return heartbeatLogDbMapper.queryByPatientId(patientId);
    }
}

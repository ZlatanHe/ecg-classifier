package com.apple.ecg.web.controller;

import com.apple.ecg.log.entity.Patient;
import com.apple.ecg.log.service.LogService;
import com.apple.ecg.log.view.ViewConverter;
import com.apple.ecg.matlabcontrol.agent.EcgClassifierAgent;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 04/03/2018 4:03 PM
 */
@RestController
@RequestMapping("/ecg")
public class EcgController {

    @Autowired
    private EcgClassifierAgent ecgClassifierAgent;

    @Autowired
    private LogService logService;

    @Autowired
    private ViewConverter viewConverter;

    @RequestMapping(value = "/classification/{patientId}/{ecg}",
            produces = MediaType.TEXT_XML_VALUE)
    public String ecgClassify(@PathVariable("patientId") String patientId,
                              @PathVariable("ecg") String ecgArray) {
        return ecgClassifierAgent.classify(ecgArray, patientId);
    }

    @RequestMapping(value = "error_log",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String queryLog(@Param("patientId") String patientId) {
        Patient patient = logService.getByPatientId(patientId);
        return viewConverter.convertHeartbeatLogList2Html(patient.getPatientName(), logService.queryLog(patientId));
    }
}

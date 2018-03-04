package com.apple.ecg.log.entity;

import java.util.Date;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 04/03/2018 1:54 PM
 */
public class Patient {

    private Long id;

    private String patientId;

    private String patientName;

    private Integer gender;

    private Integer age;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

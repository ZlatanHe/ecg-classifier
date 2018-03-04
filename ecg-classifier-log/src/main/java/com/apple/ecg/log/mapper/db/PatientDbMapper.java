package com.apple.ecg.log.mapper.db;

import com.apple.ecg.log.entity.Patient;
import org.apache.ibatis.annotations.*;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 04/03/2018 2:27 PM
 */
public interface PatientDbMapper {

    @Insert("INSERT INTO `patient` " +
            "(`patient_id`, " +
            "`patient_name`, " +
            "`gender`, " +
            "`age`, " +
            "`create_time`" +
            ") " +
            "VALUES(" +
            "#{patientId}, " +
            "#{patientName}, " +
            "#{gender}, " +
            "#{age}, " +
            "now()" +
            ")")
    void insert(Patient patient);

    @Select("SELECT * FROM `patient` WHERE `patient_id` = #{patientId}")
    @Results(
            id = "Patient",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "patientId", column = "patient_id"),
                    @Result(property = "patientName", column = "patient_name"),
                    @Result(property = "gender", column = "gender"),
                    @Result(property = "age", column = "age"),
                    @Result(property = "createTime", column = "create_time")
            }
    )
    Patient getByPatientId(@Param("patientId") String patientId);
}

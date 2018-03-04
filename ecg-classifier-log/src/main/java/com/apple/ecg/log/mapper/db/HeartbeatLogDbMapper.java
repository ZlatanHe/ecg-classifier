package com.apple.ecg.log.mapper.db;

import com.apple.ecg.log.entity.HeartbeatLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 04/03/2018 1:40 PM
 */
public interface HeartbeatLogDbMapper {

    @Insert("INSERT INTO `heartbeat_log` " +
            "(" +
            "`patient_id`, " +
            "`patient_name`, " +
            "`heartbeat_type`, " +
            "`create_time`" +
            ") " +
            "VALUES(" +
            "#{patientId}, " +
            "#{patientName}, " +
            "#{heartBeatType}, " +
            "now()" +
            ")")
    void insert(HeartbeatLog heartbeatLog);

    @Select("SELECT * FROM `heartbeat_log` " +
            "WHERE `patient_id` = #{patientId} " +
            "AND `heartbeat_type` <> 'NORMAL' " +
            "ORDER BY `create_time` DESC")
    @Results(
            id = "HeartbeatLog",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "patientId", column = "patient_id"),
                    @Result(property = "patientName", column = "patient_name"),
                    @Result(property = "heartBeatType", column = "heartbeat_type"),
                    @Result(property = "createTime", column = "create_time")
            }
    )
    List<HeartbeatLog> queryByPatientId(@Param("patientId") String patientId);
}

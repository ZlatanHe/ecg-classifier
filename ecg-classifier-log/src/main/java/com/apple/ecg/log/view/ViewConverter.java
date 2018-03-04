package com.apple.ecg.log.view;

import com.apple.ecg.log.entity.HeartbeatLog;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 04/03/2018 3:32 PM
 */
@Component
public class ViewConverter {

    private static final String HEARTBEAT_LOG_HTML_TEMPLATE = "<html>" +
            "<head>" +
            "<style>table {border-collapse: collapse;width: 100%%;}" +
            "th, td {text-align: left;padding: 8px;}" +
            "tr:nth-child(even){background-color: #f2f2f2}" +
            "th {background-color: #4CAF50;color: white;}</style>" +
            "</head>" +
            "<body><h2>Arrhythmia Log<br/><i>Patient Name: %s</i></h2>" +
            "<table>" +
            "<tr><th>Arrhythmia Type</th><th>Time</th></tr>" +
            "%s" +
            "</table>" +
            "</body>" +
            "</html>";

    private static final String TABLE_ROW_TEMPLATE = "<tr><td>%s</td><td>%s</td></tr>";

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public String convertHeartbeatLogList2Html(String patientName,
                                               List<HeartbeatLog> heartbeatLogList) {
        StringBuilder content = new StringBuilder();
        for (HeartbeatLog log : heartbeatLogList) {
            content.append(String.format(
                    TABLE_ROW_TEMPLATE,
                    log.getHeartBeatType(),
                    simpleDateFormat.format(log.getCreateTime())));
        }
        return String.format(
                HEARTBEAT_LOG_HTML_TEMPLATE,
                patientName,
                content.toString()
        );
    }
}

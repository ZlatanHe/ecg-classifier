package com.apple.ecg.matlabcontrol.agent;

import com.apple.ecg.log.constant.HeartbeatTypeEnum;
import com.apple.ecg.log.service.LogService;
import com.apple.ecg.matlabcontrol.property.MatlabControlProperties;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.extensions.MatlabNumericArray;
import matlabcontrol.extensions.MatlabTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 03/03/2018 10:24 PM
 */
@Component
public class EcgClassifierAgent {

    @Autowired
    private MatlabProxy matlabProxy;

    @Autowired
    private MatlabTypeConverter matlabTypeConverter;

    @Autowired
    private MatlabControlProperties matlabControlProperties;

    @Autowired
    private LogService logService;

    public String classify(String ecg,
                           String patientId) {
        //Handling the input ECG
        String[] ecgArr = ecg.split(" ");
        int ecgLength = ecgArr.length;
        double[][] ecgToBeClassified = new double[1][ecgLength];
        for (int i = 0; i < ecgLength; i++) {
            ecgToBeClassified[0][i] = Double.parseDouble(ecgArr[i]);
        }

        //Ensure matlab process works in a write path
        shiftPath();

        String res = "";
        try {
            matlabTypeConverter.setNumericArray("ecg", new MatlabNumericArray(ecgToBeClassified,null));
            matlabProxy.eval("output = classfication(ecg)");
            MatlabNumericArray array = matlabTypeConverter.getNumericArray("output");
            final double[][] output = array.getRealArray2D();
            final double[] labels = output[0];

            storeHeartbeats(labels, patientId);

            boolean normal = true;
            ArrayList<Integer> sequence = new ArrayList<Integer>();
            ArrayList<String> type = new ArrayList<String>();
            String[] types = {"Normal","Supraventricular ectopic beat","Ventricular ectopic beat","Fusion beat"};
            if (labels[0] == 0) {
                res = "<return><status>0</status></return>";
            } else {
                for (int i = 0; i < labels.length; i++) {
                    int label = (int) labels[i];
                    if (label != 1) {
                        normal = false;
                        sequence.add(i);
                        type.add(types[label-1]);
                    }
                }
                if (normal) {
                    res = "<return><status>1</status></return>";
                } else {
                    int count = sequence.size();
                    res = "<return><status>2</status>";
                    res = res + "<arrhythmia>" + count + "</arrhythmia>";
                    Integer[] seq = new Integer[count];
                    String[] tp = new String[count];
                    res += extractProblemBeats(sequence.toArray(seq),type.toArray(tp));
                    res += "</return>";
                }
            }
        } catch (MatlabInvocationException e) {
            e.printStackTrace();
        }
        return res;
    }

    private String extractProblemBeats(Integer[] sequence, String[] type) {
        shiftPath();
        String res = "";
        try {
            matlabProxy.eval("d = load('savedFeature.mat');");
            matlabProxy.eval("featureMat = getfield(d,'savedFeature');");
            MatlabNumericArray array = matlabTypeConverter.getNumericArray("featureMat");
            double[][] featureMat = array.getRealArray2D();
            for (int i = 0; i < sequence.length; i++) {
                int seq = i + 1;
                String tagType1 = "<type" + seq + ">";
                String tagType2 = "</type" + seq + ">";
                String tagECG1 = "<ecg" + seq + ">";
                String tagECG2 = "</ecg" + seq + ">";
                res = res + tagType1 + type[i] + tagType2 + tagECG1;
                double[] beat = featureMat[sequence[i]];
                for (int j = 0; j < beat.length; j++) {
                    res += (int)beat[j];
                    if (j + 1 < beat.length) res += " ";
                }
                res += tagECG2;
            }
        } catch (MatlabInvocationException e) {
            e.printStackTrace();
        }
        return res;
    }

    private void shiftPath() {
        try {
            matlabProxy.eval(String.format("path(path,'%s');", matlabControlProperties.getDetector().getPath()));
        } catch (MatlabInvocationException e) {
            e.printStackTrace();
        }
    }

    private void storeHeartbeats(double[] labels,
                                 String patientId) {
        if (labels == null || labels[0] == 0) {
            return;
        }
        for (int i = 0; i < labels.length; i++) {
            int label = (int) labels[i] - 1;
            HeartbeatTypeEnum heartbeatTypeEnum = HeartbeatTypeEnum.findByCode(label);
            if (heartbeatTypeEnum != null) {
                logService.writeLog(heartbeatTypeEnum.getCode(), patientId);
            }
        }
    }
}

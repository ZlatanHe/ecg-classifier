package com.apple.ecg.log.constant;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 04/03/2018 3:25 PM
 */
public enum HeartbeatTypeEnum {

    NORMAL(0),
    SUPRA_VENTRICULAR_ECTOPIC_BEAT(1),
    VENTRICULAR_ECTOPIC_BEAT(2),
    FUSION_BEAT(3);

    private int code;

    HeartbeatTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static HeartbeatTypeEnum findByCode(int code) {
        for (HeartbeatTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}

package com.apple.ecg.util;

import java.util.UUID;

/**
 * @Title:
 * @Description:
 * @Date: Created By hewei in 04/03/2018 1:56 PM
 */
public class IdUtil {

    public static String create() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}

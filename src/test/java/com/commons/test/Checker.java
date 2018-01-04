package com.commons.test;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.routines.IntegerValidator;

import java.util.Objects;

/**
 * Created by lenovo on 2018/1/3.
 */
public class Checker {
    public void checkRequired(FiledConstants.FiledType filedType, Object value, Object compareValue) throws Exception {
        if (Objects.isNull(value)) {
            throw new Exception(" 为空null");
        }

        if (filedType == FiledConstants.FiledType.STRING) {
            if (StringUtils.isBlank(String.valueOf(value))) {
                throw new Exception("为空");
            }
        }
    }

    public void checkRange(FiledConstants.FiledType filedType, Object value, Object compareValue) throws Exception {
        boolean ret = false;
        String[] range = compareValue.toString().split(",");
        String min = range[0];
        String max = range[1];

        switch (filedType) {
            case INT:
                IntegerValidator.getInstance().isInRange((Integer) value, NumberUtils.toInt(min), NumberUtils.toInt(max));
                break;

            case STRING:
                break;
        }
        if (!ret) {
            throw new Exception("不在范围内！");
        }
    }
}

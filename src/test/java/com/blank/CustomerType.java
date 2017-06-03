package com.blank;

/**
 * 定义顾客类型
 *
 * @author inth-liqiuwei
 * @version V1.0
 * @ClassName: CustomerType
 * @Description: TODO
 * @date 2015年7月19日 下午1:54:54
 */
public enum CustomerType {
    COMMON, EXPRESS, VIP;

    public String toString() {
        switch (this) {
            case COMMON:
                return "【普通】";
            case EXPRESS:
                return "【快捷】";
            case VIP:
                return "【VIP】";
        }

        return null;
    }

}

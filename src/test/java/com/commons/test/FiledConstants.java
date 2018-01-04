package com.commons.test;

/**
 * Created by lenovo on 2018/1/3.
 */
public interface FiledConstants {
    enum FiledType {
        INT(1, "int"), STRING(2, "string");
        private Integer type;
        private String name;

        FiledType(Integer type, String name) {
            this.type = type;
            this.name = name;
        }

        public Integer getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public static FiledType getFiledType(int type) {
            for (FiledType filedType : FiledType.values()) {
                if (filedType.getType().equals(type)) {
                    return filedType;
                }
            }
            return null;
        }
    }

    enum ValiadType {
        REQURIE(1, "Required"), RANG(2, "Range");
        private Integer type;
        private String name;

        ValiadType(Integer type, String name) {
            this.type = type;
            this.name = name;
        }

        public Integer getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public static ValiadType getValiadType(int type) {
            for (ValiadType valiadType : ValiadType.values()) {
                if (valiadType.getType().equals(type)) {
                    return valiadType;
                }
            }
            return null;
        }
    }
}

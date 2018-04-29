package com.java.basic;

import java.util.LinkedHashMap;
import java.util.Map;

public class AboutMap {
    public static void main(String[] args) {

        Map<String, Object> detailInfo = new LinkedHashMap<>();
        detailInfo.put("最小储值",30);
        detailInfo.put("最大储值",60);
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : detailInfo.entrySet()) {
            stringBuilder.append(entry.getKey()).append(":").append(entry.getValue()).append("    ");
        }
        System.out.println(stringBuilder);
    }
}

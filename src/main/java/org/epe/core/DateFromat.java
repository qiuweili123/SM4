package org.epe.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���ڴ�����
 *
 * @author libo
 */
public class DateFromat {

    /**
     * ��String����ת����date����
     *
     * @param old_date
     * @param paten
     * @return
     */
    public static Date formatDate(String old_date, String paten) {
        Date date = null;
        if (old_date == null || "".equals(old_date)) return null;
        SimpleDateFormat tempDate = new SimpleDateFormat(paten);
        try {
            date = tempDate.parse(old_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    /**
     * ��ʽ�����ڣ�����������ת����string��
     *
     * @param old_date
     * @param paten
     * @return
     */
    public static String formatDate(Date old_date, String paten) {
        String date = null;
        if (old_date == null) return null;
        SimpleDateFormat tempDate = new SimpleDateFormat(paten);
        try {
            date = tempDate.format(old_date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}

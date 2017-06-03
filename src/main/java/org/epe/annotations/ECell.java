package org.epe.annotations;

import java.lang.annotation.*;

/**
 * Excelӳ��ע�⣬���Խ��ֶ�ӳ�䵽Excel��
 *
 * @author Administrator
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ECell {

    /**
     * ��ʾ������
     *
     * @return
     */
    public String name();

    /**
     * �Ƿ�����
     *
     * @return
     */
    public boolean isHidden() default false;
}

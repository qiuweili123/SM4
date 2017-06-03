package org.mec.validation.annotation;

import java.lang.annotation.*;

/**
 * �ֻ�������֤
 *
 * @author ������
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {

    /**
     * ��֤�ֻ�����
     *
     * @return
     */
    public String pattern() default "^1[3|4|5|8][0-9]\\d{4,8}$";
}

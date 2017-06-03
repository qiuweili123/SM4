package org.mec.validation.annotation;

import java.lang.annotation.*;

/**
 * �����ʼ���֤
 *
 * @author ������
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    /**
     * ��֤�����ʼ�
     *
     * @return
     */
    public String pattern() default "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
}

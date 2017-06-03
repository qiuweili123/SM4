package org.mec.validation.annotation;

import java.lang.annotation.*;

/**
 * �ʱ���֤
 *
 * @author ������
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZipCode {
    /**
     * ��֤�ʱ�
     *
     * @return
     */
    public String pattern() default "[1-9]{1}(\\d+){5}";
}

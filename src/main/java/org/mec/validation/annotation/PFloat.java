package org.mec.validation.annotation;

import java.lang.annotation.*;

/**
 * ��֤��������
 *
 * @author ������
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PFloat {
    /**
     * ��֤��������
     *
     * @return
     */
    public String pattern() default "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";
}

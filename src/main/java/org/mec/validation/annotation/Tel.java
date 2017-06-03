package org.mec.validation.annotation;

import java.lang.annotation.*;

/**
 * �绰������֤
 *
 * @author ������
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tel {
    /**
     * ��֤�绰����
     *
     * @return
     */
    public String pattern() default "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";
}

package org.mec.validation.annotation;

import java.lang.annotation.*;

/**
 * ���֤��֤
 *
 * @author ������
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IDCard {

    /**
     * ��֤���ͣ�Ĭ��Ϊ18λ��֤
     *
     * @return
     */
    public String new_pattern() default "^\\s*\\d{16}[\\dxX]{2}\\s*$";

    /**
     * ��֤���ͣ�Ĭ��Ϊ15λ��֤
     *
     * @return
     */
    public String pattern() default "^\\s*\\d{15}\\s*$";
}

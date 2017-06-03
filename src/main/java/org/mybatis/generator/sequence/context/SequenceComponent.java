package org.mybatis.generator.sequence.context;

import org.mybatis.generator.api.IntrospectedTable;

/**
 * 建造组件
 *
 * @author liqiuwei
 */
public interface SequenceComponent {
    public void createGeneratedKey(IntrospectedTable introspectedTable);
}

package org.mybatis.generator.sequence.context;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.config.GeneratedKey;

/**
 * 构造懒加载单例
 *
 * @author liqiuwei
 */
public class SequenceFactory {

    private SequenceFactory() {

    }

    public static SequenceFactory getInstance() {
        return SequenceHoder.INSTANCE;
    }

    /**
     * 创建对应生成序列的工厂
     *
     * @param generatorClassName
     * @return
     */
    public GeneratedKey createSequenceContext(IntrospectedTable introspectedTable) {
        SequenceComponent context = null;
        try {
            String generatorClassName = introspectedTable.getGeneralSequenceGenerator();
            if (StringUtils.isNotEmpty(generatorClassName)) {
                context = (SequenceComponent) Class.forName(generatorClassName).newInstance();
                context.createGeneratedKey(introspectedTable);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return introspectedTable.getGeneratedKey();
    }

    static class SequenceHoder {
        private final static SequenceFactory INSTANCE = new SequenceFactory();

    }

}

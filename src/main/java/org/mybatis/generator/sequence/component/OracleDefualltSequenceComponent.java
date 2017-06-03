package org.mybatis.generator.sequence.component;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.sequence.builder.GeneratedKeyBuilder.Builder;
import org.mybatis.generator.sequence.context.SequenceComponent;

public class OracleDefualltSequenceComponent implements SequenceComponent {

    private final static String COLUMN = "id";
    /**
     * 从表名多少位开始截取
     */
    private final static Integer SUB_TABLE_PRE = 5;
    protected TableConfiguration tableConfiguration;
    protected FullyQualifiedTable fullyQualifiedTable;
    private String configuredSqlStatement = "select SEQ_tableName.nextval from dual ";

    @Override
    public void createGeneratedKey(IntrospectedTable introspectedTable) {
        tableConfiguration = introspectedTable.getTableConfiguration();
        fullyQualifiedTable = introspectedTable.getFullyQualifiedTable();
        Builder builder = new Builder();
        builder.column(COLUMN);
        builder.runtimeSqlStatement(getConfiguredSqlStatement());
        tableConfiguration.setGeneratedKey(builder.build());
    }

    public String getConfiguredSqlStatement() {
        String tableName = fullyQualifiedTable.getIntrospectedTableName();//返回数据库中实际的表名
        return configuredSqlStatement.replaceAll("tableName", tableName.substring(SUB_TABLE_PRE));
    }


}

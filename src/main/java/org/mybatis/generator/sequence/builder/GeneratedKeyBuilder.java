package org.mybatis.generator.sequence.builder;

import org.mybatis.generator.config.GeneratedKey;

public class GeneratedKeyBuilder {


    public GeneratedKeyBuilder() {

    }

    public GeneratedKey createGeneratedKey(Builder builder) {
        GeneratedKey generatedKey = new GeneratedKey();
        generatedKey.setColumn(builder.column);
        generatedKey.setRuntimeSqlStatement(builder.runtimeSqlStatement);
        return generatedKey;

    }

    public static class Builder {

        private String column;

        private String runtimeSqlStatement;

        public Builder column(String column) {
            this.column = column;
            return this;
        }

        public Builder runtimeSqlStatement(String runtimeSqlStatement) {
            this.runtimeSqlStatement = runtimeSqlStatement;
            return this;
        }

        public GeneratedKey build() {
            return new GeneratedKeyBuilder().createGeneratedKey(this);
        }


    }


}

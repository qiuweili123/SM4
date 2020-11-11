package com.aviator.test;

import com.google.common.collect.Lists;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.*;

/**
 * 自定义规则函数
 */
public interface AvatorFuncConstant {
    enum FuncEnum {

        EVAL_ARRAY("evalArray", "数组运算", new AbstractFunction() {
            /**
             * 与like一样模糊匹配
             *
             * @param env
             * @param objArrays 目标字段。例如 1390089889
             * @param objArrays  比较值，例如139,138,137
             * @return
             */
            @Override
            public AviatorObject call(Map<String, Object> env, AviatorObject objArrays, AviatorObject expres) {
                Object objArraysVal = objArrays.getValue(env);
                Object expresVal = expres.getValue(env);
                if (Objects.isNull(objArraysVal) || Objects.isNull(expresVal)) {
                    return AviatorBoolean.FALSE;
                }
                List list = Lists.newArrayList(1, 2, 3);
                Map map = new HashMap();
                map.put("list", objArraysVal);
                boolean execute = (Boolean) AviatorEvaluator.execute(" let ret=false;   for obj in list { if(" + expresVal + "){ println(obj);  ret=true; break;}};return ret;", map);
                System.out.println("ret::" + execute);


                return AviatorBoolean.TRUE;

            }

            @Override
            public String getName() {
                return "evalArray";
            }

        });

        private String code;
        private String name;
        private AbstractFunction function;

        FuncEnum(String code, String name, AbstractFunction function) {
            this.code = code;
            this.name = name;
            this.function = function;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public AbstractFunction getFunction() {
            return function;
        }
    }


}

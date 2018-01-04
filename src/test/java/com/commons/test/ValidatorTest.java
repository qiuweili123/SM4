package com.commons.test;

import java.beans.Expression;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/1/2.
 */
public class ValidatorTest {
    public static void main(String[] args) throws Exception {
        Filed filed = new Filed();
        filed.setFiledName("name");
        filed.setDataType(1);
        List<FiledValidator> list = new ArrayList<>();
        FiledValidator filedValidator1 = new FiledValidator();
        filedValidator1.setValidateType(1);

        FiledValidator filedValidator2 = new FiledValidator();
        filedValidator2.setValidateType(2);
        filedValidator2.setValidataValue("1,10");

        list.add(filedValidator1);
        list.add(filedValidator2);

        filed.setFiledValidatorList(list);

        FiledData data = new FiledData();
        data.setKey("name");
        data.setValue(3);


        Checker checker = new Checker();

        String fildName = filed.getFiledName();

        try {
            for (FiledValidator validator : filed.getFiledValidatorList()) {
                System.out.println("##" + validator.getValidateType());
                String methodName = "check" + FiledConstants.ValiadType.getValiadType(validator.getValidateType()).getName();
                System.out.println("##methodName==" + methodName);
                FiledConstants.FiledType filedType = FiledConstants.FiledType.getFiledType(filed.getDataType());

                Expression expression = new Expression(checker, methodName, new Object[]{filedType, data.getValue(), validator.getValidataValue()});
                expression.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error,filed:" + fildName + ",error:" + e.getMessage());
        }
        System.out.println("-------验证通过-----");

    }
}

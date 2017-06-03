package org.mec.validation.test;

import org.epe.core.DateFromat;
import org.mec.validation.PatternCompile;

public class Test {
    public static void main(String[] args) {

        Student student = new Student();
        student.setIdCard("4302251987121656110");

        student.setPhone("186108");

        student.setTel("0121-775825851");
        student.setPinteger(1234);
        student.setTestInte(12345677);

        PatternCompile pc = new PatternCompile();
        System.out.println(pc.compile(student, "idCard"));

        pc.compile(student, "phone");

        pc.compile(student, "tel");
        pc.compile(student, "tel");
        System.out.println(" ----" + pc.compile(student, "pinteger"));

        student.setTel("0121-77582585");
        student.setEmail("lishengbo4444@163.com");
        student.setPfloat(-28.0f);
        student.setPinteger(-100);
        student.setZipCode("2563145");
        student.setDate(DateFromat.formatDate("1987-12-16", "yyyy-MM-dd"));
        student.setDate(DateFromat.formatDate("1987-12asd-16", "yyyy-MM-dd"));

        System.out.println("���֤��:" + pc.compile(student, "idCard"));
        System.out.println("�ֻ�����:" + pc.compile(student, "phone"));
        System.out.println("�绰����:" + pc.compile(student, "tel"));
        System.out.println("�����ʼ�:" + pc.compile(student, "email"));
        System.out.println("������:" + pc.compile(student, "pinteger"));
        System.out.println("������:" + pc.compile(student, "pfloat"));
        System.out.println("�ʱ�:" + pc.compile(student, "zipCode"));
        System.out.println("������֤" + pc.compile(student, "date"));

    }

}

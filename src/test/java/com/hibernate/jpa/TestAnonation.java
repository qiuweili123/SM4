package com.hibernate.jpa;

import com.sh.model.Teacher;
import com.sh.service.BaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/application.xml"})
public class TestAnonation {

    @Resource(name = "baseService")
    private BaseService<Teacher> teacherService;

    @Test

    public void testCreateUser() throws Exception {
        System.out.println("----");
    }

    @Test
    public void testFindTeacher() throws Exception {

    }

    @Test
    public void testSaveTeacher() throws Exception {

        try {
            Teacher teacher = new Teacher();
            teacher.setName("张三1");
            teacher.setCreateBy("www");
            teacherService.save(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    @Test
    public void testDynimcObj() {
        try {
            Teacher teacher = teacherService.get(1L);
            teacher.setDescription("test");
            System.out.println("save----");
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }


        //teacherService.save(teacher);
    }
}

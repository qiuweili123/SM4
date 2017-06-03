package com.java.basic;

import com.sh.model.Student;

import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Map;

@Path("name")
public interface InnerInterface {
    public void strMetheod(@QueryParam("ipname") String inString, @QueryParam("cnt") int i, @FormParam("form") String formp);

    @Path("name22")
    public void objMethod(Map<String, String> tMap, @BeanParam Student student);

    public void objMethod2(Student student, Student student2);
}
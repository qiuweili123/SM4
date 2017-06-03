package com.java.wrapper;

import org.kohsuke.args4j.Option;

public class Args {
    @Option(required = true, name = "-name", usage = "name is something")
    private String name;

    @Option(name = "-sex", usage = "sex is something")
    private String sex;

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return this.sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }


}
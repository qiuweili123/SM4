package com.javapatterns.builder;

public class DoDoContact {
    private final int age;
    private final int safeID;
    private final String name;
    private final String address;

    private DoDoContact(Builder b) {
        age = b.age;
        safeID = b.safeID;
        name = b.name;
        address = b.address;

    }

    public int getAge() {
        return age;
    }

    public int getSafeID() {
        return safeID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public static class Builder {
        private int age = 0;
        private int safeID = 0;
        private String name = null;
        private String address = null;

        // 构建的步骤
        public Builder(String name) {
            this.name = name;
        }

        public Builder age(int val) {
            age = val;
            return this;
        }

        public Builder safeID(int val) {
            safeID = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public DoDoContact build() { // 构建，返回一个新对象
            return new DoDoContact(this);
        }
    }
}
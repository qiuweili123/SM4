package com.javapatterns.builder;

public class AppleBuilder {
    //final约束的，又可以保证线程安全。

    private final String color;


    private final String size;

    private AppleBuilder(Builder builder) {
        color = builder.color;
        size = builder.size;
    }

    public static Builder build() {
        return new Builder();
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public static class Builder {
        private String color;
        private String size;


        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder setSize(String size) {
            this.size = size;
            return this;
        }

        public AppleBuilder build() {
            return new AppleBuilder(this);
        }

    }


}

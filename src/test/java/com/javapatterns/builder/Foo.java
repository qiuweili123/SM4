package com.javapatterns.builder;

public class Foo<K> {

    private  final  String key;

    private final K k;

    public Foo(String key, K k) {
        this.key = key;
        this.k = k;
    }

    public  void print(){
        System.out.println("##ckey=="+k);
    }

   public   static class Builder<K2> {

        private String key;

        private K2 k2;

        private Builder() {

        }

        public   static <K3>    Builder<K3> init(Class<K3> k2Class){
             return  new Builder<K3>();
        }




        public Builder<K2> key(String key){
            this.key=key;
            return  this;
        }

        public  Builder<K2> setK(K2 k){
            this.k2=k;
            return this;
        }

        public  Foo<K2> build(){
          return    new Foo(key,k2);
        }

    }

}

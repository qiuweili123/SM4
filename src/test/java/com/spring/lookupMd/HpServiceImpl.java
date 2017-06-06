package com.spring.lookupMd;

/**
 * 我们把这个类作为BeanA.
 * 这是一个抽象类. 为什么要抽象? 因为有个抽象方法..
 * 既然抽象了, 就不能final... 切记切记
 */
public abstract class HpServiceImpl implements HpService {

    /*
     * 这个方法是抽象的.
     * 返回的是cglib构造的BeanB的子类.
     */
    public abstract HpDao getHpDao();

    @Override
    public int getHp() {
        // 调用原型BeanB的方法
        System.out.println("into hpservice ....");
        return getHpDao().getHp();
    }
}

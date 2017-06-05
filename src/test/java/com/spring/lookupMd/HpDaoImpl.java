package com.spring.lookupMd;

/**
 * 我们把这个类作为BeanB.
 * (Hp就是血量... 游戏中某个英雄的血量总在变化)
 * (比如说我们希望通过英雄名找到的英雄的血是实时变化的. )
 */
public class HpDaoImpl implements HpDao {
    @Override
    public int getHp() {
        System.out.println("insert into method");
        // 返回0~100之间的随即数
        return (int) (Math.random() * 100);
    }
}

package com.java.generic;

/**
 * Dao 泛型类的使用
 * public class MicrorepastBaseDao<E extends Serializable, PK extends Serializable> extends BaseDao implements IMicrorepastDao<E, PK>{
 *
 * @param <T>
 * @Override public void save(E entity) {
 * super.save(entity);
 * }
 * @Override public void saveOrUpdate(E entity) {
 * super.saveOrUpdate(entity);
 * }
 */
public class Pair<T> {
    //private T first;
    private T second;

    /*  public Pair() { first = null; second = null; }
      public Pair(T first, T second) { this.first = first; this.second = second; }
      public T getFirst() { return first; }*/
    public T getSecond() {
        return second;
    }

    //注意setFirst1增加了<T>变成了泛型方法，不能强制newValue的数据类型默认为Object，setFirst为不是泛型的方法，使用的时候强制类型提示
    public <T> void setFirst1(T newValue) { //first = newValue;
    }

    public void setFirst(T newValue) { //first = newValue;
    }

    public void setSecond(T newValue) {
        second = newValue;
    }


}
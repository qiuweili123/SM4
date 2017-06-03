package com.sh.hibernate.dao;

import com.sh.hibernate.dao.support.GenericsUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * 负责为单个Entity对象提供CRUD操作的Hibernate DAO基类. <p/> 子类只要在类定义时指定所管理Entity的Class,
 * 即拥有对单个Entity对象的CRUD操作.
 * <p>
 * <pre>
 * public class UserManager extends HibernateEntityDao<User> {
 * }
 * </pre>
 *
 * @author springside
 * @see HibernateGenericDao
 */
@SuppressWarnings("unchecked")
@Repository("hedao")
public class HibernateEntityDao<T> extends HibernateGenericDao implements IEntityDao<T> {
    protected Class<T> entityClass;// DAO所管理的Entity类型.

    /**
     * 在构造函数中将泛型T.class赋给entityClass.
     */
    public HibernateEntityDao() {
        System.out.println("##entityClass" + entityClass);
        entityClass = GenericsUtils.getSuperClassGenricType(getClass());
    }

    /**
     * 重载构造函数 让spring提供构造函数注入
     */
    public HibernateEntityDao(Class<T> type) {
        this.entityClass = type;
    }

    /**
     * 取得entityClass.JDK1.4不支持泛型的子类可以抛开Class<T> entityClass,重载此函数达到相同效果。
     */
    protected Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> type) {
        this.entityClass = type;
    }

    /**
     * 根据ID获取对象.
     *
     * @see HibernateGenericDao#getId(Class, Object)
     */
    public T get(Serializable id) {
        return get(getEntityClass(), id);
    }

    /**
     * 获取全部对象
     *
     * @see HibernateGenericDao#getAll(Class)
     */
    public List<T> getAll() {
        return getAll(getEntityClass());
    }

    /**
     * 获取全部对象,带排序参数.
     *
     * @see HibernateGenericDao#getAll(Class, String, boolean)
     */
    public List<T> getAll(String orderBy, boolean isAsc) {
        return getAll(getEntityClass(), orderBy, isAsc);
    }

    /**
     * 根据ID移除对象.
     *
     * @see HibernateGenericDao#removeById(Class, Serializable)
     */
    public void removeById(Serializable id) {
        removeById(getEntityClass(), id);
    }

    /**
     * 取得Entity的Criteria.
     *
     * @see HibernateGenericDao#createCriteria(Class, Criterion[])
     */
    public Criteria createCriteria(Criterion... criterions) {
        return createCriteria(getEntityClass(), criterions);
    }

    /**
     * 取得Entity的Criteria,带排序参数.
     *
     * @see HibernateGenericDao#createCriteria(Class, String, boolean, Criterion[])
     */
    public Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions) {
        return createCriteria(getEntityClass(), orderBy, isAsc, criterions);
    }

    /**
     * 根据属性名和属性值查询对象.
     *
     * @return 符合条件的对象列表
     * @see HibernateGenericDao#findBy(Class, String, Object)
     */
    public List<T> findBy(String propertyName, Object value) {
        return findBy(getEntityClass(), propertyName, value);
    }

    /**
     * 根据属性名和属性值查询对象,带排序参数.
     *
     * @return 符合条件的对象列表
     * @see HibernateGenericDao#findBy(Class, String, Object, String, boolean)
     */
    public List<T> findBy(String propertyName, Object value, String orderBy, boolean isAsc) {
        return findBy(getEntityClass(), propertyName, value, orderBy, isAsc);
    }

    /**
     * 根据属性名和属性值查询单个对象.
     *
     * @return 符合条件的唯一对象 or null
     * @see HibernateGenericDao#findUniqueBy(Class, String, Object)
     */
    public T findUniqueBy(String propertyName, Object value) {
        return findUniqueBy(getEntityClass(), propertyName, value);
    }

    /**
     * 判断对象某些属性的值在数据库中唯一.
     *
     * @param uniquePropertyNames 在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password"
     * @see HibernateGenericDao#isUnique(Class, Object, String)
     */
    public boolean isUnique(Object entity, String uniquePropertyNames) {
        return isUnique(getEntityClass(), entity, uniquePropertyNames);
    }

    /**
     * 消除与 Hibernate Session 的关联
     *
     * @param entity
     */
    public void evit(Object entity) {
        getHibernateTemplate().evict(entity);
    }
}
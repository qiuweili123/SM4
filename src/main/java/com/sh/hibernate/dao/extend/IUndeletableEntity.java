package com.sh.hibernate.dao.extend;

/**
 * 标识商业对象不能被删除,只能被设为无效的接口.
 *
 * @author springside
 */
public interface IUndeletableEntity {
    void setStatus(String status);
}
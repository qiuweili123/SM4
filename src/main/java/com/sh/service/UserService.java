package com.sh.service;

import com.sh.dao.UsersDAO;
import com.sh.dao.master.MasterUserDao;
import com.sh.model.Users;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import javax.annotation.Resource;
import java.util.List;


public class UserService<T> {
    @Resource
    private UsersDAO userDao;

    @Resource
    private MasterUserDao masterUserDao;


    public List<Users> findUsers() {

        List<Users> list = userDao.getAllUser("from Users");
        return list;
    }


    public String findUserInfoById(Long id) {
        Users user = (Users) masterUserDao.getSession().get(Users.class, id);
        return user.getName();
    }

    public List<Users> findUsersList() {
        List<Users> list = masterUserDao.getSession().createQuery(" from Users").list();
        return list;
    }

    public List<T> find(String sql) {


        List<T> list = masterUserDao.getSession().createQuery(sql).list();
        return list;
    }

    public List<T> findBySqlQuery(String sql, String entryName) {
        SQLQuery query = masterUserDao.getSession().createSQLQuery(sql);
        List<T> list = ((entryName.indexOf("Map") < 0) ? query.addEntity(entryName) : query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)).list();
        return list;
    }


}

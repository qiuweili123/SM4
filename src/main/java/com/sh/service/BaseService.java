package com.sh.service;

import com.sh.hibernate.dao.SysDao;
import com.sh.model.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("baseService")
public class BaseService<T> {

    @Resource
    private SysDao<Teacher> sysDao;

    public void save(Teacher t) {
        sysDao.save(t);
    }

    public Teacher get(Long id) {
        return sysDao.get(id);
    }


}

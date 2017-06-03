package com.sh.dao.slave;

import com.sh.dao.GenericDao;
import com.sh.model.Users;
import org.springframework.stereotype.Repository;

@Repository("slaveUsersDao")
public class SlaveUsersDao extends GenericDao<Users> {

}

package com.sh.dao.master;

import com.sh.dao.GenericDao;
import com.sh.model.Users;
import org.springframework.stereotype.Repository;

@Repository("masterUserDao")
public class MasterUserDao extends GenericDao<Users> {

}

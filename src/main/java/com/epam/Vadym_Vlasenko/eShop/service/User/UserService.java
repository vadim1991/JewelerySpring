package com.epam.Vadym_Vlasenko.eShop.service.User;

import com.epam.Vadym_Vlasenko.eShop.db.dao.mysql.UserDaoMySQL;
import com.epam.Vadym_Vlasenko.eShop.db.generic_dao.GenericHibernateDao;
import com.epam.Vadym_Vlasenko.eShop.entity.User;
import com.epam.Vadym_Vlasenko.eShop.service.generic_service.GenericManagerImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by swift-seeker-89717 on 09.04.2015.
 */
@Service("userService")
public class UserService extends GenericManagerImpl<User, UserDaoMySQL> implements IUserService<User> {

    private static final Logger LOG = Logger.getLogger(UserService.class);

    @Autowired
    @Qualifier("userDao")
    @Override
    public void setDao(GenericHibernateDao dao) {
        super.setDao(dao);
    }

    @Transactional
    @Override
    public User findByLogin(final String login) {
        return dao.findByLogin(login);
    }

}

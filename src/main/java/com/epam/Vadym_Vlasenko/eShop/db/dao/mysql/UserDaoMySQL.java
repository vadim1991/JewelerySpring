package com.epam.Vadym_Vlasenko.eShop.db.dao.mysql;

import com.epam.Vadym_Vlasenko.eShop.db.dao.IUserDAO;
import com.epam.Vadym_Vlasenko.eShop.db.generic_dao.GenericHibernateDaoImpl;
import com.epam.Vadym_Vlasenko.eShop.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by swift-seeker-89717 on 07.04.2015.
 */
@Repository("userDao")
public class UserDaoMySQL extends GenericHibernateDaoImpl<User> implements IUserDAO<User> {

    @Override
    public User findByLogin(String login) {
        User user = (User) getCurrentSession()
                .createQuery("from User where login=?")
                .setString(0, login);
        return user;
    }

}

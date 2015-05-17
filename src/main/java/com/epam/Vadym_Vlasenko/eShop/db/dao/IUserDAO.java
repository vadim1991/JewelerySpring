package com.epam.Vadym_Vlasenko.eShop.db.dao;

import com.epam.Vadym_Vlasenko.eShop.db.generic_dao.GenericHibernateDao;
import com.epam.Vadym_Vlasenko.eShop.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by swift-seeker-89717 on 07.04.2015.
 */
public interface IUserDAO<T> extends GenericHibernateDao<T> {

    T findByLogin(String login);

}

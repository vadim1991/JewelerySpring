package com.epam.Vadym_Vlasenko.eShop.db.dao.order;

import com.epam.Vadym_Vlasenko.eShop.db.generic_dao.GenericHibernateDao;
import com.epam.Vadym_Vlasenko.eShop.entity.Order;
import com.epam.Vadym_Vlasenko.eShop.entity.StatusTypes;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by swift-seeker-89717 on 16.04.2015.
 */
public interface IOrderDAO<T> extends GenericHibernateDao<T> {

    List<T> getOrdersByUser(int userId);

}

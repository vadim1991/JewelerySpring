package com.epam.Vadym_Vlasenko.eShop.service.order;

import com.epam.Vadym_Vlasenko.eShop.db.generic_dao.GenericHibernateDao;
import com.epam.Vadym_Vlasenko.eShop.entity.Order;
import com.epam.Vadym_Vlasenko.eShop.entity.OrderInfo;
import com.epam.Vadym_Vlasenko.eShop.entity.StatusTypes;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by swift-seeker-89717 on 16.04.2015.
 */
public interface IOrderService<T> extends GenericHibernateDao<T>{

    List<T> getOrdersByUser(int userId);

}

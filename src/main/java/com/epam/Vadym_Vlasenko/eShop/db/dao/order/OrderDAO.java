package com.epam.Vadym_Vlasenko.eShop.db.dao.order;

import com.epam.Vadym_Vlasenko.eShop.db.generic_dao.GenericHibernateDaoImpl;
import com.epam.Vadym_Vlasenko.eShop.entity.Order;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by swift-seeker-89717 on 16.04.2015.
 */
@Repository("orderDao")
public class OrderDAO extends GenericHibernateDaoImpl<Order> implements IOrderDAO<Order> {

    private static final Logger LOG = Logger.getLogger(OrderDAO.class);

    public OrderDAO() {
        setClass(Order.class);
    }

    @Override
    public List<Order> getOrdersByUser(int userId) {
        return (List<Order>) getCurrentSession().createQuery("from Order where user.id = ?").setInteger(0, userId).list();
    }

}

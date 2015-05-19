package com.epam.Vadym_Vlasenko.eShop.service.order;

import com.epam.Vadym_Vlasenko.eShop.db.dao.order.IOrderDAO;
import com.epam.Vadym_Vlasenko.eShop.db.dao.order.OrderDAO;
import com.epam.Vadym_Vlasenko.eShop.db.generic_dao.GenericHibernateDao;
import com.epam.Vadym_Vlasenko.eShop.entity.Order;
import com.epam.Vadym_Vlasenko.eShop.service.generic_service.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by swift-seeker-89717 on 19.04.2015.
 */
@Service("orderService")
public class OrderService extends GenericManagerImpl<Order, OrderDAO> implements IOrderService<Order> {

    @Autowired
    private IOrderDAO orderDAO;

    @Autowired
    @Qualifier("orderDao")
    @Override
    public void setDao(GenericHibernateDao dao) {
        super.setDao(dao);
    }

    @Transactional
    @Override
    public List<Order> getOrdersByUser(int userId) {
        List<Order> orders = orderDAO.getOrdersByUser(userId);
        return orders;
    }
}

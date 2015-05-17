package com.epam.Vadym_Vlasenko.eShop.service.order;

import com.epam.Vadym_Vlasenko.eShop.db.dao.IOrderDAO;
import com.epam.Vadym_Vlasenko.eShop.db.dao.IOrderInfoDAO;
import com.epam.Vadym_Vlasenko.eShop.entity.Order;
import com.epam.Vadym_Vlasenko.eShop.entity.OrderInfo;
import com.epam.Vadym_Vlasenko.eShop.entity.StatusTypes;
import com.epam.Vadym_Vlasenko.eShop.service.transaction.TransactionManager;
import com.epam.Vadym_Vlasenko.eShop.service.transaction.TransactionOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by swift-seeker-89717 on 19.04.2015.
 */
@Service("orderService")
public class OrderService implements IOrderService {

    @Autowired
    private TransactionManager tm;
    @Autowired
    private IOrderDAO orderDAO;
    @Autowired
    private IOrderInfoDAO orderInfoDAO;

    @Override
    public void create(final Order order) {
        tm.transaction(new TransactionOperation<Void>() {
            @Override
            public Void execute() throws SQLException {
                orderDAO.save(order);
                return null;
            }
        });
    }

    @Override
    public void create(final Order order, final List<OrderInfo> orderInfoList) {
        tm.transaction(new TransactionOperation<Void>() {
            @Override
            public Void execute() throws SQLException {
                Order newOrder = orderDAO.save(order);
                for (OrderInfo orderInfo : orderInfoList) {
                    orderInfoDAO.save(orderInfo, newOrder.getId());
                }
                return null;
            }
        });
    }

    @Override
    public List<Order> getOrders() {
        return null;
    }

    @Override
    public Map<Order, List<OrderInfo>> getOrdersWithInfo(final int userId) {
        return tm.transaction(new TransactionOperation<Map<Order, List<OrderInfo>>>() {
            @Override
            public Map<Order, List<OrderInfo>> execute() throws SQLException {
                Map<Order, List<OrderInfo>> orderInfoMap = new LinkedHashMap<>();
                List<Order> orders = orderDAO.getOrdersByUser(userId);
                for (int i = 0; i < orders.size(); i++) {
                    List<OrderInfo> orderInfoList = orderInfoDAO.findById(orders.get(i).getId());
                    orderInfoMap.put(orders.get(i), orderInfoList);
                }
                return orderInfoMap;
            }
        });
    }

    @Override
    public boolean changeOrderStatus(Order order, StatusTypes statusTypes) {
        return false;
    }

    @Override
    public List<Order> getOrdersByUser(int userId) {
        return null;
    }

    @Override
    public List<Order> getOrdersFromDate(Date from, Date toDate) {
        return null;
    }

    @Override
    public Order getOrderById(int orderId) {
        return null;
    }
}

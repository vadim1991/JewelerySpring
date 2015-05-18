package com.epam.Vadym_Vlasenko.eShop.db.dao.mysql;

import com.epam.Vadym_Vlasenko.eShop.db.DBConnectionHolder;
import com.epam.Vadym_Vlasenko.eShop.db.dao.IOrderDAO;

import com.epam.Vadym_Vlasenko.eShop.entity.Order;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by swift-seeker-89717 on 16.04.2015.
 */
@Repository("orderDao")
public class OrderDAOMySQL implements IOrderDAO {

    private static final Logger LOG = Logger.getLogger(OrderDAOMySQL.class);

    private static final String ORDERS_TABLE_NAME = "orders";
    private static final String ORDER_ID_COLUMN = "id";
    private static final String STATUS_ID_COLUMN = "status_id";
    private static final String ORDER_INFO_COLUMN = "order_info";
    private static final String USER_ID_COLUMN = "user_id";
    private static final String DATE_COLUMN = "date";
    private static final String TOTAL_PRICE = "total_price";

    private static final String CREATE_ORDER_QUERY = "INSERT INTO orders VALUES(?,?,?,?,?,?)";
    private static final String UPDATE_STATUS_QUERY = "UPDATE orders SET status_id=? WHERE (id=?)";

    @Override
    public Order save(Order order) throws SQLException {
        Order newOrder = new Order();
        Connection connection = DBConnectionHolder.getConnectionHolder().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;
            preparedStatement.setInt(index++, order.getId());
            preparedStatement.setInt(index++, order.getOrderStatus().getId());
            preparedStatement.setString(index++, order.getOrderInfo());
            preparedStatement.setInt(index++, order.getUser().getId());
            preparedStatement.setTimestamp(index++, new Timestamp(order.getDate().getTime()));
            preparedStatement.setInt(index++, order.getTotalPrice());
            if (preparedStatement.executeUpdate() > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    newOrder.setId(resultSet.getInt(1));
                }
            }
        }
        return newOrder;
    }

    @Override
    public List<Order> findAll() throws SQLException {
        List<Order> orders = new ArrayList<>();

        return orders;
    }

    @Override
    public List<Order> getOrdersByUser(int userId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        return orders;
    }

    @Override
    public List<Order> getOrdersFromDate(Date from, Date toDate) throws SQLException {
        return null;
    }

    @Override
    public Order findById(int orderId) throws SQLException {
        Order order = new Order();
        return order;
    }

}

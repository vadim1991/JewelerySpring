package com.epam.Vadym_Vlasenko.eShop.db.dao;

import com.epam.Vadym_Vlasenko.eShop.entity.OrderInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by swift-seeker-89717 on 16.04.2015.
 */
public interface IOrderInfoDAO {

    void save(OrderInfo orderInfo, int orderId) throws SQLException;

    List<OrderInfo> findAll() throws SQLException;

    List<OrderInfo> findById(int orderInfoId) throws SQLException;

}
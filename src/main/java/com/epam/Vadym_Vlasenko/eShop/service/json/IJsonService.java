package com.epam.Vadym_Vlasenko.eShop.service.json;

import com.epam.Vadym_Vlasenko.eShop.entity.Order;

import java.util.List;

/**
 * Created by Вадим on 09.05.2015.
 */
public interface IJsonService {

    String ordersToJSON(List<Order> orderListMap);

}

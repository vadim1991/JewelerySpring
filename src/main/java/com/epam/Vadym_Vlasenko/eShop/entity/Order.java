package com.epam.Vadym_Vlasenko.eShop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by swift-seeker-89717 on 16.04.2015.
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private OrderStatus orderStatus;
    private String orderInfo;
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderInfo> orderInfoList;
    private Date date;
    private int totalPrice;

    public Order() {
        this.date = new Date();
    }

    public Order(OrderStatus orderStatus, String orderInfo, User user, List orderInfoSet, int totalPrice) {
        this.orderStatus = orderStatus;
        this.orderInfo = orderInfo;
        this.user = user;
        this.orderInfoList = orderInfoSet;
        this.date = new Date();
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderInfo> getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(List<OrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (totalPrice != order.totalPrice) return false;
        if (orderStatus != null ? !orderStatus.equals(order.orderStatus) : order.orderStatus != null) return false;
        if (orderInfo != null ? !orderInfo.equals(order.orderInfo) : order.orderInfo != null) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (orderInfoList != null ? !orderInfoList.equals(order.orderInfoList) : order.orderInfoList != null) return false;
        return !(date != null ? !date.equals(order.date) : order.date != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (orderInfo != null ? orderInfo.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (orderInfoList != null ? orderInfoList.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + totalPrice;
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderStatus=" + orderStatus +
                ", orderInfo='" + orderInfo + '\'' +
                ", user=" + user +
                ", orderInfoSet=" + orderInfoList +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

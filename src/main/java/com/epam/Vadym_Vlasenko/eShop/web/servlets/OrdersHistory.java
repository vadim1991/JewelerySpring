package com.epam.Vadym_Vlasenko.eShop.web.servlets;

import com.epam.Vadym_Vlasenko.eShop.entity.Order;
import com.epam.Vadym_Vlasenko.eShop.entity.OrderInfo;
import com.epam.Vadym_Vlasenko.eShop.entity.User;
import com.epam.Vadym_Vlasenko.eShop.service.json.IJsonService;
import com.epam.Vadym_Vlasenko.eShop.service.json.JsonService;
import com.epam.Vadym_Vlasenko.eShop.service.order.IOrderService;
import com.epam.Vadym_Vlasenko.eShop.web.Constants;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Вадим on 01.05.2015.
 */
@Controller
@WebServlet("/ordersHistory")
public class OrdersHistory extends HttpServlet {

    private static final String USER_ATTRIBUTE = "user";
    private static final String HISTORY_PAGE = "history.jsp";

    private IOrderService orderService;
    private IJsonService jsonService;
    private WebApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        orderService = (IOrderService) context.getBean("orderService");
        jsonService = (IJsonService) context.getBean("jsonService");
        //orderService = (IOrderService) getServletContext().getAttribute(Constants.ORDER_SERVICE);
        //jsonService = new JsonService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        if (user != null) {
            Map<Order, List<OrderInfo>> orderListMap = orderService.getOrdersWithInfo(user.getId());
            if (orderListMap != null) {
                String jsonMap = jsonService.orderMapToJSON(orderListMap);
                resp.getWriter().write(jsonMap);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(HISTORY_PAGE).forward(req, resp);
    }
}

package com.epam.Vadym_Vlasenko.eShop.web.servlets;

import com.epam.Vadym_Vlasenko.eShop.entity.CriteriaFormBean;
import com.epam.Vadym_Vlasenko.eShop.entity.criteria.CriteriaResultBean;
import com.epam.Vadym_Vlasenko.eShop.service.product.IProductService;
import com.google.gson.Gson;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by swift-seeker-89717 on 05.04.2015.
 */
@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private static final String PRODUCT_SERVICE = "product_service";
    private static final int PRODUCT_ON_PAGE = 15;
    private static CriteriaFormBean criteria;

    private IProductService service;
    private WebApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        service = (IProductService) context.getBean("productService");
        criteria = new CriteriaFormBean();
        criteria.setProductOnPage(PRODUCT_ON_PAGE);
        criteria.setPositionFrom(0);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        CriteriaResultBean resultBean = service.getProductsByCriteria(criteria);
        resp.getWriter().write(gson.toJson(resultBean.getProducts()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}

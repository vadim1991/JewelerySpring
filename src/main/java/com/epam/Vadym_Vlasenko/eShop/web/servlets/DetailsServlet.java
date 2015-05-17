package com.epam.Vadym_Vlasenko.eShop.web.servlets;

import com.epam.Vadym_Vlasenko.eShop.entity.Product;
import com.epam.Vadym_Vlasenko.eShop.service.product.IProductService;
import com.epam.Vadym_Vlasenko.eShop.web.Constants;
import org.springframework.stereotype.Controller;
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
@Controller
@WebServlet("/details")
public class DetailsServlet extends HttpServlet {

    private static final String PRODUCT_SERVICE = "product_service";
    private static final String PRODUCT_ATTRIBUTE = "product";
    private static final String DETAIL_PAGE = "details.jsp";
    private static final String ID_PARAMETER = "id";

    private IProductService service;
    private WebApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        service = (IProductService) context.getBean("productService");
        //service = (IProductService) context.getAttribute(PRODUCT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(ID_PARAMETER);
        try {
            Product product = (Product) service.findById(Integer.parseInt(id));
            if (product != null) {
                req.setAttribute(PRODUCT_ATTRIBUTE, product);
                req.getRequestDispatcher(DETAIL_PAGE).forward(req, resp);
            } else {
                req.getRequestDispatcher(Constants.NOT_FOUND_PAGE).forward(req, resp);
            }
        } catch (NumberFormatException e) {
            req.getRequestDispatcher(Constants.BED_REQUEST_PAGE).forward(req, resp);
            return;
        }
    }

}

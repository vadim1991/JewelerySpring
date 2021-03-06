package com.epam.Vadym_Vlasenko.eShop.web.servlets;

import com.epam.Vadym_Vlasenko.eShop.entity.CriteriaFormBean;
import com.epam.Vadym_Vlasenko.eShop.entity.Product;
import com.epam.Vadym_Vlasenko.eShop.entity.criteria.CriteriaResultBean;
import com.epam.Vadym_Vlasenko.eShop.service.product.IProductService;
import com.epam.Vadym_Vlasenko.eShop.web.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Вадим on 22.03.2015.
 */
@Controller
@WebServlet("/rings")
public class RingsServlet extends HttpServlet {

    private static final String PRODUCT_SERVICE = "product_service";
    private static final String RINGS_ATTRIBUTE = "products";
    private static final int PRODUCT_ON_PAGE_DEFAULT = 6;

    private static final String ENCODING_TYPE = "utf-8";

    private static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private static final String PAGE_PARAMETER = "page";
    private static final String MIN_PRICE_PARAMETER = "minPrice";
    private static final String MAX_PRICE_PARAMETER = "maxPrice";
    private static final String MIN_WEIGHT_PARAMETER = "minWeight";
    private static final String MAX_WEIGHT_PARAMETER = "maxWeight";
    private static final String INSERT_PARAMETER = "insert";
    private static final String MATERIAL_PARAMETER = "material";
    private static final String PRODUCT_ON_PAGE_PARAMETER = "noOfPages";
    private static final String SORT_TYPE_PARAMETER = "sortType";

    private IProductService productService;
    private WebApplicationContext context;

    @Override
    public void init() throws ServletException {
        context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        productService = (IProductService) context.getBean("productService");
        //productService = (ProductService) config.getServletContext().getAttribute(PRODUCT_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING_TYPE);
        resp.setCharacterEncoding(ENCODING_TYPE);
        Gson gson = new Gson();
        int page = 1;
        int records = PRODUCT_ON_PAGE_DEFAULT;
        String pageValue = req.getParameter(PAGE_PARAMETER);
        if (pageValue != null) {
            page = Integer.parseInt(pageValue);
        }
        CriteriaFormBean criteria = getCriteria(req, resp);
        criteria.setPositionFrom((page - 1) * records);
        criteria.setProductOnPage(records);
        CriteriaResultBean criteriaResultBean = productService.getProductsByCriteria(criteria);
        List<Product> products = criteriaResultBean.getProducts();
        long countProduct = criteriaResultBean.getAmount();
        int noOfPages = (int) Math.ceil((int) countProduct * 1.0 / records);
        if (products == null) {
            req.getRequestDispatcher(Constants.BED_REQUEST_PAGE).forward(req, resp);
            return;
        }
        JsonObject object = new JsonObject();
        object.addProperty(PRODUCT_ON_PAGE_PARAMETER, noOfPages);
        object.addProperty(CURRENT_PAGE_ATTRIBUTE, page);
        object.add(RINGS_ATTRIBUTE, gson.toJsonTree(products));
        resp.getWriter().write(gson.toJson(object));
    }

    private CriteriaFormBean getCriteria(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CriteriaFormBean criteria = new CriteriaFormBean();
        criteria.setIdCategory(Constants.RINGS_CATEGORY);
        criteria.setSortType(req.getParameter(SORT_TYPE_PARAMETER));
        criteria.setInsertId(req.getParameter(INSERT_PARAMETER));
        criteria.setMaterialId(req.getParameter(MATERIAL_PARAMETER));
        criteria.setMaxPrice(req.getParameter(MAX_PRICE_PARAMETER));
        criteria.setMinPrice(req.getParameter(MIN_PRICE_PARAMETER));
        criteria.setMinWeight(req.getParameter(MIN_WEIGHT_PARAMETER));
        criteria.setMaxWeight(req.getParameter(MAX_WEIGHT_PARAMETER));
        return criteria;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Constants.RING_PAGE).forward(req, resp);
    }
}

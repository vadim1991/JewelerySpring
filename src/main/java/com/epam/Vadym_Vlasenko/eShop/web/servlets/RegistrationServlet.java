package com.epam.Vadym_Vlasenko.eShop.web.servlets;

import com.epam.Vadym_Vlasenko.eShop.entity.User;
import com.epam.Vadym_Vlasenko.eShop.service.User.IUserService;
import com.epam.Vadym_Vlasenko.eShop.service.captcha.ICaptchaHandler;
import com.epam.Vadym_Vlasenko.eShop.web.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by swift-seeker-89717 on 09.04.2015.
 */
@Controller
@WebServlet("/registration")
@MultipartConfig(location = "c:\\JewelerySpring\\src\\main\\webapp\\images\\avatar\\", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10)
public class RegistrationServlet extends HttpServlet {

    private static final String USER_ATTRIBUTE = "user";
    private static final String SUCCESS_ATTRIBUTE = "success";
    private static final String SUCCESS_MESSAGE = "Пользователь добавлен";

    @Autowired
    private IUserService userService;
    private ICaptchaHandler captchaHandler;
    private WebApplicationContext context;

    @Override
    public void init() throws ServletException {
        context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        userService = (IUserService) context.getBean("userService");
        captchaHandler = (ICaptchaHandler) context.getBean("captchaHandler");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        captchaHandler.init(req, resp);
        req.getRequestDispatcher(Constants.REGISTRATION_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getAttribute(USER_ATTRIBUTE);
        user.setLastLoginDate(new Date());
        user.setUnblockedDate(new Date());
        userService.save(user);
        req.setAttribute(SUCCESS_ATTRIBUTE, SUCCESS_MESSAGE);
        req.getRequestDispatcher(Constants.LOGIN_PAGE).forward(req, resp);
    }

}

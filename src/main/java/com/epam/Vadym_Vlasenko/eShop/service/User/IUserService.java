package com.epam.Vadym_Vlasenko.eShop.service.User;

import com.epam.Vadym_Vlasenko.eShop.entity.User;
import com.epam.Vadym_Vlasenko.eShop.service.generic_service.GenericManager;

import java.sql.SQLException;

/**
 * Created by Вадим on 17.05.2015.
 */
public interface IUserService<T> extends GenericManager<T> {

    User findByLogin(String login);

}

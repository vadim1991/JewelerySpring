package com.epam.Vadym_Vlasenko.eShop.db.generic_dao;

import java.util.List;

/**
 * Created by Вадим on 02.05.2015.
 */

public interface GenericHibernateDao<T> {

    public T findById(int id);

    public List<T> findAll();

    public void update(T entity);

    public void save(T entity);

    public void delete(T entity);

}

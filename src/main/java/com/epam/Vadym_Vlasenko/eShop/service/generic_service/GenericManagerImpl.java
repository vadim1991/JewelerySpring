package com.epam.Vadym_Vlasenko.eShop.service.generic_service;

import com.epam.Vadym_Vlasenko.eShop.db.generic_dao.GenericHibernateDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class GenericManagerImpl<T, D extends GenericHibernateDao<T>> implements
        GenericManager<T> {

    protected D dao;

    public void setDao(GenericHibernateDao dao) {
        this.dao = (D) dao;
    }

    @Transactional
    @Override
    public T findById(int id) {
        return dao.findById(id);
    }

    @Transactional
    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

    @Transactional
    @Override
    public void save(T entity) {
        dao.save(entity);
    }

    @Transactional
    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    @Transactional
    @Override
    public void delete(T entity) {
        dao.delete(entity);
    }

}

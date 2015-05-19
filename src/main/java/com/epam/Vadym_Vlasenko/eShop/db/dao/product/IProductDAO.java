package com.epam.Vadym_Vlasenko.eShop.db.dao.product;

import com.epam.Vadym_Vlasenko.eShop.db.generic_dao.GenericHibernateDao;
import com.epam.Vadym_Vlasenko.eShop.entity.CriteriaFormBean;
import com.epam.Vadym_Vlasenko.eShop.entity.criteria.CriteriaResultBean;

/**
 * Created by Вадим on 22.03.2015.
 */
public interface IProductDAO<T> extends GenericHibernateDao<T> {

    CriteriaResultBean getProductsByCriteria(CriteriaFormBean criteria);

}

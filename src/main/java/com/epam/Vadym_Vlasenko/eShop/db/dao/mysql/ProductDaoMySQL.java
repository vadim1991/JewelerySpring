package com.epam.Vadym_Vlasenko.eShop.db.dao.mysql;

import com.epam.Vadym_Vlasenko.eShop.db.dao.IProductDAO;
import com.epam.Vadym_Vlasenko.eShop.db.generic_dao.GenericHibernateDaoImpl;
import com.epam.Vadym_Vlasenko.eShop.entity.CriteriaFormBean;
import com.epam.Vadym_Vlasenko.eShop.entity.Product;
import com.epam.Vadym_Vlasenko.eShop.entity.criteria.CriteriaResultBean;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Вадим on 22.03.2015.
 */
@Repository("productDao")
public class ProductDaoMySQL extends GenericHibernateDaoImpl<Product> implements IProductDAO<Product> {

    private static final String CATEGORY_FIELD = "category";
    private static final String PRICE_FIELD = "price";
    private static final String INSERT_FIELD = "insert_id";
    private static final String MATERIAL_FIELD = "material";
    private static final String WEIGHT_FIELD = "weight";
    private static final String SORT_FIELD = "sortType";

    public ProductDaoMySQL() {
        setClass(Product.class);
    }

    @Override
    public void save(Product entity) {
        super.save(entity);
    }

    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    @Override
    public CriteriaResultBean getProductsByCriteria(CriteriaFormBean criteriaFormBean) {
        Criteria criteria = getCriteriaFromBean(criteriaFormBean);
        List<Product> products = criteria.list();
        return new CriteriaResultBean(0, products);
    }

    private Criteria getCriteriaFromBean(CriteriaFormBean criteriaFormBean) {
        String insertId = criteriaFormBean.getInsertId();
        String materialId = criteriaFormBean.getMaterialId();
        Criteria criteria = getCurrentSession().createCriteria(Product.class);
//        if (criteriaFormBean.getIdCategory() != 0) {
//            criteria.add(Restrictions.eq(CATEGORY_FIELD, criteriaFormBean.getIdCategory()));
//        }
        if (criteriaFormBean.getMaxPrice() != null || criteriaFormBean.getMinPrice() != null) {
            criteria.add(Restrictions.between(PRICE_FIELD, criteriaFormBean.getMinPrice(), criteriaFormBean.getMaxPrice()));
        }
        if (criteriaFormBean.getMaxWeight() != null || criteriaFormBean.getMinPrice() != null) {
            criteria.add(Restrictions.between(WEIGHT_FIELD, criteriaFormBean.getMinWeight(), criteriaFormBean.getMaxWeight()));
        }
        if (insertId != null) {
            if (!insertId.isEmpty())
            criteria.add(Restrictions.eq(INSERT_FIELD, insertId));
        }
        if (materialId != null) {
            if (!materialId.isEmpty())
            criteria.add(Restrictions.eq(MATERIAL_FIELD, materialId));
        }
        if (criteriaFormBean.getSortType() != null) {
            criteria.addOrder(chooseSortType(criteriaFormBean.getSortType()));
        }
        criteria.setMaxResults(criteriaFormBean.getProductOnPage());
        criteria.setFirstResult(criteriaFormBean.getPositionFrom());
        return criteria;
    }

    private Order chooseSortType(String sortType) {
        if ("1".equals(sortType)) {
            return Order.asc(PRICE_FIELD);
        } else {
            return Order.desc(PRICE_FIELD);
        }
    }

}

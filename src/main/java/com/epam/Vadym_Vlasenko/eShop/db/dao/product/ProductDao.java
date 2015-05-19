package com.epam.Vadym_Vlasenko.eShop.db.dao.product;

import com.epam.Vadym_Vlasenko.eShop.db.generic_dao.GenericHibernateDaoImpl;
import com.epam.Vadym_Vlasenko.eShop.entity.CriteriaFormBean;
import com.epam.Vadym_Vlasenko.eShop.entity.Product;
import com.epam.Vadym_Vlasenko.eShop.entity.criteria.CriteriaResultBean;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Вадим on 22.03.2015.
 */
@Repository("productDao")
public class ProductDao extends GenericHibernateDaoImpl<Product> implements IProductDAO<Product> {

    private static final String CATEGORY_FIELD = "category";
    private static final String PRICE_FIELD = "price";
    private static final String INSERT_FIELD = "insert_id";
    private static final String MATERIAL_FIELD = "material";
    private static final String WEIGHT_FIELD = "weight";
    private static final String SORT_FIELD = "sortType";

    public ProductDao() {
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
        System.out.println(criteria.toString());
        List<Product> products = criteria.list();
        criteria.setFirstResult(0);
        criteria.setProjection(Projections.rowCount());
        Long amountProduct = (Long) criteria.uniqueResult();
        System.out.println(amountProduct);
        System.out.println(products);
        if (amountProduct == null) {
            amountProduct = Long.valueOf(1);
        }
        return new CriteriaResultBean(amountProduct, products);
    }

    private Criteria getCriteriaFromBean(CriteriaFormBean criteriaFormBean) {
        String insertId = criteriaFormBean.getInsertId();
        String materialId = criteriaFormBean.getMaterialId();
        Criteria criteria = getCurrentSession().createCriteria(Product.class);
        System.out.println(criteriaFormBean);
        criteria.createAlias("category", "ct");
        criteria.createAlias("material", "mt");
        criteria.createAlias("insert", "ins");
        if (criteriaFormBean.getIdCategory() != 0) {
            criteria.add(Restrictions.eq("ct.id", criteriaFormBean.getIdCategory()));
        }
        if (criteriaFormBean.getMaxPrice() != null || criteriaFormBean.getMinPrice() != null) {
            int minPrice = Integer.parseInt(criteriaFormBean.getMinPrice());
            int maxPrice = Integer.parseInt(criteriaFormBean.getMaxPrice());
            criteria.add(Restrictions.between(PRICE_FIELD, minPrice, maxPrice));
        }
        if (criteriaFormBean.getMaxWeight() != null || criteriaFormBean.getMinWeight() != null) {
            double minWeight = Double.parseDouble(criteriaFormBean.getMinWeight());
            double maxWeight = Double.parseDouble(criteriaFormBean.getMaxWeight());
            criteria.add(Restrictions.between(WEIGHT_FIELD, minWeight, maxWeight));
        }
        if (insertId != null) {
            if (!insertId.isEmpty())
                criteria.add(Restrictions.eq("ins.id", Integer.parseInt(insertId)));
        }
        if (materialId != null) {
            if (!materialId.isEmpty())
                criteria.add(Restrictions.eq("mt.id", Integer.parseInt(materialId)));
        }
        if (criteriaFormBean.getSortType() != null) {
            criteria.addOrder(chooseSortType(criteriaFormBean.getSortType()));
        }
        criteria.setFirstResult(criteriaFormBean.getPositionFrom());
        criteria.setMaxResults(criteriaFormBean.getProductOnPage());
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

package com.epam.Vadym_Vlasenko.eShop.service.product;

import com.epam.Vadym_Vlasenko.eShop.db.dao.product.ProductDao;
import com.epam.Vadym_Vlasenko.eShop.db.generic_dao.GenericHibernateDao;
import com.epam.Vadym_Vlasenko.eShop.entity.CriteriaFormBean;
import com.epam.Vadym_Vlasenko.eShop.entity.Product;
import com.epam.Vadym_Vlasenko.eShop.entity.criteria.CriteriaResultBean;
import com.epam.Vadym_Vlasenko.eShop.service.generic_service.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Вадим on 22.03.2015.
 */
@Service("productService")
public class ProductService extends GenericManagerImpl<Product, ProductDao> implements IProductService<Product> {

    @Autowired
    @Qualifier("productDao")
    @Override
    public void setDao(GenericHibernateDao dao) {
        super.setDao(dao);
    }

    @Transactional
    @Override
    public CriteriaResultBean getProductsByCriteria(final CriteriaFormBean criteria) {
        return dao.getProductsByCriteria(criteria);
    }

}

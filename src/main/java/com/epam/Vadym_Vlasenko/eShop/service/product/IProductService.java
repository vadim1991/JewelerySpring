package com.epam.Vadym_Vlasenko.eShop.service.product;

import com.epam.Vadym_Vlasenko.eShop.entity.CriteriaFormBean;
import com.epam.Vadym_Vlasenko.eShop.entity.criteria.CriteriaResultBean;
import com.epam.Vadym_Vlasenko.eShop.service.generic_service.GenericManager;

/**
 * Created by Вадим on 22.03.2015.
 */
public interface IProductService<T> extends GenericManager<T> {

    CriteriaResultBean getProductsByCriteria(CriteriaFormBean criteria);

}

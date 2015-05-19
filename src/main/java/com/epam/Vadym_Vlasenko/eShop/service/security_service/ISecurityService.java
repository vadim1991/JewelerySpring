package com.epam.Vadym_Vlasenko.eShop.service.security_service;

import com.epam.Vadym_Vlasenko.eShop.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by swift-seeker-89717 on 19.05.2015.
 */
public interface ISecurityService {

    Map<String, List<Role>> getMapFromXml();

    boolean isSecureUrl(String url, Map<String, List<Role>> roleMap);

    boolean checkUrlWithRole(String url, Map<String, List<Role>> roleMap, Role role);
}

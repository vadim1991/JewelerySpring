package com.epam.Vadym_Vlasenko.eShop.service.xml_parser;

import com.epam.Vadym_Vlasenko.eShop.entity.Role;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by swift-seeker-89717 on 19.05.2015.
 */
public interface IXmlParser {

    Map<String, List<Role>> parseXml(String fileName) throws ParserConfigurationException, IOException, SAXException;

}

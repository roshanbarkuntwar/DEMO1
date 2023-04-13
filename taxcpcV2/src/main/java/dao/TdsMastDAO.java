/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.generic.GenericDAO;
import globalUtilities.Util;
import hibernateObjects.TdsMast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public interface TdsMastDAO extends GenericDAO<TdsMast, Serializable> {

    LinkedHashMap<String, String> getTdsAsLinkedHashMap(String tdsType, Date quarterToDate);

    public TdsMast getAsDeductReasonAsCode(String tdsCode, Util utl);

    LinkedHashMap<String, String> getSectionAsHashMap(String tds_type_code, Date qtrToDate);

    LinkedHashMap<String, String> getSectionAsHashMapForDeductee(String tds_type_code, Date qtrToDate);

    List<TdsMast> getTdsCodeList(String tds_code, String tds_type_code, String Deducteeflag, Util utl);

    public ArrayList<String> getAsDeductReasonAsString(String tdsCode, Util utl);

}

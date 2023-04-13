package com.lhs.taxcpcAdmin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lhs.taxcpcAdmin.dao.generic.GenericCustomRepository;
import com.lhs.taxcpcAdmin.model.entity.AppDetails;
import com.lhs.taxcpcAdmin.model.entity.ServerDetailModel;

public interface AppDetailsRepository  extends JpaRepository<AppDetails,Integer> , GenericCustomRepository<Integer,AppDetails>{

	@Query(value = "select *  from lhs_taxcpc_app_details t where server_id=:server_id", nativeQuery = true)
	List<AppDetails> getAppNameDetails(String server_id);

	@Query(value = "select t.app_code  from lhs_taxcpc_app_details t where server_id=:serverId", nativeQuery = true)
	List<String> getListOfId(String serverId);

	@Query(value = "select count(*) from lhs_taxcpc_app_details where server_id=:server_id", nativeQuery = true)
	String getDeleteAppCount(String server_id);

	@Query(value = "select t.app_code from lhs_taxcpc_app_details t where server_id=:server_id", nativeQuery = true)
	List<Integer> getAppCode(String server_id);

	

}

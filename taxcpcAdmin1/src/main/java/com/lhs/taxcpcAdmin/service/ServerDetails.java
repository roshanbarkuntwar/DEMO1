package com.lhs.taxcpcAdmin.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.lhs.taxcpcAdmin.global.pagination.DataGridDTO;
import com.lhs.taxcpcAdmin.model.entity.AppDetails;
import com.lhs.taxcpcAdmin.model.entity.ServerDetailModel;

public interface ServerDetails {

	public Map<String, String> getServerTypeList();

	public String addPhysicalServerEntry(ServerDetailModel serverDetailModel);

	public String addAppServerEntry(ServerDetailModel serverDetailModel, String server_id2);

	public String addAppDetailEntry(ServerDetailModel serverDetailModel, String server_id);

	public String addAppNameEntry(ServerDetailModel serverDetailModel, String serverId, String appName);

	public List<ServerDetailModel> getServerList();

	public List<ServerDetailModel> getServerListByServerType(String server_type);

	public List<ServerDetailModel> getServerListByServerId(String server_id, String server_type_code);

	public long getUserCount();
	
	public long getUserCount(String server_type);
	
	public List<ServerDetailModel> getServerBrowseList(String server_type,HttpSession session, DataGridDTO dataGridDTO,
			String recPerPage);
					
	public List<ServerDetailModel> getServerBrowseList1(HttpSession session, DataGridDTO dataGridDTO,
			String recPerPage, String server_type);

	public String deleteServerById(String server_id, String server_type_code);

	public ServerDetailModel getServerById(String server_id);

	public List<String> getAppNameListById(String server_id);

	public Map<String, String> getServerIpList();

	public Map<String, String> getAppServerNameList();

	public Map<String, String> getDrivelist();

	public String getServerIDByServerIP(String serverIp);

	public String validateServerName(String app_server_ip, String app_server_name);

	public String setPublicIp(String app_server_ip);

	public String saveAppNameEntry(AppDetails serverDetailModel);

	public String updatePhysicalServerEntry(ServerDetailModel serverDetailModel, String server_id);

	public List<AppDetails> getAppNameDetails(String server_id);

	public List<String> getListOfId(String serverId);

	public String validateAppName(String server_id);

	public String viewServerDetails(String server_id);

	public String getDeleteCount(String server_id);

	public String checkServerIp(String server_id);

	public String getDeleteAppCount(String server_id);

	

}

package com.lhs.taxcpcAdmin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhs.javautilities.LhsStringUtility;
import com.lhs.taxcpcAdmin.dao.AppDetailsRepository;
import com.lhs.taxcpcAdmin.dao.EntityMastRepository;
import com.lhs.taxcpcAdmin.dao.ServerDetailsRepository;
import com.lhs.taxcpcAdmin.dao.ServerDetailsRepositorySupport;
import com.lhs.taxcpcAdmin.dao.generic.GlobalDoWorkExecuter;
import com.lhs.taxcpcAdmin.global.pagination.DataGridDTO;
import com.lhs.taxcpcAdmin.global.pagination.PaginatorEntity;
import com.lhs.taxcpcAdmin.global.pagination.PaginatorManipulation;
import com.lhs.taxcpcAdmin.model.entity.AppDetails;
import com.lhs.taxcpcAdmin.model.entity.LhssysTaxcpcDeploymentTran;
import com.lhs.taxcpcAdmin.model.entity.ReqTran;
import com.lhs.taxcpcAdmin.model.entity.ServerDetailModel;

@Service
public class ServerDetailsIMPL implements ServerDetails {

	@Autowired
	private ServerDetailsRepository serverDetailsRepository;

	@Autowired
	private ServerDetailsRepositorySupport serverDetailsRepositorySupport;
	@Autowired
	private GlobalDoWorkExecuter executer;

	@Autowired
	private AppDetailsRepository appDetailsRepo;

	@Autowired
	private LhsStringUtility strUtl;
	
	@Autowired
	private EntityMastRepository entityMastRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Map<String, String> getServerTypeList() {
		Map<String, String> errorGroup = new HashMap<>();
		try {
			String queryStr = "select server_type_code, server_type_name from view_server_type";
			List<Object[]> result = executer.executeSQLQueryAsList(queryStr);
			for (Object[] obj : result) {
				errorGroup.put((String) obj[0], (String) obj[1]);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return (errorGroup != null && errorGroup.size() > 0) ? errorGroup : null;
	}

	@Override
	public String addPhysicalServerEntry(ServerDetailModel serverDetailModel) {
		String response = "error";
		try {
			for (int i = 0; i < 2; i++) {

				ServerDetailModel entity = new ServerDetailModel();

				entity.setServer_owner_name(serverDetailModel.getServer_owner_name());
				entity.setEntity_code(serverDetailModel.getEntity_code());
				entity.setServer_ip(serverDetailModel.getServer_ip());
				entity.setPublic_ip(serverDetailModel.getPublic_ip());
				entity.setHost_name(serverDetailModel.getHost_name());
				entity.setMapped_drive(serverDetailModel.getMapped_drive());
				entity.setTag_name(serverDetailModel.getTag_name());
				entity.setServer_remark(serverDetailModel.getServer_remark());
				entity.setLastupdate(new Date());

				if (i == 0) {
					entity.setConfiguration_type(serverDetailModel.getConfiguration_type());
					entity.setServer_os(serverDetailModel.getServer_os());
					entity.setRemote_username(serverDetailModel.getRemote_username());
					entity.setRemote_pwd(serverDetailModel.getRemote_pwd());
				} else if (i == 1) {
					entity.setServer_type_code(serverDetailModel.getServer_type_code());
					entity.setInstalled_db(serverDetailModel.getInstalled_db());
					entity.setInstalled_db_tool(serverDetailModel.getInstalled_db_tool());
					String max = serverDetailsRepository.getMaxCount();
					entity.setParent_server(max);
				}
				this.serverDetailsRepository.save(entity);
			}
			response = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public String addAppServerEntry(ServerDetailModel serverDetailModel, String server_id) {
		String response = "error";
		try {
			
			System.out.println("serverDetailModel..============"+serverDetailModel);
			
			

			Optional<ServerDetailModel> server_Detail = this.serverDetailsRepository.findById(server_id);
			

			System.out.println("server_details>>" + server_Detail);
			ServerDetailModel serverDetailModel1 = server_Detail.get();

			System.out.println("serverDetailModel.getApp_server_name()===="+server_Detail.get().getApp_server_name());
			server_Detail.get().setServer_type_code(serverDetailModel.getServer_type_code());
			server_Detail.get().setServer_owner_name(serverDetailModel.getServer_owner_name());
//			server_Detail.get().setConfiguration_type(serverDetailModel.getConfiguration_type());
			server_Detail.get().setEntity_code(serverDetailModel.getEntity_code());
			server_Detail.get().setServer_ip(serverDetailModel.getServer_ip());
			server_Detail.get().setPublic_ip(serverDetailModel.getPublic_ip());
			server_Detail.get().setHost_name(serverDetailModel.getHost_name());
			server_Detail.get().setServer_os(serverDetailModel.getServer_os());
			server_Detail.get().setRemote_username(serverDetailModel.getRemote_username());
			server_Detail.get().setRemote_pwd(serverDetailModel.getRemote_pwd());
			server_Detail.get().setMapped_drive(serverDetailModel.getMapped_drive());
			server_Detail.get().setTag_name(serverDetailModel.getTag_name());
			server_Detail.get().setServer_remark(serverDetailModel.getServer_remark());
			server_Detail.get().setLastupdate(new Date());
			
//			server_Detail.get().setConfiguration_type(serverDetailModel.getConfiguration_type());
			server_Detail.get().setApp_server_name(server_Detail.get().getApp_server_name());
			server_Detail.get().setApp_server_ip(serverDetailModel.getApp_server_ip());
//			server_Detail.get().setPublic_ip(serverDetailModel.getPublic_ip());
			server_Detail.get().setApp_server_port(serverDetailModel.getApp_server_port());
			server_Detail.get().setApp_server_console_add(serverDetailModel.getApp_server_console_add());
			server_Detail.get().setApp_server_log_path(serverDetailModel.getApp_server_log_path());
			server_Detail.get().setApp_server_username(serverDetailModel.getApp_server_username());
			server_Detail.get().setApp_server_pwd(serverDetailModel.getApp_server_pwd());
			server_Detail.get().setApp_server_tag_name(serverDetailModel.getApp_server_tag_name());
			server_Detail.get().setApp_server_remark(serverDetailModel.getApp_server_remark());
			server_Detail.get().setLastupdate(new Date());

			serverDetailModel = serverDetailsRepository.saveAndFlush(server_Detail.get());
			System.out.println("serverDetailModel............................."+serverDetailModel);

			response = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public String addAppDetailEntry(ServerDetailModel serverDetailModel, String server_id) {
		String response = "error";
		System.out.println("servber id====" + server_id);
		try {
			Optional<ServerDetailModel> server_Detail = this.serverDetailsRepository.findById(server_id);

			ServerDetailModel entity = new ServerDetailModel();

			entity.setParent_server(server_Detail.get().getParent_server());
			entity.setConfiguration_type("");
			entity.setServer_type_code("APS");
			entity.setParent_server(server_id);
			entity.setServer_owner_name(server_Detail.get().getServer_owner_name());
			entity.setEntity_code(server_Detail.get().getEntity_code());
			entity.setServer_ip(server_Detail.get().getServer_ip());
			entity.setPublic_ip(server_Detail.get().getPublic_ip());
			entity.setHost_name(server_Detail.get().getHost_name());
			entity.setServer_os(server_Detail.get().getServer_os());
			entity.setRemote_username(server_Detail.get().getRemote_username());
			entity.setRemote_pwd(server_Detail.get().getRemote_pwd());
			entity.setMapped_drive(server_Detail.get().getMapped_drive());
			entity.setTag_name(server_Detail.get().getTag_name());
			entity.setInstalled_db(server_Detail.get().getInstalled_db());
			entity.setInstalled_db_tool(server_Detail.get().getInstalled_db_tool());
			entity.setServer_remark(server_Detail.get().getServer_remark());

//			entity.setConfiguration_type(serverDetailModel.getConfiguration_type());
			entity.setApp_server_name(serverDetailModel.getApp_server_name());
			entity.setApp_server_ip(serverDetailModel.getApp_server_ip());
//			entity.setPublic_ip(serverDetailModel.getPublic_ip());
			entity.setApp_server_port(serverDetailModel.getApp_server_port());
			entity.setApp_server_console_add(serverDetailModel.getApp_server_console_add());
			entity.setApp_server_log_path(serverDetailModel.getApp_server_log_path());
			entity.setApp_server_username(serverDetailModel.getApp_server_username());
			entity.setApp_server_pwd(serverDetailModel.getApp_server_pwd());
			entity.setApp_server_tag_name(serverDetailModel.getApp_server_tag_name());
			entity.setApp_server_remark(serverDetailModel.getApp_server_remark());
			entity.setLastupdate(new Date());

			this.serverDetailsRepository.save(entity);

//			serverDetailModel=serverDetailsRepository.saveAndFlush(server_Detail.get());
//			System.out.println("serverDetailModel1>>>>>>>>"+server_Detail.get());

			response = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public String addAppNameEntry(ServerDetailModel serverDetailModel, String server_id, String appName) {
		String response = "error";
		try {
			if (serverDetailModel != null) {
				Optional<ServerDetailModel> server_Detail = this.serverDetailsRepository.findById(server_id);
//				server_Detail.get().setApp_name(appName);
				serverDetailModel.setLastupdate(new Date());
				this.serverDetailsRepository.save(server_Detail.get());
				response = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<ServerDetailModel> getServerList() {
		List<ServerDetailModel> serverList = new ArrayList<ServerDetailModel>();
		try {
			serverList = serverDetailsRepository.findAll();
//			System.out.println("serverList=="+serverList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return serverList;
	}

	@Override
	public List<ServerDetailModel> getServerListByServerType(String server_type) {
		List<ServerDetailModel> serverList = new ArrayList<ServerDetailModel>();
		String Query = "";
		try {
			Query = "select a.entity_code,\r\n" + "       a.configuration_type,\r\n" + "       a.server_type_code,\r\n"
					+ "       a.parent_server,\r\n" + "       a.server_owner_name,\r\n" + "       a.server_ip,\r\n"
					+ "       a.public_ip,\r\n" + "       a.host_name,\r\n" + "       a.server_os,\r\n"
					+ "       a.remote_username,\r\n" + "       a.remote_pwd,\r\n" + "       a.mapped_drive,\r\n"
					+ "       a.tag_name,\r\n" + "       a.installed_db,\r\n" + "       a.server_remark,\r\n"
					+ "       a.app_server_name,\r\n" + "       a.app_server_ip,\r\n" + "       a.app_server_port,\r\n"
					+ "       a.app_server_console_add,\r\n" + "       a.app_server_log_path,\r\n"
					+ "       a.app_server_username,\r\n" + "       a.app_server_pwd,\r\n"
					+ "       a.app_server_tag_name,\r\n" + "       a.app_server_remark,\r\n"
					+ "       a.lastupdate,\r\n" + "       a.flag,\r\n" + "       a.installed_db_tool,\r\n"
					+ "       a.server_id\r\n" + "  from lhs_taxcpc_server_details a ";
			if (!server_type.isEmpty()) {
				Query = Query + "where a.server_type_code='" + server_type + "' order by a.lastupdate desc";
			}
			System.out.println("Query======" + Query);
			serverList = executer.executeSQLQuery6(Query);
//			System.out.println("serverList=="+serverList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return serverList;
	}

	@Override
	public List<ServerDetailModel> getServerListByServerId(String server_id, String server_type_code) {
		List<ServerDetailModel> serverList = new ArrayList<ServerDetailModel>();
		System.out.println("server_id==" + server_id);
		System.out.println("server_type_code==" + server_type_code);
		String Query = "";
		try {
//			Query = "select*  from lhs_taxcpc_server_details a WHERE 1=1";

			Query = "select * from LHS_TAXCPC_SERVER_DETAILS t WHERE t.server_type_code ='" + server_type_code
					+ "' AND t.server_id =" + server_id;

//			if (!server_id.isEmpty()) {
//				Query = Query + " and a.server_id='" + server_id + "'";
//			}
			if (!server_type_code.isEmpty()) {
//				Query = Query + " and a.server_type_code='" + server_type_code + "'";
				Query = "select * from LHS_TAXCPC_SERVER_DETAILS t WHERE t.server_type_code ='" + server_type_code
						+ "' AND t.server_id =" + server_id;
			} else {
				Query = "select * from LHS_TAXCPC_SERVER_DETAILS t WHERE t.server_type_code ='APS' AND t.app_server_name is not null AND t.parent_server ="
						+ server_id;
			}
			System.out.println("Query>>" + Query);
			serverList = executer.executeSQLQuery6(Query);
			System.out.println("serverList=>>" + serverList);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return serverList;
	}

	@Override
	public long getUserCount(String server_type) {
		return serverDetailsRepositorySupport.getUserTranBrowseCount(server_type);
	}

	@Override
	public List<ServerDetailModel> getServerBrowseList(String server_type, HttpSession session, DataGridDTO dataGridDTO,
			String recPerPage) {
		List<ServerDetailModel> list = new ArrayList<>();
		PaginatorManipulation manipulation = new PaginatorManipulation();
		System.out.println("dataGridDTO.getPaginator()....." + dataGridDTO.getPaginator());
		try {
			PaginatorEntity paginatorEntity = manipulation.getCalculatedValue(dataGridDTO.getPaginator());
//			System.out.println("paginatorEntity.getRecordsPerPage()).." + paginatorEntity.getRecordsPerPage());
			if (paginatorEntity != null) {
				long minVal = paginatorEntity.getMinVal();
//				long maxVal = (!strUtl.isNull(paginatorEntity.getRecordsPerPage()) && !paginatorEntity.getRecordsPerPage().equalsIgnoreCase("ALL")) ? Long.parseLong(paginatorEntity.getRecordsPerPage()) : paginatorEntity.getTotal();
				long maxVal = (!strUtl.isNull(recPerPage) && !recPerPage.equalsIgnoreCase("ALL"))
						? Long.parseLong(recPerPage)
						: paginatorEntity.getTotal();
				System.out.println("minVal.." + minVal);
				System.out.println("maxVal.." + maxVal);

//				UserMast userMast = (UserMast) session.getAttribute("LOGIN_USER");
				list = serverDetailsRepositorySupport.getReqApprovalBrowseList(server_type, minVal, maxVal);
//				System.out.println("list==" + list);
			} else {
				System.out.println("Object is null..");
			}
		} catch (Exception e) {
		}
		return list;
	}

	@Override
	public List<ServerDetailModel> getServerBrowseList1(HttpSession session, DataGridDTO dataGridDTO, String recPerPage,
			String server_type) {
		List<ServerDetailModel> list = new ArrayList<>();
		PaginatorManipulation manipulation = new PaginatorManipulation();
		System.out.println("dataGridDTO.getPaginator()..." + dataGridDTO.getPaginator());
		try {
			PaginatorEntity paginatorEntity = manipulation.getCalculatedValue(dataGridDTO.getPaginator());
//			System.out.println("paginatorEntity.getRecordsPerPage()).." + paginatorEntity.getRecordsPerPage());
			if (paginatorEntity != null) {
				long minVal = paginatorEntity.getMinVal();
//				long maxVal = (!strUtl.isNull(paginatorEntity.getRecordsPerPage()) && !paginatorEntity.getRecordsPerPage().equalsIgnoreCase("ALL")) ? Long.parseLong(paginatorEntity.getRecordsPerPage()) : paginatorEntity.getTotal();
				long maxVal = (!strUtl.isNull(recPerPage) && !recPerPage.equalsIgnoreCase("ALL"))
						? Long.parseLong(recPerPage)
						: paginatorEntity.getTotal();
				System.out.println("minVal.." + minVal);
				System.out.println("maxVal.." + maxVal);

//				UserMast userMast = (UserMast) session.getAttribute("LOGIN_USER");
				list = serverDetailsRepositorySupport.getServerBrowseList1(server_type, minVal, maxVal);
				System.out.println("list==" + list);
			} else {
				System.out.println("Object is null..");
			}
		} catch (Exception e) {
		}
		return list;
	}

	@Override
	@Transactional
	public String deleteServerById(String server_id, String server_type_code) {
		String response = "error";
		try {

//			String query = "delete from lhs_taxcpc_server_details where server_id in\r\n" + 
//					"                  (select a.server_id from lhs_taxcpc_server_details a\r\n" + 
//					"                     where a.parent_server =:server_id1 OR a.server_id =:server_id2 OR PARENT_SERVER = NULL)";
//			Query nativeQuery = entityManager.createNativeQuery(query, ServerDetailModel.class);
//			nativeQuery.setParameter("server_id1", server_id);
//			nativeQuery.setParameter("server_id2", server_id);
//			System.out.println("query===="+query);
//			nativeQuery.executeUpdate();
			List<Integer> app_code =appDetailsRepo.getAppCode(server_id);
			System.out.println("app_code=="+app_code);
			
			if(server_type_code.equalsIgnoreCase("null")) {//physical server
			
			serverDetailsRepository.deleteById(server_id);
			int temp = Integer.parseInt(server_id);
			temp++;
			String server_id1= String.valueOf(temp);
			serverDetailsRepository.deleteById(server_id1);
			
			response = "success";
			}else {
				serverDetailsRepository.deleteById(server_id); //app server
				if(! app_code.isEmpty()) {
					for(int i= 0; i < app_code.size(); i++) {
						appDetailsRepo.deleteById(app_code.get(i));
					}
				}
				response = "success";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("get error msg...."+ e.getMessage());
		}
		return response;
	}

	@Override
	public ServerDetailModel getServerById(String server_id) {
		ServerDetailModel serverDetailModel = new ServerDetailModel();
		System.out.println("server_ID==" + server_id);
		try {

			serverDetailModel = serverDetailsRepository.findById(server_id).get();
			System.out.println("serverDetailModel....." + serverDetailModel);

			serverDetailModel.setServer_type_code("APS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serverDetailModel;
	}

	@Override
	public List<String> getAppNameListById(String server_id) {
		List<String> list = new ArrayList<>();
		try {
//			list = serverDetailsRepository.getAppNameList(server_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public long getUserCount() {
		String server_type = null;
		return serverDetailsRepositorySupport.getUserTranBrowseCount(server_type);
	}

	@Override
	public Map<String, String> getServerIpList() {
		Map<String, String> serverList = new HashMap<String, String>();
		try {
//		serverList= serverDetailsRepository.findAll()
//				String queryStr = "select  t.server_id , t.server_ip from lhs_taxcpc_server_details t where t.server_type_code='APS'";
			String queryStr = "select v.parent_server, v.server_ip\r\n" + "  from LHS_TAXCPC_SERVER_DETAILS v\r\n"
					+ " where v.server_ip in (select t.server_ip\r\n"
					+ "                         from LHS_TAXCPC_SERVER_DETAILS t\r\n"
					+ "                        WHERE t.server_type_code = 'APS'\r\n"
					+ "                        group by t.server_ip)\r\n" + "   and v.parent_server is not null\r\n"
					+ " group by v.parent_server, v.server_ip";
			List<Object[]> result = executer.executeSQLQueryAsList(queryStr);
			for (Object[] obj : result) {
				serverList.put((String) obj[0], (String) obj[1]);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return (serverList != null && serverList.size() > 0) ? serverList : null;
	}

	@Override
	public Map<String, String> getAppServerNameList() {
		Map<String, String> appServerName = new HashMap<>();
		try {
			String queryStr = " select server_type_code, server_type_name from view_server_name ";

			List<Object[]> result = executer.executeSQLQueryAsList(queryStr);
			for (Object[] obj : result) {
				appServerName.put((String) obj[0], (String) obj[1]);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return (appServerName != null && appServerName.size() > 0) ? appServerName : null;
	}

	@Override
	public Map<String, String> getDrivelist() {

		Map<String, String> driveList = new LinkedHashMap<String, String>();
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			driveList.put(ch + ":", ch + ":");
		}
//		System.out.println("drivelist=="+driveList);
		return (driveList != null && driveList.size() > 0) ? driveList : null;
	}

	@Override
	public String getServerIDByServerIP(String serverIp) {
		String serverID = serverDetailsRepository.getServerId(serverIp);
		System.out.println("serverID=====" + serverID);
		return serverID;
	}

	@Override
	public String validateServerName(String app_server_ip, String app_server_name) {

		String response = "";
		try {
			response = serverDetailsRepository.validateServerName(app_server_ip, app_server_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public String setPublicIp(String app_server_ip) {
		String response = "";
		try {
			response = serverDetailsRepository.setPublicIp(app_server_ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public String saveAppNameEntry(AppDetails serverDetailModel) {
		String response = "error";
		AppDetails appDetails = new AppDetails();
		try {
			System.out.println("serverDetailModel==>" + serverDetailModel);

			appDetailsRepo.save(serverDetailModel);
//			appDetails = appDetailsRepo.saveAndFlush(serverDetailModel);

			response = "success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public String updatePhysicalServerEntry(ServerDetailModel serverDetailModel, String server_id) {
		ServerDetailModel entity = new ServerDetailModel();
		String response = "error";
		try {
			if (!strUtl.isNull(server_id)) {
//			entity.setConfiguration_type(serverDetailModel.getConfiguration_type());
//			entity.setServer_owner_name(serverDetailModel.getServer_owner_name());
//			entity.setEntity_code(serverDetailModel.getEntity_code());
//			entity.setServer_ip(serverDetailModel.getServer_ip());
//			entity.setPublic_ip(serverDetailModel.getPublic_ip());
//			entity.setHost_name(serverDetailModel.getHost_name());
//			entity.setMapped_drive(serverDetailModel.getMapped_drive());
//			entity.setTag_name(serverDetailModel.getTag_name());
//			entity.setServer_remark(serverDetailModel.getServer_remark());
//			entity.setServer_os(serverDetailModel.getServer_os());
//			entity.setRemote_username(serverDetailModel.getRemote_username());
//			entity.setRemote_pwd(serverDetailModel.getRemote_pwd());
//			entity.setLastupdate(new Date());
//			entity.setServer_type_code(serverDetailModel.getServer_type_code());

				Optional<ServerDetailModel> server_Detail = this.serverDetailsRepository.findById(server_id);

//			server_Detail.get().setServer_type_code(serverDetailModel.getServer_type_code());
				server_Detail.get().setServer_owner_name(serverDetailModel.getServer_owner_name());
				server_Detail.get().setConfiguration_type(serverDetailModel.getHidden_config_type());
				server_Detail.get().setEntity_code(serverDetailModel.getEntity_code());
				server_Detail.get().setServer_ip(serverDetailModel.getServer_ip());
				server_Detail.get().setPublic_ip(serverDetailModel.getPublic_ip());
				server_Detail.get().setHost_name(serverDetailModel.getHost_name());
				server_Detail.get().setServer_os(serverDetailModel.getServer_os());
				server_Detail.get().setRemote_username(serverDetailModel.getRemote_username());
				server_Detail.get().setRemote_pwd(serverDetailModel.getRemote_pwd());
				server_Detail.get().setMapped_drive(serverDetailModel.getMapped_drive());
				server_Detail.get().setInstalled_db(serverDetailModel.getInstalled_db());
				server_Detail.get().setInstalled_db_tool(serverDetailModel.getInstalled_db_tool());
				server_Detail.get().setTag_name(serverDetailModel.getTag_name());
				server_Detail.get().setServer_remark(serverDetailModel.getServer_remark());
				server_Detail.get().setLastupdate(new Date());

				serverDetailModel = serverDetailsRepository.save(server_Detail.get());

//			this.serverDetailsRepository.save;
				response = "success";
				System.out.println("askdjh==" + response);
			} else {
				System.out.println("etew==" + response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<AppDetails> getAppNameDetails(String server_id) {
		List<AppDetails> list = new ArrayList<>();
		try {
			list = appDetailsRepo.getAppNameDetails(server_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> getListOfId(String serverId) {
		List<String> list = new ArrayList<>();
		try {
			list = appDetailsRepo.getListOfId(serverId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String validateAppName(String server_id) {
		String var = "";
		try {
//			System.out.println("serverID............" + server_id);
			var = serverDetailsRepository.getValidateAppName(server_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return var;
	}

	@Override
	public String viewServerDetails(String server_id) {

		StringBuilder sb = new StringBuilder();

		ServerDetailModel entity = getAllDataByServerId(server_id);

		String appName = "";
		String serverType= "";
		List<String> entity_name = new ArrayList<String>();
		String server_ip = entity.getServer_ip();
		List<String> listOfID = new ArrayList<>();
		listOfID = serverDetailsRepository.getListOfID(server_id);
		System.out.println("listOfID=" + listOfID);
		
		if(entity.getServer_type_code() != null && entity.getServer_type_code().equalsIgnoreCase("APS")){
			serverType = "Application Server";
		}else if(entity.getServer_type_code() != null && entity.getServer_type_code().equalsIgnoreCase("DBS")) {
			serverType = "Database Server";
		}else {
			serverType = "Physical Server";
		}
		
		String b[]= {};
		try{
		if(! entity.getEntity_code().isEmpty()) {
			if(entity.getEntity_code().contains(",")) {
				b=entity.getEntity_code().split(",");
				System.out.println("b.length--"+b.length);
				for (int i=0 ; i< b.length ; i++) {
					entity_name.add( entityMastRepository.getEntityName(b[i]) );
					System.out.println("entity_name--"+entity_name);
				}
			}
//			entity_name.add( entityMastRepository.getEntityName(entity.getEntity_code()) );
//		ServerDetailModel appServerEntity = getAllDataByServerId(listOfID.get(0));
		}else {
			entity_name.add("");
		}
		}catch (Exception e) {
//			e.printStackTrace();
		}
		System.out.println("entity_name--"+entity_name);
		
		sb.append("<div>");
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-md-12\">");
		
		try {

		if (entity != null) {
			if(strUtl.isNull(entity.getParent_server()) || entity.getServer_type_code().equalsIgnoreCase("DBS")) {
				
				sb.append("<div class=\"container\">");
				sb.append("<div id=\"accordion\">");

				sb.append("<div class=\"card\">");
				sb.append("<div class=\"card-header\" style=\"padding: 0.15rem;\">");

				sb.append(" <a class=\"card-link\" data-toggle=\"collapse\" href=\"#collapseOne\"> "+serverType+" Details </a>");
				sb.append(" </div>");

				sb.append("<div id=\"collapseOne\" class=\"collapse show\" data-parent=\"#accordion\">");
				sb.append("<div class=\"card-body\">");

				sb.append("<table  class=\"table details-table\">");
				sb.append("<tbody>");

				if (entity.getServer_type_code() != null) {
					sb.append("<tr><td class=\"text-bold\">Server Type  </td><td>" + serverType
							+ "</td></tr>");
				} else {
					sb.append("<tr><td class=\"text-bold\">Server Type  </td><td> Physical Server</td></tr>");
				}
				if (entity.getServer_owner_name() != null) {
					sb.append("<tr><td class=\"text-bold\">Server Owner  </td><td>" + entity.getServer_owner_name()
							+ "</td></tr>");
				} else {
					sb.append("<tr><td class=\"text-bold\">Server Type  </td><td></td></tr>");
				}
				if (entity.getEntity_code() != null) {
					sb.append(
							"<tr><td class=\"text-bold\">Entity Name  </td><td class=\"pre-wrap\">" + entity_name + "</td></tr>");
				} else {
					sb.append("<tr><td class=\"text-bold\">Entity Name  </td><td></td></tr>");
				}
				if (entity.getServer_ip() != null) {
					sb.append("<tr><td class=\"text-bold\">Server IP  </td><td>" + entity.getServer_ip() + "</td></tr>");
				} else {
					sb.append("<tr><td class=\"text-bold\">Server IP  </td><td></td></tr>");
				}
				if (entity.getPublic_ip() != null) {
					sb.append("<tr><td class=\"text-bold\">Public IP  </td><td>" + entity.getPublic_ip() + "</td></tr>");
				} else {
					sb.append("<tr><td class=\"text-bold\">Public IP  </td><td></td></tr>");
				}
				if (entity.getHost_name() != null) {
					sb.append("<tr><td class=\"text-bold\">Host Name  </td><td>" + entity.getHost_name() + "</td></tr>");
				} else {
					sb.append("<tr><td class=\"text-bold\">Host Name Type  </td><td></td></tr>");
				}
				if (entity.getServer_os() != null) {
					sb.append("<tr><td class=\"text-bold\">Server OS  </td><td>" + entity.getServer_os() + "</td></tr>");
				} else {
					sb.append("<tr><td class=\"text-bold\">Server OS  </td><td></td></tr>");
				}
				if (entity.getRemote_username() != null) {
					sb.append("<tr><td class=\"text-bold\">Remote Username  </td><td>" + entity.getRemote_username()
							+ "</td></tr>");
				} else {
					sb.append("<tr><td class=\"text-bold\">Remote Username  </td><td></td></tr>");
				}
				if (entity.getRemote_pwd() != null) {
					sb.append("<tr><td class=\"text-bold\">Remote Password  </td><td>" + entity.getRemote_pwd()
							+ "</td></tr>");
				} else {
					sb.append("<tr><td class=\"text-bold\">Remote Password  </td><td></td></tr>");
				}
				if (entity.getMapped_drive() != null) {
					sb.append("<tr><td class=\"text-bold\">Drive required for mapping  </td><td>" + entity.getMapped_drive()
							+ "</td></tr>");
				} else {
					sb.append("<tr><td class=\"text-bold\">Drive required for mapping  </td><td></td></tr>");
				}
				if (entity.getTag_name() != null) {
					sb.append("<tr><td class=\"text-bold\">Tag Name  </td><td>" + entity.getTag_name() + "</td></tr>");
				} else {
					sb.append("<tr><td class=\"text-bold\">Tag Name  </td><td></td></tr>");
				}
				if(entity.getServer_type_code() != null && entity.getServer_type_code().equalsIgnoreCase("DBS")) {
				if(entity.getInstalled_db() != null && entity.getServer_type_code().equalsIgnoreCase("DBS")) {
					sb.append("<tr><td class=\"text-bold\">Install Database  </td><td>" + entity.getInstalled_db() + "</td></tr>");
				}else {
					sb.append("<tr><td class=\"text-bold\">Install Database  </td><td></td></tr>");
				}
				if(entity.getInstalled_db_tool() != null && entity.getServer_type_code().equalsIgnoreCase("DBS")) {
					sb.append("<tr><td class=\"text-bold\">Install Database Tool </td><td>" + entity.getInstalled_db_tool() + "</td></tr>");
				}else {
					sb.append("<tr><td class=\"text-bold\">Install Database Tool </td><td></td></tr>");
				}}
				if( entity.getServer_remark() != null) {
					sb.append("<tr><td class=\"text-bold\">Remark  </td><td>" + entity.getServer_remark() + "</td></tr>");
				}else {
					sb.append("<tr><td class=\"text-bold\">Remark  </td><td></td></tr>");
				}

				sb.append("</tbody>");
				sb.append("</table>");

				sb.append("</div>");// card body
				sb.append("</div>");// card header
				sb.append("</div>");// card

				/////////////////////

//	      		sb.append("<div class=\"container\">"); 
//	  	      	sb.append("<div id=\"accordion\">");
				if(! listOfID.isEmpty()) {
					
				
				sb.append("<div class=\"card\">");
				sb.append("<div class=\"card-header\" style=\"padding: 0.15rem;\">");

				sb.append(" <a class=\"card-link\" data-toggle=\"collapse\" href=\"#collapseTwo\"> Application Server Details </a>");

				sb.append(" </div>");

				sb.append("<div id=\"collapseTwo\" class=\"collapse \" >");
				sb.append("<div class=\"card-body\">");

				sb.append("<table  class=\"table details-table\">");
				sb.append("<tbody>");
				
				sb.append("<tr><td class=\"\">");
				sb.append("<div class=\"container\">"); 
		      	sb.append("<div id=\"accordion"+1+"\">");
		      	
				for (int i = 0; i < listOfID.size(); i++) {
					System.out.println("i=" + listOfID.get(i));
					
					ServerDetailModel appServerEntity = getAllDataByServerId(listOfID.get(i));
					
					if(appServerEntity.getApp_server_name().equalsIgnoreCase("JBS")){
						appName = "JBOSS";
					}else if(appServerEntity.getApp_server_name().equalsIgnoreCase("WBL")) {
						appName = "WEB LOGIC";
					}else if(appServerEntity.getApp_server_name().equalsIgnoreCase("TMC")) {
						appName = "TOMCAT";
					}
	  	      
					sb.append("<div class=\"card\">");
					sb.append("<div class=\"card-header\" style=\"padding: 0.15rem;\">");

					sb.append(" <a class=\"card-link\" data-toggle=\"collapse\" href=\"#collapseThree"+i+"\"> Application Server Name : "+ appName+" </a>");
					
					sb.append(" </div>");

					sb.append("<div id=\"collapseThree"+i+"\" class=\"collapse \" data-parent=\"#accordion"+1+"\" >");
					sb.append("<div class=\"card-body\">");

					sb.append("<table  class=\"table details-table\">");
					sb.append("<tbody>");
					
//					if(appServerEntity.getApp_server_name() != null) {
//						sb.append("<tr><td class=\"text-bold\">Application Server Name </td><td>" + appServerEntity.getApp_server_name()+ "</td></tr>");
//					}else {
//						sb.append("<tr><td class=\"text-bold\">Application Server Name </td><td></td></tr>");
//					}
					if(appServerEntity.getApp_server_ip() != null) {
						sb.append("<tr><td class=\"text-bold\">Application Server IP </td><td>" + appServerEntity.getApp_server_ip()+ "</td></tr>");
					}else {
						sb.append("<tr><td class=\"text-bold\">Application Server IP </td><td></td></tr>");
					}
					if(appServerEntity.getApp_server_port() != null) {
						sb.append("<tr><td class=\"text-bold\">Application Server Port </td><td>" + appServerEntity.getApp_server_port()+ "</td></tr>");
					}else {
						sb.append("<tr><td class=\"text-bold\">Application Server Port </td><td></td></tr>");
					}
					if(appServerEntity.getApp_server_console_add() != null) {
						sb.append("<tr><td class=\"text-bold\">Application Console Address </td><td>" + appServerEntity.getApp_server_console_add()+":"+appServerEntity.getApp_server_port()+ "</td></tr>");
					}else {
						sb.append("<tr><td class=\"text-bold\">Application Console Address </td><td></td></tr>");
					}
					if(appServerEntity.getApp_server_log_path() != null) {
						sb.append("<tr><td class=\"text-bold\">Application Server Log Path </td><td>" + appServerEntity.getApp_server_log_path()+ "</td></tr>");
					}else {
						sb.append("<tr><td class=\"text-bold\">Application Server Log Path </td><td></td></tr>");
					}
					if(appServerEntity.getApp_server_username() != null) {
						sb.append("<tr><td class=\"text-bold\">Application Server Username </td><td>" + appServerEntity.getApp_server_username()+ "</td></tr>");
					}else {
						sb.append("<tr><td class=\"text-bold\">Application Server Username </td><td></td></tr>");
					}
					if(appServerEntity.getApp_server_pwd() != null) {
						sb.append("<tr><td class=\"text-bold\">Application Server Password </td><td>" + appServerEntity.getApp_server_pwd()+ "</td></tr>");
					}else {
						sb.append("<tr><td class=\"text-bold\">Application Server Password </td><td></td></tr>");
					}
					if(appServerEntity.getApp_server_tag_name() != null) {
						sb.append("<tr><td class=\"text-bold\">Application Server Tag Name </td><td>" + appServerEntity.getApp_server_tag_name()+ "</td></tr>");
					}else
					{
						sb.append("<tr><td class=\"text-bold\">Application Server Tag Name </td><td></td></tr>");
					}
					if(appServerEntity.getApp_server_remark() != null) {
						sb.append("<tr><td class=\"text-bold\">Application Sever Remark  </td><td>" + appServerEntity.getApp_server_remark() + "</td></tr>");
					}else {
						sb.append("<tr><td class=\"text-bold\">Application server Remark  </td><td></td></tr>");
					}
					
					
					sb.append("</tbody>");
					sb.append("</table>");

					sb.append("</div>");// card body
					sb.append("</div>");// card header
					sb.append("</div>");// card
					
					
				}
				sb.append("</div>");// accordion
				sb.append("</div>");// container
				
				sb.append("</td></tr>");
				
				sb.append("</tbody>");
				sb.append("</table>");

				sb.append("</div>");// card body
				sb.append("</div>");// card header
				sb.append("</div>");// card

				///////////////
				sb.append("</div>");// accordion
				sb.append("</div>");// container
				
				}
				
			}else if(entity.getServer_type_code().equalsIgnoreCase("APS") ){
				
				
				sb.append("<div class=\"container\">");
				sb.append("<div id=\"accordion\">");

				sb.append("<div class=\"card\">");

				sb.append("<div class=\"card-header\" style=\"padding: 0.15rem;\">");

				sb.append(" <a class=\"card-link\" data-toggle=\"collapse\" href=\"#collapseOne\"> "+serverType+" Details </a>");
				sb.append(" </div>");

				sb.append("<div id=\"collapseOne\" class=\"collapse show\" data-parent=\"#accordion\">");
				sb.append("<div class=\"card-body\">");
 
				sb.append("<table  class=\"table details-table\">");
				sb.append("<tbody>");
				
				if (entity.getServer_ip() != null) {
					sb.append("<tr><td class=\"text-bold\">Server IP  </td><td>" + entity.getServer_ip() + "</td></tr>");
				} else {
					sb.append("<tr><td class=\"text-bold\">Server IP  </td><td></td></tr>");
				}
				if (entity.getPublic_ip() != null) {
					sb.append("<tr><td class=\"text-bold\">Public IP  </td><td>" + entity.getPublic_ip() + "</td></tr>");
				} else {
					sb.append("<tr><td class=\"text-bold\">Public IP  </td><td></td></tr>");
				}
				
				if(entity.getApp_server_ip() != null) {
					sb.append("<tr><td class=\"text-bold\">Application Server IP </td><td>" + entity.getApp_server_ip()+ "</td></tr>");
				}else {
					sb.append("<tr><td class=\"text-bold\">Application Server IP </td><td></td></tr>");
				}
				if(entity.getApp_server_port() != null) {
					sb.append("<tr><td class=\"text-bold\">Application Server Port </td><td>" + entity.getApp_server_port()+ "</td></tr>");
				}else {
					sb.append("<tr><td class=\"text-bold\">Application Server Port </td><td></td></tr>");
				}
				if(entity.getApp_server_console_add() != null) {
					sb.append("<tr><td class=\"text-bold\">Application Console Address </td><td>" + entity.getApp_server_console_add()+":"+entity.getApp_server_port()+ "</td></tr>");
				}else {
					sb.append("<tr><td class=\"text-bold\">Application Console Address </td><td></td></tr>");
				}
				if(entity.getApp_server_log_path() != null) {
					sb.append("<tr><td class=\"text-bold\">Application Server Log Path </td><td>" + entity.getApp_server_log_path()+ "</td></tr>");
				}else {
					sb.append("<tr><td class=\"text-bold\">Application Server Log Path </td><td></td></tr>");
				}
				if(entity.getApp_server_username() != null) {
					sb.append("<tr><td class=\"text-bold\">Application Server Username </td><td>" + entity.getApp_server_username()+ "</td></tr>");
				}else {
					sb.append("<tr><td class=\"text-bold\">Application Server Username </td><td></td></tr>");
				}
				if(entity.getApp_server_pwd() != null) {
					sb.append("<tr><td class=\"text-bold\">Application Server Password </td><td>" + entity.getApp_server_pwd()+ "</td></tr>");
				}else {
					sb.append("<tr><td class=\"text-bold\">Application Server Password </td><td></td></tr>");
				}
				if(entity.getApp_server_tag_name() != null) {
					sb.append("<tr><td class=\"text-bold\">Application Server Tag Name </td><td>" + entity.getApp_server_tag_name()+ "</td></tr>");
				}else
				{
					sb.append("<tr><td class=\"text-bold\">Application Server Tag Name </td><td></td></tr>");
				}
				if(entity.getApp_server_remark() != null) {
					sb.append("<tr><td class=\"text-bold\">Application Sever Remark  </td><td>" + entity.getApp_server_remark() + "</td></tr>");
				}else {
					sb.append("<tr><td class=\"text-bold\">Application server Remark  </td><td></td></tr>");
				}
				  
				sb.append("</tbody>");
				sb.append("</table>");

				sb.append("</div>");// card body
				sb.append("</div>");// card header
				sb.append("</div>");// card
				
				
			
		}
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</div>");
		return sb.toString();
	}

	private ServerDetailModel getAllDataByServerId(String server_id) {
		ServerDetailModel entity = new ServerDetailModel();

		try {
			entity = serverDetailsRepository.findByServerId(server_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("entity==" + entity);
		return entity;
	}

	@Override
	public String getDeleteCount(String server_id) {
		String response = "";
		try {
			response= serverDetailsRepository.getDeleteCount(server_id);
			System.out.println("response=="+response);
//			response = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public String checkServerIp(String server_id) {
		String response = "";
		try {
			response= serverDetailsRepository.checkServerIp(server_id);
		//	System.out.println("response==....."+response);
//			response = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public String getDeleteAppCount(String server_id) {
		String response = "";
		try {
			response= appDetailsRepo.getDeleteAppCount(server_id);
			System.out.println("response=="+response);
//			response = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}

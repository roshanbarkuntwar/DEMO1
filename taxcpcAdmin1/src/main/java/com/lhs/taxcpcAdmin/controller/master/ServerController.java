package com.lhs.taxcpcAdmin.controller.master;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhs.javautilities.LhsStringUtility;
import com.lhs.taxcpcAdmin.dao.AppDetailsRepository;
import com.lhs.taxcpcAdmin.dao.ServerDetailsRepository;
import com.lhs.taxcpcAdmin.global.pagination.DataGridDTO;
import com.lhs.taxcpcAdmin.global.pagination.PaginatorDTO;
import com.lhs.taxcpcAdmin.global.pagination.PaginatorManipulation;
import com.lhs.taxcpcAdmin.model.entity.AppDetails;
import com.lhs.taxcpcAdmin.model.entity.EntityMast;
import com.lhs.taxcpcAdmin.model.entity.ServerDetailModel;
import com.lhs.taxcpcAdmin.model.entity.UserMast;
import com.lhs.taxcpcAdmin.service.EntityMastService;
import com.lhs.taxcpcAdmin.service.ServerDetails;
import com.sun.net.httpserver.Authenticator.Success;
import com.zaxxer.hikari.util.SuspendResumeLock;

@Controller
public class ServerController {

	@Autowired
	private ServerDetails serverDetails;

	@Autowired
	private EntityMastService entityMastService;

	@Autowired
	private ServerDetailsRepository serverDetailsRepository;

	@Autowired
	private LhsStringUtility strUtl;
	
	@Autowired
	private AppDetailsRepository appDetailsRepo;

//1
	@RequestMapping("/serverDetailEntry")
	public String serverDetailEntry(Model map, @RequestParam(name = "server_id", required = false) String server_id,
			 @RequestParam(name = "server_type_code", required = false) String server_type_code,
			 			HttpSession session) {
		
		session.setAttribute("ACTIVE_TAB", "MENU-017");
		session.setAttribute("ACTIVE_SUB_TAB", "MENU-022");
		
		List<ServerDetailModel> serverlist = new ArrayList<ServerDetailModel>();
		Map<String, String> serverTypeList = new HashMap<String, String>();
		Map<String, String> entityNameList = new HashMap<String, String>();
		Map<String, String> serverIpList = new HashMap<String, String>();
		Map<String, String> appServerName = new HashMap<String, String>();
		Map<String, String> driveList = new HashMap<String, String>();
		ServerDetailModel serverDetailModel = new ServerDetailModel();

		String action = "save";

		try {
//			serverlist = serverDetails.getServerList();
			serverTypeList = serverDetails.getServerTypeList();
			entityNameList = entityMastService.getEntityNameList(); 
			serverIpList = serverDetails.getServerIpList();
			appServerName = serverDetails.getAppServerNameList();
			driveList= serverDetails.getDrivelist();
			System.out.println("entityNameList=="+entityNameList);

			
			if (server_id != null) {
				action = "edit";
				serverDetailModel = serverDetails.getServerById(server_id);
				
			}
			else {
				serverlist = serverDetailsRepository.findAll();
				map.addAttribute("serverDetailModel", serverlist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.addAttribute("serverDetailModel", serverDetailModel);
		map.addAttribute("driveList", driveList);
		map.addAttribute("appServerName", appServerName);
		map.addAttribute("serverIpList", serverIpList);
		map.addAttribute("serverTypeList", serverTypeList);
		map.addAttribute("entityNameList", entityNameList);
		map.addAttribute("action", action);
		map.addAttribute("server_type_code_edit", server_type_code);

		return "pages/server/serverEntry";
	}

//Physical Server -->DBS/APS
	@RequestMapping("/physicalServerEntry")
	@ResponseBody
	public String physicalServerEntry(ServerDetailModel serverDetailModel, HttpSession session,
			@RequestParam(name = "server_id", required = false) String server_id) {
		String response = "error";
		try {
			if (strUtl.isNull(server_id)) { //save entry
				response = serverDetails.addPhysicalServerEntry(serverDetailModel);
			} else { //update
//				serverDetailModel.setConfiguration_type(serverDetailModel.getHidden_config_type());
				response = serverDetails.updatePhysicalServerEntry(serverDetailModel,server_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

// APP Server--> WBL/JBS/TMT
	@RequestMapping("/appServerDetailEntry")
	@ResponseBody
	public String appServerDetailEntry(ServerDetailModel serverDetail, HttpSession httpSession, Model map,
			@RequestParam(name = "server_id", required = false) String server_id) {
		String response = "error";
		
		System.out.println("server_id...>>" + server_id);
		ServerDetailModel serverDetailModel = new ServerDetailModel();
		String server_id2 = serverDetail.getServer_id();
		System.out.println("server_id2>>" + server_id2);
		try {
			if (!server_id2.isEmpty()) {//update

				
				serverDetail.getServer_ip();
				serverDetailModel = serverDetails.getServerById(server_id2);
				System.out.println("serverDetailModel===" + serverDetailModel);
				
				response=	 serverDetails.addAppServerEntry(serverDetail, server_id2);

//				response= "success";
				String max= serverDetailsRepository.getMaxCount();
				httpSession.setAttribute("serverId", max);
			} else {//save
			
				String serverIp=serverDetail.getServer_ip_config();
				System.out.println("serverIp=="+serverIp);
				String severID= serverDetails.getServerIDByServerIP(serverIp);
				System.out.println("severID=="+severID);
//				serverDetailModel = serverDetails.getServerById(severID);

				response =serverDetails.addAppDetailEntry(serverDetail, severID);
				
				String max= serverDetailsRepository.getMaxCount();
				httpSession.setAttribute("serverId", max);
				map.addAttribute("server_id", max);
				
				
//				response = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.addAttribute("serverDetailModel", serverDetailModel);
		return response;
	}

//2s
//	@RequestMapping("/appDetailEntry")
//	@ResponseBody
//	public String appDetailEntry(ServerDetailModel serverDetailModel, HttpSession httpSession) {
//		String response = "error";
//		String serverId = (String) httpSession.getAttribute("serverId");
////		String serverId = server_id.toString();
//		try {
//			if (serverDetailModel != null) {
//				response = serverDetails.addAppDetailEntry(serverDetailModel, serverId);
//				response = "success";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return response;
//	}

//3s
	@RequestMapping("/appNameEntry")
	@ResponseBody
	public String appNameEntry(AppDetails serverDetailModel, @RequestParam(name = "appNames" , required = false) String appNameList,
			 @RequestParam(name = "localAppUrl", required = false) String localAppUrl,
			 @RequestParam(name = "publicAppUrl", required = false) String publicAppUrl,
			 @RequestParam(name = "appCode", required = false) String appCode,
			HttpSession httpSession) {
		
		
		System.out.println("appNames=="+appNameList);
		System.out.println("localAppUrl=="+localAppUrl);
		System.out.println("publicAppUrl=="+publicAppUrl);
		
		String response = "error";
		Object server_id = httpSession.getAttribute("serverId");
		String serverId = server_id.toString();
		System.out.println("serverId>"+serverId);
		AppDetails appDetails = new AppDetails();
		
		
		String str1[] = appNameList.split(",");
		String str2[] =localAppUrl.split(",");
		String str3[] = publicAppUrl.split(",");
		String str4[] = appCode.split(",");
		
		System.out.println("appNameList="+appNameList);
		try {
			if (serverDetailModel != null) {
				for(int l=0; l< str1.length; l++) {
					System.out.println("applength="+str1.length);
					
					serverDetailModel.setApp_name(str1[l]);
					
				if(str2[l].equalsIgnoreCase("null")) {
					serverDetailModel.setLocal_app_url("");
				}else {
					serverDetailModel.setLocal_app_url(str2[l]);
				}
				if(str3[l].equalsIgnoreCase("null")) {
					serverDetailModel.setPublic_app_url("");
				}else {
					serverDetailModel.setPublic_app_url(str3[l]);
				}
				if(str4[l].equalsIgnoreCase("null")) {
					System.out.println("str4=null>>"+str4[l]);
					serverDetailModel.setApp_code(null);
				}else {
					System.out.println("str4=="+str4[l]);
					Integer int1 = Integer.parseInt(str4[l]);
					
					serverDetailModel.setApp_code(int1);
				}
				
				serverDetailModel.setServer_id(serverId);
				serverDetailModel.setLastupdate(new Date());
				
				response=serverDetails.saveAppNameEntry(serverDetailModel);
				System.out.println("respose="+response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

//1t
	@RequestMapping("/appServerDetailTemp")
	public String appServerDetailEntryTemp(AppDetails appDetails, Model map, HttpSession httpSession,
			@RequestParam(name = "server_id", required = false) String server_id) {
//		ServerDetailModel serverDetailModel = new ServerDetailModel();
		AppDetails entity = new AppDetails();
		List<AppDetails> details = new ArrayList<AppDetails>();
		
		int size =0;
		System.out.println("server_id=>>"+server_id);
		String action = "save";
//		 String server= serverDetail.getServer_id();
		String server_id1 = (String) httpSession.getAttribute("serverId");
		System.out.println("server_id=" + server_id1); 
//		 httpSession.getAttribute("serverDetailModel");
//		 System.out.println("session=="+httpSession.getAttribute("serverDetailModel"));
		try {
			if (! server_id.isEmpty()) {
				action = "edit";
//				entity =  appDetailsRepo.findAll();
				details = serverDetails.getAppNameDetails(server_id1);
//				serverDetailModel = serverDetails.getServerById(server_id1);
				 size= details.size();
				System.out.println("size="+details.size());
				System.out.println("details=1=" + details);
			}else {
				action = "save";
				details =  appDetailsRepo.findAll();
//				serverDetailModel = serverDetails.getServerById(server_id1);
				System.out.println("details=2=" + details);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.addAttribute("server_id", server_id1);
		map.addAttribute("action", action);
		map.addAttribute("size", size);
		map.addAttribute("appDetailsList", details);
		return "pages/server/appilicationNameEntry";
	}

//2t
	@RequestMapping("/appServerDetailEntryTemp")
	public String appServerDetailEntryTemp(ServerDetailModel serverDetailModel, HttpSession httpSession, Model map,
			@RequestParam(name = "server_id", required = false) String server_id) {

//		String server_id1 = (String) httpSession.getAttribute("serverId");
//		System.out.println("server_id=" + server_id1);
		try {
			serverDetailModel = serverDetails.getServerById(server_id);
			System.out.println("serverDetailsModel2==" + serverDetailModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.addAttribute("serverDetailModel", serverDetailModel);
		return "pages/server/applicationServerDetailEntry";
	}

	
	@RequestMapping(value = "/getAppServerListWithStatus", method = RequestMethod.GET)
	public @ResponseBody String getAppServerListWithStatus(@RequestParam("server_id") String server_id,
			@RequestParam("server_type_code") String server_type_code,
			Model map) {
		String appName ="";
		System.out.println("server_id>>" + server_id);
		System.out.println("server_type_code>>" + server_type_code);
		int tableCount = 0;
		StringBuilder sb = new StringBuilder();
		try {
			List<ServerDetailModel> serverListByServerId = serverDetails.getServerListByServerId(server_id,server_type_code);
			System.out.println("serverListByServerId=="+serverListByServerId);
//			for(int i=0 ; i> serverListByServerId.size(); i++) {
//			System.out.println("serverListByServerId=="+serverListByServerId.get(i).getServer_id());
//			}&& server_type_code.equalsIgnoreCase("APS")
			
			String var= serverDetails.validateAppName(server_id);
//			System.out.println("val.........."+var);
			if (serverListByServerId != null && server_type_code.isEmpty()) {
				sb.append("<table id=\"appServerTable_" + tableCount + "\" class=\"table\">");
				sb.append("<thead>");
				sb.append("<tr>");
				sb.append("<th>Sr. No</th>");
				sb.append("<th>App Server</th>");
				sb.append("<th>Tag name</th>");
				sb.append("<th>Port</th>");
				sb.append("<th>Action</th>");
				sb.append("</tr>");
				sb.append("</thead>");
				sb.append("<tbody>");
				int count = 0;
				if(strUtl.isNull(var)) {
					sb.append("<tr>");
					sb.append("<td colspan=\"5\" class=\"text-center\">No Record Found</td>");
					sb.append("</tr>");
				}else{
				for (ServerDetailModel appServerObj : serverListByServerId) {
					
					if(appServerObj.getApp_server_name().equalsIgnoreCase("JBS")){
						appName = "JBOSS";
					}else if(appServerObj.getApp_server_name().equalsIgnoreCase("WBL")) {
						appName = "WEB LOGIC";
					}else if(appServerObj.getApp_server_name().equalsIgnoreCase("TMC")) {
						appName = "TOMCAT";
					}
					
					count++;
					sb.append("<tr>");
					sb.append("<td class=\"text-center\">" + count + "</td>");
					sb.append("<td class=\"server-name\">" + appName + "</td>");
					sb.append("<td id=\"tag_name" + count + "\">" + appServerObj.getApp_server_tag_name() + "</td>");
					sb.append("<td id=\"appServerPort_" + count + "\">" + appServerObj.getApp_server_port() + "</td>");
					sb.append("<input type=\"hidden\" id=\"type_code\" value=\""+ appServerObj.getServer_type_code() +"\">");
//					sb.append("<td class=\"app-names\">" + appServerObj.getApp_name() + "</td>");
					sb.append("<td <span th:text=\"${'' + item.tag_name}\"\r\n" + 
							"											style=\"font-weight: bold;\" title=\"Tag Name\"></span> <span title=\"Edit\"\r\n" + 
							"											onclick=\"editAppServerDetails('"+appServerObj.getServer_id()+"');\">\r\n" + 
							"											<i class=\"fa fa-pencil edit-button \"\r\n" + 
							"											style=\"background-color: #14de9b; color: #fff; padding: 2px 2px; border-radius: 5px\"></i>\r\n" + 
							"										</span> <span\r\n" + 
							"											onclick=\"deleteAppServerDetails('"+appServerObj.getServer_id()+"');\">\r\n" + 
							"											<i class=\"fa fa-trash delete-button\"\r\n" + 
							"											style=\"background-color: #ef4009; color: #fff; padding: 2px 2px; border-radius: 5px\"\r\n" + 
							"											title=\"Delete\"></i>\r\n" + 
							"										</span></td>");
					sb.append("</tr>");
				}
				}
				sb.append(" </tbody>");
				sb.append("</table>");
			}  else if(serverListByServerId != null && server_type_code.equalsIgnoreCase("APS")) {
				sb.append("<table id=\"appServerTable_" + tableCount + "\" class=\"table\">");
				sb.append("<thead>");
				sb.append("<tr>");
				sb.append("<th>Sr. No</th>");
				sb.append("<th>App Server</th>");
				sb.append("<th>Tag name</th>");
				sb.append("<th>Port</th>");
				sb.append("<th>Action</th>");
				sb.append("</tr>");
				sb.append("</thead>");
				sb.append("<tbody>");
				int count = 0;
				for (ServerDetailModel appServerObj : serverListByServerId) {

					if(appServerObj.getApp_server_name().equalsIgnoreCase("JBS")){
						appName = "JBOSS";
					}else if(appServerObj.getApp_server_name().equalsIgnoreCase("WBL")) {
						appName = "WEB LOGIC";
					}else if(appServerObj.getApp_server_name().equalsIgnoreCase("TMC")) {
						appName = "TOMCAT";
					}
					
					count++;
					sb.append("<tr>");
					sb.append("<td class=\"text-center\">" + count + "</td>");
					sb.append("<td class=\"server-name\">" +appName + "</td>");
					sb.append("<td id=\"tag_name" + count + "\">" + appServerObj.getApp_server_tag_name() + "</td>");
					sb.append("<td id=\"appServerPort_" + count + "\">" + appServerObj.getApp_server_port() + "</td>");
					sb.append("<input type=\"hidden\" id=\"type_code\" value=\""+ appServerObj.getServer_type_code() +"\">");
//					sb.append("<td class=\"app-names\">" + appServerObj.getApp_name() + "</td>");
					sb.append("<td <span th:text=\"${'' + item.tag_name}\"\r\n" + 
							"											style=\"font-weight: bold;\" title=\"Tag Name\"></span> <span title=\"Edit\"\r\n" + 
							"											onclick=\"editAppServerDetails('"+appServerObj.getServer_id()+"');\">\r\n" + 
							"											<i class=\"fa fa-pencil edit-button \"\r\n" + 
							"											style=\"background-color: #14de9b; color: #fff; padding: 2px 2px; border-radius: 5px\"></i>\r\n" + 
							"										</span> <span\r\n" + 
							"											onclick=\"deleteAppServerDetails('"+appServerObj.getServer_id()+"');\">\r\n" + 
							"											<i class=\"fa fa-trash delete-button\"\r\n" + 
							"											style=\"background-color: #ef4009; color: #fff; padding: 2px 2px; border-radius: 5px\"\r\n" + 
							"											title=\"Delete\"></i>\r\n" + 
							"										</span></td>");
					sb.append("</tr>");
				}
				sb.append(" </tbody>");
				sb.append("</table>");
			}
			
			else if (serverListByServerId != null && server_type_code.equalsIgnoreCase("DBS")) {
				sb.append("<table id=\"appServerTable_" + tableCount + "\" class=\"table\">");
				sb.append("<thead>");
				sb.append("<tr>");
				sb.append("<th>Sr. No</th>");
				sb.append("<th>Installed Database</th>");
				sb.append("<th>Installed Database Tool</th>");
				sb.append("</tr>");
				sb.append("</thead>");
				sb.append("<tbody>");
				int count = 0;
				for (ServerDetailModel appServerObj : serverListByServerId) {
					count++;
					sb.append("<tr>");
					sb.append("<td  class=\"text-center\">" + count + "</td>");
					sb.append("<td class=\"Installed-Database\">" + appServerObj.getInstalled_db() + "</td>");
					sb.append("<td id=\"Installed-Database-tool" + count + "\">" + appServerObj.getInstalled_db_tool()
							+ "</td>");
					sb.append("</tr>");
				}
				sb.append(" </tbody>");
				sb.append("</table>");
			}
			map.addAttribute("var_count", var);
		} catch (Exception e) {
//            	log.info("JAXBException:GetAppServerListWithStatus----->"+e);
			e.printStackTrace();
		}
		
		return sb.toString();
	}

	public static String encodeURIComponent(String s) {
		String result;
		try {
			result = URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!")
					.replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")")
					.replaceAll("\\%7E", "~");
		} catch (UnsupportedEncodingException e) {
			result = s;
		}
		return result;
	}

	@RequestMapping("/serverDetailDashboard")
	public String serverDetailDashboard(HttpSession session, ModelMap map, HttpServletRequest request,
			@ModelAttribute("dataGridDTO") DataGridDTO dataGridDTO,
			@RequestParam(name = "server_type", required = false) String server_type) {
		System.out.println("server_type=" + server_type);
//		String server_type1 = server_type;
		List<ServerDetailModel> serverlist = new ArrayList<ServerDetailModel>();
		String recPerPage = "";
		session.setAttribute("ACTIVE_TAB", "MENU-017");
		session.setAttribute("ACTIVE_SUB_TAB", "MENU-021");
		long total = 0l;
		try {
//			if(strUtl.isNull(server_type1)) {
//				server_type1="ALL";
//			}
			if (server_type.equalsIgnoreCase("PHY") ) {
				serverlist = serverDetails.getServerList();
				System.out.println("serverlist>>>>>" + serverlist);
				
				total = serverDetails.getUserCount(server_type);
				System.out.println("total....11.."+total);
				if (dataGridDTO != null && dataGridDTO.getPaginator() != null) {
					recPerPage = dataGridDTO.getPaginator().getRecordsPerPage();
				} else {
					recPerPage = "10";
				}
				PaginatorManipulation manipulation = new PaginatorManipulation();
				session.setAttribute("recPerPage", recPerPage);

				session.setAttribute("server_type", server_type);
				PaginatorDTO paginator = manipulation.getPaginatorCount("serverDetailGridData", total, recPerPage);
				
				dataGridDTO.setPaginator(paginator);
				map.addAttribute("dataGridDTO1", dataGridDTO);
				System.out.println("dataGridDTO.."+dataGridDTO.getPaginator().getTotalRecords());
				
			} else if (server_type.equalsIgnoreCase("APS") || server_type.equalsIgnoreCase("DBS") ) {
				serverlist = serverDetails.getServerListByServerType(server_type);
				System.out.println("serverlist==>>" + serverlist);

				
				total = serverDetails.getUserCount(server_type);
				System.out.println("total>>" + total);
				if (dataGridDTO != null && dataGridDTO.getPaginator() != null) {
					recPerPage = dataGridDTO.getPaginator().getRecordsPerPage();
				} else {
					recPerPage = "10";
				}
				PaginatorManipulation manipulation = new PaginatorManipulation();

				session.setAttribute("recPerPage", recPerPage);
				session.setAttribute("server_type", server_type);
				PaginatorDTO paginator = manipulation.getPaginatorCount("serverDetailGridData", total, recPerPage);

				paginator.setTotalRecords(total);
				dataGridDTO.setPaginator(paginator);
				System.out.println("dataGridDTO>>>>" + dataGridDTO);
//				dataGridDTO.setPaginator(paginator.setTotalRecords(total));
//				map.addAttribute("dataGridDTO", dataGridDTO);
				map.addAttribute("dataGridDTO1", dataGridDTO);
				map.addAttribute("serverGrid", serverlist);

			}
			map.addAttribute("server_type_code_edit", server_type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.addAttribute("serverGrid", serverlist);
		map.addAttribute("total", total);
		return "pages/server/serverDetail";
	}

	@RequestMapping(value = "/serverDetailGridData", method = RequestMethod.POST)
	public String serverDetailGridData(Model map, HttpServletRequest request, HttpSession session,
			@ModelAttribute("dataGridDTO") DataGridDTO dataGridDTO) {
		String recPerPage = "";
		Long total=0l;
		try {
			System.out.println("dataGridDTO....>>"+dataGridDTO.getPaginator().getTotalRecords());
			recPerPage = (String) session.getAttribute("recPerPage");
			String server_type = (String) session.getAttribute("server_type");
			System.out.println("server_type>?>" + server_type);
			

			if (server_type.equalsIgnoreCase("PHY") ) {
				List<ServerDetailModel> serverGrid = serverDetails.getServerBrowseList(server_type,session, dataGridDTO,
						recPerPage);
				
				total = serverDetails.getUserCount(server_type);
				System.out.println("total....1234."+total);
				if (dataGridDTO != null && dataGridDTO.getPaginator() != null) {
					recPerPage = dataGridDTO.getPaginator().getRecordsPerPage();
				} else {
					recPerPage = "10";
				}
				PaginatorManipulation manipulation = new PaginatorManipulation();
				session.setAttribute("recPerPage", recPerPage);

				session.setAttribute("server_type", server_type);
				PaginatorDTO paginator = manipulation.getPaginatorCount("", total, recPerPage);
				
				dataGridDTO.setPaginator(paginator);
				map.addAttribute("dataGridDTO1", dataGridDTO);
				
				System.out.println("dataGridDTO?>>?>???>>>>"+dataGridDTO.getPaginator().getTotalRecords());
//				long slnoStartFrom = 0l;
//				if (dataGridDTO != null) {
//					if (dataGridDTO.getPaginator().getPageNumber() > 1l) {
//						slnoStartFrom = slnoStartFrom * (dataGridDTO.getPaginator().getPageNumber() - 1);
//					}
//				}
				map.addAttribute("serverGrid", serverGrid);
				map.addAttribute("server_type_code_edit", server_type);
			} else if (server_type.equalsIgnoreCase("APS") || server_type.equalsIgnoreCase("DBS") ) {
				List<ServerDetailModel> serverGrid = serverDetails.getServerBrowseList1(session, dataGridDTO,
						recPerPage, server_type);
				total = serverDetails.getUserCount(server_type);
				System.out.println("total....1234."+total);
				if (dataGridDTO != null && dataGridDTO.getPaginator() != null) {
					recPerPage = dataGridDTO.getPaginator().getRecordsPerPage();
				} else {
					recPerPage = "10";
				}
				PaginatorManipulation manipulation = new PaginatorManipulation();
				session.setAttribute("recPerPage", recPerPage);

				session.setAttribute("server_type", server_type);
				PaginatorDTO paginator = manipulation.getPaginatorCount("", total, recPerPage);
				
				dataGridDTO.setPaginator(paginator);
//				long slnoStartFrom = 0l;
//				if (dataGridDTO != null) {
//					if (dataGridDTO.getPaginator().getPageNumber() > 1l) {
//						slnoStartFrom = slnoStartFrom * (dataGridDTO.getPaginator().getPageNumber() - 1);
//					}
//				}
				map.addAttribute("server_type_code_edit", server_type);
				map.addAttribute("serverGrid", serverGrid);
				map.addAttribute("dataGridDTO1", dataGridDTO);
			}else {
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pages/server/serverDetail :: ajaxServerList";
	}

	@RequestMapping("/deleteServer")
	@ResponseBody
	public String deleteServer(@RequestParam(name = "server_id") String server_id,
			@RequestParam(name = "server_type_code", required = false) String server_type_code) {
		String response = "error";
		try {
			if(server_type_code.equalsIgnoreCase("null")) {
				
			
				response = serverDetails.deleteServerById(server_id,server_type_code);
			
			}else {
				response = serverDetails.deleteServerById(server_id,server_type_code);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping("/deleteServerTemp")
	@ResponseBody
	public String deleteServerTemp(@RequestParam(name = "server_id", required = false) String server_id,
			 @RequestParam(name = "server_type_code", required = false) String server_type_code) {
		String response = "";
		try {
			if(server_type_code.equalsIgnoreCase("null")) {
			
				response = serverDetails.getDeleteCount(server_id);
			
			}else {
				response = "0";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping("/deleteAppServer")
	@ResponseBody
	public String deleteAppServer(@RequestParam(name = "server_id") String server_id,
			 @RequestParam(name = "server_type_code", required = false) String server_type_code) {
		String response = "error";
		try {
			if(server_type_code.equalsIgnoreCase("null")) {
				
				
					response = serverDetails.deleteServerById(server_id,server_type_code);//Physical server
				
				}else {
					response = serverDetails.deleteServerById(server_id,server_type_code);//app server
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping("/deleteAppServerTemp")
	@ResponseBody
	public String deleteAppServerTemp( @RequestParam(name = "server_id", required = false) String server_id,
			 @RequestParam(name = "server_type_code", required = false) String server_type_code) {
		String response = "";
		try {
			if(server_type_code.equalsIgnoreCase("null")) {
				response = "0";
			}else {
			
				response = serverDetails.getDeleteAppCount(server_id);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping("/editServerDetails")
	public String editServerDetails(HttpSession session, ModelMap map,
			@RequestParam(name = "server_id", required = false) String server_id) {

		List<ServerDetailModel> serverlist = new ArrayList<ServerDetailModel>();
		Map<String, String> serverTypeList = new HashMap<String, String>();
		Map<String, String> entityNameList = new HashMap<String, String>();

		ServerDetailModel serverDetailModel = new ServerDetailModel();
		try {

			serverlist = serverDetails.getServerList();
			serverTypeList = serverDetails.getServerTypeList();
//			entityNameList = entityMastService.getEntityNameList();

			if (server_id != null) {
				serverDetailModel = serverDetails.getServerById(server_id);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		map.addAttribute("serverlist", serverlist);
		map.addAttribute("serverTypeList", serverTypeList);
		map.addAttribute("entityNameList", entityNameList);

		map.addAttribute("serverDetailModel", serverDetailModel);
//		modelmap.addAttribute("entityList", entityList);
//		modelmap.addAttribute("action",action);

		return "pages/server/serverEntry";

	}

	@RequestMapping(value = "/getEntityList")
	@ResponseBody
	public Map<String, String> getEntityList(@RequestParam("server_owner") String server_owner) {
		Map<String, String> list = new HashMap<>();
		try {

			list = entityMastService.getEntityNameList(server_owner);

	

			System.out.println("list><>>" + list);

			list = entityMastService.getEntityNameList(server_owner);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = "validateServerName")
	@ResponseBody
	public String validateServerName(@RequestParam(name = "app_server_ip", required = false) String app_server_ip,
									@RequestParam(name = "app_server_name", required = false) String app_server_name) {
		String response="";
		
		try {
			response= serverDetails.validateServerName(app_server_ip,app_server_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	@RequestMapping(value = "setPublicIp")
	@ResponseBody
	public String setPublicIp(@RequestParam(name = "app_server_ip", required = false) String app_server_ip) {
		String response="";
		
		try {
			response= serverDetails.setPublicIp(app_server_ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	// openViewBox Server Details
	@RequestMapping("/viewServerDetails")
	@ResponseBody
	public String viewServerDetails(@RequestParam(name = "server_id") String server_id, HttpSession session) {
		String response = "error";
		
		
		try {
			if (server_id != null) {
				
				response = serverDetails.viewServerDetails(server_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("response.." + response);
		return response;
	}
	
	@RequestMapping("/checkServerIp")
	@ResponseBody
	public String checkServerIp(@RequestParam(name = "server_id") String server_id, HttpSession session) {
		String response = "error";
		try {
			if (server_id != null) {
				
				response = serverDetails.checkServerIp(server_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("response.." + response);
		return response;
	}
	
	
	
	
}

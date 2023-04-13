package com.lhs.taxcpcAdmin.controller.master;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lhs.taxcpcAdmin.global.pagination.DataGridDTO;
import com.lhs.taxcpcAdmin.global.pagination.FilterDTO;
import com.lhs.taxcpcAdmin.global.pagination.PaginatorDTO;
import com.lhs.taxcpcAdmin.global.pagination.PaginatorManipulation;
import com.lhs.taxcpcAdmin.model.entity.AppDetails;
import com.lhs.taxcpcAdmin.model.entity.ClientDetails;
import com.lhs.taxcpcAdmin.model.entity.EntityMast;
import com.lhs.taxcpcAdmin.model.entity.ServerDetailModel;
import com.lhs.taxcpcAdmin.model.entity.View_return_qt;
import com.lhs.taxcpcAdmin.service.ClientDetailsService;
import com.lhs.taxcpcAdmin.service.DocMgmtService;
import com.lhs.taxcpcAdmin.service.EntityMastService;
import com.lhs.taxcpcAdmin.service.ServerDetails;
import com.lhs.taxcpcAdmin.service.UserMastService;

@Controller
public class FileViewController {

	@Autowired
	DocMgmtService docMgmtService;

	@Autowired
	private EntityMastService entityMastService;

	@Autowired
	private ServerDetails serverDetails;
	
	@Autowired
	private ClientDetailsService clientDetailsService;

	
	
	
	@RequestMapping(value = "TreeFilter", method = RequestMethod.POST)
	public String appTextFilter(@Param(value = "entity_code") String entity_code, HttpSession session, Model modelmap) {

		System.out.println("entity_code......TreeFilter......>" + entity_code);
		
		String website = "";

		List<EntityMast> entityDisplayList = new ArrayList<EntityMast>();
		List<AppDetails> appList = new ArrayList<>();
		List<ClientDetails> teamList = new ArrayList<>();
		List<ServerDetailModel> serverList = new ArrayList<>();
		
//		Map<String, String> entityNameList = new HashMap<String, String>();
		Map<String, byte[]> List = new HashMap<>();
		Map<String, String> maplist = new HashMap<>();
//		Map<String, String> entityDisplayList = new HashMap<>();
		
		try {
//			entityNameList = entityMastService.getEntityNameList1();
			List = entityMastService.getEntityLogo();
			appList = serverDetails.getAppList(entity_code);
			teamList = clientDetailsService.getTeamlist(entity_code);
			serverList = serverDetails.getServerListByEntity_code(entity_code);
			entityDisplayList = entityMastService.getEntityDisplayList(entity_code);
			website = entityMastService.getWebsite(entity_code);
			
			System.out.println("list.."+website);
			
			
			String encodeBase64;
			List<String> base64Encoded = new ArrayList<String>();
			for (Entry<String, byte[]> entry : List.entrySet()) {
				byte[] byt = entry.getValue(); 
				encodeBase64 = Base64.getEncoder().encodeToString(byt);
				if (encodeBase64 != null) {
					maplist.put(entry.getKey(), encodeBase64);
				}
				base64Encoded.add(encodeBase64);
				modelmap.addAttribute("contentImage", base64Encoded);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelmap.addAttribute("List", List);
		//modelmap.addAttribute("entityNameList", entityNameList);
		modelmap.addAttribute("new_entity_code", entity_code);
		modelmap.addAttribute("maplist", maplist);
		modelmap.addAttribute("appList", appList);
		modelmap.addAttribute("teamList", teamList);
		modelmap.addAttribute("serverList", serverList);
		modelmap.addAttribute("entityDisplayList", entityDisplayList);
		modelmap.addAttribute("website", website);


		return "pages/fileView/treeDiagram :: ajaxtreediagramList";
	}

	
	@RequestMapping("/treeDiagram")
	public String treeDiagram(HttpSession session, ModelMap modelmap,
			@ModelAttribute("dataGridDTO") DataGridDTO dataGridDTO, FilterDTO filter,
			@RequestParam(name = "entity_code", required = false) String entity_code) {

		
		session.setAttribute("ACTIVE_TAB", "MENU-020");
		session.setAttribute("ACTIVE_SUB_TAB", "MENU-041");
		
		List<AppDetails> applist = new ArrayList<AppDetails>();
		Map<String, String> entityNameList = new HashMap<String, String>();
		Map<String, byte[]> List = new HashMap<>();
		Map<String, String> maplist = new HashMap<>();
		List<String> logo = new ArrayList<String>();
		String new_entity_code = (String) session.getAttribute("new_entity_code");

		try {

			applist = serverDetails.getAppList();
			entityNameList = entityMastService.getEntityNameList1();

			System.out.println("entity_code.........>" + entity_code);
			System.out.println("applist for tree.........." + applist);

			List = entityMastService.getEntityLogo();
			String encodeBase64;
			List<String> base64Encoded = new ArrayList<String>();
			for (Entry<String, byte[]> entry : List.entrySet()) {
				byte[] byt = entry.getValue();
				encodeBase64 = Base64.getEncoder().encodeToString(byt);
				if (encodeBase64 != null) {
					maplist.put(entry.getKey(), encodeBase64);
				}
				base64Encoded.add(encodeBase64);

				modelmap.addAttribute("contentImage", base64Encoded);

			}

			for (Entry<String, String> entry : maplist.entrySet()) {
				if (entity_code != null && entity_code.equalsIgnoreCase(entry.getKey())) {
					logo.add(entry.getValue());
					System.out.println("logo in filter........" + logo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

//		modelmap.addAttribute("entity_code", entity_code);
		modelmap.addAttribute("applist", applist);
		modelmap.addAttribute("entityNameList", entityNameList);
		modelmap.addAttribute("maplist", maplist);
//		modelmap.addAttribute("new_entity_code", new_entity_code);

		return "pages/fileView/treeDiagram";

	}

	
	// sushma
	
	@RequestMapping("/bankFileView")
	public String bankFileView(HttpSession session, ModelMap modelmap,
			@ModelAttribute("dataGridDTO") DataGridDTO dataGridDTO, FilterDTO filter) {

		List<View_return_qt> viewList1 = new ArrayList<View_return_qt>();
		List<View_return_qt> viewList2 = new ArrayList<View_return_qt>();
		List<View_return_qt> viewList3 = new ArrayList<View_return_qt>();
		List<View_return_qt> viewList4 = new ArrayList<View_return_qt>();
		
		try {
			viewList1 = docMgmtService.getListByYear();
			viewList2=docMgmtService.getListByFYear();
			viewList3=docMgmtService.getListByRegular_type();
			viewList4=docMgmtService.getListBy15GH_type();
		
		} catch (Exception e) {
			e.printStackTrace(); 
		}

		modelmap.addAttribute("viewList1", viewList1);
		modelmap.addAttribute("viewList2", viewList2);
		modelmap.addAttribute("viewList3", viewList3);
		modelmap.addAttribute("viewList4", viewList4);


		return "pages/fileView/bankFileView";

	}


}

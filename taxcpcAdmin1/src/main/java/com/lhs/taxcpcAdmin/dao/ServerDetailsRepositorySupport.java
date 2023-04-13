package com.lhs.taxcpcAdmin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.taxcpcAdmin.global.pagination.FilterDTO;
import com.lhs.taxcpcAdmin.model.entity.ReqTran;
import com.lhs.taxcpcAdmin.model.entity.ServerDetailModel;

@Service
@Transactional
public class ServerDetailsRepositorySupport {

	@Autowired
	private ServerDetailsRepository serverDetailsRepository;
 
	public long getUserTranBrowseCount(String server_type) { 
		Long count = 0l;
//		StringBuilder sb= new StringBuilder();
		Long queryString = 0l;
		try {
			System.out.println("getserver type---" + server_type);
			if (server_type.equalsIgnoreCase("ALL")) {

				queryString = serverDetailsRepository.getCount();
			} else if (server_type.equalsIgnoreCase("PHY") || server_type.isEmpty()) {

				queryString = serverDetailsRepository.getCountPhysical();
			} else {
				if (server_type.equalsIgnoreCase("APS")) {
					queryString = serverDetailsRepository.getCountApp(server_type);
				} else {
					queryString = serverDetailsRepository.getCount(server_type);
				}
			}

//			String queryString = "select count(*) from lhs_taxcpc_server_details " ;

//			System.out.println("Count ="+queryString);
			count = queryString;
//			Query query =  serverDetailsRepository.getSession().createQuery(queryString);

//			count = (Long) query.uniqueResult();	
//			System.out.println("c="+count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("count..." + count);
		return count;
	}

	public List<ServerDetailModel> getReqApprovalBrowseList(String server_type, long minVal, long maxVal) {
		List<ServerDetailModel> list = new ArrayList<>();
//		StringBuilder sb= new StringBuilder();
		try {
			String queryString = "";
			System.out.println("server_type..>" + server_type);
			if (server_type.equalsIgnoreCase("ALL")) {

				queryString = "select * from lhs_taxcpc_server_details  order by server_type_code desc";
			} else {

				queryString = "select * from LHS_TAXCPC_SERVER_DETAILS t WHERE t.parent_server IS NULL order by lastupdate desc";
			}
			System.out.println("Get Detail list Query==>> " + queryString);
//			Query<ReqTran> query = reqTranRepository.getSession().createQuery(queryString, ReqTran.class);
			Query<ServerDetailModel> query = serverDetailsRepository.getSession().createNativeQuery(queryString,
					ServerDetailModel.class);

			if (minVal >= Integer.MIN_VALUE && minVal <= Integer.MAX_VALUE) {
				query.setFirstResult((int) minVal);
			}
			if (maxVal >= Integer.MIN_VALUE && maxVal <= Integer.MAX_VALUE) {
				query.setMaxResults((int) maxVal);
			}
//			System.out.println("query...11.."+query);
			list = (List<ServerDetailModel>) query.getResultList();
			System.out.println("list>..>" + list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ServerDetailModel> getServerBrowseList1(String server_type, long minVal, long maxVal) {
		List<ServerDetailModel> list = new ArrayList<>();
//		StringBuilder sb= new StringBuilder();
		String queryString = "";
		try {
			if (server_type.equalsIgnoreCase("APS")) {
				queryString = "select * from lhs_taxcpc_server_details t where t.server_type_code='" + server_type
						+ "' and t.app_server_name is not null order by t.lastupdate desc";
			} else if (server_type.equalsIgnoreCase("DBS")) {
				queryString = "select * from lhs_taxcpc_server_details t where t.server_type_code='" + server_type
						+ "'  order by t.lastupdate desc";
			}
			System.out.println("Get Detail list Query=====> " + queryString);
//			Query<ReqTran> query = reqTranRepository.getSession().createQuery(queryString, ReqTran.class);
			Query<ServerDetailModel> query = serverDetailsRepository.getSession().createNativeQuery(queryString,
					ServerDetailModel.class);

			if (minVal >= Integer.MIN_VALUE && minVal <= Integer.MAX_VALUE) {
				query.setFirstResult((int) minVal);
			}
			if (maxVal >= Integer.MIN_VALUE && maxVal <= Integer.MAX_VALUE) {
				query.setMaxResults((int) maxVal);
			}
//			System.out.println("query...11111.."+query);
			list = (List<ServerDetailModel>) query.getResultList();
			System.out.println("list>>" + list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//	public long getUserTranBrowseCount() {
//	Long count = 0l;
//	StringBuilder sb= new StringBuilder();
//	try {
//		
//		String queryString = "select count(t) from ServerDetailModel t where 1=1 "+sb ;
//		System.out.println("Count Query== "+queryString);
//		Query query =  serverDetailsRepository.getSession().createQuery(queryString);
//		
//		count = (Long) query.uniqueResult();	
//		
//	}catch(Exception e) {
//		e.printStackTrace();
//	}
//System.out.println("count.."+count);
//	return count;
//}
//
// 
//	public List<ServerDetailModel> getServerDetailsList( long minVal, long maxVal) {
//		List<ServerDetailModel> list = new ArrayList<>();
//		StringBuilder sb= new StringBuilder();
//		try {
//			
//			String queryString = "from ServerDetailModel t where 1=1 "+sb ;
//		
//			System.out.println("Get Detail list Query=11== "+queryString);
////			Query<ReqTran> query = reqTranRepository.getSession().createQuery(queryString, ReqTran.class);
//			Query<ServerDetailModel> query = serverDetailsRepository.getSession().createNativeQuery(queryString, ServerDetailModel.class);
//			if (minVal >= Integer.MIN_VALUE && minVal <= Integer.MAX_VALUE) {
//				query.setFirstResult((int) minVal);
//			}
//			if (maxVal >= Integer.MIN_VALUE && maxVal <= Integer.MAX_VALUE) {
//				query.setMaxResults((int) maxVal);
//			}
//			list = (List<ServerDetailModel>) query.getResultList();
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

}

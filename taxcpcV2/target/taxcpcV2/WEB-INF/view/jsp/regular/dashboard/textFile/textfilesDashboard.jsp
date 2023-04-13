<%-- 
    Document   : textfilesDashboard
    Created on : Apr 4, 2022, 1:07:11 PM
    Author     : akash.meshram
--%>
<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<div>
    <div class="page-section">
        <div class="tab-section col-md-12 my-1">
            <ul class="nav nav-pills">

                <li  class="nav-item mr-5" onclick="javascript:generateFvuBulkTextFileAction();"><a  href="" class="nav-link" data-toggle="pill" ><p>Generate FVU Bulk Text File Status</p> </a></li>
                <li  class="nav-item mr-5" onclick="javascript:textFileDashboardAction();"><a  href="" class="nav-link active" data-toggle="pill"  ><p>Generated Text files Dashboard</p> </a></li>
            </ul>
        </div>
        <div class="clearfix"></div>

        <div class="tab-content px-4 py-4">
            <div id="browse" class="tab-pane show in active">
                <div class="button-section  my-1 pt-1"> 

                </div>
                <form id="bulktextfiledashboard" name="bulktextfiledashboard" action="/taxcpcV2/bulktextFileDashboard?search=true" method="post"> 
                    <div class="filter-section">
                        <div class="row sec-bottom">
                            <div class="col-lg-10 col-xl-11">
                                <div class="row form-group">
                                    <div class="col-lg-3">

                                        <input type="text" name="bulkTextFileBean.process_seqno" value="" id="processSeqNO" class="form-control" title="Process Seq No." placeholder="Process Seq No."    onkeypress="return lhsIsNumber(event);">
                                    </div>
<!--                                    <div class="col-lg-3">

                                        <input type="text" name="bulkTextFileBean.client_code" value="" id="clientCode" class="form-control" title="Client Code" placeholder="Client Code">

                                    </div>-->

                                    <div class="col-lg-3">



                                        <input type="text" name="bulkTextFileBean.bank_branch_code" value="" id="bankBranchCode" class="form-control" title="Bank Branch Code" placeholder="Bank Branch Code">

                                    </div>


                                </div>


                            </div>
                            <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="bulkTextFilevalidateFilter();" title="Search"><i class="fa search"></i></button>
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('deducteeMgmtFrm', 'submit');" title="Clear"><i class="fa clear"></i></button>
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" data-target="#remainingFilter" title="Extra Filters"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button> 
                            </div> 
                        </div>  
                    </div> 

                    <input type="hidden" name="filterFlag" id="filterFlag">
                    <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
                    <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
                    <s:hidden value="bulktextFileDataGrid" id="dataGridAction"/>
                    <s:hidden value="%{pageNumber}" id="pageNumber"/>
                    <s:hidden value="%{mulDelFlag}" id="mulDelFlag"/>
                    <input type="hidden" id="countCall" value="0">
                    <input type="hidden" id="fltrformId" value="bulktextfiledashboard">
                    <s:hidden name="tokenNo" value="%{tokenNo}" id="tokenNo"/>
                </form>
                <div class="clearfix"></div>  
                <!--paginator-->
                <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %> 

                <!-----------------settings------------>
                <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %> 
                <div class="clearfix"></div> 
                <!--Table Section-->

                <div class="table-responsive mt-2" id="dataSpan">
                    <!--Process Details Ajax Data Here-->
                    <%
                        out.println(GlobalMessage.PAGINATION_SHOW_MORE);
                    %>

                </div>
                    
                    <!--Table Section-->
            <div class="clearfix"></div> 


            </div>




            <script type="text/javascript" src="resources/js/regular/bulkDownload/bulkDownload.js?r=0.39592048544387903"></script>
        </div>


        <!-----------------settings------------>






        

       
        
       
    </div>



</div>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script type="text/javascript" src="resources/js/regular/validation/validateError.js?r=<%=Math.random()%>"></script>
<script>

  $(document).ready(function() {
    bulkTextFilevalidateFilter();
   });
</script>
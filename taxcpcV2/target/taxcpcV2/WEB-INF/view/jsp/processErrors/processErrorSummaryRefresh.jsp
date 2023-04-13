<%-- 
    Document   : processErrorSummaryRefresh
    Created on : Dec 18, 2019, 2:45:33 PM
    Author     : gaurav.khanzode
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="row mx-0">
    <s:if test="%{viewTranLoadErrorsSummery!=null && viewTranLoadErrorsSummery.size()>0}">              
        <s:iterator value="viewTranLoadErrorsSummery" status="head" >
            <div class="col-lg-4">
                <div class="media error-list position-relative">
                    <i class="fa fa-info-circle mr-3 position-absolute" title="<s:property value="error_type_name"/>"></i>
                    <div class="media-body">
                        <s:hidden id="error_type_code~%{#head.count}" name="error_type_code"/>
                        <h5 class="d-inline-block"><s:property value="error_type_name"/></h5>
                        <span class="text-primary float-right font-weight-bold"><s:property value="record_count"/></span>
                    </div>
                </div>
            </div>
        </s:iterator>
    </s:if>
    <s:else>
        <!--Global no record message-->
        <div class="no-record-found-browse col-lg-6 offset-lg-3 my-3 text-center">
            <img class="pr-3" src="resources/images/global/empty-box.png"> No Records Found.
        </div>
    </s:else>
</div>
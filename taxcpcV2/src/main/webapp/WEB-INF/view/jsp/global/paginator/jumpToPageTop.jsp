<%-- 
    Document   : jumpToPageTop
    Created on : Sep 14, 2015, 11:22:09 AM
    Author     : akash.dev
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="resources/js/global/paginator/paginator.js?r=<%=Math.random()%>"></script>
<s:div cssClass="input-group" id="jumpTop">
    <input type="text" name="" id="pagination_number" class="form-control" placeholder="Jump to page" style="margin-top: 5px;" onkeypress="return isNumber(event);" oncopy="return false" onpaste="return false">
    <span class="input-group-btn" >
        <input type="button" id="topBtn" class="btn btn-default" value="GO" style="margin-top: 5px; cursor: default;" onclick="jumpToPage(this.id);">
    </span>
</s:div>

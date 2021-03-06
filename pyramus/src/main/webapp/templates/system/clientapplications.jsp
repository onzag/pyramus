<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html>
  <head>
    <title><fmt:message key="system.clientapplications.pageTitle"/></title>

    <jsp:include page="/templates/generic/head_generic.jsp"></jsp:include>
    <jsp:include page="/templates/generic/scriptaculous_support.jsp"></jsp:include>
    <jsp:include page="/templates/generic/tabs_support.jsp"></jsp:include>
    <jsp:include page="/templates/generic/table_support.jsp"></jsp:include>

    <script type="text/javascript">
      function onLoad(event) {
        tabControl = new IxProtoTabs($('tabs'));
        
        var settingsTable = new IxTable($('clientApplicationsTableContainer'), {
          id : "clientApplicationsTable",
          columns : [{
            dataType: 'hidden',
            paramName: "id"
          },{
            dataType: 'hidden',
            paramName: "remove"
          },{
            dataType: 'hidden',
            paramName: "regenerateSecret"
          },{
            header : '<fmt:message key="system.clientapplications.nameHeader"/>',
            left : 8,
            width: 300,
            required: true,
            dataType: 'text',
            editable: true,
            paramName: 'appName'
          }, {
            header : 'client_id',
            left : 316,
            width: 300,
            dataType: 'text',
            editable: false,
            paramName: 'appId'
          }, {
            header : 'client_secret',
            left : 616,
            width: 600,
            dataType: 'text',
            editable: false,
            paramName: 'appSecret'
          }, {
            header : '<fmt:message key="system.clientapplications.skipHeader"/>',
            right : 60,
            width : 80,
            dataType : 'checkbox',
            editable : true,
            paramName : 'skipPrompt'
          }, {
            right: 34,
            width: 22,
            dataType: 'button',
            paramName: 'regenerateSecretBtn',
            imgsrc: GLOBAL_contextPath + '/gfx/icons/16x16/actions/refresh.png',
            tooltip: '<fmt:message key="system.clientapplications.regenTooltip"/>',
            onclick: function (event) {
               var table = event.tableComponent;
               table.setCellValue(event.row, table.getNamedColumnIndex('regenerateSecret'), 1);
               table.hideCell(event.row, table.getNamedColumnIndex('regenerateSecretBtn'));
            }
          }, {
            right: 8,
            width: 22,
            dataType: 'button',
            paramName: 'deleteButton',
            imgsrc: GLOBAL_contextPath + '/gfx/icons/16x16/actions/mail-mark-junk.png',
            tooltip: '<fmt:message key="system.clientapplications.removeTooltip"/>',
            onclick: function (event) {
                var table = event.tableComponent;
                table.setCellValue(event.row, table.getNamedColumnIndex('remove'), 1);
                table.disableRow(event.row);
                table.hideCell(event.row, table.getNamedColumnIndex('deleteButton'));
                table.showCell(event.row, table.getNamedColumnIndex('undeleteButton'));
                table.enableCellEditor(event.row, table.getNamedColumnIndex('undeleteButton'));
            }
          }, {
            right: 8,
            width: 22,
            dataType: 'button',
            paramName: 'undeleteButton',
            hidden: true,
            imgsrc: GLOBAL_contextPath + '/gfx/icons/16x16/actions/mail-mark-not-junk.png',
            tooltip: '<fmt:message key="system.clientapplications.restoreTooltip"/>',
            onclick: function (event) {
              var table = event.tableComponent;
              table.setCellValue(event.row, table.getNamedColumnIndex('remove'), 0);
              table.enableRow(event.row);
              table.showCell(event.row, table.getNamedColumnIndex('deleteButton'));
              table.enableCellEditor(event.row, table.getNamedColumnIndex('deleteButton'));
              table.hideCell(event.row, table.getNamedColumnIndex('undeleteButton'));
            }
          }]
        });

        var rows = new Array();
        <c:forEach var="clientApplication" items="${clientApplications}">
         
          var skipPrompt = '${clientApplication.skipPrompt}' === 'true' ? 1 : 0;
      
          rows.push([
            '${clientApplication.id}',
            0,
            0,
            '${clientApplication.clientName}',
            '${clientApplication.clientId}',
            '${clientApplication.clientSecret}',
            skipPrompt,
            null,
            null,
            null,
          ]);
        </c:forEach>
        settingsTable.addRows(rows);
      }
      
      function addClientApplication() {
          var table = getIxTableById('clientApplicationsTable');
          rowIndex = table.addRow(['', 0, 0, '', '', '',null, null, null, null]);
      }
      
    </script>
  </head>
  
  <body onload="onLoad(event);">
    <jsp:include page="/templates/generic/header.jsp"></jsp:include>
    
    <h1 class="genericPageHeader"><fmt:message key="system.clientapplications.pageTitle"/></h1>
    
    <div class="genericFormContainer"> 
      <form action="clientapplications.page" method="post">
  
        <div class="tabLabelsContainer" id="tabs">
          <a class="tabLabel" href="#clientApplications">
            <fmt:message key="system.clientapplications.tabLabel"/>
          </a>
        </div>
        
        <div id="clientApplications" class="tabContent">
          <div class="genericTableAddRowContainer">
            <span class="genericTableAddRowLinkContainer" onclick="addClientApplication();"><fmt:message key="system.clientapplications.addBtn"/></span>
          </div>
          <div id="clientApplicationsTableContainer"></div>
        </div>
  
        <div class="genericFormSubmitSectionOffTab">
          <input type="submit" class="formvalid" value="<fmt:message key="system.clientapplications.saveBtn"/>">
        </div>

      </form>
    </div>
    
    <jsp:include page="/templates/generic/footer.jsp"></jsp:include>
  </body>
</html>
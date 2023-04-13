/**
 * @aman.dahat
 */

function selectSever() {// onload method

	var action = $('#action').val();

	var config_type = $('#configuration_type1').val();

	var server_type = document.getElementById('server_type_code_edit').value;
	// alert("server_type="+server_type)

	if (config_type == "" && server_type == "" && action == "save") {// save

		document.getElementById("server_entry").style.display = "block";
		document.getElementById("app_server_detail").style.display = "none";
		document.getElementById("app_server_detail").disabled;
		document.getElementById("app_server_entry").style.display = "none";
		document.getElementById("app_server_entry").disabled;
	} else if ((config_type == 'PSD' || config_type == '')
			&& server_type == 'null' && action == 'edit') {// physical edit

		document.getElementById("configurationDiv").style.display = "none";
		document.getElementById("btnDiv").style.display = "none";
		document.getElementById("serverTypeDiv").style.display = "none";

		document.getElementById("databaseDiv").style.display = "none";
		document.getElementById("databaseToolDiv").style.display = "none";

		document.getElementById("server_entry").style.display = "flex";
		document.getElementById("app_server_detail").style.display = "none";
		document.getElementById("app_server_detail").disabled;
		document.getElementById("app_server_entry").style.display = "none";
		document.getElementById("app_server_entry").disabled;

		var btnName = document.getElementById("btn-1").textContent;
		var y = btnName.innerHTML = "Update & Exit";
		document.getElementById("btn-1").innerText = y;
		document.getElementById('btn-2').style.display = 'none';

		document.getElementById('serverNameDiv').style.display = 'flex';
		document.getElementById('entityNameDiv').style.display = 'flex';
		document.getElementById('serverIpDiv').style.display = 'flex';
		document.getElementById('server_ip').readOnly = true;
		document.getElementById('publicIpDiv').style.display = 'flex';
		document.getElementById('hostNameDiv').style.display = 'flex';
		document.getElementById('serverOsDiv').style.display = 'flex';
		document.getElementById('remoteUserNameDiv').style.display = 'flex';
		document.getElementById('remotePwdDiv').style.display = 'flex';
		document.getElementById('driveDiv').style.display = 'flex';
		document.getElementById('tagNameDiv').style.display = 'flex';
		document.getElementById('remarkDiv').style.display = 'flex';
		document.getElementById('btns_1').style.display = 'flex';

		document.getElementById('serverIPDiv').style.display = 'none';
		document.getElementById('appServerIPDiv').style.display = 'none';
		document.getElementById('appServerDiv').style.display = 'none';
		document.getElementById('appServerPortDiv').style.display = 'none';
		document.getElementById('appServerConsoleDiv').style.display = 'none';
		document.getElementById('appServerLogPathDiv').style.display = 'none';
		// document.getElementById('portDiv').style.display = 'flex';
		// document.getElementById('controllerDiv').style.display = 'flex';
		document.getElementById('AppUsernameDiv').style.display = 'none';
		document.getElementById('appPasswordDiv').style.display = 'none';
		document.getElementById('appTagNameDiv').style.display = 'none';
		document.getElementById('appRemarkDiv').style.display = 'none';
		// document.getElementById('btns_2').style.display = 'none';
	} else if (config_type == "" && server_type == 'APS' && action == 'edit') {// app
		// server
		// edit

		document.getElementById("server_entry").style.display = "none";
		document.getElementById("app_server_detail").style.display = "block";
		document.getElementById("app_server_detail").disabled;
		document.getElementById("app_server_entry").style.display = "block";
		document.getElementById("app_server_entry").disabled;

		document.getElementById("configurationDiv").style.display = "none";
		document.getElementById("btnDiv").style.display = "none";
		document.getElementById("serverTypeDiv").style.display = "none";

		var btnName = document.getElementById("btn-1").textContent;
		var y = btnName.innerHTML = "Update & Next";
		document.getElementById("btn-1").innerText = y;
		document.getElementById('btn-2').style.display = 'inline-block';

		document.getElementById('serverTypeDiv').style.display = 'none';
		document.getElementById('app_server_ip1').disabled = true;
		document.getElementById('app_server_name').disabled = true;
		document.getElementById('serverNameDiv').style.display = 'none';
		document.getElementById('entityNameDiv').style.display = 'none';
		document.getElementById('serverIpDiv').style.display = 'none';
		document.getElementById('publicIpDiv').style.display = 'none';
		document.getElementById('hostNameDiv').style.display = 'none';
		document.getElementById('serverOsDiv').style.display = 'none';
		document.getElementById('remoteUserNameDiv').style.display = 'none';
		document.getElementById('remotePwdDiv').style.display = 'none';
		document.getElementById('driveDiv').style.display = 'none';
		document.getElementById('databaseDiv').style.display = 'none';
		document.getElementById('databaseToolDiv').style.display = 'none';
		document.getElementById('tagNameDiv').style.display = 'none';
		document.getElementById('remarkDiv').style.display = 'none';
		document.getElementById('btns_1').style.display = 'flex';

		document.getElementById('serverIPDiv').style.display = 'flex';
		document.getElementById('appServerIPDiv').style.display = 'flex';
		document.getElementById('appServerDiv').style.display = 'flex';
		document.getElementById('appServerPortDiv').style.display = 'flex';
		document.getElementById('appServerConsoleDiv').style.display = 'flex';
		document.getElementById('appServerLogPathDiv').style.display = 'flex';
		// document.getElementById('portDiv').style.display = 'flex';
		// document.getElementById('controllerDiv').style.display = 'flex';
		document.getElementById('AppUsernameDiv').style.display = 'flex';
		document.getElementById('appPasswordDiv').style.display = 'flex';
		document.getElementById('appTagNameDiv').style.display = 'flex';
		document.getElementById('appRemarkDiv').style.display = 'flex';
		// document.getElementById('btns_2').style.display = 'flex';

	} else if (config_type == '' && server_type == 'DBS' && action == 'edit') {// database
		// edit

		document.getElementById("configurationDiv").style.display = "none";
		document.getElementById("btnDiv").style.display = "none";
		document.getElementById("serverTypeDiv").style.display = "none";

		document.getElementById("databaseDiv").style.display = "flex";
		document.getElementById("databaseToolDiv").style.display = "flex";

		document.getElementById("server_entry").style.display = "block";
		document.getElementById("app_server_detail").style.display = "none";
		document.getElementById("app_server_entry").style.display = "none";

		var btnName = document.getElementById("btn-1").textContent;
		var y = btnName.innerHTML = "Update & Exit";
		document.getElementById("btn-1").innerText = y;
		document.getElementById('btn-2').style.display = 'none';

		document.getElementById('serverNameDiv').style.display = 'flex';
		document.getElementById('entityNameDiv').style.display = 'flex';
		document.getElementById('serverIpDiv').style.display = 'flex';
		document.getElementById('publicIpDiv').style.display = 'flex';
		document.getElementById('hostNameDiv').style.display = 'flex';
		document.getElementById('serverOsDiv').style.display = 'flex';
		document.getElementById('remoteUserNameDiv').style.display = 'flex';
		document.getElementById('remotePwdDiv').style.display = 'flex';
		document.getElementById('driveDiv').style.display = 'flex';
		document.getElementById('tagNameDiv').style.display = 'flex';
		document.getElementById('remarkDiv').style.display = 'flex';
		document.getElementById('btns_1').style.display = 'flex';

		document.getElementById('serverIPDiv').style.display = 'none';
		document.getElementById('appServerIPDiv').style.display = 'none';
		document.getElementById('appServerDiv').style.display = 'none';
		document.getElementById('appServerPortDiv').style.display = 'none';
		document.getElementById('appServerConsoleDiv').style.display = 'none';
		document.getElementById('appServerLogPathDiv').style.display = 'none';
		// document.getElementById('portDiv').style.display = 'flex';
		// document.getElementById('controllerDiv').style.display = 'flex';
		document.getElementById('AppUsernameDiv').style.display = 'none';
		document.getElementById('appPasswordDiv').style.display = 'none';
		document.getElementById('appTagNameDiv').style.display = 'none';
		document.getElementById('appRemarkDiv').style.display = 'none';
		// document.getElementById('btns_2').style.display = 'none';

	}

}

function selectSever1() {
	var config_type = document.getElementById('configuration_type').value;
	var server_type_code = document.getElementById('server_type_code111').value;
	var action = $('#action').val();

	if (config_type === 'PSD' && server_type_code === 'DBS') {

		document.getElementById("databaseDiv").style.display = "flex";
		document.getElementById("databaseToolDiv").style.display = "flex";

		document.getElementById("server_entry").style.display = "flex";
		document.getElementById("app_server_detail").style.display = "none";
		document.getElementById("app_server_detail").disabled;
		document.getElementById("app_server_entry").style.display = "none";
		document.getElementById("app_server_entry").disabled;

		var btnName = document.getElementById("btn-1").textContent;
		var y = btnName.innerHTML = "Save & Exit";
		document.getElementById("btn-1").innerText = y;
		document.getElementById('btn-2').style.display = 'none';
	} else if (config_type === 'PSD' && server_type_code === 'APS') {

		document.getElementById("databaseDiv").style.display = "none";
		document.getElementById("databaseToolDiv").style.display = "none";

		document.getElementById("server_entry").style.display = "flex";
		document.getElementById("app_server_detail").style.display = "none";
		document.getElementById("app_server_entry").style.display = "none";

		var btnName = document.getElementById("btn-1").textContent;
		var y = btnName.innerHTML = "Save & Exit";
		document.getElementById("btn-1").innerText = y;
	}
}

function saveServerEntry() {
	var btnName = document.getElementById('btn-1').textContent;
	var action = document.getElementById('action').value;
	var config_type = document.getElementById('configuration_type').value;

	if (btnName == 'Save & Exit' && config_type == 'PSD') {// database server
		// entry save
		if (validateServerEntryForm()) {

			ajaxSubmitPostData("physicalServerEntry", "serverEntryForm",
					serverEntryResponse);
		}
	} else if (btnName == 'Save & Next' && config_type == 'ASD') {// app server entry save
		if (validateServerEntryForm1()) {

			ajaxSubmitPostData("appServerDetailEntry", "serverEntryForm",
					serverEntryResponse);
		}
	} else if (btnName == 'Update & Exit') {// database server entry update
		if (validateServerEntryForm()) {

			ajaxSubmitPostData("physicalServerEntry", "serverEntryForm",
					serverEntryUpdateResponse);
		}
	} else if (btnName == 'Update & Next') {// app server entry update
		if (validateServerEntryForm()) {
			ajaxSubmitPostData("appServerDetailEntry", "serverEntryForm",
					serverEntryResponse);
		}

	} else if (btnName == 'Save') {

		openErrorModal("Please select Server Type.");
	}
}

function updateAppServerDetails() {
	ajaxSubmitPostData("appServerDetailEntry", "serverEntryForm",
			serverEntryUpdateResponse);
}

function serverEntryResponse(response) {
	var type = document.getElementById('configuration_type').value;
	var action = document.getElementById('action').value;

	if (response === "success" && type == 'PSD' && action == "save") {
		openSuccessModal("Data Saved Successfully!", "resetServerEntry()");
	} else if (response === "success" && type == 'ASD' && action == "save") {
		openSuccessModal("Data Saved Successfully!", "resetAppServerEntry()");
	} else if (response === "success" && type == 'PSD' && action == "edit") {
		openSuccessModal("Data Updated Successfully!", "resetServerEntry()");
	} else if (response === "success" && type == 'ASD' && action == "edit") {
		openSuccessModal("Data updated Successfully!", "resetAppServerEntry()");
	} else {
		openErrorModal("Some Error Occured..!", "");
	}
}// End function

function serverEntryUpdateResponse(response) {
	var type = document.getElementById('configuration_type').value;
	if (response === "success") {
		openSuccessModal("Data Updated Successfully!", "resetServerEntry()");
	} else {
		openErrorModal("Some Error Occured..!", "");
	}
}

function resetServerEntry() {
	var cp = document.getElementById("globalcontextpath").value;
	window.location.href = cp + 'serverDetailDashboard?server_type=PHY';
}

function resetAppServerEntry() {

	var server_id = document.getElementById("server_id").value;
	// alert("server_id="+server_id);
	var cp = document.getElementById("globalcontextpath").value;
	window.location.href = cp + 'appServerDetailTemp?server_id=' + server_id;
}

function backToAppEntry() {
	var server_id = document.getElementById("server_id").value;
	var cp = document.getElementById("globalcontextpath").value;
	// window.location.href = cp + 'serverDetailEntry?server_id=' + server_id;
	window.location = cp + "serverDetailEntry?server_id=" + server_id
			+ "&server_type_code=APS";
}

function validateServerEntryForm123() {
	var status;

	var server_type_code = $("#server_type_code111").val();
	var server_owner_name = $("#server_owner_name").val();
	var entity_code = $("#entity_code").val();
	var server_ip = $("#server_ip").val();
	var host_name = $("#host_name").val();
	var server_os = $("#server_os").val();
	var remote_username = $("#remote_username").val();
	var remote_pwd = $("#remote_pwd").val();
	var mapped_drive = $("#mapped_drive").val();
	var tag_name = $("#tag_name").val();

	var action = document.getElementById('action').value;

	if (lhsIsNull(server_type_code) && action === 'save') {
		openErrorModal("Please select Server type.");
		status = false;
	} else if (lhsIsNull(server_owner_name)) {
		openErrorModal("Please select server owner name.");
		status = false;
	} else if (server_owner_name === "BANK") {
		if (lhsIsNull(entity_code)) {
			openErrorModal("Please enter entity name.");
			status = false;
		}
	} else if (lhsIsNull(server_ip)) {
		openErrorModal("Please enter server IP.");
		status = false;
	} else if (lhsIsNull(host_name)) {
		openErrorModal("Please enter host name.");
		status = false;
	} else if (lhsIsNull(server_os)) {
		openErrorModal("Please select Server OS.");
		status = false;
	} else if (lhsIsNull(remote_username)) {
		openErrorModal("Please enter remote username.");
		status = false;
	} else if (lhsIsNull(remote_pwd)) {
		openErrorModal("Please enter remote password.");
		status = false;
	} else if (lhsIsNull(mapped_drive)) {
		openErrorModal("Please enter drive name.");
		status = false;
	} else if (lhsIsNull(tag_name)) {
		openErrorModal("Please enter tag name.");
		status = false;
	} else {
		status = true;
	}
	// alert(status);
	return status;
}

function validateServerEntryForm() {
	var status;

	var server_type_code = $("#server_type_code111").val();
	var server_owner_name = $("#server_owner_name").val();
	var entity_code = $("#entity_code").val();
	var server_ip = $("#server_ip").val();
	var host_name = $("#host_name").val();
	var server_os = $("#server_os").val();
	var remote_username = $("#remote_username").val();
	var remote_pwd = $("#remote_pwd").val();
	var mapped_drive = $("#mapped_drive").val();
	var tag_name = $("#tag_name").val();
	var installed_db = $("#installed_db").val();
	var installed_db_tool = $("#installed_db_tool").val();

	var action = document.getElementById('action').value;

	if (lhsIsNull(server_type_code) && action === 'save') {
		openErrorModal("Please Select Server Type.");
		status = false;
	} else if (lhsIsNull(server_owner_name)) {
		openErrorModal("Please Select Server Owner Name.");
		status = false;
	} else if (server_owner_name === 'BANK') {
		if (entity_code == "") {
			openErrorModal("Please Select Entity Name.");
			status = false;
		}
	} else if (lhsIsNull(server_ip)) {
		openErrorModal("Please Enter Server IP.");
		status = false;
	} else if (lhsIsNull(host_name)) {
		openErrorModal("Please Enter Host Name.");
		status = false;
	} else if (lhsIsNull(server_os)) {
		openErrorModal("Please Select Server OS.");
		status = false;
	} else if (lhsIsNull(remote_username)) {
		openErrorModal("Please Enter Remote Username.");
		status = false;
	} else if (lhsIsNull(remote_pwd)) {
		openErrorModal("Please Enter Remote Password.");
		status = false;
	} else if (mapped_drive == "") {
		openErrorModal("Please Enter Drive Name.");
		status = false;
	} else if (lhsIsNull(tag_name)) {
		openErrorModal("Please Enter Tag Name.");
		status = false;
	} else if (lhsIsNull(installed_db) && server_type_code == 'DBS') {
		openErrorModal("Please Enter Installed Database Name.");
		status = false;
	} else if (lhsIsNull(installed_db_tool) && server_type_code == "DBS") {
		openErrorModal("Please Enter Installed Database Tool Name.");
		status = false;
	} else if ($("#server_remark").val().length > 240) {
		openErrorModal("Remark Exceed Maximum Limit Of Characters.");
		status = false;
	} else {
		status = true;
	}
	// alert(status);
	return status;
}

function validateServerEntryForm1() {
	var status;

	var configuration_type =$("#configuration_type").val();
	var app_server_name = $("#app_server_name").val();
	var app_server_ip = $("#app_server_ip").val();
	var app_server_port = $("#app_server_port").val();
	var app_server_console_add = $("#app_server_console_add").val();
	var app_server_log_path = $("#app_server_log_path").val();
	var app_server_username = $("#app_server_username").val();
	var app_server_pwd = $("#app_server_pwd").val();
	
	if(lhsIsNull){
		openErrorModal("Please Configuration Type.");
		status = false;
	}else if (lhsIsNull(app_server_name)) {
		openErrorModal("Please Enter Server Name.");
		status = false;
	} else if (lhsIsNull(app_server_ip)) {
		openErrorModal("Please Select Server IP.");
		status = false;
	} else if (lhsIsNull(app_server_port)) {
		openErrorModal("Please Enter Server Port.");
		status = false;
	} else if (lhsIsNull(app_server_console_add)) {
		openErrorModal("Please Enter Server Console Address.");
		status = false;
	} else if (lhsIsNull(app_server_log_path)) {
		openErrorModal("Please Enter Server Log Path.");
		status = false;
	} else if (lhsIsNull(app_server_username)) {
		openErrorModal("Please Enter Server Username.");
		status = false;
	} else if (lhsIsNull(app_server_pwd)) {
		openErrorModal("Please Enter Server Password.");
		status = false;
	} else if ($("#app_server_remark").val().length > 240) {
		openErrorModal("Remark Exceed Maximum Limit Of Characters.");
		status = false;
	} else {
		status = true;
	}
	return status;
}

function copyContent() {
	var a = document.getElementById('app_server_ip').value;

	document.getElementById('app_server_console_add').value = a;

	var b = document.getElementById('app_server_port').value;
	document.getElementById('port').value = b;

	// document.getElementById("public_ip").value=a;
}

function validateIPaddress(inputElement) {

	var ipaddress = inputElement.value;
	var server_ip = $("#server_ip").val();
	var public_ip = $("#public_ip").val();
	
	if ( server_ip != '') {
		if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
				.test(ipaddress)) {
			return (true)
		}
	 else {
		openErrorModal('You have entered an invalid IP address!', '')
		$('#' + inputElement.id).focus();
		inputElement.value = '';
		$('#host_name').val('');
		return (false)
	}
	}
	else if ( public_ip != '') {
		if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
				.test(ipaddress)) {
			return (true)
		}
		 else {
			openErrorModal('You have entered an invalid IP address!', '')
			$('#' + inputElement.id).focus();
			inputElement.value = '';
			$('#host_name').val('');
			return (false)
		}

	}
}

function validatePortNumber(inputElement) {
	var ipaddress = inputElement.value;
	if (/^([1-9][0-9]{0,3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$/
			.test(ipaddress)) {
		return (true)
	}
	openErrorModal('You have entered an invalid Port Number!', '')
	$('#' + inputElement.id).focus();
	inputElement.value = '';
	return (false)
}

function saveAppNameEntry() {
	var inputs = $("#TextBoxContainer input");

	var appCode = [];
	var appNames = [];
	var localAppUrl = [];
	var publicAppUrl = [];
	// Iterate over the form controls
	var num = 1;
	var app_code1;
	var variable1;
	var variable2;
	var variable3;

	if (inputs.length == 0) {
		openErrorModal('Please Add Atleast One Application Name !', '');
	} else {
		for (i = 1; i <= count1; i++) {
			var app = inputs[i].value;
			// if (!lhsIsNull(app)) {
			app_code1 = document.getElementById("app_code~" + i + "~0").value;
			variable1 = document.getElementById("DynamicTextBox~" + i + "~1").value;
			variable2 = document.getElementById('DynamicTextBox~' + i + '~2').value;
			variable3 = document.getElementById('DynamicTextBox~' + i + '~3').value;

			if (lhsIsNull(app_code1)) {
				appCode.push("null");
			} else {
				appCode.push(app_code1);
			}
			if (lhsIsNull(variable1)) {
				appNames.push("null");
			} else {
				appNames.push(variable1);
			}
			if (lhsIsNull(variable2)) {
				localAppUrl.push("null");
			} else {
				localAppUrl.push(variable2);
			}
			if (lhsIsNull(variable3)) {
				publicAppUrl.push("null");
			} else {
				publicAppUrl.push(variable3);
			}
		}

		if (!lhsIsNull(variable1)) {
			var url = "./appNameEntry?appNames=" + appNames + "&localAppUrl="
					+ localAppUrl + "&publicAppUrl=" + publicAppUrl
					+ "&appCode=" + appCode;
			ajaxPostUrl(url, appNameEntryResponse);

		} else {
			openErrorModal(
					'Please Add Application Name Or Delete The Empty Input Box!',
					'');
		}
	}
}

function appNameEntryResponse(response) {

	var btn = document.getElementById('btn-1').textContent;

	if (response === "success" && btn === "Save & Exit") {
		openSuccessModal("Application Name Details Saved Successfully!",
				"resetAppNameEntry();");
	} else if (response === "success" && btn === "Update & Exit") {
		openSuccessModal("Application Name Details Updated Successfully!",
				"resetAppNameEntry();");
	} else {

		openErrorModal("Some Error Occured..!", "");
	}
}

function appNameUpdateResponse(response) {
	if (response === "success") {
		openSuccessModal("Application Name Update Successfully!",
				"resetAppNameEntry();");
	} else {
		openErrorModal("Some Error Occured..!", "");
	}
}

function resetAppNameEntry() {
	var cp = document.getElementById("globalcontextpath").value;
	window.location.href = cp + 'serverDetailDashboard?server_type=PHY';
}

function onLoadRadioButton() {

	document.getElementById('bulk_download_btn').style.display = 'none';// hide
	// download
	// btn

	var server_type = document.getElementById("radio_btn").value;

	if (server_type == "APS") {
		document.getElementById('PHY').checked = false;
		document.getElementById('DBS').checked = false;
		document.getElementById('APS').checked = true;
	} else if (server_type == "DBS") {
		document.getElementById('PHY').checked = false;
		document.getElementById('APS').checked = false;
		document.getElementById('DBS').checked = true;

	} else {
		document.getElementById('PHY').checked = true;
		document.getElementById('APS').checked = false;
		document.getElementById('DBS').checked = false;
	}
}

function getServerData(server_id) {
	// alert("id= "+server_id);
	id_count = server_id.split("-");
	var server_id1 = document.getElementById("parent_server11-" + id_count[1]).value;

	// var server_id1 = document.getElementById("parent_server11").value;

	// alert("server_id = "+server_id1)
	var server_type_code = document.getElementById("server_type_code-"
			+ id_count[1]).value;
	// alert("server_type_code = "+server_type_code)
	// var x = document.getElementById("displayAppServerDiv");
	var x = document.getElementById("displayAppServerDiv_" + id_count[1]);
	var cp = document.getElementById("globalcontextpath").value;
	if (x.style.display === "none") {
		x.style.display = "block";
		var data = 'server_id=' + encodeURIComponent(server_id1)
				+ '&server_type_code=' + encodeURIComponent(server_type_code);
		$
				.ajax({
					type : "GET",
					contentType : "application/json",
					url : cp + "getAppServerListWithStatus",
					data : data,
					cache : false,
					timeout : 600000,
					success : function(response) {
						// document.getElementById("tableContent").innerHTML =
						// response
						document.getElementById("tableContent_" + id_count[1]).innerHTML = response
						const serverIPPort = [];
						$('#appServerTable_' + id_count).find('tr').each(
								function() {
									if (!this.rowIndex)
										return; // skip first row
									var serverIp = this.cells[2].innerHTML;
									var serverPort = this.cells[3].innerHTML;
									serverIPPort.push(serverIp + "#"
											+ serverPort);
								});
					},
					error : function(e) {
						openErrorModal('Please Add Application Server, Not Single One Configured.');
					}
				});
	} else {
		x.style.display = "none";
	}
}

let server_id_new = "";
let server_type_code_new = "";

function deleteServerDetails(id) {
	var id1 = id.split("-");
	var server_id = id1[0];
	var server_type_code = id1[1];
	
	server_id_new = server_id;
	server_type_code_new = server_type_code;
	var url = "./deleteServerTemp?server_id=" + server_id+"&server_type_code="+server_type_code;
	ajaxPostUrl(url, deleteServerDetailsResponse);
}

function deleteServerDetailsResponse(response) {

	if (response == 0) {
		openConfirmModal("Do You Want To Delete ?", "deleteSelectedServer('"+ server_id_new + "')");
	} else {
		openConfirmModal("Delete Application Server First","closeConfirmModal();");
	}
}

function deleteSelectedServer(server_id) {
	closeConfirmModal();
	var server_type_code= server_type_code_new;
	var url = "./deleteServer?server_id=" + server_id+"&server_type_code="+server_type_code_new;
	ajaxPostUrl(url, deleteServerResponse);
}

function deleteServerResponse(response) {
	// alert("response..."+response);
	if (response === "success") {
		openSuccessModal(globalDeleteMessage, "window.location.reload();");
	} else {
		openErrorModal("Some Error Occured..!", "");
	}
}// End function
//app
function deleteAppServerDetails(server_id) {

//	server_id_new = server_id;
//	var url = "./deleteAppServerTemp?server_id=" + server_id;
//	ajaxPostUrl(url, deleteAppServerDetails1);
	openConfirmModal("Do You Want To Delete ?", "deleteAppServer('"+ server_id + "')");
	
}

function deleteAppServerDetails1(response){
	if (response == 0) {
		openConfirmModal("Do You Want To Delete ?", "deleteAppServer('"+ server_id + "')");
	} else {
		openConfirmModal("This Contains Application Name , Delete Application Name First","closeConfirmModal();");
	}
}

function deleteAppServer(server_id) {
	closeConfirmModal();
	var server_type_code= server_type_code_new;
	var url = "./deleteAppServer?server_id=" + server_id+"&server_type_code="+server_type_code_new;
	ajaxPostUrl(url, deleteAppServerResponse);
}

function deleteAppServerResponse(response) {
	if (response === "success") {
		openSuccessModal(globalDeleteMessage, "window.location.reload();");
	} else {
		openErrorModal("Some Error Occured..!", "");
	}
}// End function

function editServerDetails(id) {

	if (!lhsIsNull(id)) {
		var id1 = id.split("-");
		var server_id = id1[0];
		var server_type_code = id1[1];

		var cp = document.getElementById("globalcontextpath").value;

		window.location = cp + "serverDetailEntry?server_id=" + server_id
				+ "&server_type_code=" + server_type_code;
	}

}

function viewServerDetails(id) {

	if (!lhsIsNull(id)) {
		var id1 = id.split("-");
		var server_id = id1[0];
		var server_type_code = id1[1];

		var cp = document.getElementById("globalcontextpath").value;
		// var server_type_code =
		// document.getElementById("server_type_code_edit").value;

		// window.location = cp + "serverDetailEntry?server_id=" + server_id;
		var url = "viewServerDetails?server_id=" + server_id;
		// + "&server_type_code=" + server_type_code;

		ajaxPostUrl(url, viewServerDetailsResponse);
	}

}

function viewServerDetailsResponse(response) {
	// alert(response)
	if (!lhsIsNull(response)) {
		// alert(response);
		$("#serverModelTableBody").html("");
		$("#serverModelTableBody").html(response);
		$("#myModal").modal({
			show : true
		});

	} else {
		$("#myModal").modal({
			show : true
		});
	}
}

function editAppServerDetails(server_id) {
	var cp = document.getElementById("globalcontextpath").value;
	var server_type_code = document.getElementById("type_code").value;

	window.location = cp + "serverDetailEntry?server_id=" + server_id
			+ "&server_type_code=" + server_type_code;

}

var count1;

function GetDynamicTextBox(num) {
	var size = document.getElementById('size').value;
	var app_name = "";
	var local_app_url = "";
	var public_app_url = "";
	var app_code = "";
	if (lhsIsNull(count1)) {
		count1 = num;

	}
	if (num <= size) {
		app_name = document.getElementById('app_name-' + num).value;
		local_app_url = document.getElementById('local_app_url-' + num).value;
		public_app_url = document.getElementById('public_app_url-' + num).value;
		app_code = document.getElementById('app_code-' + num).value;
	}

	document.getElementById('boxCount').value = count1;
	return ('<div class="input-group mb-1 mr-2" style="padding:10px ; width:700px"><input type="hidden" id="app_code~'
			+ count1
			+ '~0" value=\"'
			+ app_code
			+ '\"/><input class="form-control form-control-sm" id="DynamicTextBox~'
			+ count1
			+ '~1" name="DynamicTextBox1" type="text" value=\"'
			+ app_name
			+ '\"  placeholder="Enter App Name"/><input class="form-control form-control-sm" id="DynamicTextBox~'
			+ count1
			+ '~2" name="DynamicTextBox2" type="text" value=\"'
			+ local_app_url
			+ '\" placeholder="Enter Local App URL"/><input class="form-control form-control-sm" id="DynamicTextBox~'
			+ count1
			+ '~3" name="DynamicTextBox3" type="text" value=\"'
			+ public_app_url + '\" placeholder="Enter Public App URL"/><div class="input-group-append mr-2"><button class="btn btn-primary btn-sm rounded-0" type="button" onclick="RemoveTextBox(this)" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-trash"></i></button></div></div>'

	);

}

function AddTextBox() {
	var boxcount = document.getElementById('boxCount').value;

	var a = document.getElementById('app_name').value;
	document.getElementById("Div").style.display = "block";
	count1 = boxcount;
	count1++;
	var div = document.createElement("DIV");
	div.innerHTML = GetDynamicTextBox(count1);
	document.getElementById("TextBoxContainer").appendChild(div);

}

function AddTextBox1() {
	var size = document.getElementById('size').value;

	if (size > 0) {
		for (let i = 1; i <= size; i++) {
			var div = document.createElement("DIV");
			div.innerHTML = GetDynamicTextBox(i);
			document.getElementById("TextBoxContainer").appendChild(div);
			count1++;
		}
	}

	count1 = size;
}

function RemoveTextBox(div) {
	document.getElementById("TextBoxContainer").removeChild(
			div.parentNode.parentNode.parentNode);

	count1--;
	document.getElementById('boxCount').value = count1;
}

function changeBtnText() {
	var app_server_name = document.getElementById("app_server_name").value;
	var app_server_ip = document.getElementById("app_server_ip").value;

	if (!lhsIsNull(app_server_name) && !lhsIsNull(app_server_ip)) {
		document.getElementById("btn-1").textContent = "Update & Next";
	} else {
		document.getElementById("btn-1").textContent = "Save & Next";
	}

}

function serachByServerType(server_type) {
	var formdata = $('#serverForm').serializeArray();
	var fdata = JSON.stringify(formdata);
	if (server_type == "") {
		server_type = "PHY";
	}
	var url = "./serverDetailDashboard?server_type=" + server_type;
	ajaxPostDataArray(url, fdata, SearchResponse);

}

function SearchResponse(response) {
	$('#dataSpan').html('');
	$('#dataSpan').html(response);
	loadDataUsingPaginatorGrid();
} // function

function selectEntity() {
	var server_owner = document.getElementById('server_owner_name').value;
	if (server_owner == 'BANK') {
		document.getElementById('span_id').innerHTML = '*';

	} else {
		document.getElementById('span_id').innerHTML = '';
	}
	var url = "getEntityList?server_owner=" + server_owner;
	ajaxPostUrl(url, selectEntityResponse);

}

function selectEntityResponse(response) {
	var entity_code = $('#entity_code');
	entity_code.empty();
	if (!lhsIsNull(response)) {

		$.each(response, function(k, v) {
			entity_code.append('<option value=' + k + '>' + v + '</option>');
			console.log("key " + k + "value " + v);
		});

		$('#entity_code').multiselect({
			buttonWidth : '160px',
			includeSelectAllOption : true,
			nonSelectedText : '--Select Entity Name--'
		});
	} else {
		entity_code.append('<option value="">--Select--</option>');
	}
}

function selectConfigType() {
	var type = document.getElementById('configuration_type').value;
	if (type == 'PSD') {

		document.getElementById("server_entry").style.display = "block";
		document.getElementById("app_server_detail").style.display = "none";
		document.getElementById("app_server_entry").style.display = "none";

		document.getElementById('serverTypeDiv').style.display = 'flex';
		document.getElementById('serverNameDiv').style.display = 'flex';
		document.getElementById('entityNameDiv').style.display = 'flex';
		document.getElementById('serverIpDiv').style.display = 'flex';
		document.getElementById('publicIpDiv').style.display = 'flex';
		document.getElementById('hostNameDiv').style.display = 'flex';
		document.getElementById('serverOsDiv').style.display = 'flex';
		document.getElementById('remoteUserNameDiv').style.display = 'flex';
		document.getElementById('remotePwdDiv').style.display = 'flex';
		document.getElementById('driveDiv').style.display = 'flex';
		document.getElementById('tagNameDiv').style.display = 'flex';
		document.getElementById('remarkDiv').style.display = 'flex';
		document.getElementById('btns_1').style.display = 'flex';

		document.getElementById('serverIPDiv').style.display = 'none';
		document.getElementById('appServerIPDiv').style.display = 'none';
		document.getElementById('appServerDiv').style.display = 'none';
		document.getElementById('appServerPortDiv').style.display = 'none';
		document.getElementById('appServerConsoleDiv').style.display = 'none';
		document.getElementById('appServerLogPathDiv').style.display = 'none';
		document.getElementById('AppUsernameDiv').style.display = 'none';
		document.getElementById('appPasswordDiv').style.display = 'none';
		document.getElementById('appTagNameDiv').style.display = 'none';
		document.getElementById('appRemarkDiv').style.display = 'none';
	} else if (type == 'ASD') {

		document.getElementById("server_entry").style.display = "none";
		document.getElementById("app_server_detail").style.display = "block";
		document.getElementById("app_server_detail").disabled;
		document.getElementById("app_server_entry").style.display = "block";
		document.getElementById("app_server_entry").disabled;

		document.getElementById('serverTypeDiv').style.display = 'none';
		document.getElementById('serverNameDiv').style.display = 'none';
		document.getElementById('entityNameDiv').style.display = 'none';
		document.getElementById('serverIpDiv').style.display = 'none';
		document.getElementById('publicIpDiv').style.display = 'none';
		document.getElementById('hostNameDiv').style.display = 'none';
		document.getElementById('serverOsDiv').style.display = 'none';
		document.getElementById('remoteUserNameDiv').style.display = 'none';
		document.getElementById('remotePwdDiv').style.display = 'none';
		document.getElementById('driveDiv').style.display = 'none';
		document.getElementById('databaseDiv').style.display = 'none';
		document.getElementById('databaseToolDiv').style.display = 'none';
		document.getElementById('tagNameDiv').style.display = 'none';
		document.getElementById('remarkDiv').style.display = 'none';
		document.getElementById('btns_1').style.display = 'flex';

		document.getElementById('serverIPDiv').style.display = 'flex';
		document.getElementById('appServerIPDiv').style.display = 'flex';
		document.getElementById('appServerDiv').style.display = 'flex';
		document.getElementById('appServerPortDiv').style.display = 'flex';
		document.getElementById('appServerConsoleDiv').style.display = 'flex';
		document.getElementById('appServerLogPathDiv').style.display = 'flex';
		document.getElementById('AppUsernameDiv').style.display = 'flex';
		document.getElementById('appPasswordDiv').style.display = 'flex';
		document.getElementById('appTagNameDiv').style.display = 'flex';
		document.getElementById('appRemarkDiv').style.display = 'flex';

		var btnName = document.getElementById("btn-1").textContent;
		var y = btnName.innerHTML = "Save & Next";
		document.getElementById("btn-1").innerText = y;
	} else {
		openErrorModal("Please Select Configuration Type!");
	}
}

function validateServerName() {
	var app_server_name = document.getElementById('app_server_name').value;
	var app_server_ip = document.getElementById('app_server_ip1').value;
	var url = "validateServerName?app_server_ip=" + app_server_ip
			+ "&app_server_name=" + app_server_name;
	ajaxPostUrl(url, validateServerNameResponse);

}

function validateServerNameResponse(response) {
	var status;
	if (!lhsIsNull(response)) {
		openErrorModal("Selected App Server Already Exists!");
		status = false;
	} else {
		status = true;
	}
}

function setPublicIp() {
	var app_server_ip = document.getElementById('app_server_ip1').value;
	if (!lhsIsNull(app_server_ip)) {
		var url = "setPublicIp?app_server_ip=" + app_server_ip;
		ajaxPostUrl(url, setPublicIpResponse);
	}

}

function setPublicIpResponse(response) {
	if (!lhsIsNull(response)) {
		document.getElementById('public_ip1').value = response;
	} else {
		document.getElementById('public_ip1').value = "";
	}
}

function cancelAppServerEntry() {
	window.location = "serverDetailDashboard?server_type=PHY";
}

function checkServerIp(inputElement) {
	var ipaddress = inputElement.value;

	var url = "checkServerIp?server_id=" + ipaddress;
	ajaxPostUrl(url, checkServerIpResponse);
}

function checkServerIpResponse(response) {
	if (!lhsIsNull(response)) {
		openErrorModal(" Server IP Is Already Exists!");
		document.getElementById('server_ip').value='';
	} else {
		
	}
}


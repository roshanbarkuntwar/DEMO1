
function saveUser() {
	var edit_right = document.getElementById("edit_right").value;
	var delete_right = document.getElementById("delete_right").value;
	var add_right = document.getElementById("add_right").value;
	
	if (validateUserEntryForm()) {
		ajaxSubmitPostData("saveOrUpdateUser", "userConfigForm",
				saveUserResponse);
	}
}

// var cp = document.getElementById("globalcontextpath").value;

function saveUserResponse(response) {
	if (response === "success") {
		var action = $('#action').val();
		var msg = "";
		if (action == "save") {
			msg = "User saved successfully !";
		} else if (action == "edit") {
			msg = "User updated successfully !";
		}
		openSuccessModal(msg, "refreshUser()");
	} else {
		openErrorModal("Some Error Occured..!", "");
	}
}// End function

function validateUserEntryForm() {
	
	var status;
	var user_name = $('#user_name').val();
	var user_type = $('#user_type').val();
	var loginId = $('#loginId').val();
	var loginPwd = $('#loginPwd').val();
	var role_code = $('#role_code').val();
	var dept_code = $('#dept_str').val();
	var user_status = $("input[type='radio'][name='user_status']:checked").val();
	
	
	if (lhsIsNull(user_name)) {
		openErrorModal("Please enter the User Name.");
		status = false;
	} else if (lhsIsNull(user_type)) {
		openErrorModal("Please select the User Type.");
		status = false;
	} else if (lhsIsNull(loginId)) {
		openErrorModal("Please enter the Login Id.");
		status = false;
	} else if (lhsIsNull(loginPwd)) {
		openErrorModal("Please enter the Password.");
		status = false;
	} else if (lhsIsNull(role_code)) {
		openErrorModal("Please select Role.");
		status = false;
	} else if (lhsIsNull(dept_code)) {
		openErrorModal("Please select Department.");
		status = false;
	} else if (lhsIsNull(user_status)) {
		openErrorModal("Please select Status.");
		status = false;
	} else {

		status = true;
	}

	return status;
}// End function

function editUser() {
	if (!lhsIsNull(checkedId)) {
		console.log("checkedId..." + checkedId);
		editUserConfig(checkedId);

	}
}

function editUserConfig(id) {
	// alert("id.."+id);
	var split_id = id.split('~');
	var user_code = split_id[2];
	var cp = document.getElementById("globalcontextpath").value;
	var url = cp + "addNewUser?user_code=" + user_code;
	window.location = url;
}

function deleteUser(id) {
	openConfirmModal("Do you want to delete ?", "deleteSelectedRow('" + id
			+ "')");
}

function deleteSelectedRow(id) {
	closeConfirmModal();
	var split_id = id.split('~');
	var user_code = split_id[2];
	var cp = document.getElementById("globalcontextpath").value;
	var url = cp + "./deleteUser?user_code=" + user_code;

	ajaxPostUrl(url, deleteUserResponse);
}

function deleteUserResponse(response) {
	if (response === "success") {
		openSuccessModal(globalDeleteMessage, "refreshUser()");
	} else {
		openErrorModal("Some Error Occured..!", "");
	}
}// End function

function searchUserFilter() {
	var formdata = $('#userForm').serializeArray();
	var fdata = JSON.stringify(formdata);
	// var cp = document.getElementById("globalcontextpath").value;
	var actionUrl = "./userDetails";
	ajaxPostDataArray(actionUrl, fdata, searchFilterBrowseResponse);
}

function searchRoleFilter() {

	var formdata = $('#userRoleForm').serializeArray();
	var fdata = JSON.stringify(formdata);

	var role_name = $('#role_name').val();
	var role_status = $('#role_status').val();

	if (lhsIsNull(role_name) && lhsIsNull(role_status)) {
		openErrorModal("Please select any filter to see the records !");
	} else {
		var actionUrl = "/roleDetails";
		ajaxPostDataArray(actionUrl, fdata, searchFilterBrowseResponse);

	}
}

function searchFilterBrowseResponse(response) {
	// console.log(response);
	$('#dataSpan').html('');
	$('#dataSpan').html(response);

	loadDataUsingPaginatorGrid();

}// end function

function resetUserFilter() {
	$("#userForm").find("input[type=text], textarea, select").val("");
	refreshUser();
}

function resetRoleFilter() {
	$("#userRoleForm").find("input[type=text], textarea, select").val("");
	refreshRole();
}

function refreshUser() {
	var url = "./userDetails";
	window.location = url;
}

function refreshRole() {
	var url = "./roleDetails";
	window.location = url;
}

function editRole(id) {
	var split_id = id.split('~');
	var role_code = split_id[2];

	var url = "addNewRole?role_code=" + role_code;
	window.location = url;
}

function saveRole() {
	if (validateRoleEntryForm()) {
		ajaxSubmitPostData("./saveOrUpdateRole", "userRoleForm",
				saveRoleResponse);
	}
}

function saveRoleResponse(response) {
	// alert("response="+response);
	if (response === "success") {
		var action = $('#action').val();
		var msg = "";
		if (action == "save") {
			msg = "Role saved successfully !";
		} else if (action == "edit") {
			msg = "Role updated successfully !";
		}
		openSuccessModal(msg, "refreshRole()");
	} else {
		openErrorModal("Some Error Occured..!", "");
	}
}// End function

function validateRoleEntryForm() {
	var selectedVal = $("#multiselect").val();
	var status;
	var role_type_code = $('#role_type_code').val();
	var role_name = $('#role_name').val();
	var role_status = $('#role_status').val();
	var assigned_dashboard = $('#assigned_dashboard').val();

	var checkboxes = document.getElementsByName('menu');
	var assigned_menu = selectedVal.toString().replaceAll(',', '#');
	// for (var i=0, n=checkboxes.length;i<n;i++)
	// {
	// if (checkboxes[i].checked)
	// {
	// assigned_menu += checkboxes[i].value + "#";
	// }
	// }

	if (lhsIsNull(role_type_code)) {
		openErrorModal("Please enter the Role Type Code.");
		status = false;
	} else if (lhsIsNull(role_name)) {
		openErrorModal("Please enter the Role Name.");
		status = false;
	} else if (lhsIsNull(role_status)) {
		openErrorModal("Please select Status.");
		status = false;
	} else if (lhsIsNull(assigned_menu)) {
		openErrorModal("Please select Assigned Menu.");
		status = false;
	} else if (lhsIsNull(assigned_dashboard)) {
		openErrorModal("Please select Assigned dashboard.");
		status = false;
	} else {
		$("#assigned_menu").val(assigned_menu);
		$("#role_name").val(role_name.trim().toUpperCase());
		status = true;
	}

	return status;
}// End function

var checkedId = '';
function openActionDiv(id) {
	checkedId = '';
	// alert(id);
	var checkbox = document.getElementById(id).checked;
	if (checkbox) {
		var cbs = document.getElementsByName("actionCheckbox");
		for (var i = 0; i < cbs.length; i++) {
			cbs[i].checked = false;
		}
		document.getElementById(id).checked = true;
		checkedId = id;
		$("#actiondiv").css('display', 'block');
	} else {
		checkedId = '';
		$("#actiondiv").css('display', 'none');
	}
}

function editRecord(funName) {
	if (!lhsIsNull(checkedId)) {
		// funName(checkedId);
		window[funName](checkedId);
	}
}

function deleteRecord1(funName) {
	if (!lhsIsNull(checkedId)) {
		// funName(checkedId);
		window[funName](checkedId);
	}
}
function deleteDocdetails(id) {
	// alert(id);
	openConfirmModal("Do you want to delete ?", "deleteSelectedRow('" + id + "')");
}
function deleteRecord1(funName) {
	if (!lhsIsNull(checkedId)) {
		// funName(checkedId);
		window[funName](checkedId);
	}
}
function deleteDocdetails(id) {
	// alert(id);
	openConfirmModal("Do you want to delete ?", "deleteSelectedRow('" + id
			+ "')");
}
function deleteRecord1(funName) {
	if (!lhsIsNull(checkedId)) {
		// funName(checkedId);
		window[funName](checkedId);
	}
}
function deleteDocdetails(id) {
	// alert(id);
	openConfirmModal("Do you want to delete ?", "deleteSelectedRow('" + id
			+ "')");
}





function viewUser() {
	if (!lhsIsNull(checkedId)) {
		var split_id = checkedId.split('~');
		var role_code = split_id[2];
		var url = "./viewUserDetail?role_code=" + role_code;
		ajaxPostUrl(url, viewWorkResponse);
	}
}


function viewWorkResponse(response) {
	if (!lhsIsNull(response)) {
		$("#workModelTableBody").html("");
		$("#workModelTableBody").html(response);
		$("#detail-modal").modal({
			show : true
		});
	} else {
		$("#detail-modal").modal({
			show : true
		});
	}
}

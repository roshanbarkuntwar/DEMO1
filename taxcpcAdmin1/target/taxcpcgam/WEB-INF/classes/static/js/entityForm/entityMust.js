function saveEntityWork() {
	var filename = document.getElementById("file_logo").value;
	if (validateEntryForm()) {
		ajaxSubmitPostData("saveEntityForm", "entityEntryForm",
				saveEntityResponse);

	}
}

function saveEntityResponse(response) {

	if (response === "success") {
		var action = $('#action').val();

		var mode = document.getElementById("mode").value;
		if (action == "save") {
			openSuccessModal("Data saved successfully!",
					"window.location.href='./entityDetail';");
		} else {
			openSuccessModal("Data Updated successfully!",
					"OpenWithView_Mode('" + mode + "');");
		}
	} else {
		openErrorModal("Some Error Occured..!", "");
	}
}

function OpenWithView_Mode(mode) {
	var url = "./entityDetail?mode=" + mode;
	window.location = url;
}

function validateEntryForm() {
	var entity_code = $('#entity_code').val();
	var entity_name = $('#entity_name').val();
	var parent_code = $('#parent_code').val();
	var division = $('#division').val();
	var add1 = $('#add1').val();
	var add3 = $('#add3').val();
	var city = $('#city').val();
	var Pin = $('#Pin').val();
	var phoneno = $('#phone').val();
	var email = $('#email').val();
	var print_name = $('#print_name').val();
	var website = $('#website').val();

	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

	var pattern = new RegExp('^(https?:\\/\\/)?' + // protocol
	'((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|' + // domain name
	'((\\d{1,3}\\.){3}\\d{1,3}))' + // OR ip (v4) address
	'(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*' + // port and path
	'(\\?[;&a-z\\d%_.~+=-]*)?' + // query string
	'(\\#[-a-z\\d_]*)?$', 'i');

	var phoneno1 = /^\d{10}$/;
	var pincode = /^\d{6}$/
	var dbuser = $('#db_user').val();
	var dbuser_pwd = $('#db_user_pwd').val();
	var action_name = $('#action').val();
	var status;

	if (lhsIsNull(entity_code)) {
		openErrorModal("Please Enter Entity Code.");
		status = false;
	} else if (lhsIsNull(entity_name)) {
		openErrorModal("Please Enter Entity Name.");
		status = false;
	} else if (lhsIsNull(division)) {
		openErrorModal("Please Enter Division.");
		status = false;
	} else if (lhsIsNull(add1)) {
		openErrorModal("Please Enter Address.");
		status = false;
	} else if (lhsIsNull(city)) {
		openErrorModal("Please Enter City Name.");
		status = false;
	} else if (lhsIsNull(Pin)) {
		openErrorModal("Please Enter PIN Code.");
		status = false;
	} else if (lhsIsNull(phoneno)) {
		openErrorModal(" Please Enter Phone Number.");
		status = false;
	} else if (!email.match(mailformat)) {
		openErrorModal("Please Enter Email Address!");
		status = false;
	} else if (lhsIsNull(print_name)) {
		openErrorModal("Please Enter Print Name.");
		status = false;
	} else if (lhsIsNull(website)) {
		openErrorModal("Please Enter  Website .");
		status = false;
	} else if (lhsIsNull(add3)) {
		openErrorModal("Please Enter War File.");
		status = false;
	} else if (lhsIsNull(dbuser)) {
		openErrorModal("Please Enter DB User.");
		status = false;
	} else if (lhsIsNull(dbuser_pwd)) {
		openErrorModal("Please Enter DB UserPassword.");
		status = false;
	} else {
		status = true;
	}

	return status;
}

function isValidUrl(field) {
	var regexp = /^(?:(?:https?|ftp):\/\/)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/\S*)?$/;
	if (regexp.test(field.value)) {
		return true;
	} else {
		openErrorModal("Invalid Website Please Enter like www.google.com");
		field.value = "";
		return false;
	}
}

function pinCode(field) {
	var validPin = /(^\d{6}$)/;
	if (validPin.test(field.value)) {
		return true;

	} else {
		openErrorModal("Invalid  Please Enter 6 Digit PIN Code");
		field.value = "";
		return false;
	}
}// end function

function mobileNO(field) {
	var validPin = /^\d{10}$/;
	if (validPin.test(field.value)) {
		return true;

	} else {
		openErrorModal("Invalid  Please Enter 10 Digit Number");
		field.value = "";
		return false;
	}
}// end function

function openActionDiv(id) {
	checkedId = '';
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

function viewRecord(funName) {
	if (!lhsIsNull(checkedId)) {
		window[funName](checkedId);
	}
}

function viewEntity(id) {
	var entity_code = id;
	var url = "./entityEntryDetails?entity_code=" + entity_code;
	ajaxPostUrl(url, viewEntitytDetails);
}

function viewEntitytDetails(response) {

	if (!lhsIsNull(response)) {
		$("#ModelTableBody").html("");
		$("#ModelTableBody").html(response);
		$("#myModal").modal({
			show : true
		});

	} else {
		$("#myModal").modal({
			show : true
		});
	}
}

function viewEntityText() {
	if (!lhsIsNull(checkedId)) {
		var split_id = checkedId.split('~');
		var entity_code = split_id[2];
		var url = "./entityEntryDetails?entity_code=" + entity_code;
		ajaxPostUrl(url, viewEntityTable);
	}
}

function viewEntityTable(response) {
	if (!lhsIsNull(response)) {
		$("#ModelTableBody").html("");
		$("#ModelTableBody").html(response);
		$("#myModal").modal({
			show : true
		});

	} else {
		$("#myModal").modal({
			show : true
		});
	}
}

function openviewForm(mode) {
	var url = "/entityDetail?mode=" + mode;
	window.location = url;
}

function deleteEntity(id) {
	openConfirmModal("Do you want to delete ?", "deleteSelectedWork('" + id
			+ "')");
}

function deleteSelectedWork(id) {

	var entity_code = id;
	var url = "./deleteEntity?entity_code=" + entity_code;
	ajaxPostUrl(url, deleteWorkResponse);

}

function deleteWorkResponse(response) {
	if (response === "success") {
		openSuccessModal(globalDeleteMessage, "window.location.reload();");
	} else {
		openErrorModal("Some Error Occured..!", "");
	}
}// End function

function editEntity(id) {

	var entity_code = id
	var mode = document.getElementById("viewCard").value;
	var url = "entityEntry?entity_code=" + entity_code + "&mode=" + mode;
	window.location = url;
}

function editEntityText() {
	if (!lhsIsNull(checkedId)) {
		var split_id = checkedId.split('~');
		var entity_code = split_id[2];
		var View = split_id[3];
		var url = "entityEntry?entity_code=" + entity_code + "&View=" + View;
		window.location = url;
	}

}

function EntitySearch() {

	var mode = document.getElementById("mode").value;
	var formdata = $('#entityDetail').serializeArray();
	var fdata = JSON.stringify(formdata);
	//var entity_name = $('#entity_name').val();
	//var entity_code = $('#entity_code').val();

	//var division = $('#division').val();

	var searchvalue = document.getElementById("search_entity").value;

	if (lhsIsNull(searchvalue)) {
		openErrorModal("Please select any filter to see the records !");
	} else {
		if (mode == 'Text') {
			var actionUrl = "./entityDetail";
			ajaxPostDataArray(actionUrl, fdata, searchFilterWorkResponse);
		} else {
			var actionUrl = "./entitySearchFilter?searchvalue="+searchvalue;
			ajaxPostDataArray(actionUrl, fdata, searchFilterWorkResponse);
		}

	}
}

function searchFilterWorkResponse(response) {
	$('#dataSpan').html('');
	$('#dataSpan').html(response);// ("response");
	loadDataUsingPaginatorGrid();

}// end function

function loadData() {
	var mode = document.getElementById("mode").value;
	if (mode === 'Card') {
		document.getElementById("Card").checked = true;
		document.getElementById("Text").checked = false;
		//document.getElementById('search_entity').style.display = 'inline-flex';
		//document.getElementById('division').style.display = 'none';
		//document.getElementById('entity_name').style.display = 'none';
		//document.getElementById('entity_code').style.display = 'none';
		document.getElementById('bulk_download_btn').style.display = 'none';

	} else if (mode === 'Text') {
		document.getElementById("Text").checked = true;
		document.getElementById("Card").checked = false;
		//document.getElementById('search_entity').style.display = 'none';
		//document.getElementById('division').style.display = 'inline-flex';
		//document.getElementById('entity_name').style.display = 'inline-flex';
		//document.getElementById('entity_code').style.display = 'inline-flex';
		document.getElementById('bulk_download_btn').style.display = 'block';
	}

}

function entityRefresh() {
	var mode = document.getElementById("mode").value;
	window.location.href = "./entityDetail?mode=" + mode;
}

function getLoadView(mode) {

	//document.getElementById('entity_name').value = '';
	//document.getElementById('division').value = '';
	document.getElementById('search_entity').value = '';

	document.getElementById('mode').value = mode;

	if (mode == 'Card') {
		document.getElementById('bulk_download_btn').style.display = 'none';
		//document.getElementById('search_entity').style.display = 'inline-flex';
		//document.getElementById('division').style.display = 'none';
		//document.getElementById('entity_name').style.display = 'none';
		//document.getElementById('actiondiv').style.display = 'none';
		//document.getElementById('entity_code').style.display = 'none';

	} else if (mode == 'Text') {
		document.getElementById('bulk_download_btn').style.display = 'block';
		//document.getElementById('search_entity').style.display = 'none';
		//document.getElementById('division').style.display = 'inline-flex';
		//document.getElementById('entity_name').style.display = 'inline-flex';
		//document.getElementById('entity_code').style.display = 'inline-flex';

	}
	var formdata = $('#entityDetail').serializeArray();
	var fdata = JSON.stringify(formdata);
	var actionUrl = "./entityDetail?mode=" + mode;
	ajaxPostDataArray(actionUrl, fdata, SaveResponse);

}// end function

function SaveResponse(response) {
	console.log(response);
	$('#dataSpan').html('');
	$('#dataSpan').html(response);

}//

function BackToDashboard(id) {
	var mode = id;
	var url = "./entityDetail?mode=" + mode;
	window.location = url;

}

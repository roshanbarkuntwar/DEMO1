function myFunction(){
	var action = document.getElementById("action").value;
	if(action == 'save'){
	document.getElementById('description').style.display='none';
	}
}

function showFieldsOnType() {
	document.getElementById('description').style.display='none';
}


function saveDictionary(){
//	docMode = $('#doc_mode').val();
//	if (!lhsIsNull(docMode) && docMode === 'Q' ) {
		var doc_data = $(".Editor-editor").html();
	//	alert("doc_data"+doc_data);
		$("#description").val(doc_data);
//	}
	if (validateForm()) {
		ajaxSubmitPostData("saveProjectDictionary",
				"projectDictionaryForm", saveDictionaryResponse);
	} 	 
}

function saveDictionaryResponse(response) {
    if(response === "success") {
    	var action = $('#action').val();
    	//alert("action"+action);
    	if (action == "save") {
    		openSuccessModal("Data saved successfully !" ,"window.location.href='./projectDictionary';");
		}else if (action == "edit") {
			openSuccessModal("Data updated successfully !" ,"window.location.href='./projectDictionary';");
		}  
    } else {
        openErrorModal("Some Error Occured..!","");
    }
}//End function

function validateForm(){
	var status;
    var keyword = $('#keyword_title_question').val();
    var project_code = $('#project_code').val();
    var description = '';
//    if (!lhsIsNull(docMode) && docMode === 'Q' ) {
    	description = $(".Editor-editor").html();
//	}else{
//		doc_file = document.getElementById("doc_file").files;
//	}
	if(lhsIsNull(project_code)) {
		openErrorModal("Please select Project Name.");
	    status = false;
	} else if(lhsIsNull(keyword)) {
    	openErrorModal("Please enter Project Keyword / Dictionary Title.");
        status = false;
    } else if (lhsIsNull(description)) {
		openErrorModal("Please enter Description.");
	} else {	
        status = true;
    }
    
    return status;
}//End function
 
function editDictionary(id) {
	
	if (!lhsIsNull(id)) {
		var doc_file = $('#doc_file').val();
		
	   var url = "./projectDictionaryEntry?seq=" + id;
		window.location =url;
		
	}	
}

function deleteDictionary(id){
	openConfirmModal("Do you want to delete ?","deleteSelectedDictionary('" + id + "')");
}

function deleteSelectedDictionary(id) {
	closeConfirmModal();
	var cp = document.getElementById("globalcontextpath").value;
	var url = cp+"deleteDictionary?seq=" + id;
	
	ajaxPostUrl(url, deleteSelectedDictionaryResponse);
}

function deleteSelectedDictionaryResponse(response) {
    if(response === "success") {
        openSuccessModal(globalDeleteMessage ,"window.location.reload();");
    } else {
        openErrorModal("Some Error Occured..!","");
    }
}//End function

function viewDictionary(id) {
	var filetypename = document.getElementById("filetype").value;
	//alert(filetypename);
	document.getElementById("filetype1").value = filetypename;
	//alert(filetype1);
if (!lhsIsNull(id)) {
		 var url = "./viewDictionaryDetail?seq=" +id;
       ajaxPostUrl(url, viewDictionaryResponse);
	}
}

function viewDictionaryResponse(response) {
	if(!lhsIsNull(response)) {
	$("#workModelTableBody").html("");
	$("#workModelTableBody").html(response);
	$("#detail-modal").modal({show: true});
	
    } else {
    	$("#detail-modal").modal({show: true});
    }
}

function onEditAction(){
	var action = $('#action').val();
	if (action == "edit") {
		var description = $("#description").text();
		$(".Editor-editor").html(description);
	}
}


function OpenLink(){
	
	var link = document.getElementById('link_dy').value;
	var url='https://'+link;
	
	if(!lhsIsNull(url)){
	window.open(url);
	}      
	}


function downloadSingleFile(){
	//var count =id.split("-");
	var seq_id = document.getElementById("seq_id_dy").value;
	var cp = document.getElementById("globalcontextpath").value;
	//alert("seq_id.."+seq_id);
	//var file_name = document.getElementById("file_name").value;
	var url = cp+"getdownloadSingleFile?seq_id="+seq_id;
	window.location = url;
   }


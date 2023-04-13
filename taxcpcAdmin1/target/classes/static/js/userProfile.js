/**
 * 
 */
function updateUserProfile() {
	if (validateUserProfileForm()) {
		ajaxSubmitPostData("/userProfileUpdate",
				"userProfileForm", updateUserProfileResponse);
	}
}// End function

function updateUserProfileResponse(response) {
    if(response === "success") {
        openSuccessModal(globalUpdateMessage ,"refreshUser()");
    } else {
        openErrorModal("Some Error Occured..!","");
    }
}// End function

function refreshUser() {
	 var url = "/logout";
	 window.location =url;
}

function validateUserProfileForm() {
    var status;
    
    var loginId = document.getElementById("loginId").value;
    var userName = document.getElementById("userName").value;
    var role_code = $('#role_code').val();
    var newPassword = document.getElementById("newPassword").value;
    var confirmNewPassword = document.getElementById("confirmNewPassword").value;
    
    if(lhsIsNull(role_code)) {
        addActionError("message","Role cannot be blank");
        status = false;
    } else if(lhsIsNull(loginId)) {
        addActionError("message","Login Id cannot be blank");
        status = false;
    } else if(lhsIsNull(userName)) {
        addActionError("message","Name cannot be blank");
        status = false;
    }  else  {	
        if(newPassword !== confirmNewPassword) {
            addActionError("message","New Password and Confirm New Password must be same");
            status = false;
            if(lhsIsNull(confirmNewPassword)){
        		addActionError("message","Please provide Confirm New Password also! ");
                status = false;
        	}
        } else {
            status = true;
        }
    }
    
    return status;
}// End function




function Validation(){  
	var checked = $('input[type=checkbox]:checked').length;
	if(checked <=5){

	}else{
		openErrorModal("You can only add up to 5 menus as favorites.!","");	
		$( "input[type=checkbox]:checked" ).prop( "checked", false );
	}
}


function SaveList(){ 
	var code = document.getElementsByName("menu_code");
    var sample_code=""
	var Menu_code = "";
	for (var i = 0; i < code.length; i++) {
		if (code[i].checked) {
			 sample_code = document.getElementById("action~" + (i + 1)).innerHTML;
			 sample_code = code[i].value;
			 Menu_code +=sample_code+"#";
			 
		}
	}
  //  var Role_code = $('#Role_Code').val(); alert(Role_code);
	menulist=sample_code+sample_code;
    //if(Role_code == 'ADM-0001'){
	var url = "dash-admin?Menu_code="+encodeURIComponent(Menu_code);
	window.location.href = url;
   // } else if(Role_code == 'DEV-0004'){
    	//var url = "dash-dev-tes?Menu_code="+encodeURIComponent(Menu_code);
    	//window.location.href = url;
  //  } else if(Role_code == '')
    	
   // }
}




function FindMenuList(){
	var formdata = $('#addmenuListForm').serializeArray();
	var fdata = JSON.stringify(formdata);
	var searchvalue = document.getElementById("search_entity").value;

	if (lhsIsNull(searchvalue)) {
		openErrorModal("Please select filter to see the records !");
	} else {
		var url = "SearchMenuList?searchvalue=" + searchvalue;
		ajaxPostDataArray(url, fdata, entitySearchResponse);
	}

}	

	function entitySearchResponse(response) {
		$('#dataspan').html('');
		$('#dataspan').html(response);
	} //function end


	function getLoadbyUrl(id){
	var url =id;
	window.location.href = url;
	}
	
	
	
	
// function togglePasswordFields(fieldId, iconId) {
// var pwdField = document.getElementById(fieldId);
// var icon = document.getElementById(iconId);
//
// try {
// if (pwdField.type === "password") {
// pwdField.type = "text";
// icon.className = "fa fa-eye-slash";
// icon.title = "Hide Password";
// } else {
// pwdField.type = "password";
// icon.className = "fa fa-eye";
// icon.title = "Show Password";
// }
// } catch (err) {
// console.log(err);
// }
// }

	

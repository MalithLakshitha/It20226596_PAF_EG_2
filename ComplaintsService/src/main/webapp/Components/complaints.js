$(document).ready(function()
{
	 $("#alertSuccess").hide();
 	 $("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
 	$("#alertSuccess").hide();
 	$("#alertError").text("");
 	$("#alertError").hide();

	// Form validation-------------------
	var status = validateComplaintForm();
	if (status != true)
	{
		 $("#alertError").text(status);
 		 $("#alertError").show();
 		 return;
 	}
 	
	// If valid-------------------------
 	var type = ($("#hidComplaintIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
 	{
 		url : "ComplaintsAPI",
 		type : type,
 		data : $("#formComplaint").serialize(),
 		dataType : "text",
 		complete : function(response, status)
 		{
 			onComplaintSaveComplete(response.responseText, status);
 		}
 	}); 
 });

function onComplaintSaveComplete(response, status)
	{
		if (status == "success")
		{
			 var resultSet = JSON.parse(response);
 			 if (resultSet.status.trim() == "success")
			 {
 				$("#alertSuccess").text("Successfully saved.");
 				$("#alertSuccess").show();
 				$("#divComplaintsGrid").html(resultSet.data);
 			 } 
 			 else if (resultSet.status.trim() == "error")
			 {
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			 }
 		} 
 		else if (status == "error")
 		{
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 		} 
 		else
 		{
 			$("#alertError").text("Unknown error while saving..");
 			$("#alertError").show();
 		}
		$("#hidComplaintIDSave").val("");
 		$("#formComplaint")[0].reset();
}

	// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
		 $("#hidComplaintIDSave").val($(this).data("complaintid"));
		 $("#eNumber").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#cType").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#cDetails").val($(this).closest("tr").find('td:eq(2)').text());
 		 $("#attachments").val($(this).closest("tr").find('td:eq(3)').text());
	});
	
	
	
	$(document).on("click", ".btnRemove", function(event)
	{
 		$.ajax(
 		{
 			url : "ComplaintsAPI",
 			type : "DELETE",
 			data : "complaintID=" + $(this).data("complaintid"),
 			dataType : "text",
 			complete : function(response, status)
 			{
 				onComplaintDeleteComplete(response.responseText, status);
 			}
 		});
	});


	function onComplaintDeleteComplete(response, status)
	{
		if (status == "success")
 		{
 			var resultSet = JSON.parse(response);
 			if (resultSet.status.trim() == "success")
 			{
 				$("#alertSuccess").text("Successfully deleted.");
 				$("#alertSuccess").show();
 				$("#divComplaintsGrid").html(resultSet.data);
 			} 
 			else if (resultSet.status.trim() == "error")
 			{
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			}
 		} 
 		else if (status == "error")
 		{
 				$("#alertError").text("Error while deleting.");
 				$("#alertError").show();
 		} 
 		else
 		{
 				$("#alertError").text("Unknown error while deleting..");
 				$("#alertError").show();
 		}
}
	

	// CLIENT-MODEL================================================================
	function validateComplaintForm()
	{
		// eNumber
		if ($("#eNumber").val().trim() == "")
		{
 			return "Insert Electricity Number.";
 		}

		// ctype
		if ($("#cType").val().trim() == "")
 		{
 			return "Insert Complaint Type.";
 		}

		// details-------------------------------
		if ($("#cDetails").val().trim() == "")
 		{
 			return "Insert Contact Details.";
 		}
 		

		// DESCRIPTION------------------------
		if ($("#attachments").val().trim() == "")
		{
 			return "Insert attachments.";
 		}

		return true;
	}
	
	
	
	

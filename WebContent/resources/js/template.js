function getEvents() {
	$.ajax({
		url: "/vertex/api/event/",
		type : "GET",
		success : function (jqXHR){
			$.each(jqXHR, listEvent)
		},
		error : function (jqXHR){
			if (jqXHR.status == 404) {
				$("#noeventsmessage").show();
			}
		}
	});
}

function getUser() {
	var userFullName = sessionStorage.getItem('userFullName');
	var userEmail = sessionStorage.getItem('userEmail');
	var userGender = sessionStorage.getItem('userGender');
	if (!userFullName || !userEmail || !userGender) {
		$.ajax({
			url: "/vertex/api/user/",
			type : "GET",
			success : function (jqXHR){
				loggedUser = jqXHR;
				$("#userFullName").text(loggedUser.fullName);
				$("#userFullName").after(loggedUser.email);
				if (loggedUser.gender == 'FEMALE') {
					$(".profile-pic").attr("src","/vertex/resources/img/profile-pics/female-user-icon.png");
				}
			}
		});
	} else {
		$("#userFullName").text(userFullName);
		$("#userFullName").after(userEmail);
		if (userGender == 'FEMALE') {
			$(".profile-pic").attr("src","/vertex/resources/img/profile-pics/female-user-icon.png");
		}
	}
	
}

function getEventIcon(event) {
	switch (event.eventComponent) {
	case 'STATUS':
		return '&#61752;';
	case 'DEFINITION':
		return '&#61952;';
	case 'PRIORITY':
		return '&#61738;';
	case 'COMMENT':
		return '&#61704;';
	default:
		return '&#61952;';
	}
}

function listEvent(index, event) {
	var eventHtml = '<div class="pull-left"><span class="icon">';
	eventHtml += getEventIcon(event);
	eventHtml += '</span></div>'
	eventHtml += '<div class="media-body">' + event.description;
	eventHtml += '  <small class="text-muted">'
			+ new Date(event.creationTime).toDateString();
	;
	eventHtml += '</small><br /></div>';
	$('#eventssection').append(eventHtml);
}

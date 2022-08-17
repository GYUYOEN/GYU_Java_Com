/**
 * 
 */
function getContextPath() {
	let contextPath = "/";
	$.ajax({
		type: "get",
		url: "/get/contextpath",
		dataType: "json",
		success: function(data) {
			contextPath = data.contextpath;
		}
	});
	return contextPath;
}

function themeChange(themeName) {
	let contextPath = getContextPath();
	let url = contextPath + 'set/theme?name=' + themeName;
	let next = "&next=" + location.href;
	location.href = url + next;
	
	$.ajax({
		type: "get",
		url: url,
		data: {
			name: themeName,
			next: location.href
		},
		dataType: "json",
		success: function(data) {
			location.assign(data.next);
		}
	});
}

function ajaxUploadImage(e) {
	var file = e.target.files[0];
	var fData = new FormData();
	fData.append("uploadImage", file, file.name);
	console.log(fData);
	$.ajax({
		type: "post",
		url: "/ajax/imageUpload",
		enctype: "multipart/form-data",
		data: fData,
		processData: false,
		contentType: false,
		success: function(data, status) {
			prevImage.src = data.loc;
		},
		error: function(data, status) {
			prevImage.src = data.loc;
		}
	});
}

function showPreview(element, id) {
	var file = element.files[0];
	var imgUrl = URL.createObjectURL(file);
	var img = document.getElementById(id);
	img.src = imgUrl;
}

function enableSaveButton(e) {
	var submit = document.querySelector("button[type='submit']");
	var enable = submit.getAttribute("class").replace("disable", "");
	submit.setAttribute("class", enable);
}

function sendElementDataValid(element, url) {
	$.ajax({
		type: "get",
		url: url,
		data: {
			name: element.name,
			value: element.value
		},
		success: function(data, status) {
			setLabelState(element.nextElementSibling, data.code, data.message);
		},
		complete: function() {
			if(element.value === "" || element.value === undefined) {
				element.nextElementSibling.innerText = "";
			}
		}
	});
}
function duplicateCheck(element, url) {
	sendElementDataValid(element, url)
}
function existsCheck(element, url) {
	sendElementDataValid(element, url)
}
function setLabelState(element, code, message) {
	if(code === "success") {
		element.innerText = message;
		element.setAttribute("class", "input-label-ok");
	} else if(code === "error") {
		// 오류 메시지
		element.innerText = message;
		element.setAttribute("class", "input-label-error");
	}
}
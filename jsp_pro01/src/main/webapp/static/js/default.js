// 이미지 업로드
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
		// 파일 업로드 할때 두가지 작성 필수(일반적인 content가 아니기 때문에)
		processData: false, // 문자열 제외 데이터 전송에 필요(이미지 경로에 대해서만 false, 문자열 일때 안써도 됨)
		contentType: false, // enctype에 맞춰서 자동으로 들어감
		success: function(data, status) {
			// console.log(data.msg)
			// console.log(data.loc)
			prevImage.src = data.loc;
		},
		error: function(data, status) {
			// console.log(data.msg)
			// console.log(data.loc)
			prevImage.src = data.loc;
		}
	});
}

// 이미지를 바꾸면 그 이미지가 뜰 수 있게 하는 js
function showPreview(element, id) { // 선택한 이미지의 파일 객체 정보 저장(배열 -> 여러개를 선택할 수 이도록 하는게 가능(multiple)하므로 배열로 있음)
	var file = element.files[0];
	var imgUrl = URL.createObjectURL(file); // 만들어진 파일에 대한 URL 정보 저장(console.log(imgUrl))
	var img = document.getElementById(id); // id = prevImage
	img.src = imgUrl; // src: 서버에 들어가 있는 이미지 정보가 있음 -> 사용자가 선책한 이미지 경로 저장되도록 함
}
	
	
// 수정하면 저장 버튼 활성화 js
function enableSaveButton(e) {
	var submit = document.querySelector("button[type='submit']");
	var enable = submit.getAttribute("class").replace("disable", "");
	submit.setAttribute("class", enable);
}

function sendElementDataValid(element, url) {
	$.ajax({
		type: "get", // DB에 변경x(검색, 조회)는 get, DB에 변경o(수정, 삭제, 추가)는 post
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
		// 정상 처리 메시지
		element.innerText = message;
		element.setAttribute("class", "input-label-ok");
	} else if(code === "error") {
		// 오류 메시지
		element.innerText = message;
		element.setAttribute("class", "input-label-error");
	}
}
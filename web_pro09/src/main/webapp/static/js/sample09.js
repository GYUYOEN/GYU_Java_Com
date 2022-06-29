/**
 * 
 */
 
window.onload = function () {
	initEventHandle();
}

function initEventHandle () {
	requiredCheckHandle();
	passwordInfoHandle();
}

function requiredCheckHandle() {
	var requiredElements = document.querySelectorAll("[required]");
	for(elemnt of requiredElements) {
		element.addEventListener("blur", requiredCheck);
	}
}

function passwordInfoHandle() {
	var passwordElements = document.querySelectorAll("input[type='password']");
	for(elemnt of passwordElements) {
		element.addEventListener("focus", passwordInfo);
	} 
}

function requiredCheck(e) {
	element = e.target;
	messageControl = element.parentElement.nextElementSibling;
	messageLabel = messageControl.getElementsByClassName("message-label")[0];
	
	if(!element.value) {
		messageControl.style.display = "inline-block";
		messageLabel.innerText = "필수 입력 항목 입니다.";
	} else {
		messageControl.style.display = "none";
		messageLabel.innerText = "";
	}
}

function passwordInfo(e) {
	element = e.target;
	messageControl = element.parentElement.nextElementSibling;
	messageLabel = messageControl.getElementsByClassName("message-label")[0];
	
	messageControl.style.display = "inline-block";
	messageLabel.innerText = "영문자 대/소문자 특수문자, 숫자 포함 8 ~ 32자";
	messageLabel.style.color = "gray";
	
	element.addEventListener("blur", function(e) {
		messageLabel.style.color = "";
	});
}

/**
 * 
 */
function toggleAll(element) {
	var name = element.getAttribute("name");
	var chk_items = document.getElementsByName(name);
	
	// 전체 선택에 체크를 풀었을 때
	if(element.getAttribute("checked") === "") {	
		for(e of chk_items) {
			// 체크 해제
			e.removeAttribute("checked");
		}
	// 체크가 되어있을 때
	} else {
		for(e of chk_items) {
			// 체크 설정
			e.setAttribute("checked", "");
		}
	}
}


// onload : html 이 다 실행된 후 실행되도록 함
window.onload = function() {
	// class가 sel-month인 곳에 전부 적용
	let month_selects = document.querySelectorAll("select.sel-month");
	
	for(e of month_selects) {
		createOptionMonth(e);
	}
};

function createOptionMonth(element) {
	for(let i = 1; i <= 12; i++) {
		let option = document.createElement("option");
		option.setAttribute("value", i);
		option.innerText = `${i}월`; // 문자열 포멧
		element.append(option);
	}
};



function createOptionDate(e1, e2) {
	let month = e1.value;
	let date = new Date();
	// 해당 month의 마지막 일자를 알려줌
	// date.setMonth(2, 0); : (2+1 -> 3월) 2월의 마지막 일자 (0 - 1) -> 28
	date.setMonth(month, 0);
	
	// 지울때마다 count가 변해서 변수로 따로 지정해줌
	let count = e2.childElementCount
	if(count > 1) {
		let opts = e2.children;
		for(let idx = 1; idx < count; idx++) {		
			// 1번을 지웠다고 1번이 null 된는 것이 아니라 1번이 지워지면
			// 1번 자리에 2번이 들어가고 2번 자리에 3번이 들어감 -> 크기도 바뀜(count 바뀜)
			// [1]로 설정한 이유 : option에 '일'을 지우지 않기 위해
			e2.removeChild(opts[1]); 
		}
	}
	
	// date.getDate() : 마지막일
	for(let d = 1; d <= date.getDate(); d++) {
		let option = document.createElement("option");
		option.setAttribute("value", d);
		option.innerText = `${d}일`;
		e2.append(option);
	}
}

function nextMenu() {
	var sliderMenu = document.getElementsByClassName("slide-menu")[0];
	var sliderItems = sliderMenu.children;
	var activeIdx;
	
	for(let idx = 0; idx < sliderItems.length; idx++) {
		let classname = sliderItems[idx].getAttribute("class");
		if(classname.indexOf("active") != -1) {
			sliderItems[idx].setAttribute("class", "slide-item");
			activeIdx = idx + 1;
			break;
		}
	}
	sliderItems[activeIdx].setAttribute("class", "slide-item active");
}

function prevMenu() {
	var sliderMenu = document.getElementsByClassName("slide-menu")[0];
	var sliderItems = sliderMenu.children;
	var activeIdx;
	
	for(let idx = 0; idx < sliderItems.length; idx++) {
		let classname = sliderItems[idx].getAttribute("class");
		if(classname.indexOf("active") != -1) {
			sliderItems[idx].setAttribute("class", "slide-item");
			activeIdx = idx - 1;
			break;
		}
	}
	sliderItems[activeIdx].setAttribute("class", "slide-item active");
}
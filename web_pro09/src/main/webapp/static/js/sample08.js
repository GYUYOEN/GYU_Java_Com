/**
 * 
 */
 window.onload = function() {
	// .clone 에 대한 이벤트 핸들러 복제는 반드시 JQuery 로 연결한 이벤트 핸들러에
	// 대해서만 적용 된다. 기존 DOM의 addEventListener 로 등록된 이벤트
	// 핸들러는 동작하지 않는다.
	
	// JQuery에서 addEventListener -> bind
	/*
	$(".copy1").bind("mouseover", function() {
		this.style.color = 'red';
	});
	$(".copy1").bind("mouseout", function() {
		this.style.color = 'black';
	});
	*/
	
	// 위 주석과 똑같이 동작
	$(".copy1").hover(function() {
		this.style.color= "red";
	}, function() {
		this.style.color= "black";
	})
	
	
	
	$("input").bind("input", function() {
		console.log(this.value);
	});
}
/**
 * 
 */


/*
var day = new Date();
undefined
day;
Thu Jun 09 2022 14:39:11 GMT+0900 (한국 표준시)
day.getFullYear();
2022
day.getMonth();
5
//0~11읽어서 6월인데 5월로 나옴 (+1 해줌)
undefined
day.getDate();
9
day.getMinutes();
39
day.getSeconds();
11
day.getMilliseconds();
243
day.getTimezoneOffset();
-540
// 세계 표준시와 한국표준시(현재지역) 차이(분 차이, +9시간 차이) 
// day 를 출력했을때 GMT 가 없으면 세계표준시, 세계표준시 + 9시간 = 한국 표준시
undefined
day.getUTCHours();
5
// 표준시간을 알려줌 
undefined
var day = new Date();
undefined
day;
Thu Jun 09 2022 14:46:03 GMT+0900 (한국 표준시)
//무조건 현재시간 출력 
undefined
day.getDay();
4
var day = new Date('6 6, 2022');
undefined
day;
Mon Jun 06 2022 00:00:00 GMT+0900 (한국 표준시)
day.getDay();
1
// 0(일요일) ~ 6(토요일) 
undefined
day.setFullYear(2023);
1685977200000
day;
Tue Jun 06 2023 00:00:00 GMT+0900 (한국 표준시)
day.setFullYear(2023, 2, 1);
1677596400000
day;
Wed Mar 01 2023 00:00:00 GMT+0900 (한국 표준시)
*/

 function f1() {
	var res1 = document.getElementById("res1");
	res1.innerHTML = new Date();
}

function f2() {
	var res2 = document.getElementById("res2");
	// res2.innerHTML = new Date("1 1, 2022");
	// res2.innerHTML = new Date(2022, 0, 1);
	res2.innerHTML = new Date("2022-01-01");
}

function f3() {
	var res3 = document.getElementById("res3");
	var date = document.getElementById("id_input1_date");
	res3.innerHTML = new Date(date.value);
	// 자바스크립트에서 사용자 입력은 다 문자열로 출력 -> 형변환 시켜줘야함
}
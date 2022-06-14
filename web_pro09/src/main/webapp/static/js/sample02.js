/**
 * 자바스크립트는 동적 타입을 지원하기 때문에 자료형에 대한 명시를 할 필요가 없다.
 * 매개변수 타입도 지정x, 그냥 값만 판단
 */
 
function f1() {
	// 문자 리턴
	return "함수 실행이 완료되었습니다."
}

function f2() {
	// 숫자 리턴
	return 100;
}

function f3() {
	// return;
}

// 함수명을 변수명으로 뺌
var f4 = function() {
	 return "익명함수 입니다.";
};

(function() {
	console.log("즉시 실행되는 익명 함수 입니다.");
}) ();

function f5(x) {
	console.log("매개변수 x 값 -> " + x);
};

function f6(x, y=0) {
	console.log("매개변수 x, y 값 -> " + x + ", " + y);
};

// args에 무슨 문자열이 들어가도 상관없음
function f7(x, ...args) {
//	console.log(typeof(args)); // object
//	console.log(args[0]); // args가 배열임을 알수 있다. (20부터 배열 -> 0 index)
//	console.log("매개변수 x, args 값 -> " + x + ", " + args);
	console.log("매개변수 x 값 -> " + x);
	for(let arg of args) {
		console.log("매개변수 args 값 -> " + arg);
	}
}
// f7() : undefined, []   f7(10) : 10, []    ...

function f8(x, y=0, ...args) {
	console.log("매개변수 x, y 값 -> " + x + ", " + y);
	for(let arg of args) {
		console.log("매개변수 args 값 -> " + arg);
	}
}

// 매개변수가 있든 없든 arguments에 다 저장
// x, y(매개변수) 에도 저장이 되고 arguments 에도 저장이 됨
// function f9(x) {
// function f9() {
function f9(x, ...y) {
	console.log(arguments);
//	console.log("매개변수 x 값 -> " + x);
	for(let v of arguments) {
		console.log(v);
	}
}

/*
// 두번째 매개변수 무시
// function 키워드 사용x
var f10 = (x) => {
	return x + 10;
};
*/

// f10() 일때 NaN인 이유 : 계산을 했을 때 숫자가 아니라서
// undefined + 10 = NaN
// var f10 = (x) => x + 10;

// 소괄호 생략 가능
// 두가지 이상의 매개변수일 때는 소괄호 생략x
// var f10 = x => x + 10;

var f10 = (x, y) => x + y;

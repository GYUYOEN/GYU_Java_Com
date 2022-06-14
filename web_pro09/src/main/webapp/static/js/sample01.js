/**
 * 
 */
 
 // var arr1 = [1, 2, 3];
 var arr1 = new Array(1, 2, 3);
 var res1 = document.getElementById("res1"); // 특정 html을 찾아내기 위한 방법
 // console에서 확인 res1이 null인지 아닌지 arr1에 값이 잘들어갔는지 안들어갔는지 확인 가능
 res1.innerHTML += arr1 + "<br>";
 res1.innerHTML += "arr1[0] = " + arr1[0] + "<br>";
 res1.innerHTML += "arr1[1] = " + arr1[1] + "<br>";
 res1.innerHTML += "arr1[2] = " + arr1[2] + "<br>";
 
 arr1[0] = 10;
 arr1[1] = 20;
 arr1[2] = 30;
 res1.innerHTML += arr1 + "<br>";
 
 // ()안에 있는 값의 index위치를 알려줌
 res1.innerHTML += "arr1.indexOf('10') -> " + arr1.indexOf(10) + "<br>";
 res1.innerHTML += "arr1.indexOf('20') -> " + arr1.indexOf(20) + "<br>";
 res1.innerHTML += "arr1.indexOf('30') -> " + arr1.indexOf(30) + "<br>";
 
 res1.innerHTML += "arr1.push(40)" + "<br>";
 // "", '' 을 사용해서 하면 string 타입으로 추가 (타입이 다름)
 /* 자바스크립트는 배열에 숫자, 문자열, 객체 등 다양한 타입을 넣어서 사용가능 */ 
 // arr1.push("40");
 // arr1.push('40');
 // 값을 추가할 떼
 arr1.push(40);
 res1.innerHTML += "arr1.push(50)" + "<br>";
 arr1.push(50);
 // res1.innerHTML += "arr1.push('60')" + "<br>"; // ""와 '' 구분 가능
 res1.innerHTML += "arr1.push(60)" + "<br>";
 arr1.push(60);
 res1.innerHTML += arr1 + "<br>";
 
 
 // 데이터 값을 앞에다가 추가
 res1.innerHTML += "arr1.unshift(0)" + "<br>"
 arr1.unshift(0);
 res1.innerHTML += arr1 + "<br>";
 
 // 마지막 데이터 값 삭제
 res1.innerHTML += "arr1.pop()" + "<br>";
 arr1.pop();
 // 삭제도 되면서 마지막 값 반환도 시켜줌
 res1.innerHTML += arr1.pop() + "<br>";
 res1.innerHTML += arr1 + "<br>";
 
 // 첫번째 데이터값 삭제
 res1.innerHTML += "arr1.shift()" + "<br>";
 arr1.shift();
 // 삭제도 되면서 첫번쪠 값 반환도 시켜줌
 res1.innerHTML += arr1.shift() + "<br>";
 res1.innerHTML += arr1 + "<br>";
 
 // 순서 뒤집음
 res1.innerHTML += "arr1.reverse()" + "<br>";
 arr1.reverse();
 res1.innerHTML += arr1 + "<br>";
  
 // 오름차순 정렬
 res1.innerHTML += "arr1.sort()" + "<br>";
 arr1.sort();
 res1.innerHTML += arr1 + "<br>";
  
 // 잘라내기
 // 특정함수를 실행하면 데이터가 수정이 되서 저장이 되는 것이 아니라 반환만 함
 // 실제 데이터에 변화가 없음
 res1.innerHTML += "arr1.slice(0,3)" + "<br>";
 res1.innerHTML += arr1.slice(0,3) + "<br>";
 res1.innerHTML += arr1 + "<br>";
 
 // 배열에 추가
 res1.innerHTML += "arr1.concat(['a', 'b', 'c'])" + "<br>";
 res1.innerHTML += arr1.concat(['a', 'b', 'c']) + "<br>";
 res1.innerHTML += arr1 + "<br>";
 
 // console : ?는 셍략가능, ...은 여러개 작성가능
 // 0 번 위치에서 0 개를 삭제하고 50, 60, 70을 추가함
 res1.innerHTML += "arr1.splice(0, 0, 50, 60, 70)" + "<br>";
 arr1.splice(0, 0, 50, 60, 70);
 res1.innerHTML += arr1 + "<br>";
 
 // 'A'를 삭제 수로 인식 해버림 -> B, C, D만 출력
 res1.innerHTML += "arr1.splice(6, 'A', 'B', 'C', 'D')" + "<br>";
 arr1.splice(6, "A", "B", "C", "D");
 res1.innerHTML += arr1 + "<br>";
 
 // 문자열로 변환
 res1.innerHTML += "arr1.toString()" + "<br>";
 arr1.toString();
 res1.innerHTML += arr1.toString() + "<br>";
 
 // 내림차순 1 방법 : 2차배열을 정렬할 때나 문자열 정렬할 때 사용
 // 두값을 비교해서 뺏을 때 양수면 오름, 음수면 내림
 res1.innerHTML += "arr1.slice(0,6)" + "<br>";
 arr1 = arr1.slice(0,6);
 res1.innerHTML += arr1 + "<br>";
 // x와 y를 비교(index 0번 1번 비교, 1번 2번 비교...)하고 y - x를 해서 음수면 내림차순 양수면 오름차순
 res1.innerHTML += "arr1.sort(function(x, y) {return y - x;})" + "<br>";
 arr1.sort(function(x, y) {return y - x;});
 res1.innerHTML += arr1 + "<br>";
 
 // 내림차순 2 방법
 res1.innerHTML += "arr1.sort()" + "<br>";
 arr1.sort();
 res1.innerHTML += arr1 + "<br>";
 res1.innerHTML += "arr1.reverse()" + "<br>";
 arr1.reverse();
 res1.innerHTML += arr1 + "<br>";
 
 res1.innerHTML += "'a' - 'b'" + "<br>";
 'a' - 'b';
 
 // 문자의 코드값을 출력해줌
 res1.innerHTML += "'a'.codePointAt()" + "<br>";
 res1.innerHTML += 'a'.codePointAt() + "<br>";
 res1.innerHTML += "'b'.codePointAt()" + "<br>";
 res1.innerHTML += 'b'.codePointAt() + "<br>";
 // 문자열의 정렬을 하기 위해 사용하기도 함
 res1.innerHTML += "'a'.codePointAt() - 'b'.codePointAt()" + "<br>";
 res1.innerHTML += 'a'.codePointAt() - 'b'.codePointAt() + "<br>";
 
 // 구분자를 만들어줌
 res1.innerHTML += " arr1.join('-')" + "<br>";
 arr1.join("-");
 res1.innerHTML += arr1.join("-") + "<br>";
 
 
 
 
 
 
 
 
 
 
/*
 * 다음의 input 요소에 있는 값을 배열로 만들어 exam1 에 출력
 * 출력형식은 ['a', 'b', 'c', 'd', 'e'] 와 같이 
 * 대괄호 안에 값이 출력되도록 한다.
 */
/*
 * var arr2 = new Array(id_input1.value);
 * exam1 = document.getElementById("exam1");
 * exam1.innerHTML += "[" + arr2 + "]";
 */
 
var arr2;
var input1 = document.getElementById("id_input1");
var exam1 = document.getElementById("exam1");
arr2 = input1.value.split(",");
 
for(var i = 0; i < arr2.length; i++) {
	arr2[i] = arr2[i].trim();
}
 
exam1.innerHTML = "['" + arr2.join("', '") + "']";
 
/*
 * 다음의 input 요소에 있는 값을 문제 1에서 만든 배열에 추가 후 출력
 * 출력형식은 문제 1과 동일한다.
 * 추가로 input 요소에 있는 값의 총 합을 구하여 배열에 추가도 한다.
 */
/*
 * var arr3 = new Array(id_input2.value);
 * exam2 = document.getElementById("exam2");
 * arr3 += arr3.concat(" " + arr2);
 * sum = arr3[0] + arr3[1] + arr3[2] + arr3[3];
 * arr3 += arr3.concat(" " + sum);
 * exam2.innerHTML += "[" + arr3 + "]";
 */
var input2 = document.getElementById("id_input2");
var exam2 = document.getElementById("exam2");
var tmp = input2.value.split(",");
var total = 0;
 
for(let item of tmp) {
	item = parseInt(item);
	total += item;
	arr2.push(item);
}
arr2.push(total);

// 화면에 출력되는 건 전부 문자열, 내부적으로 봤을 때는 구분됨
exam2.innerHTML = "['" + arr2.join("', '") + "']";

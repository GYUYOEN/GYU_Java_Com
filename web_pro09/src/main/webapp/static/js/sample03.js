/**
 * 
 */
 
 var  student = {
	name : "홍길동",
	age : 23,
	gender: "M"
};

function f1() {
	var res1 = document.getElementById("res1");
	res1.innerHTML += "var student = {" + "<br>";
	res1.innerHTML += "	   name: '홍길동'" + "<br>";
	res1.innerHTML += "    age: 23, " + "<br>";
	res1.innerHTML += "    gender: 'M'" + "<br>";
	res1.innerHTML += "};"
}

function f2() {
	var arr = new Array();
	var res2 = document.getElementById("res2");
	for(let name in student) {
		let value = student[name];
		if(typeof(value) === 'string') {
			value = "'" + value + "'";
		}
		arr.push(name + " : " + value);
	};
	res2.innerHTML += "student -> ";
	res2.innerHTML += "{" + arr.join(", ") + "}" + "<br>";
	res2.innerHTML += "student['name'] -> " + student['name'] + "<br>";
	res2.innerHTML += "student.name -> " + student.name + "<br>";
}

/* <console>
student;
{name: '홍길동', age: 23, gender: 'M'}
student['name'];
'홍길동'
student.name;
'홍길동'
*/

function f3() {
	// input 태그에 입력된 정보를 추출하여 stock 객체 생성하기
	// 만약 입력된 값이 없으면, res3 에 값을 입력하라는 메시지 출력
	var name = document.getElementById("id_input1_name");
	var count = document.getElementById("id_input1_count");
	var price = document.getElementById("id_input1_price");
	var res3 = document.getElementById("res3");
	
	if(!name.value) {
		res3.innerHTML = "주식명을 입력해 주세요."
		// 입력안한 부분을 포커싱 해줌
		name.focus();
		return;
	}
	
	if(!count.value) {
		res3.innerHTML = "주식수량을 입력해 주세요."
		count.focus();
		return;
	}

	
	if(!price.value) {
		res3.innerHTML = "주식금액을 입력해 주세요."
		price.focus();
		return;
	}
	
	var data = genStock(name.value, parseInt(count.value), parseInt(price.value));
	res3.innerHTML = print(data);
}

function f4() {
	var name = document.getElementById("id_input2_name");
	var value = document.getElementById("id_input2_value");
	var res4 = document.getElementById("res4");
	
	if(!name.value) {
		res4.innerHTML = "속성명을 입력해 주세요.";
		name.focus();
		return;
	}
	
	if(!value.value) {
		res4.innerHTML = "속성값을 입력해 주세요.";
		value.focus();
		return;
	}
	
	// 수정 : 덮어씌움, 추가 : 새로 생성
	student[name.value] = value.value;
	res4.innerHTML = print(student);
}

function genStock(name, count, price) {
	var stock = {
		name : name,
		count : count,
		price : price
	}
	return stock;
}

function print(data) {
	var arr = new Array();
	for(let name in data) {
		let value = data[name];
		if(typeof(value) === 'string') {
			value = "'" + value + "'";
		}
		arr.push(name + " : " + value);
	}
	return "{" + arr.join(", ") + "}";
}

/* <console>
student;
{name: '홍길동', age: 23, gender: 'M'}
student.age = 25;
25
student;
{name: '홍길동', age: 25, gender: 'M'}
student.phone = "010-1234-5678";
'010-1234-5678'
student;
{name: '홍길동', age: 25, gender: 'M', phone: '010-1234-5678'}
student['email'] = 'sample@example.com';
'sample@example.com'
student;
{name: '홍길동', age: 25, gender: 'M', phone: '010-1234-5678', email: 'sample@example.com'}
student.getGender = function() {
    if(this.gender === 'M') { // this = student
        return "남자";
    } else {
        return "여자";
    }
};
ƒ () {
    if(this.gender === 'M') {
        return "남자";
    } else {
        return "여자";
    }
}
student.getGender(); // 함수가 적용된 거라서 () 써야함
'남자'
student;
{name: '홍길동', age: 25, gender: 'M', phone: '010-1234-5678', email: 'sample@example.com', …}age: 25email: "sample@example.com"gender: "M"getgender: ƒ ()arguments: nullcaller: nulllength: 0name: ""prototype: {constructor: ƒ}[[FunctionLocation]]: VM1091:1[[Prototype]]: ƒ ()[[Scopes]]: Scopes[1]name: "홍길동"phone: "010-1234-5678"[[Prototype]]: Object
student.gender = "W";
'W'
student.getGender();
'여자'
*/

function genSquare(width=1, height=1, color="#000000") {
	var square = {	
		width: width,
		height: height,
		color: color,
		getArea: () => {
			return width * height; // 화살표 함수를 사용할 때 this 붙이지x
		}
	}
	return square;
}

function f5() {
	var width = document.getElementById("id_input_width");
	var height = document.getElementById("id_input_height");
	var color = document.getElementById("id_input_color");
	var res5 = document.getElementById("res5");
	
	if(!width.value) {
		res5.innerHTML = "너비를 입력하세요.";
		width.focus();
		return;
	}
	if(!height.value) {
		res5.innerHTML = "높이를 입력하세요.";
		height.focus();
		return;
	}
	if(!color.value) {
		res5.innerHTML = "색상을 입력하세요.";
		color.focus();
		return;
	}
	
	var square = genSquare(parseInt(width.value), parseInt(height.value), color.value);
	// res5.innerHTML = print(square);
	/*
	var element = "";
	element += "<div";
	element += " style='display:inline-block;";
	element += "width:" + square.width + "px;";
	element += "height:" + square.height + "px;";
	element += "background-color:" + square.color + ";'>";
	element += "면적이 " + square.getArea() + "인 사각형 입니다.";
	element += "</div>";
	res5.innerHTML = element;
	// innerText 를 사용하면 HTML 빼고 택스트만 출력됨
	*/
	
	var element = document.createElement("div");
	element.style.width = square.width + "px";
	element.style.height = square.height + "px";
	element.style.backgroundColor = square.color;
	element.innerHTML = "이 사각형의 면적은 " + square.getArea() + " 입니다.";
	res5.appendChild(element);
	// 마지막에 자식으로 추가
	
}

function print(square) {
	var arr = new Array();
	for(let name in square) {
		let value = square[name];
		if(typeof(value) === 'string') {
			value = "'" + value + "'";
		} else if(typeof(value) === 'function') {
			value = "'" + value() + "'";
		}
		arr.push(name + " : " + value);
	}
	return "{" + arr.join(", ") + "}";
}

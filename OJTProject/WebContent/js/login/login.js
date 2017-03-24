/**
 * 2017.03.14
 * 로그인 페이지 스크립트
 * AUTH : KWONYW
 */

// 페이지 로딩 완전 완료
window.onload  = function() {

	// 테스트
	console.log('login 페이지 로딩 완료');
	
	// 1. 계열사 조회
	httpGetAsync("/OJTProject/search_affiliate.do", function(responseText) {
		
		var affiliateList = eval(responseText);
		console.log('responseText : ' + JSON.stringify(affiliateList));

		
		var selectObj = document.loginForm.affiliateList;
		
		for (var i = 0; i < affiliateList.length; i++) {
//			var option = document.getElementById("affiliateList"); // 1.
			var optionObj = document.createElement("option"); // 2.

//			optionObj.setAttribute("value", affiliateList[i].CD); // 1.
			optionObj.value = affiliateList[i].CD; // 계열사 코드 // 2.
			
//			var t = document.createTextNode(affiliateList[i].NM); // 1.
//			optionObj.appendChild(t);
			optionObj.text = affiliateList[i].NM; // 계열사 이름 // 2.
		    
//			selectObj.innerHTML = '<option>' + 'apple' + '</option>'; // 1.
//			selectObj.options.add(optionObj); // 2.
			selectObj.add(optionObj); // 3.
		}
		
	});
	
	
	/****************************************************************
	 * 이벤트
	 ****************************************************************/
	/*
	 * 로그인 버튼 클릭 이벤트
	 */
	document.getElementById('loginBtn').onclick = function() {
		
		if (validationCheck()) { // 유효성 통과
			
			// param 설정
			var affiliate = document.loginForm.affiliateList.value;
			var id = document.getElementById("id").value;
			// TODO : 자바스크립트 패스워드 암호화
			var password = document.getElementById("password").value;
			// 테스트
			console.log('############### param #################');
			console.log('affiliate : ' + affiliate);
			console.log('id : ' + id);
			console.log('password : ' + password);
			console.log('#######################################');
			
			var param = 'affiliate=' + affiliate + '&id=' + id + '&password=' + password;
			
			// 로그인 요청
			httpPostAsync("/OJTProject/request_login.do", param, function(responseText) {
				alert('responseText : ' + responseText);
				if (responseText == 'true') {
					return true;
				} else {
					alert('로그인 실패... (임시)');
					return false;
				}
			});
			
		} else { // 유효성 실패
			// ...
		}
	}
	/****************************************************************/
	
}

// form onsubmit : 로그인 요청
function requestLogin() {
	
	// 0. form 유효성 검사
	if (validationCheck()) {
		
		// 1. 로그인 요청
//		httpPostAsync("/OJTProject/request_login.do", function(responseText) {
//			
//			// 테스트
//			return false;
//			
////			alert('responseText : ' + responseText);
////			if (responseText == 'true') {
////				return true;
////			} else {
////				alert('로그인 실패... (임시)');
////				return false;
////			}
//		});
		
		return true;
		
	} else {
		
		alert('유효성 실패');
		
		return false;
	}
	
}

// 로그인 유효성 검사 함수
function validationCheck() {
	
	var validationFlag = true;
	
	var selectObj = document.loginForm.affiliateList;
	var selectObjVal = selectObj.options[selectObj.selectedIndex].value; // options[i]
	console.log('selectObjVal : ' + selectObjVal);
	
	// 계열사 선택 select 박스
	if (!selectObjVal || selectObjVal == '선택') {
		validationFlag = false;
		alert('계역사를 선택해주세요.');
		return validationFlag;
	}
	
	// 아이디
	var idVal = document.getElementById('id').value;
	console.log('idVal : ' + idVal);
	
//	var pattern = /^[A-Za-z0-9_]{3,16}&/g;
	var pattern = /[A-Za-z0-9_]{3,16}/; // TODO : 자릿수 제한{3,16} : maximum 자릿수 16이 적용 안 됨..
	// 테스트
	console.log('pattern test : ' + pattern.test(idVal));
	if ( !(pattern.test(idVal)) || idVal.length > 16) {
		validationFlag = false;
		alert('아이디는 3 ~ 16자리의 영문, 숫자, _(under scope)만 가능합니다.');
		return validationFlag;
	}
	
	// 패스워드
	var passwordVal = document.getElementById('password').value;
	console.log('passwordVal : ' + passwordVal);
	
	// \{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\" =====> 특수문자 정규식
	pattern = /[A-Za-z0-9\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]{3,16}/; // TODO : 자릿수 제한{3,16} : maximum 자릿수 16이 적용 안 됨..
	// 테스트
	console.log('pattern test : ' + pattern.test(passwordVal));
	if ( !(pattern.test(passwordVal)) || passwordVal.length > 16) {
		validationFlag = false;
		alert('패스워드는 3 ~ 16자리의 영문, 숫자, 특수문자만 가능합니다.');
		return validationFlag;
	}
	
	return validationFlag;
	
}

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
	// 로그인 버튼 클릭 이벤트
	document.getElementById('loginBtn').onclick = function() {
		
		// validationFlag
		if (validationCheck()) { // 유효성 통과
			
			// 로그인 요청
			
			
		} else { // 유효성 실패
			// ...
		}
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
	
}

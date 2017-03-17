/**
 * 2017.03.14
 * 공통 스크립트
 * AUTH : KWONYW
 */

/**
 * http Async 요청 - GET (AJAX)
 */
function httpGetAsync(theUrl, callback) {
	
    var httpRequest;
    
    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
		httpRequest = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE
		try {
			httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}

	if (!httpRequest) {
		alert('Giving up :( Cannot create an XMLHTTP instance');
		return false;
	}
    
	httpRequest.onreadystatechange = function() {
        if (httpRequest.readyState == 4 && httpRequest.status == 200) {
        	if (callback) {
            	callback(httpRequest.responseText);
            }
        }
    }
	httpRequest.open("GET", theUrl, true); // true (asynchronous) or false (synchronous)
	// Sends the request to the server.
	// Used for POST requests
	httpRequest.send(null);
    
}

/**
 * http Async 요청 - POST (AJAX)
 */
function httpPostAsync(theUrl, param, callback) {
	
    var httpRequest;
    
    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
		httpRequest = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE
		try {
			httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}

	if (!httpRequest) {
		alert('Giving up :( Cannot create an XMLHTTP instance');
		return false;
	}
    
    
	httpRequest.onreadystatechange = function() {
        if (httpRequest.readyState == 4 && httpRequest.status == 200)
            callback(httpRequest.responseText);
    }
	httpRequest.open("POST", theUrl, true); // true (asynchronous) or false (synchronous)
	// Sends the request to the server.
	// Used for POST requests
	httpRequest.send(param);
    
}

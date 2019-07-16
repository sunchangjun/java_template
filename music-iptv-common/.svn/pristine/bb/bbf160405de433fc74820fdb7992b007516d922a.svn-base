var paramObj = {
    myUrl : "",
    serverUrl : location.protocol+"//"+location.hostname+":"+location.port+"/"+(location.pathname.split("/")[1]=="show"?"":location.pathname.split("/")[1]),
    imgUrl : location.protocol+"//"+location.hostname+":"+location.port+"/"+(location.pathname.split("/")[1]=="show"?"":location.pathname.split("/")[1]),
    dataReBack:"http://localhost:8080/ROOT/collect/",
    epgHost:"",
    starttime:"",
    userId:false,
    userDevice:false,
    test_string:"",
    test:true,
    prid:""
};

function getCurrntTime(){
	return dateFtt("yyyy-MM-dd HH:mm:ss",new Date);
}

function getCAcardNum(fn){
	
	if(!paramObj.userId){
		try {
			paramObj.userId = window.widgetmanager.getParameter("userId");
			paramObj.userDevice = window.widgetmanager.getParameter("deviceID’");
		} catch (e) {
			// TODO: handle exception
			//web页面测试时使用
			paramObj.userId ="test";
			paramObj.userDevice = "test";
		}
	}
	
	if(fn){
		fn();
	}
	return paramObj.userId;
//	if(paramObj.userInfo){
//		return paramObj.userInfo.userID;
//	}
//	ajax({
//		url: paramObj.serverUrl + "getUserInfo.do?",// 请求的URL,可传参数
//		type: "GET", //HTTP 请求类型,GET或POST
//		dataType: "html", //请求的文件类型html/xml
//		onSuccess: function(html){ //请求成功后执行[可选]
//			//var json = JSON.parse(html);
//			var json = eval('('+html+')');
//			if("0" == json.status&&json.userInfo) {
//				paramObj.userInfo =  eval('('+json.userInfo+')');
//				if(fn){
//					fn();
//				}
//			} else {
//				popUpObj.init("获取用户数据失败！");
//			}
//		},
//		onError:function(){ //请求失败后执行[可选]
//		},
//        onComplete:function(){// 请求成功.失败或完成(无论成功失败都会调用)时执行的函数[可选]
//		   // alert("onComplete");	             			           		  
//        },
//        post:"", 
//        timeout:7000  //请求的超时时间，默认7000ms，也可自行设定[可选]
//	});
}


function checkSTBType() {
	return false;
}



var menuObj = {
	pos : 0,
	pos_this : 0,
	max_size : 7,
	cur_size : 0,
	focusObj : $("menu_focus"),
	dataArray : [
		{title:"首页", code:"sy"},
		{title:"最新音乐", code:"zxyy"},
		{title:"排行榜", code:"phb"},
		{title:"歌手", code:"gs"},
		{title:"歌单", code:"gd"},
		{title:"MV", code:"mv"},
		{title:"免费专区", code:"mfzq"}
//		,
//		{title:"古典音乐", code:"gdyy"},
//		{title:"儿童音乐", code:"etyy"}
	],
	init : function() {
		this.pos_this = this.pos;
		this.cur_size = this.max_size > this.dataArray.length ? this.dataArray.length : this.max_size;
		this.focusOn(1);
		this.focusOut();
		this.show();
		for(var i=0;i<7;i++){
			$("menu"+i).style.fontSize="25px";
			if(menuObj.pos!=i){
				$("menu"+i).style.color="#969696";
			}
			$("menu"+i).style.width="200px";
		}
	},
	show : function() {
		for(var i = 0; i < this.max_size; i++) {
			if(i < this.cur_size) {
				$("menu" + i).innerHTML = "<span style='padding-bottom:6px;'>"+this.dataArray[i].title+"</span>";
			} else {
				$("menu" + i).innerHTML = "&nbsp;";
			}
		}
	},
	
	focusOn : function(_flag) {
		var span = $("menu" + this.pos).getElementsByTagName("span")[0];
		if(!_flag) {
			//this.focusObj.style.visibility = "visible";
		} else {
			$("menu" + this.pos).style.color = "#0EAF52";
			if(span){
				span.style.borderBottom="4px solid #0EAF52";
			}
		}
	},
	
	focusOut : function(_flag) {
		var span = $("menu" + this.pos).getElementsByTagName("span")[0];
		if(!_flag) {
			//this.focusObj.style.visibility = "hidden";
			if(span){
				span.style.borderBottom="";
			}
		} else {
			$("menu" + this.pos).style.color = "#969696";
			if(span){
				span.style.borderBottom="";
			}
		}
	},
	
	do_time_out : 0,
	focusMove : function(_num) {
		var num = _num || 0;
		var pos = this.pos + num;
		if(pos >= 0 && pos < this.cur_size) {
			this.focusOut(1);
			this.pos = pos;
			this.focusObj.style.left = -115 +200 * this.pos + "px";
			this.focusOn(1);
			
			clearTimeout(this.do_time_out);
			var that = this;
			this.do_time_out = setTimeout(function() {
				that.doSelect();
			}, 800);
		}
	},
	
	doSelect : function() {
		if(this.pos != this.pos_this) {
			var code = this.dataArray[this.pos].code;
			var url = "";
			
			switch(code) {
				case "sy":
					url = "index.htm";
					break;
				case "zxyy":
				case "gs":
				case "gd":
				case "gdyy":
				case "etyy":
				case "mv":
					url = "index02.htm";
					break;
				case "mfzq":
					url = "index04.htm";
					break;
				case "phb":
					url = "index03.htm";
					break;
			}
			
			location.href = Q.getNewAddUrl(url) + "menu_pos=" + this.pos + "&backUrl=" + Q.encode(oparateObj.backUrl);
		}
	}
};

var topBtnObj = {
	pos : 1,
	size : 5,
	array : [
		{title:"订购", url:"order.htm?from_flag=1"},
		{title:"搜索", url:"search.htm"},
		{title:"最近播放", url:"list.htm?code=play_record"},
		{title:"我喜欢", url:"list.htm?code=collect"}
	],
	
	init : function() {
		
	},
	
	focusOn : function() {
		$("top_btn" + this.pos + "_icon").style.visibility = "visible";
		$("top_btn" + this.pos).style.visibility = "hidden";
	},
	
	focusOut : function() {
		$("top_btn" + this.pos + "_icon").style.visibility = "hidden";
		$("top_btn" + this.pos).style.visibility = "visible";
	},
	
	focusMove : function(_num) {
		var num = _num || 0;
		var pos = this.pos + num;
		if(pos >= 1 && pos < this.size) {
			this.focusOut(1);
			this.pos = pos;
			this.focusOn(1);
		}
	},
	
	doSelect : function() {
		var backUrl = window.location.href.split("?")[0] + "?area=" + oparateObj.area + "&t_pos=" + this.pos + "&menu_pos=" + menuObj.pos_this + "&backUrl=" + Q.encode(oparateObj.backUrl);
		if(oparateObj.id) {
			backUrl += "&id=" + oparateObj.id;
		}
		if(oparateObj.code) {
			backUrl += "&code=" + oparateObj.code;
		}
		if(oparateObj.title) {
			backUrl += "&title=" + Q.encode(oparateObj.title);
		}
		var url = this.array[this.pos].url;
		if(3 == this.pos && "collect" == oparateObj.code) {
			return;
		} else if(4 == this.pos && window.location.href.indexOf("my_gd_list") > -1) {
			return;
		} else if("" != url) {
			location.href = Q.getNewAddUrl(url) + "backUrl=" + Q.encode(backUrl);
		} else {
			popUpObj.init("敬请期待！");
		}
	}
};

var popUpObj = {
	pos : 0,
	size : 2,
	oo_area : 0,
	doFuc : null,
	doBack : null,
	
	init : function(_ts_text, _btn0_text, _btn1_text) {
		this.focusOut();
		$("pop_up_text").innerHTML = _ts_text || "未知错误";
		if(_btn1_text) {
			this.size = 2;
			$("pop_up_btn0").innerHTML = _btn0_text;
			$("pop_up_btn1").innerHTML = _btn1_text;
			$("pop_up_btn0").style.visibility = "visible";
			$("pop_up_btn1").style.visibility = "visible";
			$("pop_up_btn2").style.visibility = "hidden";
			$("pop_up_bg").style.backgroundImage = "url(images/039.png)";
		} else {
			this.size = 1;
			$("pop_up_btn2").innerHTML = _btn0_text || "确定";
			$("pop_up_btn0").style.visibility = "hidden";
			$("pop_up_btn1").style.visibility = "hidden";
			$("pop_up_btn2").style.visibility = "visible";
			$("pop_up_bg").style.backgroundImage = "url(images/038.png)";
		}
		
		this.pos = 0;
		this.show();
		this.focusOn();
		this.focusMove();
		$("pop_up_text").style.color="#FFFFFF";
		$("pop_up_text").style.fontSize="23px";
	},
	
	show : function() {
		this.oo_area = oparateObj.area;
		oparateObj.area = 10;
		$("pop_up").style.display = "block";
	},
	
	hidden : function(_flag) {
		var flag = _flag || 0;
		oparateObj.area = this.oo_area;
		$("pop_up").style.display = "none";
		
		if(this.doBack && !flag) {
			this.doBack();
		}
		this.doBack = null;
		this.doFuc = null;
	},
	
	focusOn : function() {
		if(2 == this.size) {
			$("pop_up_btn" + this.pos).style.backgroundImage = "url(images/041_" + this.pos + ".png)";
		}
	},
	
	focusOut : function() {
		if(2 == this.size) {
			$("pop_up_btn" + this.pos).style.backgroundImage = "url(images/tm.gif)";
		}
	},
	
	focusMove : function(_num) {
		var num = _num || 0;
		var pos = this.pos + num;
		if(pos >= 0 && pos < this.size) {
			this.focusOut(1);
			this.pos = pos;
			this.focusOn(1);
		}
	},
	
	doSelect : function() {
		if(this.doFuc) {
			this.doFuc();
		}
		this.hidden(1);
	}
}

var Event = function(_event){
    var keycode = _event.keyCode | _event.which;
    var code = "";
    switch (keycode) {
        case 1:
        case 38:
        case 65362:
		case 87:
            code = "KEY_UP";
            break;
        case 2:
        case 40:
        case 65364:
		case 83:
            code = "KEY_DOWN";
            break;
        case 3:
        case 37:
        case 65361:
		case 65:
            code = "KEY_LEFT";
            break;
        case 4:
        case 39:
        case 65363:
		case 68:
            code = "KEY_RIGHT";
            break;
        case 13:
        case 65293:
            code = "KEY_SELECT";
            break; 
        case 340:
		case 640:
		case 283:
        case 8: 
        case 27: 
        case 65367: 
            code = "KEY_BACK";
            break;
		case 339:
			code = "KEY_EXIT";
			break;
        case 372:
        case 25:
		case 33:
		case 306:
            code = "KEY_PAGE_UP";
            break;
        case 373:
        case 26:
		case 34:
		case 307:
            code = "KEY_PAGE_DOWN";
            break;
        case 513: 
        case 65360: 
		case 72:
            code = "KEY_MENU";
            break;
        case 595: 
        case 63561: 
		case 61:
		case 1095:
            code = "KEY_VOLUME_UP";
            break;
        case 596: 
        case 63562: 
		case 45:
		case 1096:
            code = "KEY_VOLUME_DOWN";
            break;
        case 597: 
        case 63563: 
		case 67:
		case 1097:
            code = "KEY_VOLUME_MUTE";
            break;
        case 32:
            code = "KEY_F1";
            break;
        case 33:
            code = "KEY_F2";
            break;
        case 34:
            code = "KEY_F3";
            break;
        case 35:
            code = "KEY_F4";
            break;
        case 49:
            code = "KEY_NUMBER1";
            break;
        case 50:
            code = "KEY_NUMBER2";
            break;
        case 51:
            code = "KEY_NUMBER3";
            break;
        case 52:
            code = "KEY_NUMBER4";
            break;
        case 53:
            code = "KEY_NUMBER5";
            break;
        case 54:
            code = "KEY_NUMBER6";
            break;
        case 55:
            code = "KEY_NUMBER7";
            break;
        case 56:
            code = "KEY_NUMBER8";
            break;
        case 57:
            code = "KEY_NUMBER9";
            break;
        case 48:
            code = "KEY_NUMBER0";
            break;
        case 65307:
            code = "KEY_TRACK";
            break;
        case 36:
		case 76:
            code = "KEY_FAV";
            break;
        case 72:
            code = "KEY_PALYBACK";
            break;
		case 320:
		case 832:
			code = "KEY_RED";
			break;
		case 321:
		case 833:
			code = "KEY_GREEN";
			break;
		case 322:
		case 834:
			code = "KEY_YELLOW";
			break;
		case 323: 
		case 835:
			code = "KEY_BLUE";
			break;
		case 11001:
		case 10901:
			code = "PLAY_END";
			break;
		case 5210:
		case 5209:
			code = "IPANEL_PLAY_?";
			break;
		case 5226:
			code = "IP_PLAY_5226";
			break;
		default:
			code = keycode;
			break;
		
    }
	return code;
};

var Q = Query = {
	getFromURL: function(url,parameter){
		var index = url.indexOf("?");
		if (index != -1) {
			var parameterString = url.substr(index + 1);
			var reg = new RegExp("(^|&)" + parameter + "=([^&]*)(&|$)", "i");
			var r = parameterString.match(reg);
			if (r != null){
				return r[2];
			}
		}
		return null;
	},
	get: function(parameter) {
		if (typeof (parameter) == "undefined" || parameter == "") {
			return null;
		}
		var url = window.location.href;
		
		var hrefArr = url.split("?");
		if(hrefArr.length > 2) {
			for(var i = 0, len = hrefArr.length; i < len; i++) {
				if(0 == i) {
					url = hrefArr[0];
				} else if(1 == i) {
					url += "?" + hrefArr[1];
				} else {
					url += "&" + hrefArr[i];
				}
			}
		}
		
		var index = url.indexOf("?");
		if (index != -1) {
			var parameterString = url.substr(index + 1);
			var reg = new RegExp("(^|&)" + parameter + "=([^&]*)(&|$)", "i");
			var r = parameterString.match(reg);
			if (r != null){
				return r[2];
			}
		}
		return null;
	},
	getInt:function(parameter,defaultValue){
		var value = parseInt(this.get(parameter));
		return isNaN(value) ? (typeof(defaultValue) == "undefined" ? 0 : defaultValue) : value;
	},
	getDecoded:function(parameter){
		return this.decode(this.get(parameter));
	},
	decode:function(srcStr){
		if(typeof(srcStr) == "undefined"){return null;}
		return decodeURIComponent(srcStr);
	},
	encode:function(srcStr){
		if(typeof(srcStr) == "undefined"){return null;}
		return encodeURIComponent(srcStr);
	},
	getSymbol:function(url){
		return url.indexOf("?") == -1 ? "?" : "&";
	},
	joinURL:function(url,queryString){
		return url + this.getSymbol(url) + queryString;
	},
	createQueryString:function(obj){
		var a = [];
		for(var p in obj){
			if(typeof(obj[p]) == "function" || obj[p] == null || typeof(obj[p])=="undefined") continue;
			a.push(p + "=" + obj[p]);
		}
		return a.join("&");	
	},
	setGlobalVar: function (__key, __value) {
		var day = 30;
		var date = new Date();
		date.setTime(date.getTime() + day * 24 * 60 * 60 * 1000);
		document.cookie = __key + "=" + escape(__value) + ";expires=" + date.toGMTString();
	},
	getGlobalVar: function (__key) {
		var __arr,
		reg = new RegExp("(^| )" + __key + "=([^;]*)(;|$)");
		if(__arr = document.cookie.match(reg)){
			return unescape(__arr[2]);
		}else{
			return '';
		}
	},
	getUrlWithNoParam:function(url){
        if(url.indexOf("?") > -1) {
            url += "&";
            return url.split("?")[0];
        } else {
           return url;
        }
	},
	getNewAddUrl:function(_url) {
		var url = _url;
		if(url.indexOf("?") > -1) {
			url += "&";
		} else {
			url += "?";
		}
		if(-1 == url.indexOf("http://") && -1 == url.indexOf("https://") && -1 == url.indexOf("file:///")) {
			url = paramObj.myUrl + url;
		}
		return url;
	},
	
	parse_time : function(_seconds)
	{
		var second = _seconds % 60;				
		_seconds = Math.floor(_seconds / 60);
		var minute = _seconds % 60;				
		_seconds = Math.floor(_seconds / 60);
		if(second < 10) second = "0" + (second < 0 ? 0 : second);
		if(minute < 10) minute = "0" + (minute < 0 ? 0 : minute);
		return minute + ":" + second;
	},
	
	get_now_time : function()
	{
		var my_date = new Date();
		var y = my_date.getFullYear();
		var m = my_date.getMonth() + 1;
		var d = my_date.getDate();
		var h = my_date.getHours();
		var __m = my_date.getMinutes();
		var s = my_date.getSeconds();
		m = m < 10 ? "0" + m : m;
		d = d < 10 ? "0" + d : d;
		h = h < 10 ? "0" + h : h;
		__m = __m < 10 ? "0" + __m : __m;
		s = s < 10 ? "0" + s : s;
		return y + "-" + m + "-" + d + "-" + h + ":" + __m + ":" + s;
	},
	
	get_now_time2 : function()
	{
		var my_date = new Date();
		var y = my_date.getFullYear();
		var m = my_date.getMonth() + 1;
		var d = my_date.getDate();
		var h = my_date.getHours();
		var __m = my_date.getMinutes();
		var s = my_date.getSeconds();
		m = m < 10 ? "0" + m : m;
		d = d < 10 ? "0" + d : d;
		h = h < 10 ? "0" + h : h;
		__m = __m < 10 ? "0" + __m : __m;
		s = s < 10 ? "0" + s : s;
		return y + "" + m + "" + d + "" + h + "" + __m + "" + s;
	},
	
	checkTime : function(time_str, now_time) {
		var yyyy = time_str.substring(0, 4) - 0,
			MM = time_str.substring(4, 6) - 1,
			dd = time_str.substring(6, 8) - 0,
			hh = time_str.substring(8, 10) - 0,
			mm = time_str.substring(10, 12) - 0;
		var time = new Date(yyyy, MM, dd, hh, mm, 0).getTime();
		var interval = parseInt((time - now_time) / 1000);
		return interval;
	}
};

function $(_id) {
	return document.getElementById(_id);
}

function showTitleForMarquee(_title, _obj, _num) {
	if(_title.getBytesLength() > _num) {
		_obj.innerHTML = "<marquee>" + _title + "</marquee>";
	} else {
		_obj.innerHTML = _title;
	}
}

function showTitleForMarquee2(_title, _obj, _num) {
	if(_title.getBytesLength()  > _num) {
		_obj.innerHTML = "<marquee>" + _title + "</marquee>";
		_obj.style.lineHeight = "50px";
	} else {
		if(_title.getBytesLength()  >  _num / 2) {
			_obj.style.lineHeight = "23px";
		} else {
			_obj.style.lineHeight = "50px";
		}
		_obj.innerHTML = _title;
	}
}

function showTitleLikeMarquee(_title, _obj, _num, _time) {
	var time_out = 0;
	if(getBytesLength() .length > _num) {
		var time = _time || 2500;
		
		var tempText = _title;
		var size=Math.ceil(tempText.length/_num);
		var array = [];
		for(var i=0;i<size;i++){
		    var start=i*_num;
		    var end=(i+1)*_num;
			if(end>tempText.length){
			  end=tempText.length;
			}
		    array.push(tempText.substring(start,end));
		}
		var temp=1;
		_obj.innerHTML=array[0];
		if(array.length>1){
		   time_out = setInterval(function(){
		    _obj.innerHTML=array[temp];
			if(temp>=(array.length-1)){
	      		temp=0;
		    }else{
			  temp++;
		    }
		   },time);
		}
	} else {
		_obj.innerHTML = _title;
	}
	return time_out;
}
  
function toDecimal2(x) {  
	var f = parseFloat(x);  
	if (isNaN(f)) {  
    	return false;  
    }  
    var f = Math.round(x*100)/100;  
    var s = f.toString();  
    var rs = s.indexOf('.');  
    if (rs < 0) {  
    	rs = s.length;  
        s += '.';  
    }  
    while (s.length <= rs + 2) {  
    	s += '0';  
    }  
    return s;  
}

function showTime(){
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth();
	month = ((month + 1 ) < 10) ? ("0" + (month +1 )) : (month + 1);
	
	var day = now.getDate();
	day = (day < 10) ? ("0" + day) : day;
	
	var hour = now.getHours();
	hour = (hour < 10) ? ("0" + hour) : hour;
	
	var minute = now.getMinutes();
	minute = (minute < 10) ? ("0" + minute) : minute;
	
	var weekD=["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
	
	$("data_text0").innerHTML = weekD[now.getDay()];
	$("data_text1").innerHTML = month + "月" + day + "日";
	$("data_text2").innerHTML = hour + ":" + minute;
}

function createXHR() {
	if(typeof XMLHttpRequest != "undefined") {
		createXHR = function() {
			return new XMLHttpRequest();
		};
	} else if(typeof ActiveXObject != "undefined") {
		createXHR = function() {
			if(typeof arguments.callee.activeXString != "string") {
				var versions = ["MSXML2.XMLHttp.6.0", "MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp"];
				for(var i = 0, len = versions.length; i < len; i++) {
					try {
						var xhr = new ActiveXObject(versions[i]);
						arguments.callee.activeXString = versions[i];
						return xhr;
					} catch(ex) {
						if(len - 1 == i) {
							throw new Error("there is no xmlhttprequest object available");
						}
					}
				}
			} else {
				return new ActiveXObject(arguments.callee.activeXString);
			}
		};
	} else {
		createXHR = function() {
			throw new Error("there is no xmlhttprequest object available");
		};
	}
	return createXHR();
}

function ajax(_optionsObj, _cfFlag, _time_out) {
	var time_out = _time_out || new Date().getTime();
	var optionsObj = {
		type:        _optionsObj.type || "GET",
		dataType:    _optionsObj.dataType,
		url:        _optionsObj.url || "",
		requestType: _optionsObj.requestType === false ? false:true,
		time_out:    _optionsObj.time_out || 10000,
		onComplete: _optionsObj.onComplete || function(){},
		onError:    _optionsObj.onError || function(){},
		onSuccess:    _optionsObj.onSuccess || function(){},
		data:        _optionsObj.data || "",
		post:        _optionsObj.post || "",
		contentType:_optionsObj.contentType || "application/x-www-form-urlencoded"
	};
	var cfFlag = _cfFlag || true;
	
	var ajaxZXFlag = true;
	var timeOutRD = setTimeout(function(){
		clearTimeout(timeOutRD);
		ajaxZXFlag = false;
		if(!cfFlag) {
			ajax(optionsObj, true, time_out);
		} else {
			optionsObj.onError();
		}
	}, optionsObj.time_out);
	
	var xml = createXHR();
	xml.onreadystatechange = function() {
		if(xml.readyState == 4 && ajaxZXFlag){
			clearTimeout(timeOutRD);
			if(httpSuccess(xml) && ajaxZXFlag){
				optionsObj.onSuccess(httpData(xml, optionsObj.dataType ));
			}else{
				optionsObj.onError();					
			}
			optionsObj.onComplete(xml);
			xml = null;
		}
	}
	var url;
	if(optionsObj.url.indexOf("?") > -1) {
		url = optionsObj.url + "&timestamp=" + new Date().getTime();
	} else {
		url = optionsObj.url + "?timestamp=" + new Date().getTime();
	}
	xml.open(optionsObj.type, url, optionsObj.requestType); 
	if("GET" == optionsObj.type){
		xml.send(null);
	}else{
		xml.setRequestHeader('Content-Type',optionsObj.contentType);
		xml.send(optionsObj.post);	
	}
}

function httpSuccess(r){
	var flag = false;
	try {
		if((r.status >= 200 && r.status <= 300) || r.status == 304) {
			flag = true;
		} else if(!r.status && location.protocol == "file:") {
			flag = true;
		} else if(navigator.userAgent.indexOf('Safari') >= 0 && typeof r.status == "undefined") {
			flag = true;
		} else {
			flag = false;
		}   
	}catch(e){
		flag = false;
	} finally {
		return flag;
	}
}

function httpData(r, type){
	var ct = r.getResponseHeader("content-type");
	var data = !type && ct && ct.indexOf('xml') >= 0;
	data = type == "xml" || data ? r.responseXML : r.responseText;
	if(type == "script"){
		eval.call(window, data);
	}
	return data;
}




function serialize(a) {
	var s = [];
	if(a.constructor == Array){
		for(var i = 0; i < a.length; i++){
			s.push(a[i].name + "=" + encodeURIComponent( a[i].value ));
		}
	
	}else{
		for( var j in a ){
			s.push( j + "=" + encodeURIComponent( a[j] ));
		}
	}
	return s.join("&");
}

/**
 * 权限判定
 * @param authResult 鉴权结果
 * @param _backUrl  返回的url
 * @param mediaFromType 媒体从什么地方来
 * @param mediaFromId  媒体来源的id
 * @param prid 线索id
 * @param env 环境
 */
function checkAndToPlay(authResult,_backUrl,mediaFromType,mediaFromId,prid,env) {
	var auth = authResult.auth;
	var program = authResult.data;
	if(auth){
        //popUpObj.init("有权限");
        window.location.href=Q.getNewAddUrl("wthx_play.htm")+"backUrl="+Q.encode(_backUrl)+"&rid="+program.rid
            +"&type="+mediaFromType+"&mediaFromId="+mediaFromId+"&prid="+prid+"&test="+env;
	}
	else if(program&&program.free){
		window.location.href=Q.getNewAddUrl("wthx_play.htm")+"backUrl="+Q.encode(_backUrl)+"&rid="+program.rid
            +"&type="+mediaFromType+"&mediaFromId="+mediaFromId+"&prid="+prid+"&test="+env;
		//popUpObj.init("无权限，免费");
	}
	else{
        //订购逻辑每个地方不一样，自定义
		window.location.href=program.cloudPayUrl+"&backUrl="+Q.encode(_backUrl);
	}
}


function ajaxForAuth(obj,backUrl, _doSucessFuc, _doFaileFuc) {
	ajax({
		url: paramObj.serverUrl + "/authorization?rid=" +obj.rid+"&userd="+getCAcardNum()+"&type="+obj.type
		+"&prid="+paramObj.prid+"&test="+paramObj.test,// 请求的URL,可传参数
		type: "POST",
		onSuccess: function(html){
			var json = eval('('+html+')');
			console.log(json);
			if(json.code=="0") {
				if(_doSucessFuc) {
					_doSucessFuc(json.data, backUrl);
				}
			} else {
				popUpObj.init(json.msg);
			}
		},
		onError:function(){
			popUpObj.init("鉴权失败！");
		},
		onComplete:function(){
		               			           		  
		},
		post:"", 
		timeout:7000
	});
}

function ajaxForAuthAP(_contentId, _poid, _doSucessFuc, _doFaileFuc) {	
	ajax({
		url: paramObj.serverUrl + "/auth_ap.do?userid=" + getCAcardNum() + "&posturl=" + Q.getGlobalVar("ismpip") + "&stbversion=1&id=" + _contentId + "&poid=" + _poid,
		type: "GET",
		dataType: "html", 
		onSuccess: function(html){ 
			var json = eval('('+html+')');
			
			if("0" == json.status) {
				if(_doSucessFuc) {
					_doSucessFuc(json.rtsp, json.runTime);
				}
			} else if("200" == json.status) {
				ajaxForAuthAP(_contentId, _poid, _doSucessFuc, _doFaileFuc);
			} else if("100" == json.status) {
				if(_doFaileFuc) {
					_doFaileFuc();
				}
			} else {
				popUpObj.init(json.msg);
			}
		},
		onError:function(){
			popUpObj.init("鉴权失败！");
		},
		onComplete:function(){
		 	             			           		  
		},
		post:"", 
		timeout:7000
	});
}	

function ajaxForPaly(_id, _startTime, _endTime) {
}

function ajaxForPalyNew(_id) {
	ajax({
		url: paramObj.serverUrl + "paly.do?userid=8280203874455656&id=" + _id,
		type: "GET", 
		dataType: "html",
		onSuccess: function(html){

			
		},
		onError:function(){
			
		},
		onComplete:function(){
		             			           		  
		},
		post:"", 
		timeout:7000
	});
}

function dcHTMLToReciveServlet(_referpage, _url, _columnid) {
	
}

function ajaxForReciveServlet(_data_str) {
	
}

function ajaxForAddPlayList(_id) {
	ajax({
		url: paramObj.serverUrl + "addPlayList.do?userid=" + getCAcardNum() + "&id=" + _id + "&flag=true",
		type: "GET", 
		dataType: "html",
		onSuccess: function(html){
			
		},
		onError:function(){
			
		},
		onComplete:function(){
		              			           		  
		},
		post:"", 
		timeout:7000 
	});
}
	
function loadScript(url, callback) {
	var script = document.createElement("script");
	script.type = "text/javascript";
	if(typeof(callback) != "undefined"){
		if (script.readyState) {
			script.onreadystatechange = function () {
				if (script.readyState == "loaded" || script.readyState == "complete") {
					script.onreadystatechange = null;
					callback();
				}
			};
		} else {
			script.onload = function () {
				callback();
			};
		}
	}
	script.src = url;
	document.body.appendChild(script);
}

var go_to_test_htm_str = "";
function grabEvent(_event){
	var code = Event(_event);
	switch(code){	
		case "KEY_UP": //
			oparateObj.moveY(-1);
			return false;
			break;
		case "KEY_DOWN": //
			oparateObj.moveY(1);
			return false;
			break;		
		case "KEY_LEFT": //			
			oparateObj.moveX(-1);
			return false;
			break;
		case "KEY_RIGHT": //			
			oparateObj.moveX(1);
			return false;
			break;		
		case "KEY_PAGE_UP": //			
			oparateObj.changePage(-1);
			return false;
			break;
		case "KEY_PAGE_DOWN": //			
			oparateObj.changePage(1);
			return false;
			break;		
		case "KEY_SELECT": // 
			oparateObj.doSelect();
			return false;
			break;
		case "KEY_EXIT":
		case "KEY_BACK":
			oparateObj.doBack();
			return false;
			break;
		case "KEY_NUMBER0":
			go_to_test_htm_str += "0";
			break;
		case "KEY_NUMBER2":
			go_to_test_htm_str += "2";
			break;
		case "KEY_NUMBER1":
			go_to_test_htm_str += "1";
			if(go_to_test_htm_str.indexOf("2201") > -1) {
				location.href = "go_to_test.htm";
			}
			break;
		default:
			break;
	}
};

function checkUserInfo(fn){
	getCAcardNum(fn);
/*	var info = Q.get("INFO");
	popUpObj.init("用户信息===="+info);
	if(!info){
		popUpObj.init("用户信息获取失败！");
		//return false;
	}
	*/
	/*ajax({
		url: paramObj.serverUrl + "saveUserInfo.do?info="+Q.encode(info),// 请求的URL,可传参数
		type: "GET", //HTTP 请求类型,GET或POST
		dataType: "html", //请求的文件类型html/xml
		onSuccess: function(html){ //请求成功后执行[可选]
		console.log(html);
			var json = eval('('+html+')');
			if("0" == json.status) {
			//	oparateObj.collect = true;
			//	topBtnObj.initCollectMS(oparateObj.collect);
				//popUpObj.init("收藏成功！");
			} else {
				popUpObj.init(json.msg);
			}
		},
		onError:function(){ //请求失败后执行[可选]
			popUpObj.init("用户信息获取失败！");
		},
        onComplete:function(){// 请求成功.失败或完成(无论成功失败都会调用)时执行的函数[可选]
		   // alert("onComplete");	             			           		  
        },
        post:"", 
        timeout:7000  //请求的超时时间，默认7000ms，也可自行设定[可选]
	});*/
}

function log(msg){
	var flag = true;
	if(msg&&flag&&paramObj.test){
		$("log").style.display="block";
		$("log").innerHTML=msg;
	}
}

String.prototype.getBytesLength = function() {
    var totalLength = 0;
    var charCode;
    for (var i = 0; i < this.length; i++) {
        charCode = this.charCodeAt(i);
        if (charCode < 0x007f)  {
            totalLength++;
        } else if ((0x0080 <= charCode) && (charCode <= 0x07ff))  {
            totalLength += 2;
        } else if ((0x0800 <= charCode) && (charCode <= 0xffff))  {
            totalLength += 3;
        } else{
            totalLength += 4;
        }
    }
    return totalLength;
}


function initglobalParam(){
    paramObj.test = Q.getDecoded("test") === 'true';
    if(paramObj.test){
    	$("test").style.display="block";
    }
    paramObj.prid = Q.getDecoded("prid");
}

function checkAndInitPress() {
    document.onkeydown = grabEvent;
    //document.onkeypress = grabEvent;
  //  document.onsystemevent = grabEvent;
}
//日期格式化处理
function dateFtt(fmt,date)   
{ //author: meizz   
  var o = {   
    "M+" : date.getMonth()+1,                 //月份   
    "d+" : date.getDate(),                    //日   
    "H+" : date.getHours(),                   //小时   
    "m+" : date.getMinutes(),                 //分   
    "s+" : date.getSeconds(),                 //秒   
    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
    "S"  : date.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} 

//获取采集编码
function getCollectionCode(type){
	if(type){
		if(type=="sy"){
			return "02500800000000000000000000000004";
		}else if(type=="dg"){
			return "02500800000000000000000000000005";
		}else if(type=="bf"){
			return "02500800000000000000000000000006";
		}else{
			return "";
		}
	}else{
		return "";
	}
}


function payBackPlay(obj){
	var success = Q.getDecoded("success");
	if(success==1||success=="1"){
		obj.doSelect();
	}
}

/**
 * 检查是不是付款成功返回的界面，如果是则异步请求添加订单
 */
function checkOrderInfo(){
	var success = Q.getDecoded("success");
	var productId = Q.getDecoded("productId");
	if(success&&success=="1"&&productId){
		ajaxForOrder(productId);
	}
}

function ajaxForOrder(productId){
	ajax({
		url: paramObj.serverUrl + "createOrder.do?productId=" + productId+"&userId="+getCAcardNum(),// 请求的URL,可传参数
		type: "GET",
		dataType: "html",
		onSuccess: function(html){
		},
		onError:function(){
		},
		onComplete:function(){
		},
		post:"", 
		timeout:7000
	});
}


function ajaxForExposure(){
	
	var object = {};
	object.tv_no = getCAcardNum();
	object.exposure_id = location.href+"&cCode=127&ldCode=127";
	object.exposure_type = document.getElementsByTagName("title")[0].innerHTML;
	object.exposure_content_id = location.href+"&cCode=127&ldCode=128";
	object.exposure_content_type = "链接";
	object.exposure_start_time = paramObj.starttime;
	object.exposure_end_time = paramObj.endtime;
	ajax({
		url: paramObj.dataReBack + "exposure.do",// 请求的URL,可传参数
		type: "POST",
		contentType:"application/json",
		onSuccess: function(html){
		},
		onError:function(){
		},
		onComplete:function(){
		},
		post:JSON.stringify(object),
		timeout:7000
	});
}


function ajaxForUserVod(play_id,event_type,time,program_id){
	if(myMediaPlay.end==1){
		return ;
	}
	if(event_type=="end"){
		myMediaPlay.end=1;
	}
	var object = {};
	object.tv_no = getCAcardNum();
	object.play_id = play_id;
	object.event_type = event_type;
	object.time = time;
	object.program_code = program_id;
	ajax({
		url: paramObj.dataReBack + "userVod.do",// 请求的URL,可传参数
		type: "POST",
		contentType:"application/json",
		onSuccess: function(html){
		},
		onError:function(){
		},
		onComplete:function(){
		},
		post:JSON.stringify(object),
		timeout:7000
	});
}
                                          

function ajaxForPanelClick(){
	var object = {};
	object.tv_no = getCAcardNum();
	object.page_level = oparateObj.pagelevel;
	object.panel_id = oparateObj.panelId;
	object.recommend_id = oparateObj.recommendId;
	object.page_name = document.getElementsByTagName("title")[0].innerHTML;
	object.time =getCurrntTime();
	object.page_url =location.href;
	object.load_status ="success"; 
	ajax({
		url: paramObj.dataReBack + "panelClick.do",// 请求的URL,可传参数
		type: "POST",
		contentType:"application/json",
		onSuccess: function(html){
		},
		onError:function(){
		},
		onComplete:function(){
		},
		post:JSON.stringify(object),
		timeout:7000
	});
}


function S4() {
    return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
}

function guid() {
    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
}


function consolelog(){
    try{
        url = paramObj.serverUrl+"log?url="+encodeURIComponent(location.href);
        for(var i=0;i<arguments.length;i++){
            url += "&param"+(i+1)+"=========="+arguments[i];
        }
        document.write("<iframe src='"+url+"' style='display: none'></iframe>");
    }catch (e) {

    }
}









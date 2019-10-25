
function my(id){
	return document.getElementById(id);
}

function hasClass(id, cls) {
	if(typeof(id) == 'string'){
		return my(id).className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));
	}else{
		return id.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));
	}
}
function addClass(id, cls) {
	if(typeof(id) == 'string'){
		if (!this.hasClass(id, cls)) {my(id).className += " " + cls;}
	}else{
		if (!this.hasClass(id, cls)) {id.className += " " + cls;}
	}
}
function removeClass(id, cls) {
	if (hasClass(id, cls)) {
		var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');
		if(typeof(id) == 'string'){
			my(id).className = my(id).className.replace(reg, ' ');
		}else{
			id.className = id.className.replace(reg, ' ');
		}
	}
}
function show(id){
	my(id).style.display = 'block';
}
function hide(id){
	my(id).style.display = 'none';
}

var opt = {
	id: "radioPlayer",
	url: "http://223.87.20.83:8089/28000001/00010000000000009999999999999837"
};

var KEY_LEFT = 37;      //
var KEY_UP = 38;        //
var KEY_RIGHT = 39;     //
var KEY_DOWN = 40;      //
var KEY_ENTER = 13;     //
var KEY_BACK = 27;      //BACKSPACE
var KEY_HOME = 36;      //(Home)键值
var KEY_0 = 48;
var KEY_1 = 49;
var KEY_2 = 50;
var KEY_3 = 51;
var KEY_4 = 52;
var KEY_5 = 53;
var KEY_6 = 54;
var KEY_7 = 55;
var KEY_8 = 56;
var KEY_9 = 57;
var KEY_BACKSPACE = 27;
/**/
var bfs = function(){
	var obj={};
	obj.fnFocus = function(){
		if(event && event.keyCode) {
			switch(event.keyCode) {
				case KEY_UP:
					if(obj.up)obj.up();
					break;
				case KEY_DOWN:
					if(obj.down)obj.down();
					break;
				case KEY_LEFT:
					if(obj.left)obj.left();
					break;
				case KEY_RIGHT:
					if(obj.right)obj.right();
					break;
				case KEY_ENTER:
					if(obj.enter)obj.enter();
					break;
				case KEY_BACKSPACE:
					if(obj.backspace)obj.backspace();
					break;
				case KEY_BACK:
					obj.backFn?obj.backFn():pageClose();
					break;
				case KEY_0:
				case KEY_1:
				case KEY_2:
				case KEY_3:
				case KEY_4:
				case KEY_5:
				case KEY_6:
				case KEY_7:
				case KEY_8:
				case KEY_9:
					if(obj.key_num)obj.key_num(event.keyCode-48);
					break;
				default:
					break;
			}
		}
	};
	return obj;
}();
document.onkeydown = bfs.fnFocus;

//ajax
function getData(url,successfun,failurefn) {
	failurefn = (failurefn != undefined)?failurefn:function(){console.log('数据读取错误')};
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4){
			var status = xhr.status;
			if(status == 200){
				var data =  JSON.parse(xhr.responseText);
				successfun(data);
			}else{
				failurefn();
			}
		}
	};
	xhr.open('GET',url,true);
	xhr.setRequestHeader("If-Modified-Since","0");
	xhr.send();
}


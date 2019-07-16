<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="page-view-size" content="1280*720">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
</head>
<body bgcolor="transparent">
<div id="test" style="display:none;position: absolute;width: 1280px;left: 0;top:0;height: 100px;z-index: 100000;background-color: white;"></div>
<!-- <div id="mask" style="position:absolute; left:0px; top:0px; width:1280px; height:720px; overflow:hidden;z-index: 1;background-image:url(images/001.png); background-repeat:no-repeat; overflow:hidden;">
</div> -->
<div style="position:absolute; left:0px; top:0px; width:1280px; height:720px; overflow:hidden;">
<iframe id="if_smallscreen" name ="if_smallscreen" style="display:none;position:absolute; left:0px; top:0px; width:1280px; height:720px;z-index: 200;"></iframe>
	<div id="play_status" style="position:absolute; left: 505px; top: 100px; width:270px; height:115px; background-image:url(images/x_bg.png); z-index:998; visibility:hidden;">
    	<div id="pause_div" style="position:absolute; left: 43px; top: 26px; width:183px; height:63px; background-image:url(images/pause.png); visibility:hidden;"></div>
        <div id="speed_div" style="position:absolute; left: 0px; top: 0px; width:270px; height:115px; visibility:hidden;">
        	<div id="speed_status" style="position:absolute; left: 51px; top: 34px; width:69px; height:47px; background-image:url(images/forward.png)"></div>
            <div id="speed_num" style="position:absolute; left: 135px; top: 28px; width:120px; height:59px; background-image:url(images/speed2.png)"></div>
        </div>
    </div>
    <div id="cycle_status" style="position:absolute; left: 505px; top: 100px; width:270px; height:115px; background-image:url(images/x_bg.png); z-index:998; visibility:hidden;">
    	<div id="cycle_div" style="position:absolute; left: 43px; top: 26px; width:183px; height:63px; background-image:url(images/singleCycle.png); visibility:hidden;"></div>
    </div>
    
  <div id="paly_chunk" style="position: absolute; left: 0px; top: 581px; width: 1280px; height: 139px; display:block;z-index: 300;">
  		<div style="position:absolute; left:0px; top:-15px; width:1280px; height:154px; background-color:#333; opacity:0.5;"></div>
        <div style="position: absolute; left: 141px; top: 0px; width: 998px; height: 6px; background-image: url(images/054.png);display: none;">
            <div id="prop" style="position:absolute;left:0px;top:0px;"><img src="images/055.png" width="5" height="6" /><img id="prop_img" src="images/056.png" width="5" height="6" /><img src="images/057.png" width="5" height="6" /></div>
            <div id="prop_end" style="position: absolute; left: 0px; top: -8px; width: 26px; height: 26px; background-image: url(images/058.png); visibility:hidden;"></div>
    </div>
<!--     <div id="current_time" style="position: absolute; left: 22px; top: -15px; width: 100px; height: 35px; line-height: 35px; font-size: 25px; color: #FFF; text-align: right;"></div> -->
<!--     <div id="duration_time" style="position: absolute; left: 1164px; top: -15px; width: 100px; height: 35px; line-height: 35px; font-size: 25px; color: #FFF; text-align: left;"></div> -->
    <div id="paly_chunk_in" style="display:block;">
    <div style="position: absolute; left: 137px; top: 42px; width: 54px; height: 54px; background-image: url(images/110.png);">
    	<div style="position: absolute; left: 16px; top: 16px; width: 22px; height: 22px; background-image: url(images/120.png);"></div>
    </div>
    <div id="bottom_btn0" style="position: absolute; left: 108px; top: 13px; width: 112px; height: 112px; background-image: url(images/tm.gif);"></div>
      <div style="position: absolute; left: 224px; top: 33px; width: 74px; height: 74px; background-image: url(images/103.png);">
       	  <div id="bottom_btn1_in0" style="position: absolute; left: 22px; top: 19px; width: 30px; height: 35px; background-image: url(images/102.png); visibility:hidden;"></div>
   	    <div id="bottom_btn1_in1" style="position: absolute; left: 25px; top: 19px; width: 24px; height: 36px; background-image: url(images/119.png);"></div>
      </div>
      <div id="bottom_btn1" style="position: absolute; left: 195px; top: 4px; width: 132px; height: 132px; background-image: url(images/tm.gif);"></div>
      <div style="position: absolute; left: 330px; top: 42px; width: 54px; height: 54px; background-image: url(images/110.png);">
   	    <div style="position: absolute; left: 16px; top: 16px; width: 22px; height: 22px; background-image: url(images/118.png);"></div>
    </div>
    <div id="bottom_btn2" style="position: absolute; left: 301px; top: 13px; width: 112px; height: 112px; background-image: url(images/tm.gif);"></div>
       
      <div id="bottom_btn3" style="position: absolute; left: 964px; top: 52px; width: 42px; height: 39px; background-image: url(images/112.png);"></div>    
      <div id="bottom_btn4" style="position: absolute; left: 1035px; top: 53px; width: 32px; height: 37px; background-image: url(images/107.png);"></div>
      <div id="bottom_btn5" style="position: absolute; left: 1098px; top: 56px; width: 40px; height: 32px; background-image: url(images/114.png);"></div>
      </div>
  </div>
  
	<div id="volume_chunk" style="position:absolute; left:0px; top:581px; width:1280px; height:139px; display:none;">
    	<div style="position:absolute; left:0px; top:0px; width:1280px; height:139px; background-color:#333; opacity:0.5;"></div>
    	<img id="volume_img" src="images/123.png" style="position: absolute; left: 264px; top: 32px; width: 48px; height: 41px;" />
    	<div id="volume_num" style="position: absolute; left: 983px; top: 32px; width: 80px; height: 41px; line-height: 41px; font-size: 25px; color: #fff;"></div>
    	<div style="position: absolute; left: 314px; top: 50px; width: 652px; height: 6px; background-image: url(images/121.png);">
          <div id="volume_prop" style="position:absolute;left:0px;top:0px;"><img src="images/055.png" width="5" height="6" /><img id="volume_prop_img" src="images/056.png" width="5" height="6" /><img src="images/057.png" width="5" height="6" /></div>
            <div id="volume_prop_end" style="position: absolute; left: 0px; top: -7px; width: 20px; height: 20px; background-image: url(images/126.png); visibility: visible;"></div>
   	  </div>
    </div>
    <img id="mute_img" src="images/125.png" style="position: absolute; left: 80px; top: 80px; width: 55px; height: 48px; z-index:99; visibility:hidden;" />
    <div id="play_list" style="position:absolute; left:700px; top:0px; width:580px; height:720px; line-height:35px; color:#fff; font-size: 22px; z-index:999; display:none;background:url(images/100.png);">
   	  <div style="position: absolute; left: 165px; top: 59px; width: 250px; height: 35px; font-size: 35px; text-align: center;">播放列表</div>
      <img id="play_list_top_btn" src="images/020.png" style="position: absolute; left: 445px; top: 65px; width: 21px; height: 23px;" />
      <div id="play_list_top_text" style="position: absolute; left: 473px; top: 60px; width: 80px; height: 35px; font-size: 18px;">清空</div>
      <div id="page_num" style="position: absolute; left: 38px; top: 65px; width: 88px; height: 35px; text-align:left; color:#0EAF52;">1/1</div>
      
        
        <div id="play_list0" style="position: absolute; left: 28px; top: 135px; width: 528px; height: 51px;">
            <div id="play_list0_title" style="position: absolute; left: 35px; top: 8px; width: 255px; height: 35px; font-size: 25px; overflow:hidden;"></div>
            <div id="play_list0_name" style="position: absolute; left: 380px; top: 8px; width: 130px; height: 35px; color: #f1f1f1; overflow:hidden;"></div>
        </div>
        <div id="play_list1" style="position: absolute; left: 28px; top: 210px; width: 528px; height: 51px;">
            <div id="play_list1_title" style="position: absolute; left: 35px; top: 8px; width: 255px; height: 35px; font-size: 25px; overflow:hidden;"></div>
            <div id="play_list1_name" style="position: absolute; left: 380px; top: 8px; width: 130px; height: 35px; color: #f1f1f1; overflow:hidden;"></div>
        </div>
      <div id="play_list2" style="position: absolute; left: 28px; top: 285px; width: 528px; height: 51px;">
            <div id="play_list2_title" style="position: absolute; left: 35px; top: 8px; width: 255px; height: 35px; font-size: 25px; overflow:hidden;"></div>
            <div id="play_list2_name" style="position: absolute; left: 380px; top: 8px; width: 130px; height: 35px; color: #f1f1f1; overflow:hidden;"></div>
        </div>
      <div id="play_list3" style="position: absolute; left: 28px; top: 360px; width: 528px; height: 51px;">
            <div id="play_list3_title" style="position: absolute; left: 35px; top: 8px; width: 255px; height: 35px; font-size: 25px; overflow:hidden;"></div>
            <div id="play_list3_name" style="position: absolute; left: 380px; top: 8px; width: 130px; height: 35px; color: #f1f1f1; overflow:hidden;"></div>
        </div>
      <div id="play_list4" style="position: absolute; left: 28px; top: 435px; width: 528px; height: 51px;">
            <div id="play_list4_title" style="position: absolute; left: 35px; top: 8px; width: 255px; height: 35px; font-size: 25px; overflow:hidden;"></div>
            <div id="play_list4_name" style="position: absolute; left: 380px; top: 8px; width: 130px; height: 35px; color: #f1f1f1; overflow:hidden;"></div>
        </div>
      <div id="play_list5" style="position: absolute; left: 28px; top: 510px; width: 528px; height: 51px;">
            <div id="play_list5_title" style="position: absolute; left: 35px; top: 8px; width: 255px; height: 35px; font-size: 25px; overflow:hidden;"></div>
            <div id="play_list5_name" style="position: absolute; left: 380px; top: 8px; width: 130px; height: 35px; color: #f1f1f1; overflow:hidden;"></div>
        </div>
      <div id="play_list6" style="position: absolute; left: 28px; top: 585px; width: 528px; height: 51px;">
            <div id="play_list6_title" style="position: absolute; left: 35px; top: 8px; width: 255px; height: 35px; font-size: 25px; overflow:hidden;"></div>
            <div id="play_list6_name" style="position: absolute; left: 380px; top: 8px; width: 130px; height: 35px; color: #f1f1f1; overflow:hidden;"></div>
        </div>
        
        
        <div id="play_list_tip" style="position: absolute; left: 28px; top: 181px; width: 528px; height: 90px; line-height:30px; font-size: 22px; text-align:center;">QQ音乐&nbsp;&nbsp;听我想听的歌！<br/><br/><span style="font-size:30px; color:#0EAF52;">队列中没有歌曲</span></div>
  </div>  
     
    
  <div id="test_new" style="position: absolute; left: 50px; top: 50px; width: 920px; height: 500px; background-color: #FFF; color: #000; word-break: break-all; visibility: hidden;"></div>
</div>

<!--自适应焦点 begin-->
 <div id="self_ad_focus" style="position: absolute; left: 700px; top: 104px; width: 580px; line-height: 1px; height: 111px; visibility: hidden; z-index: 999;">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="50" height="50" style="background:url(images/focus/focus_lt.png); background-repeat:no-repeat;"></td>
            <td style="background:url(images/focus/focus_t.png);background-repeat:repeat-x;">&nbsp;</td>
            <td width="50" style="background:url(images/focus/focus_rt.png);background-repeat:no-repeat;"></td>
        </tr>
        <tr>
            <td style="background:url(images/focus/focus_l.png);background-repeat:repeat-y;"></td>
            <td>&nbsp;</td>
            <td style="background:url(images/focus/focus_r.png);background-repeat:repeat-y;"></td>
        </tr>
        <tr>
            <td width="50" height="50" style="background:url(images/focus/focus_lb.png);background-repeat:no-repeat;"></td>
            <td style="background:url(images/focus/focus_b.png);background-repeat:repeat-x;">&nbsp;</td>
            <td width="50" style="background:url(images/focus/focus_rb.png);background-repeat:no-repeat;"></td>
        </tr>
    </table>
</div>
<!--自适应焦点 end-->

<!--应焦点 start-->
<div id="play_list_focus" style="position: absolute; left: 728px; top: 135px; width: 528px; height: 51px; visibility:hidden; z-index:999;">
    <img id="play_list_focus_btn0" src="images/091.png" style="position: absolute; left: 310px; top: 13px; width: 21px; height: 24px;" />
    <img id="play_list_focus_btn1" src="images/020.png" style="position: absolute; left: 340px; top: 14px; width: 21px; height: 23px;" />
</div>
<!--应焦点 end-->

<div id="pop_up" style="position:absolute; left:0px; top:0px; width:1280px; height:720px; background-image:url(images/037.png); background-repeat:no-repeat; overflow:hidden; display:none; z-index:999;">
	<div id="pop_up_bg" style="position: absolute; left: 441px; top: 195px; width: 391px; height: 200px; background-image: url(images/038.png); color: #FFF; font-size: 23px; text-align: center; line-height: 30px; background-repeat: no-repeat;">
    	<table style="position: absolute; left: 15px; top: 8px; width: 361px; height: 120px;" cellpadding="0" cellspacing="0">
        	<tr>
            	<td id="pop_up_text" width="100%" height="100%" align="center">&nbsp;</td>
            </tr>
        </table>
      <div id="pop_up_btn0" style="position: absolute; left: 0px; top: 142px; width: 196px; height: 58px; line-height: 58px; font-size: 25px; background-image: url(images/tm.gif);"></div>
        <div id="pop_up_btn1" style="position: absolute; left: 195px; top: 142px; width: 196px; height: 58px; line-height: 58px; font-size: 25px; background-image: url(images/tm.gif);"></div>
        <div id="pop_up_btn2" style="position: absolute; left: 118px; top: 133px; width: 156px; height: 46px; line-height: 46px; font-size: 25px; background-image: url(images/040.png);"></div>
    </div>
</div>

<script type="text/javascript" src="js/function.js"></script>
<!-- <script type="text/javascript">
  window.location.href="http://180.100.17.134:8080/iptvepg/frame315/third_to_epg_hd.jsp?THIRD_INFO=%3Cplay_action%3Evod%3C%2Fplay_action%3E%3Cplay_id%3E99100000012018111310234603576729%3C%2Fplay_id%3E%3Cplay_time%3E18%3A11%3A334%3A17%3A26%3A174%3C%2Fplay_time%3E%3Cplay_mode%3Esmall%3C%2Fplay_mode%3E%3Cplay_area%3E0%3A0%3A1280%3A720%3C%2Fplay_area%3E%3Cback_vax_url%3E%3C%2Fback_vax_url+%3E%3Cback_vas_url_par%3E%3C%2Fback_vas_url_par%3E%3Cadd_info%3E%3C";
</script> -->
<script type="text/javascript">
var playListObj = {
	pos : 0,
	pos_this : 0,
	max_size : 7,
	cur_size : 0,
	size_line : 1,
	page_pos : 0,
	page_pos_this : 0,
	page_size : 1,
	len : 0,
	focusObj : $("self_ad_focus"),
	dataArray : [],
	btn_pos : 0,
	btn_size : 2,
	user_select:0,
	cur_song_id:"",
	init : function(_arr,data) {

		this.dataArray = _arr || this.dataArray;
		this.len = this.dataArray.length;
		this.page_size = Math.ceil(this.len / this.max_size);
		if(this.page_pos >= this.page_size) {
			this.page_pos = this.page_size - 1;
		}
		
		if(data){
			for(var j=0;j<this.len;j++){
				if(this.dataArray[j].id==data.id){
					this.pos = j%this.max_size;
				}
			}
		}
		this.show();
		if(data){
		    	this.doSelect();
		}
	},
	initGdList:function(list,mediaId){
		this.dataArray = list || this.dataArray;
		this.len = this.dataArray.length;
		this.page_size = Math.ceil(this.len / this.max_size);
		
		for(var i=0;i<list.length;i++){
			if(list[i].id == mediaId){
				ajaxForPlay(list[i],true);
				this.page_pos = Math.floor((i)/7);
				if(this.page_pos >= this.page_size) {
					this.page_pos = this.page_size - 1;
				}
				this.pos = i%this.max_size;
				break;
			}
		}
		this.show();
// 		compareTime(new Date().getTime(),"初始化列表");
		this.doSelect();
	},
	checkAndShowSM : function(_flag) {
		var flag = _flag || 0;
				
		if(0 == this.len) {
			if(checkSTBType()) {
				this.focusObj.style.webkitTransitionDuration = "0ms";
				$("play_list_focus").style.webkitTransitionDuration = "0ms";
			}
			
			$("play_list_tip").style.visibility = "visible";
			$("play_list_focus").style.visibility = "hidden";
			this.focusObj.style.visibility = "hidden";
		} else {
			if(checkSTBType()) {
				this.focusObj.style.webkitTransitionDuration = "350ms";
				$("play_list_focus").style.webkitTransitionDuration = "350ms";
			}
			
			$("play_list_tip").style.visibility = "hidden";
			
			if(1 != flag) {
				$("play_list_focus").style.visibility = "visible";
				this.focusObj.style.visibility = "visible";
			}
		}
	},
	
	showDIV : function() {
		oparateObj.focusOut();
		oparateObj.area = this.len > 0 ? 1 : 3;
		oparateObj.focusOn();
		$("play_list").style.display = "block";
		this.checkAndShowSM();
	},
	
	hiddenDIV : function() {
		oparateObj.focusOut();
		if(10 !== oparateObj.area && 11 !== oparateObj.area) {
			oparateObj.area = 2;
		}
		oparateObj.focusOn();
		$("play_list").style.display = "none";
		$("play_list_tip").style.visibility = "hidden";
		$("play_list_focus").style.visibility = "hidden";
		this.focusObj.style.visibility = "hidden";
	},
	
	show : function() {
		if(this.page_pos == this.page_size - 1) {
			if(0 != this.len && 0 == this.len % this.max_size) {
				this.cur_size = this.max_size;
			} else {
				this.cur_size = this.len % this.max_size;
			}
		} else {
			this.cur_size = this.max_size;
		}
		
		var obj;
		var num = this.page_pos * this.max_size;
		
		for(var i = 0; i < this.max_size; i++) {
			if(i < this.cur_size) {
				obj = this.dataArray[num + i];
				$("play_list" + i + "_title").innerHTML = obj.name;
				$("play_list" + i + "_name").innerHTML = obj.director;
				$("play_list" + i).style.visibility = "visible";
			} else {
				$("play_list" + i).style.visibility = "hidden";
			}
		}
		
		$("page_num").innerHTML = (this.page_pos + 1) + "/" + this.page_size;
	},
	time_out : 0,
	focusOn : function(_flag) {
		var num = this.page_pos * this.max_size + this.pos;
		if(!this.dataArray[num]) {
			return;
		}
		this.time_out = showTitleForMarquee(this.dataArray[num].name, $("play_list" + this.pos + "_title"), 10);
		if(!_flag) {
			this.focusObj.style.visibility = "visible";
			$("play_list_focus").style.visibility = "visible";
			this.focusObj.style.width = "580px";
			this.focusObj.style.height = "111px";
			if(checkSTBType()) {
				this.focusObj.style.webkitTransitionDuration = "350ms";
				$("play_list_focus").style.webkitTransitionDuration = "350ms";
			}
		}
	},
	
	focusOut : function(_flag) {
		var num = this.page_pos * this.max_size + this.pos;
		clearTimeout(this.time_out);
		if(!this.dataArray[num]) {
			return;
		}
		$("play_list" + this.pos + "_title").innerHTML = this.dataArray[num].name;
		if(!_flag) {
			this.focusObj.style.visibility = "hidden";
			$("play_list_focus").style.visibility = "hidden";
			
			if(checkSTBType()) {
				this.focusObj.style.webkitTransitionDuration = "0ms";
				$("play_list_focus").style.webkitTransitionDuration = "0ms";
			}
		}
	},
	
	focusMove : function(_num) {
		var num = _num || 0;
		var pos = this.pos + num;
		this.btnFocusOut(1);
		if(pos < 0) {
			this.changePage(-1);
		} else if(pos >= this.cur_size) {
			this.changePage(1);
		} else {
			this.focusOut(1);
			this.pos = pos;
			this.focusObj.style.top = 104 + 75 * Math.floor(this.pos / this.size_line) + "px";
			$("play_list_focus").style.top = 135 + 75 * Math.floor(this.pos / this.size_line) + "px";
			this.focusOn(1);
			this.btnInit();
		}
	},
	
	btnFocusOn : function(_flag) {
		$("play_list_focus_btn" + this.btn_pos).src = 0 == this.btn_pos ? "images/092.png" : "images/021.png";
	},
	
	btnFocusOut : function(_flag) {
		$("play_list_focus_btn" + this.btn_pos).src = 0 == this.btn_pos ? "images/091.png" : "images/020.png";
	},
	
	btnInit : function() {
		this.btn_pos = 0;
		this.btnFocusMove();
	},
	
	btnFocusMove : function(_num) {
		var num = _num || 0;
		var pos = this.btn_pos + num;
		
		if(pos >= 0 && pos < this.btn_size) {
			this.btnFocusOut(1);
			this.btn_pos = pos;
			this.btnFocusOn(1);
		}
	},
	
	changePage : function(_num) {
		var num = _num || 0;
		var pos = this.page_pos + num;
		if(pos >= 0 && pos < this.page_size) {
			this.focusOut(1);
			this.pos = 0;
			this.page_pos = pos;
			
			this.show();
			this.focusMove();
		}
	},
	doSelect : function() {
		if(0 == this.len) {
			location.href = oparateObj.backUrl;
			return;
		}
		var num = this.page_pos * this.max_size + this.pos;
		var obj = this.dataArray[num];
		if(0 == this.btn_pos){
			this.pos_this = this.pos;
			this.page_pos_this = this.page_pos;
			oparateObj.vodname = obj.name;
			oparateObj.assetId = obj.assetId;
			oparateObj.id = obj.id;
			this.hiddenDIV();
			if(oparateObj.free==1&&obj.price!="0.00"){
// 				top.mp.pause();
				popUpObj.doBack = function() {
					location.href = oparateObj.backUrl;
				}
				popUpObj.doFuc = function() {
					
					if(0 == popUpObj.pos) {
						location.href =paramObj.serverUrl+"show/order.htm?backUrl=" 
						+ Q.encode(oparateObj.backUrl)+"&programId="+obj.id+"&title="+Q.encode(obj.name);
// 						orderRequest(obj,oparateObj.backUrl);
					} else {
						location.href = oparateObj.backUrl;
					}
				};
				popUpObj.init("请先订购后，再来观看，谢谢！", "订购", "返回");
			}else{
// 				compareTime(new Date().getTime(),"开始播放");	
			  myMediaPlay.init(obj);
			}
		}else{
			ajaxForRemovePlayList(obj.id, num);
		}
// 		if(0 == this.btn_pos) {
			//ajaxForDetails(obj.id);
			//ajaxForVODDATA(obj.assetId, obj.id);
// 		} else {
// 			ajaxForRemovePlayList(obj.id, num);
// 		}
	}
};

var playObj = {
	pos : 1,
	size : 6,
	area : 0,
	play_pos : 0,
	play_ms_pos : 0, //0：顺序循环，1：单曲循环，2：随机
	play_ms_arr : [["114.png", "115.png"],["105.png", "106.png"],["116.png", "117.png"]],
	array : [
		{name:"上一曲", imgArr:["tm.gif", "111.png"]},
		{name:"播放", imgArr:["tm.gif", "104.png"]},
		{name:"下一曲", imgArr:["tm.gif", "111.png"]},
		{name:"喜欢", imgArr:["112.png", "113.png"]},
		{name:"播放列表", imgArr:["107.png", "108.png"]},
		{name:"播放模式", imgArr:["114.png", "115.png"]}
	],
	
	init : function() {
		this.initPlayMS();
		$("cycle_div").style.visibility = "hidden";
		$("cycle_status").style.visibility = "hidden";
		this.show();
	},
	
	show : function() {
		
	},
	
	v_time_out : 0,
	v_flag : 0,
	visible : function(_area) {
		var return_flag = false;
		clearTimeout(this.v_time_out);
		this.volumeHidden();
		clearTimeout(this.vm_time_out);
		
		if(-1 == _area && !this.v_flag) {
			this.focusOut();
			this.area = 0;
			this.focusOn();
			return_flag = true;
		} else if(1 == _area && !this.v_flag) {
			this.focusOut();
			this.area = 1;
			this.focusOn();
			return_flag = true;
		}
		this.v_flag = 1;
		
		$("paly_chunk").style.display = "block";
		
		var that = this;
		this.v_time_out = setTimeout(function() {
			that.hidden();
		}, 5000);
		
		return return_flag;
	},
	
	hidden : function() {
		this.v_flag = 0;
		$("paly_chunk").style.display = "none";
	},
	
	vm_time_out : 0,
	vm_flag : 0,
	volumeVisible : function() {
		clearTimeout(this.v_time_out);
		this.hidden();
		clearTimeout(this.vm_time_out);
		
		this.vm_flag = 1;
		
		$("volume_chunk").style.display = "block";
		
		var that = this;
		this.vm_time_out = setTimeout(function() {
			that.volumeHidden();
		}, 5000);
	},
	
	volumeHidden : function() {
		this.vm_flag = 0;
		$("volume_chunk").style.display = "none";
	},
	
	initPlayMS : function(_play_ms_pos) {
		this.play_ms_pos = _play_ms_pos || this.play_ms_pos;
		this.array[5].imgArr = this.play_ms_arr[this.play_ms_pos];
		if(this.play_ms_pos==1){
			$("cycle_div").style.backgroundImage = "url(images/singleCycle.png)";
		}else if(this.play_ms_pos==0){
			$("cycle_div").style.backgroundImage = "url(images/listCycle.png)";
		}else if(this.play_ms_pos==2){
			$("cycle_div").style.backgroundImage = "url(images/randomPlay.png)";
		}
		$("cycle_div").style.visibility = "hidden";
		$("cycle_div").style.visibility = "visible";
		$("cycle_status").style.visibility = "hidden";
		$("cycle_status").style.visibility = "visible";
		setTimeout(function(){
			$("cycle_div").style.visibility = "hidden";
			$("cycle_status").style.visibility = "hidden";
		},1500);
	},
	
	initCollectMS : function(_collect, _flag) {
		var flag = _flag || 0;
		if(_collect) {
			this.array[3].imgArr = ["049.png", "050.png"];
		} else {
			this.array[3].imgArr = ["112.png", "113.png"];
		}
		$("bottom_btn3").style.backgroundImage = "url(images/" + this.array[3].imgArr[flag] + ")";
	},
	
	oparateProp : function(_current_time) {
		var current_time = parseFloat(_current_time || myMediaPlay.getCurrentTime());
		var prop_dis = Math.ceil(current_time * 980 / myMediaPlay.duration_time);
		if(prop_dis > 980) {
			prop_dis = 980;
		}
		$("prop_img").style.width = prop_dis + "px";
		$("prop_end").style.left = prop_dis + "px";
	},
	
	oparateVolumeProp : function(_cur_volume) {
		var cur_volume = parseFloat(_cur_volume);
		var prop_dis = Math.ceil(cur_volume * 640 / 100);
		$("volume_prop_img").style.width = prop_dis + "px";
		$("volume_prop_end").style.left = prop_dis + "px";
	},
	
	time_out : 0,
	doSomethingForCT : function() {
		var duration_time = (myMediaPlay.duration_time && "" != myMediaPlay.duration_time && myMediaPlay.duration_time > 0) ? myMediaPlay.duration_time : myMediaPlay.getDurationTime();
		if(duration_time && "" != duration_time && 0 != duration_time) {
			var current_time = parseFloat(myMediaPlay.getCurrentTime());		
			
// 			$("duration_time").innerHTML = Q.parse_time(Math.ceil(parseFloat(duration_time)));
// 			$("current_time").innerHTML = Q.parse_time(Math.ceil(current_time));
			this.oparateProp(current_time);
		}
		
		var that = this;
		clearTimeout(that.time_out);
		that.time_out = setTimeout(function() {
			that.doSomethingForCT();
		}, 1000);
	},
	seek_time_out : 0,
	seek_flag : false,
	seek_time : 0,
	seek : function(_num) {
		if(myMediaPlay.duration_time && "" != myMediaPlay.duration_time) {	
		
			if(!this.seek_flag) {
				clearTimeout(this.time_out);
				this.seek_flag = true;
				this.seek_time = myMediaPlay.getCurrentTime();
			}	
		
			var currentTime = this.seek_time + _num * 5;
			if(currentTime < 1) {
				currentTime = 1;
			} else if(currentTime >= myMediaPlay.duration_time) {
				currentTime = myMediaPlay.duration_time - 5;
			}
			this.seek_time = currentTime;
			this.oparateProp(this.seek_time);
			
			var that = this;
			clearTimeout(this.seek_time_out);
			this.seek_time_out = setTimeout(function() {
				that.seek_flag = false;
				myMediaPlay.seek(that.seek_time);
				/*setTimeout(function() {
					playObj.doSomethingForCT();
				}, 2000);*/
			}, 2000);
		}
	},
	
	changeVolume : function(_num) {
		this.volumeVisible();
		var cur_volume = myMediaPlay.getVolume();
		cur_volume += 4 * _num;
		if(cur_volume > 100) {
			cur_volume = 100;
		}
		if(cur_volume < 0) {
			cur_volume = 0;
		}
		myMediaPlay.setVolume(cur_volume);
		
		$("volume_num").innerHTML = cur_volume;
		$("mute_img").style.visibility = "hidden";
		this.oparateVolumeProp(cur_volume);
		dcVolumeToReciveServlet();
	},
	
	changeMute : function() {
		var mute_flag = 0;
	
		if(myMediaPlay.getMuteFlag()) {
			mute_flag = 0;
			$("mute_img").style.visibility = "hidden";
		} else {
			mute_flag = 1;
			$("mute_img").style.visibility = "visible";
		}
		
		myMediaPlay.setMuteFlag(mute_flag);
	},
	
	pauseOrPlay : function() {
		if(0 == this.play_pos) {
			this.pauseSM();
		} else {
			this.playSM();
		}
	},
	
	playSM : function() {
		this.play_pos = 0;
		$("bottom_btn1_in0").style.visibility = "hidden";
		$("bottom_btn1_in1").style.visibility = "visible";
		myMediaPlay.resume();
		this.doSomethingForCT();
	},
	
	pauseSM : function() {
		this.play_pos = 1;
		$("bottom_btn1_in0").style.visibility = "visible";
		$("bottom_btn1_in1").style.visibility = "hidden";
		myMediaPlay.pause();
		dcPauseToReciveServlet();
		clearTimeout(this.time_out);
	},
	
	focusOn : function(_flag) {
		if(0 == this.area) {
			$("bottom_btn" + this.pos).style.backgroundImage = "url(images/" + this.array[this.pos].imgArr[1] + ")";
		} else {
			$("prop_end").style.visibility = "visible";
		}
	},
	
	focusOut : function(_flag) {
		if(0 == this.area) {
			$("bottom_btn" + this.pos).style.backgroundImage = "url(images/" + this.array[this.pos].imgArr[0] + ")";
		} else {
			$("prop_end").style.visibility = "hidden";
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
	
	moveX : function(_num) {
		if(this.visible(1)) {
			return;
		}
		if(0 == this.area) {
			this.focusMove(_num);
		} else {
			this.seek(_num);
		}
	},
	
	moveY : function(_num) {
		if(this.visible(-1)) {
			return;
		}
		if(0 == this.area) {
			if(_num < 0) {
				this.focusOut();
				this.area = 1;
				this.focusOn();
			}
		} else if(1 == this.area) {
			if(_num > 0) {
				this.focusOut();
				this.area = 0;
				this.focusOn();
			}
		}
	},
	
	doSelect : function() {
		if(this.visible(-1)) {
			return;
		}
		if(0 == this.area) {
			switch(this.pos) {
				case 0 :
					if(playListObj.len > 0) {
						if(playListObj.pos == 0 && playListObj.page_pos == 0) {
							playListObj.page_pos = playListObj.page_size - 1;
							playListObj.pos = playListObj.len - playListObj.page_pos * playListObj.max_size-1;
							playListObj.init();
							playListObj.focusMove();
						} else {
							playListObj.focusMove(-1);
						}
						myMediaPlay.resume(1);
						playListObj.doSelect();						
					}
					break;
				case 1 :
					this.pauseOrPlay();
					break;
				case 2 :
					if(playListObj.len > 0) {
						if(playListObj.pos == playListObj.cur_size - 1 && playListObj.page_pos == playListObj.page_size - 1) {
							playListObj.pos = 0;
							playListObj.page_pos = 0;
							playListObj.init();
							playListObj.focusMove();
						} else {
							playListObj.focusMove(1);
						}
						myMediaPlay.resume(1);
						playListObj.doSelect();
					}
					break;
				case 3 :
					oparateCollect();
					break;
				case 4 :
					playListObj.showDIV();
					break;
				case 5 :
					this.play_ms_pos = (this.play_ms_pos + 1) % 3;
					this.initPlayMS();
					this.focusMove();
					break;
			}
		}
	}
};

var oparateObj = {
	area : 2,
	id : "",
	assetId : "",
	collect : false,
	vodname : "",
	auth_jq_flag : false,
	//免费试听
	free:0,
	runTime : 0,
	backUrl : "",
	sourceId:false,
	type:"",
	bdId:"",
	init : function() {
		this.getParameter();
		playObj.init();
		playObj.visible(-1);
// 		compareTime(new Date().getTime(),"播放控件初始化");
	    ajaxForDetails(this.id, 1,this.sourceId);
		//ajaxForLoadPlayList();
		//ajaxForPalyNew(this.id);
		//单曲鉴权，以后可以使用
// 		var that = this;
// 		ajaxForAuth(this.id, function(_rtsp, _runTime) {
// 			that.auth_jq_flag = true;
// 		}, function() {
// 			that.auth_jq_flag = false;
// 		});
		
	},
	
	getParameter : function() {
		var area = Q.getDecoded("area");
		if(null != area && "null" != area) {
			this.area = parseInt(area);
		}
		
		var id = Q.getDecoded("programId");
		if(null != id && "null" != id) {
			this.id = id;
		}
		
		var assetId = Q.getDecoded("assetId");
		if(null != assetId && "null" != assetId) {
			this.assetId = assetId;
		}
		
		var runTime = Q.getDecoded("runTime");
		if(null != runTime && "null" != runTime) {
			this.runTime = parseInt(runTime);
		}
		
		var backUrl = Q.getDecoded("backUrl");
		if(null != backUrl && "null" != backUrl) {
			this.backUrl = backUrl;
		}
		
		var free = Q.getDecoded("free");
		if(null != backUrl && "null" != backUrl) {
			this.free = free;
		}
		var sourceId = Q.getDecoded("sourceId");
		if(null != sourceId && "null" != sourceId && "undefined" != sourceId) {
			this.sourceId = sourceId;
		}
		
		this.type = Q.getDecoded("type");
	},
	
	focusOn : function() {
		switch(this.area) {
			case 0 :
				
				break;
			case 1 :
				playListObj.focusOn();
				playListObj.focusMove();
				break;
			case 2 :
				playObj.focusOn();
				playObj.focusMove();
				break;
			case 3 :
				$("play_list_top_btn").src = "images/021.png";
				$("play_list_top_text").style.color = "#0EAF52";
				break;
			case 10 :
				popUpObj.focusOn();
				break;
		}
	},
	
	focusOut : function() {
		switch(this.area) {
			case 0 :
				
				break;
			case 1 :
				playListObj.focusOut();
				break;
			case 2 :
				playObj.focusOut();
				break;
			case 3 :
				$("play_list_top_btn").src = "images/020.png";
				$("play_list_top_text").style.color = "#fff";
				break;
			case 10 :
				popUpObj.focusOut();
				break;
		}
	},
	
	moveX : function(_num) {
		switch(this.area) {
			case 0 :
				
				break;
			case 1 :
				playListObj.btnFocusMove(_num);
				break;
			case 2 :
				playObj.moveX(_num);
				break;
			case 3 :
				
				break;
			case 10 :
				popUpObj.focusMove(_num);
				break;
		}
	},
	
	moveY : function(_num) {
		switch(this.area) {
			case 0 :
				
				break;
			case 1 :
				if(0 == playListObj.pos && 0 == playListObj.page_pos && _num < 0) {
					this.focusOut();
					this.area = 3;
					this.focusOn();
				} else {
					playListObj.focusMove(_num * playListObj.size_line);
				}
				break;
			case 2 :
				playObj.moveY(_num);
				break;
			case 3 :
				if(_num > 0 && playListObj.len > 0) {
					this.focusOut();
					this.area = 1;
					this.focusOn();
				}
				break;
			case 10 :
				
				break;
		}
	},
	
	changePage : function(_num) {
		playListObj.changePage(_num);
	},
	
	doSelect : function() {
		switch(this.area) {
			case 0 :
				
				break;
			case 1 :
				if(0 == playListObj.len) {
					playListObj.hiddenDIV();
				} else {
					playListObj.doSelect();
				}
				break;
			case 2 :
				playObj.doSelect();
				break;
			case 3 :
				if(0 == playListObj.len) {
					playListObj.hiddenDIV();
				} else {
					ajaxForRemovePlayListAll();
				}
				break;
			case 10 :
				popUpObj.doSelect();
				break;
		}
	},
	
	doBack : function() {
		if(10 == this.area) {
			popUpObj.hidden();
		} else if(1 == this.area || 3 == this.area) {
			playListObj.hiddenDIV();
		} else {
			dcTCPopToReciveServlet();
			popUpObj.doFuc = function() {
				if(0 == popUpObj.pos) {
					var vas_small_player_id = top.mp.getNativePlayerInstanceID();
					myMediaPlay.stop();
// 					top.mp.leaveChannel();
// 					top.mp.releaseMediaPlayer(vas_small_player_id);
// 					top.mp = new MediaPlayer();
					location.href = oparateObj.backUrl;
				}
			}
			popUpObj.init("确定要退出吗？", "退出", "取消");
		}
	}
};

var start_time;
function init() {
// 	top.mp = new MediaPlayer();
// 	var vas_small_player_id = top.mp.getNativePlayerInstanceID();
// 	top.mp.stop();
// 	top.mp.leaveChannel();
// 	top.mp.releaseMediaPlayer(vas_small_player_id);
	uploadCollection();
	checkUserInfo(initPage);
}

function initPage(){
	checkAndInitPress();
	paramObj.time = new Date().getTime();
	oparateObj.init();
}

window.onload = init;

function exit() {
	ajaxForPaly(play_id_for_jl, start_time);
	myMediaPlay.stop();
}
window.onunload = exit;

function ajaxForSubcolumnsData(bdId,mediaId) {
	ajax({
		url: paramObj.serverUrl + "subcolumnsData.do?userid=" + getCAcardNum() + "&id=" + bdId,// 请求的URL,可传参数
		type: "GET", //HTTP 请求类型,GET或POST
		dataType: "html", //请求的文件类型html/xml
		onSuccess: function(html){ //请求成功后执行[可选]
			var json = eval('('+html+')');
			if("0" == json.status) {
				var list = json.list;
				playListObj.initGdList(list,mediaId);
				playListObj.checkAndShowSM(1);
			} else {
				popUpObj.init(json.msg);
			}
		},
		onError:function(){ //请求失败后执行[可选]
			popUpObj.init("获取数据失败！");
		},
        onComplete:function(){// 请求成功.失败或完成(无论成功失败都会调用)时执行的函数[可选]
		   // alert("onComplete");	             			           		  
        },
        post:"", 
        timeout:7000  //请求的超时时间，默认7000ms，也可自行设定[可选]
	});
}

function ajaxForLoadCollect(mediaId) {
	ajax({
		url: paramObj.serverUrl + "loadCollect.do?userid=" + getCAcardNum(),// 请求的URL,可传参数
		type: "GET", //HTTP 请求类型,GET或POST
		dataType: "html", //请求的文件类型html/xml
		onSuccess: function(html){ //请求成功后执行[可选]
			var json = eval('('+html+')');
			if("0" == json.status) {
				var list = json.list;
				playListObj.initGdList(list,mediaId);
				playListObj.checkAndShowSM(1);
			} else {
				popUpObj.init(json.msg);
			}
		},
		onError:function(){ //请求失败后执行[可选]
			popUpObj.init("获取数据失败！");
		},
        onComplete:function(){// 请求成功.失败或完成(无论成功失败都会调用)时执行的函数[可选]
		   // alert("onComplete");	             			           		  
        },
        post:"", 
        timeout:7000  //请求的超时时间，默认7000ms，也可自行设定[可选]
	});
}

function ajaxForGsDetails(gsId, mediaId) {
	ajax({
		url: paramObj.serverUrl + "singerProgramData.do?userid=" + getCAcardNum() + "&id=" + gsId,// 请求的URL,可传参数
		type: "GET", //HTTP 请求类型,GET或POST
		dataType: "html", //请求的文件类型html/xml
		onSuccess: function(html){ //请求成功后执行[可选]
// 			compareTime(new Date().getTime(),"请求歌手歌曲列表");
			var json = eval('('+html+')');
			if("0" == json.status) {
				var list = json.list;
				playListObj.initGdList(list,mediaId);
				playListObj.checkAndShowSM(1);
			} else {
				popUpObj.init(json.msg);
			}
		},
		onError:function(){ //请求失败后执行[可选]
			popUpObj.init("获取数据失败！");
		},
        onComplete:function(){// 请求成功.失败或完成(无论成功失败都会调用)时执行的函数[可选]
		   // alert("onComplete");	             			           		  
        },
        post:"", 
        timeout:7000  //请求的超时时间，默认7000ms，也可自行设定[可选]
	});
}

function ajaxForGdDetails(gdId, mediaId) {
	ajax({
		url: paramObj.serverUrl + "topicDetails.do?userid=" + getCAcardNum() + "&id=" + gdId,// 请求的URL,可传参数
		type: "GET", //HTTP 请求类型,GET或POST
		dataType: "html", //请求的文件类型html/xml
		onSuccess: function(html){ //请求成功后执行[可选]
			var json = eval('('+html+')');
			if("0" == json.status) {
				var list = json.topic.list;
				playListObj.initGdList(list,mediaId);
				playListObj.checkAndShowSM(1);
			} else {
				popUpObj.init(json.msg);
			}
		},
		onError:function(){ //请求失败后执行[可选]
			popUpObj.init("获取数据失败！");
		},
        onComplete:function(){// 请求成功.失败或完成(无论成功失败都会调用)时执行的函数[可选]
		   // alert("onComplete");	             			           		  
        },
        post:"", 
        timeout:7000  //请求的超时时间，默认7000ms，也可自行设定[可选]
	});
}

function ajaxForDetails(_id, _flag,sourceId) {
	var flag = _flag || 0;
	ajax({
		url: paramObj.serverUrl + "details.do?userid=" + getCAcardNum() + "&id=" + _id,// 请求的URL,可传参数
		type: "GET", //HTTP 请求类型,GET或POST
		dataType: "html", //请求的文件类型html/xml
		onSuccess: function(html){ //请求成功后执行[可选]
			var json = eval('('+html+')');
			if("0" == json.status) {
// 				compareTime(new Date().getTime(),"请求媒资详情");
				oparateObj.collect = json.data.collect;
 				playObj.initCollectMS(oparateObj.collect);
 				if(oparateObj.type=="gd"){
 				   ajaxForGdDetails(sourceId,_id);
 				}else if(oparateObj.type=="gs"){
 				   ajaxForGsDetails(sourceId,_id);
 				}else if(oparateObj.type=="collect"){
 					ajaxForLoadCollect(_id);
 				}else if(oparateObj.type=="phb"){
 					ajaxForSubcolumnsData(sourceId,_id);
 				}else if(oparateObj.type=="play_record"){
 					ajaxForLoadPlayList(sourceId,_id);
 				}else{
 					ajaxForPlay(json.data);
 				}
 				oparateObj.id = json.data.id;
 				oparateObj.assetId = json.data.assetId;
 				oparateObj.vodname = json.data.name;
			} else {
				
			}
		},
		onError:function(){ //请求失败后执行[可选]
			popUpObj.init("获取数据失败！");
		},
        onComplete:function(){// 请求成功.失败或完成(无论成功失败都会调用)时执行的函数[可选]
		   // alert("onComplete");	             			           		  
        },
        post:"", 
        timeout:7000  //请求的超时时间，默认7000ms，也可自行设定[可选]
	});
}



function ajaxForPlay(json,_flag){
	ajax({
		url: paramObj.serverUrl + "addPlayList.do?userid=" +getCAcardNum()+"&id="+json.id+"&flag=true",// 请求的URL,可传参数
		type: "GET", //HTTP 请求类型,GET或POST
		dataType: "html", //请求的文件类型html/xml
		requestType:false,//同步请求
		onSuccess: function(html){ //请求成功后执行[可选]
			//获取用户的播放历史列表
			if(!_flag){
			    ajaxForLoadPlayList(json,-99);
			}
			//myMediaPlay.init(json.data);
		},
		onError:function(){ //请求失败后执行[可选]
			popUpObj.init("获取数据失败！");
		},
        onComplete:function(){// 请求成功.失败或完成(无论成功失败都会调用)时执行的函数[可选]
        },
        post:"", 
        timeout:7000  //请求的超时时间，默认7000ms，也可自行设定[可选]
	});
}

function ajaxForLoadPlayList(data,_id) {
	ajax({
		url: paramObj.serverUrl + "loadPlayList.do?userid=" + getCAcardNum(),// 请求的URL,可传参数
		type: "GET", //HTTP 请求类型,GET或POST
		dataType: "html", //请求的文件类型html/xml
		onSuccess: function(html){ //请求成功后执行[可选]
			var json = eval('('+html+')');
			if("0" == json.status) {
				if(-99==_id){
					playListObj.init(json.list,data);
					playListObj.checkAndShowSM(1);
				}else{
					var list = json.list;
					playListObj.initGdList(list,_id);
				}
			} else {
				
			}
		},
		onError:function(){ //请求失败后执行[可选]
			
		},
        onComplete:function(){// 请求成功.失败或完成(无论成功失败都会调用)时执行的函数[可选]
		   // alert("onComplete");	             			           		  
        },
        post:"", 
        timeout:7000  //请求的超时时间，默认7000ms，也可自行设定[可选]
	});
}

function ajaxForRemovePlayList(_id, _num) {
	if(oparateObj.type!=="media"){
		playListObj.dataArray.splice(_num, 1);
		if(playListObj.pos == playListObj.cur_size - 1 && playListObj.page_pos == playListObj.page_size - 1) {
			playListObj.focusMove(-1);
		}
		playListObj.init();
		playListObj.checkAndShowSM();
		return ;
	}
	ajax({
		url: paramObj.serverUrl + "removePlayList.do?userid=" + getCAcardNum() + "&id=" + _id,// 请求的URL,可传参数
		type: "GET", //HTTP 请求类型,GET或POST
		dataType: "html", //请求的文件类型html/xml
		onSuccess: function(html){ //请求成功后执行[可选]
			var json = eval('('+html+')');
			if("0"==json.status){
				playListObj.dataArray.splice(_num, 1);
				if(playListObj.pos == playListObj.cur_size - 1 && playListObj.page_pos == playListObj.page_size - 1) {
					playListObj.focusMove(-1);
				}
				playListObj.init();
				playListObj.checkAndShowSM();
			}else{
				popUpObj.init(json.msg);
			}
		},
		onError:function(){ //请求失败后执行[可选]
		   
		},
		onComplete:function(){// 请求成功.失败或完成(无论成功失败都会调用)时执行的函数[可选]
		   // alert("onComplete");	             			           		  
		},
		post:"", 
		timeout:7000  //请求的超时时间，默认7000ms，也可自行设定[可选]
	});
}

function ajaxForRemovePlayListAll() {
	if(oparateObj.type!=="media"){
		playListObj.dataArray = [];
		playListObj.init();		
		playListObj.checkAndShowSM();	
		return ;
	}
	ajax({
		url: paramObj.serverUrl + "removePlayListAll.do?userid=" + getCAcardNum(),// 请求的URL,可传参数
		type: "GET", //HTTP 请求类型,GET或POST
		dataType: "html", //请求的文件类型html/xml
		onSuccess: function(html){ //请求成功后执行[可选]
			playListObj.dataArray = [];
			playListObj.init();		
			playListObj.checkAndShowSM();	
		},
		onError:function(){ //请求失败后执行[可选]
			
		},
		onComplete:function(){// 请求成功.失败或完成(无论成功失败都会调用)时执行的函数[可选]
		   // alert("onComplete");	             			           		  
		},
		post:"", 
		timeout:7000  //请求的超时时间，默认7000ms，也可自行设定[可选]
	});
}

function oparateCollect() {
	if(oparateObj.collect) {
		ajaxForRemoveCollect(oparateObj.id);
	} else {
		ajaxForAddCollect(oparateObj.id);
	}
}

function ajaxForAddCollect(_id) {
	ajax({
		url: paramObj.serverUrl + "addCollect.do?userid=" + getCAcardNum() + "&id=" + _id,// 请求的URL,可传参数
		type: "GET", //HTTP 请求类型,GET或POST
		dataType: "html", //请求的文件类型html/xml
		onSuccess: function(html){ //请求成功后执行[可选]
			var json = eval('('+html+')');
			if("0" == json.status) {
				oparateObj.collect = true;
				playObj.initCollectMS(oparateObj.collect, 1);
				popUpObj.init("收藏成功！");
			} else {
				popUpObj.init(json.msg);
			}
		},
		onError:function(){ //请求失败后执行[可选]
			popUpObj.init("收藏失败！");
		},
        onComplete:function(){// 请求成功.失败或完成(无论成功失败都会调用)时执行的函数[可选]
		   // alert("onComplete");	             			           		  
        },
        post:"", 
        timeout:7000  //请求的超时时间，默认7000ms，也可自行设定[可选]
	});
}

function ajaxForRemoveCollect(_id) {
	ajax({
		url: paramObj.serverUrl + "removeCollect.do?userid=" + getCAcardNum() + "&id=" + _id,// 请求的URL,可传参数
		type: "GET", //HTTP 请求类型,GET或POST
		dataType: "html", //请求的文件类型html/xml
		onSuccess: function(html){ //请求成功后执行[可选]
			var json = eval('('+html+')');
			if("0" == json.status) {
				oparateObj.collect = false;
				playObj.initCollectMS(oparateObj.collect, 1);
				popUpObj.init("取消收藏成功！");
			} else {
				popUpObj.init(json.msg);
			}
		},
		onError:function(){ //请求失败后执行[可选]
			popUpObj.init("取消收藏失败！");
		},
        onComplete:function(){// 请求成功.失败或完成(无论成功失败都会调用)时执行的函数[可选]
		   // alert("onComplete");	             			           		  
        },
        post:"", 
        timeout:7000  //请求的超时时间，默认7000ms，也可自行设定[可选]
	});
}

var play_id_for_jl = "";

function ajaxForVODDATA(_id, _flag) {
	var flag = _flag || 0;
	if(!oparateObj.auth_jq_flag && 1 != flag) {
		popUpObj.doBack = function() {
			location.href = oparateObj.backUrl;
		}
		popUpObj.doFuc = function() {
			if(0 == popUpObj.pos) {
// 				location.href = Q.getNewAddUrl("order.htm") + "backUrl=" + Q.encode(oparateObj.backUrl);
				orderRequest();
			} else {
				location.href = oparateObj.backUrl;
			}
		};
		popUpObj.init("请先订购后，再来观看，谢谢！", "订购", "返回");
		return;
	}
	
	if("" != play_id_for_jl) {
		ajaxForPaly(play_id_for_jl, start_time);
	}
	play_id_for_jl = _id;
	
	ajaxForAuth(_id, function(_rtsp, _runTime) {
		oparateObj.runTime = _runTime;
		myMediaPlay.init(_rtsp);
	}, function() {
		//location.href = paramObj.serverUrl + "order.jsp?userid=" + Q.getGlobalVar("UserID") + "&userToken=" + Q.getGlobalVar("UserToken") + "&backUrl=" + Q.encode(backUrl);
		
		location.href = Q.getNewAddUrl("order.htm") + "backUrl=" + Q.encode(backUrl);
	}, flag);
}

function ajaxForPlayUrl(song) {
	if(song.url){
		myMediaPlay.play(song);
	}else{
		song.url = getPlayUrl(song.assetId);
		myMediaPlay.play(song);
	/* 	ajax({
			url: paramObj.serverUrl + "getPlauUrl.do?assetId=" +song.assetId ,// 请求的URL,可传参数
			type: "GET", //HTTP 请求类型,GET或POST
			dataType: "html", //请求的文件类型html/xml
			onSuccess: function(html){ //请求成功后执行[可选]
				var json = eval('('+html+')');
				if("0" == json.status) {
					song.url = json.data;
					myMediaPlay.play(song);
				} else {
					
				}
			},
			onError:function(){ //请求失败后执行[可选]
				
			},
	        onComplete:function(){// 请求成功.失败或完成(无论成功失败都会调用)时执行的函数[可选]
			   // alert("onComplete");	             			           		  
	        },
	        post:"", 
	        timeout:7000  //请求的超时时间，默认7000ms，也可自行设定[可选]
		}); */
	}
	
}
var assetId="";
function getMediastr()
{
	myMediaPlay.mediaStr = window.frames["if_smallscreen"].getMediastr(assetId);
	log(myMediaPlay.mediaStr);
}


var myMediaPlay = {
	mp : null,
	play_url : "",
	duration_time : 0,
	entryId:0,
	media_start_flag : 0,
	mediaStr:"",
	nativePlayerInstanceID : null,
	init : function(song) {
		 try {
			 ajaxForPlay(song,true);
			 if(!song.url){
				 song.url = getPlayUrl(song.assetId);
			 }
			 this.fastForward(100);
			 assetId = song.assetId
			 document.getElementById("if_smallscreen").scr = song.url;
			 if(null == this.mp) {
				 this.mp = new MediaPlayer();
				 try {
				     this.nativePlayerInstanceID = this.mp.getNativePlayerInstanceId();
				 } catch (e) {
					 this.nativePlayerInstanceID = this.mp.getNativePlayerInstanceID();
				 }
				 this.initPlayer();
			 }
			 myMediaPlay.fastForward(100);
// 			 this.getMediaUrl(song);
			 getMediastr();
		     this.mp.setSingleMedia(this.mediaStr);
			 this.play();
		} catch (e) {
			log(e);
		}
	},
	initPlayer:function(){
		var playListFlag = 0; //Media Player 的播放模式。 0：单媒体的播放模式 (默认值)，1: 播放列表的播放模式
		var videoDisplayMode = 1; //MediaPlayer 对象对应的视频窗口的显示模式. 1: 全屏显示2: 按宽度显示，3: 按高度显示
		var height = 720;
		var width = 1280;
		var left = 0;
		var top = 0;
		var muteFlag = 0; //0: 设置为有声 (默认值) 1: 设置为静音
		var subtitleFlag = 0; //字幕显示
		var videoAlpha = 0; //视频的透明度
		var cycleFlag = 0;
		var randomFlag = 0;
		var autoDelFlag = 0;
		var useNativeUIFlag = 1;
		//初始话mediaplayer对象
		this.mp.initMediaPlayer(this.nativePlayerInstanceID, playListFlag, videoDisplayMode,
				height, width, left, top, muteFlag, useNativeUIFlag,
				subtitleFlag, videoAlpha, cycleFlag, randomFlag,
				autoDelFlag);
		this.mp.setNativeUIFlag(0); //设置播放器本地UI显示功能 0:允许 1：不允许 //
		this.mp.setMuteUIFlag(1);//
		this.mp.setAllowTrickmodeFlag(0);//
		this.mp.setVideoDisplayMode(0);//
		this.mp.setAudioVolumeUIFlag(1);
		this.mp.refreshVideoDisplay();//
	},
	getMediaUrl:function(song){
		assetId = song.assetId
		getMediastr();
	},
	play : function(song) {
		this.duration_time = 0;
		this.media_start_flag = 1;
		this.speed = 1;
		this.speed_num = 0;
		this.mp.playFromStart();
		var that = this;
		this.interval = setInterval(function(){
		   that.isEnd();	
		   that.play_flag=1;
		}, 50)
//		top.mp = new MediaPlayer();
//		var that = this;
//		top.mp.bindNativePlayerInstance(0);
//		setTimeout(function(){
//				that.playFrame.src = song.url;
//				that.play_flag=1;
//				that.duration_time = 0;
//				that.media_start_flag = 1;
//				that.speed = 1;
//				that.speed_num = 0;
//// 				top.mp.playFromStart();
//			},1000);
//		compareTime(new Date().getTime(),"播放初始化完成");	
	},
	interval:'',
	playTimes:1,
	//更新时间，判断结束播放下一曲
	isEnd:function(){
		var curTime = this.mp.getCurrentPlayTime();
		var allTime = this.mp.getMediaDuration();
		if(allTime&&!this.duration_time){
			this.duration_time = allTime;
		}
		if(curTime){
		    this.oparateProp(curTime);
		}
		try {
			if ((allTime == curTime) && (curTime != 0)) {
				this.times=0;
				clearInterval(this.interval);
				//根据播放模式，播放下一曲
				if(playObj.play_ms_pos==0&&playListObj.len > 0){
						if(playListObj.pos == playListObj.cur_size - 1 && playListObj.page_pos == playListObj.page_size - 1) {
							playListObj.pos = 0;
							playListObj.page_pos = 0;
							playListObj.init();
							playListObj.focusMove();
						} else {
							playListObj.focusMove(1);
						}
				}else if(playObj.play_ms_pos==2){
					j = parseInt(playListObj.dataArray.length*Math.random());
					playListObj.pos = j%playListObj.max_size;
					playListObj.page_pos = Math.floor(j/playListObj.max_size);
					playListObj.init();
					playListObj.focusMove();
				}
				myMediaPlay.resume(1);
				playListObj.doSelect();	
			}else if((allTime-curTime)<5){
				this.times++;
				if(this.times>110){
					this.times=0;
					clearInterval(this.interval);
					//根据播放模式，播放下一曲
					if(playObj.play_ms_pos==0&&playListObj.len > 0){
							if(playListObj.pos == playListObj.cur_size - 1 && playListObj.page_pos == playListObj.page_size - 1) {
								playListObj.pos = 0;
								playListObj.page_pos = 0;
								playListObj.init();
								playListObj.focusMove();
							} else {
								playListObj.focusMove(1);
							}
					}else if(playObj.play_ms_pos==2){
						j = parseInt(playListObj.dataArray.length*Math.random());
						playListObj.pos = j%playListObj.max_size;
						playListObj.page_pos = Math.floor(j/playListObj.max_size);
						playListObj.init();
						playListObj.focusMove();
					}
					myMediaPlay.resume(1);
					playListObj.doSelect();	
				}
			} 
		} catch (e) {
			log(e);
		}
		
	},
	times:0,
	oparateProp : function(_current_time) {
		var current_time = parseFloat(_current_time);
		var prop_dis = Math.ceil(current_time * 980 / this.duration_time);
		if(prop_dis > 980) {
			prop_dis = 980;
		}
		$("prop_img").style.width = prop_dis + "px";
		$("prop_end").style.left = prop_dis + "px";
	}
	,
	parse_time : function(_seconds)
	{
		var second = _seconds % 60;				
		_seconds = Math.floor(_seconds / 60);
		var minute = _seconds % 60;				
		_seconds = Math.floor(_seconds / 60);
		if(second < 10) second = "0" + second;
		if(minute < 10) minute = "0" + minute;
		return minute + ":" + second;
	},
	//暂停
	pause_resume_flag : 0,
	pause : function()
	{
		this.mp.pause();
		this.pause_resume_flag = 1;
		$("bottom_btn1_in0").style.visibility = "visible";
		$("bottom_btn1_in1").style.visibility = "hidden";
		
		$("pause_div").style.visibility = "visible";
		$("play_status").style.visibility = "visible";
	},
	
	//恢复
	resume : function(_flag) {
		var flag = _flag || 0;
		if(!flag) {
			this.mp.resume();
		}
		this.pause_resume_flag = 0;
		$("bottom_btn1_in0").style.visibility = "hidden";
		$("bottom_btn1_in1").style.visibility = "visible";
		$("pause_div").style.visibility = "hidden";
		$("play_status").style.visibility = "hidden";
	},
	
	stop : function() {
		this.mp.stop();
	},
	
	getCurrentTime : function() {
		return parseInt(this.mp.getCurrentPlayTime());
	},
	
	getDurationTime : function() {
		var duration_time = this.mp.getMediaDuration();
		
		if("" != duration_time && 0 != duration_time) {
			this.duration_time = duration_time;
			return this.duration_time;
		} else if(0 != oparateObj.runTime) {
			this.duration_time = oparateObj.runTime;
			return this.duration_time;
		} else {
			var that = this;
			setTimeout(function() {
				that.getDurationTime();
			}, 1000);
			return "";
		}
	},
	
	seek : function(_startTime) {
		var type = 1;
		var speed = 1;
		this.mp.playByTime(type, _startTime, speed);
		myMediaPlay.resume(1);
	},
	
	speed : 1,
	speed_num : 0,
	fastForward : function(_num, _flag) {
		try {
			var num = _num || 0;
			var flag = _flag || 0;
			if(100 == num){
				this.speed = 1;
				this.speed_num = 0;
				$("play_status").style.visibility = "hidden";
				$("speed_div").style.visibility = "hidden";
				$("paly_chunk_in").style.display = "block";			
				 playObj.visible(1);
				 return;
			}
			if(0 != num) {
				if(this.speed_num != num) {
					this.speed = 1;
					this.speed_num = num;
				}
				
				this.speed = this.speed * 2;
				if(this.speed > 32) {
					this.speed = 2;
				}
				
				clearTimeout(playObj.v_time_out);
				playObj.volumeHidden();
				clearTimeout(playObj.vm_time_out);
				$("play_status").style.visibility = "visible";
				$("speed_div").style.visibility = "visible";
				$("pause_div").style.visibility = "hidden";
				$("speed_status").style.backgroundImage = "url(images/" + (1 == num ? "forward.png" : "backward.png") + ")";
				$("speed_num").style.backgroundImage = "url(images/speed" + this.speed + ".png)";
				$("paly_chunk_in").style.display = "none";
				$("paly_chunk").style.display = "block";
				if(num > 0) {
					this.mp.fastForward(this.speed * num);
				} else {
					this.mp.fastRewind(this.speed * num);
				}
			} else {
				this.speed = 1;
				this.speed_num = 0;
				$("play_status").style.visibility = "hidden";
				$("speed_div").style.visibility = "hidden";
				$("paly_chunk_in").style.display = "block";			
				playObj.visible(1);
				
				if(-1 != flag) {
					this.resume();
				}
			}
			playObj.doSomethingForCT();
		} catch (e) {
			log(e);
		}
	},
	
	getMuteFlag : function() {
		return this.mp.getMuteFlag();
	},
	
	setMuteFlag : function(_flag) {
		this.mp.setMuteFlag(_flag);
	},
	
	getVolume : function() {
		return parseInt(this.mp.getVolume());
	},
	
	setVolume : function(_volume) {
		this.mp.setVolume(_volume);
	},
	
	//一键到尾
	goEnd : function() {
		this.mp.gotoEnd();
	},
	
	//一键到头
	goStart : function() {
		this.mp.gotoStart();
	}
}


function grabEvent(_event){
	var code = Event(_event);
	//$("test_new").innerHTML += " code : " + code;
	switch(code){	
		case "KEY_UP": //
			if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				return;
			}	
			oparateObj.moveY(-1);
			return false;
			break;
		case "KEY_DOWN": //
			if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				return;
			}	
			oparateObj.moveY(1);
			return false;
			break;		
		case "KEY_LEFT": //		
			if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				return;
			}		
			oparateObj.moveX(-1);
			return false;
			break;
		case "KEY_RIGHT": //		
			if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				return;
			}		
			oparateObj.moveX(1);
			return false;
			break;		
		case "KEY_PAGE_UP": //
			/*if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				return;
			}*/
			if(1 != oparateObj.area && 3 != oparateObj.area) {
				myMediaPlay.fastForward(-1);
			} else {
				oparateObj.changePage(-1);
			}
			return false;
			break;
		case "KEY_PAGE_DOWN": //	
			/*if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				return;
			}	*/	
			
			if(1 != oparateObj.area && 3 != oparateObj.area) {
				myMediaPlay.fastForward(1);
			} else {
				oparateObj.changePage(1);
			}
			return false;
			break;		
		case "KEY_SELECT": // 
			if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				myMediaPlay.fastForward();
			} else {
				oparateObj.doSelect();
			}
			return false;
			break;
		case "KEY_EXIT":
		case "KEY_BACK":
			oparateObj.doBack();
			return false;
			break;
		case "KEY_VOLUME_MUTE":
			if(0 != myMediaPlay.speed_num) {
				return;
			}
			playObj.changeMute();
			return false;
			break;
		case "KEY_VOLUME_UP":
			if(0 != myMediaPlay.speed_num) {
				return;
			}
			playObj.changeVolume(1);
			return false;
			break;
		case "KEY_VOLUME_DOWN":
			if(0 != myMediaPlay.speed_num) {
				return;
			}
			playObj.changeVolume(-1);
			return false;
			break;
		//case 1043://停止
		case 19://暂停
			myMediaPlay.fastForward();
			playObj.visible(1);
			playObj.pauseSM();
			return false;
			break;
		case 1045://播放
		case 3864://播放
			if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				myMediaPlay.fastForward();
			} else {
				playObj.visible(1);
				//playObj.playSM();
				playObj.pauseOrPlay();
			}
			return false;
			break;
		case 417:
		case 102:
		case 75:
			myMediaPlay.fastForward(1);
			return false;
			break;
		case 412:
		case 74:
			myMediaPlay.fastForward(-1);
			return false;
			break;
		default:
			break;
	}
};

function grabEvent2(_event){
	var code = Event(_event);
	switch(code){
	    case 768:
	    	 var media_event  =  Utility.getEvent();
	         var obj = eval('('+media_event+')');
	         if(obj.new_play_mode==2){
	        	 myMediaPlay.fastForward(100);
	         }
	         if(myMediaPlay.play_flag==1&&obj.type=="EVENT_MEDIA_END"){
	        	var vas_small_player_id = myMediaPlay.nativePlayerInstanceID();
	        	myMediaPlay.stop();
	     		if(playObj.play_ms_pos==0&&playListObj.len > 0){
					if(playListObj.pos == playListObj.cur_size - 1 && playListObj.page_pos == playListObj.page_size - 1) {
						playListObj.pos = 0;
						playListObj.page_pos = 0;
						playListObj.init();
						playListObj.focusMove();
					} else {
						playListObj.focusMove(1);
					}
				}else if(playObj.play_ms_pos==2){
					j = parseInt(playListObj.dataArray.length*Math.random());
					playListObj.pos = j%playListObj.max_size;
					playListObj.page_pos = Math.floor(j/playListObj.max_size);
					playListObj.init();
					playListObj.focusMove();
				}
				myMediaPlay.resume(1);
				playListObj.doSelect();	
	            myMediaPlay.play_flag=0
	         }
	        
	         break;    
		case 40202:
			var capturedEvent = eval('(' + Utility.getEvent(40202,event.userInt) + ')');
			//$("test_new").innerHTML += "40202 : " + capturedEvent.error_code;
			if (capturedEvent.error_code == 6)//开始播放
			{
				if(1 == myMediaPlay.media_start_flag) {
					myMediaPlay.media_start_flag = 0;
					start_time = Q.get_now_time();
					$("mute_img").style.visibility = myMediaPlay.getMuteFlag() ? "visible" : "hidden";
					playObj.doSomethingForCT();
					playObj.play_pos = 0;
					myMediaPlay.pause_resume_flag = 0;
					$("bottom_btn1_in0").style.visibility = "hidden";
					$("bottom_btn1_in1").style.visibility = "visible";
					dcPlayToReciveServlet();
				}
			}
			else if (capturedEvent.error_code == 8 || capturedEvent.error_code == 22)//结束播放
			{
				myMediaPlay.fastForward(0, -1);
				switch(playObj.play_ms_pos) {
					case 0:
						if(playListObj.pos == playListObj.cur_size - 1 && playListObj.page_pos == playListObj.page_size - 1) {
							playListObj.pos = 0;
							playListObj.page_pos = 0;
							playListObj.init();
							playListObj.focusMove();
						} else {
							playListObj.focusMove(1);
						}
						break;
					case 1:
						playListObj.pos = playListObj.pos_this;
						playListObj.page_pos = playListObj.page_pos_this;
						playListObj.init();
						playListObj.focusMove();
						break;
					case 2:
						var num = Math.ceil((Math.random() * 1000)) % playListObj.len;
						playListObj.pos = num % playListObj.max_size;
						playListObj.page_pos = Math.ceil(num / playListObj.max_size) - 1;
						playListObj.init();
						playListObj.focusMove();
						break;
				}
				
				playListObj.doSelect();
			}
			return 0;
			break;
		case "KEY_UP": //
			if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				return;
			}	
			oparateObj.moveY(-1);
			return false;
			break;
		case "KEY_DOWN": //
			if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				return;
			}	
			oparateObj.moveY(1);
			return false;
			break;		
		case "KEY_LEFT": //		
			if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				return;
			}		
			oparateObj.moveX(-1);
			return false;
			break;
		case "KEY_RIGHT": //		
			if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				return;
			}		
			oparateObj.moveX(1);
			return false;
			break;		
		case "KEY_PAGE_UP": //
		case 265:
			/*if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				return;
			}*/
			myMediaPlay.fastForward(-1);
			return false;
			break;
		case "KEY_PAGE_DOWN": //
		case 264:
			/*if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				return;
			}	*/	
			myMediaPlay.fastForward(1);
			return false;
			break;		
		case "KEY_SELECT": // 
			if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				myMediaPlay.fastForward();
			} else {
				oparateObj.doSelect();
			}
			return false;
			break;
		case "KEY_EXIT":
		case "KEY_BACK":
			oparateObj.doBack();
			return false;
			break;
		case "KEY_VOLUME_MUTE":
		case 261:
			if(0 != myMediaPlay.speed_num) {
				return;
			}
			playObj.changeMute();
			return false;
			break;
		case "KEY_VOLUME_UP":
		case 259:
			if(0 != myMediaPlay.speed_num) {
				return;
			}
			playObj.changeVolume(1);
			return false;
			break;
		case "KEY_VOLUME_DOWN":
		case 260:
			if(0 != myMediaPlay.speed_num) {
				return;
			}
			playObj.changeVolume(-1);
			return false;
			break;
		//case 1043://停止
		case 19://暂停
			myMediaPlay.fastForward();
			playObj.visible(1);
			playObj.pauseSM();
			return false;
			break;
		case 1045://播放
		case 3864://播放
			if(0 != myMediaPlay.speed_num && 10 != oparateObj.area && 11 != oparateObj.area) {
				myMediaPlay.fastForward();
			} else {
				playObj.visible(1);
				//playObj.playSM();
				playObj.pauseOrPlay();
			}
			return false;
			break;
		case 417:
		case 75:
			myMediaPlay.fastForward(1);
			return false;
			break;
		case 412:
		case 74:
			myMediaPlay.fastForward(-1);
			return false;
			break;
		default:
			break;
	}
	
	
};

function checkAndInitPress() {
//     document.onkeydown = grabEvent;
	document.onkeypress = grabEvent2;
}


function dcPlayToReciveServlet() {
	
}


function dcPauseToReciveServlet() {	
	
}

function dcVolumeToReciveServlet() {
	
}

function dcTCPopToReciveServlet() {	
	
}

function dcEndToReciveServlet() {	
	
}
//页面行为采集
function uploadCollection(){
	getCAcardNum(function(){
		var contentId = Q.get("programId");
		var contentName = Q.decode(Q.get("title"));
		var url = "http://180.100.134.81:8290/gatherEpg";
		var param = "gatherSource=MUSIC&userId="+paramObj.userInfo.userId+"&areaCode="+paramObj.userInfo.areaCode+"&gathSourceId="+getCollectionCode("bf")
		+"&gathTargetId="+getCollectionCode("bf")+"&currentTime="+dateFtt("yyyyMMddHHmmss",new Date())
		+"&userToken="+paramObj.userInfo.userToken+"&contentId="+contentId+"&contentName="+Q.encode(contentName)
		+"&stbId="+paramObj.userInfo.stbId+"&contentType=MAIN&actType=3";
		ajax({
			url: url+ "?"+param,// 请求的URL,可传参数
			type: "GET", //HTTP 请求类型,GET或POST
			dataType: "html" //请求的文件类型html/xml
		});
	});
}
</script>
</body>
</html>

    
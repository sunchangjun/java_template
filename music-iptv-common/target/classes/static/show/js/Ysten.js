(function() {
	/*
	 * Ysten namespace init
	 */
	var Ysten = window.Ysten = {};
	Ysten.core = {};
	Ysten.dom = {};
	Ysten.data = {};
	Ysten.player = {};
	/*
	 * Ysten player
	 * --------------------------------------------------------------------------------------------
	 * player callback register
	 */
	Ysten.player = function() {
		var playerCore = {};
		//当前播放时间，总时长
		var currentTime = 0, totalTime = 0;
		//当前播放器状态，上一状态，状态检测定时器
		var currentstatus = -2, prevstatus = -1,backupstatus = -1;
		//当前缓冲进度，缓冲时间，进度检测定时器
		var bufferNumber = 0, bufferTimeout = 0, bufferTimer;
		//媒体文件地址
		var mediaUrl = "";
		//播放器对象
		var mediaObj = null;
		//匹配数字
		var digit = /\d/;
		var simple = false;
		var hasTimeout = true;
		var isonReady = false;
		var status = playerCore.status = {
			init : -2, //初始化
			stop : -1, //停止
			ready : 0, //准备
			play : 1, //播放
			pause : 2, //暂停
			buffer : 3, //缓冲
		bufferComplete:33,
			over : 4, //结束
			exception : 5, //异常
			error : 6//错误
		};

		var opt = {
			me : null,
			id : "", //播放控件ID
			url : "", //视频地址
			timeout : 120, //超时时间
			beginTime : 0, //开始播放时间
			onReady : "", //参数处理结束回调函数
			onPlay : "", //进入播放状态的回调函数
			onBuffer : "", //进入缓冲状态时的回调函数
			onBufferComplete:"",		//缓冲状态完成时的回调函数
			onPause : "", //进入暂停状态的回调函数
			onResume : "", //进入唤醒状态的回调函数
			onStop : "", //视频播被停止后的回调函数
			onOver : "", //视频播放结束后的回调函数
			onException : "", //发生异常时的回调函数
			onError : "", //发生错误时的回调函数
			onstatusChange : ""	//状态改变时的回调函数
		};

		var coreTimer;

		var isException = false;
		var isError = false;

/*	*/
		function daolei (objId) {
		  return document.getElementById(objId) || null;
		}
//改变播放器状态
		function changestatus(s) {
			console.log("playerCore changestatus start");
			currentstatus = s;
			console.log("playerCore:in changestatus,prevstatus -> " + prevstatus);
			console.log("playerCore:in changestatus,currentstatus -> " + currentstatus);
			if(prevstatus != s) {
				currentstatus = prevstatus = s;
				if(opt.onStatusChange && typeof opt.onStatusChange == 'function') {
					opt.me ? opt.onStatusChange.call(opt.me) : opt.onStatusChange();
				}
				switch(s) {
					case status.ready:
						if(opt.onReady && typeof opt.onReady == 'function') {
							opt.me ? opt.onReady.call(opt.me) : opt.onReady();
						}
						break;
					case status.stop:
						if(opt.onStop && typeof opt.onStop == 'function') {
							opt.me ? opt.onStop.call(opt.me) : opt.onStop();
						}
						break;
					case status.play:
						if(opt.onPlay && typeof opt.onPlay == 'function') {
							opt.me ? opt.onPlay.call(opt.me) : opt.onPlay();
						}
						break;
					case status.pause:
						if(opt.onPause && typeof opt.onPause == 'function') {
							opt.me ? opt.onPause.call(opt.me) : opt.onPause();
						}
						break;
					case status.buffer:
						if(opt.onBuffer && typeof opt.onBuffer == 'function') {
							opt.me ? opt.onBuffer.call(opt.me) : opt.onBuffer();
						}
						// case state.bufferComplete:
						// if(opt.onBufferComplete && typeof opt.onBufferComplete=='function'){
						// opt.onBufferComplete();
						// }
						break;
					case status.over:
						if(opt.onOver && typeof opt.onOver == 'function') {
							opt.me ? opt.onOver.call(opt.me) : opt.onOver();
						}
						break;
					case status.exception:
						if(opt.onException && typeof opt.onException == 'function') {
							opt.me ? opt.onException.call(opt.me) : opt.onException();
						}
						break;
					case status.error:
						if(opt.onError && typeof opt.onError == 'function') {
							opt.me ? opt.onError.call(opt.me) : opt.onError();
						}
						break;
				}
			}
			console.log("playerCore changestatus end");
		};

		//收到底层播放器onPlayReady消息
		function onPlayReady() {
			console.log("playerCore onPlayReady start");
			isonReady = true;
			changestatus(status.ready);
			console.log("playerCore onPlayReady end");
		};

		//收到底层播放器onPlayForceStop消息
		function onPlayForceStop() {
			console.log("playerCore onPlayForceStop start");
			if(isError){
				console.log("ignore exception on catch error");
				return;
			}
			clearTimer();
			if(isException) {
				changestatus(status.exception);
			}else{
				changestatus(status.stop);
			}
			console.log("playerCore onPlayForceStop end");
		};

		//收到底层播放器onPlayStop消息
		function onPlayStop() {
			console.log("playerCore onPlayStop start");
			clearTimer();
			changestatus(status.over);
			console.log("playerCore onPlayStop end");
		};

		//收到底层播放器onPlayError消息
		function onPlayError() {
			console.log("playerCore onPlayError start");
			if(isException){
				console.log("ignore error on catch exception");
				return;
			}
			isError = true;
			clearTimer();
			changestatus(status.error);
			console.log("playerCore onPlayError end");
		};


		function clearTimer() {
			console.log("playerCore clearTimer start");
			if(bufferTimer)
				clearInterval(bufferTimer);
			console.log("playerCore clearTimer end");
		};

		/**
		 * start player.
		 * @param {opt}
		 * @return {booleam}
		 * @example var result=playerCore.ready(opt);
		 */
		playerCore.init = function(){
			prevstatus = currentstatus = status.init;
			backupstatus = status.init;
			currentTime = totalTime = 0;
			isonReady = false;
			isException = false;
		};
		playerCore.ready = function() {
			console.log("playerCore ready start");
			var options = (arguments && arguments[0]) || {};
			var data=options.data||'{"type":"vod_replay"}';
			var name=encodeURIComponent(options.name)||'0';
			prevstatus = currentstatus = status.init;
			backupstatus = status.init;
			currentTime = totalTime = 0;
			isonReady = false;
			isException = false;
			isError = false;


			if( typeof options.id == 'string' && (options.id)) {
				mediaObj = daolei(options.id);
				if(!!!mediaObj) {
					opt.id = options.id;
				}
			} else {//no id,so remove old and create a new embed;
				if(!!!mediaObj) {
				}
			}
			//handle timeout
			opt.me = options.me || null;
			opt.timeout = typeof (options.timeout = parseInt(options.timeout)) == 'number' ? options.timeout : 60;
			//handle beginTime
			opt.beginTime = typeof (options.beginTime = parseInt(options.beginTime)) == 'number' ? options.beginTime : 0;
			//handle event callback
			opt.onReady = typeof options.onReady == 'function' ? options.onReady : '';
			opt.onPlay = typeof options.onPlay == 'function' ? options.onPlay : '';
			opt.onBuffer = typeof options.onBuffer == 'function' ? options.onBuffer : '';
			opt.onBufferComplete = typeof options.onBufferComplete == 'function' ? options.onBufferComplete : '';
			opt.onPause = typeof options.onPause == 'function' ? options.onPause : '';
			opt.onResume = typeof options.onResume == 'function' ? options.onResume : '';
			opt.onStop = typeof options.onStop == 'function' ? options.onStop : '';
			opt.onOver = typeof options.onOver == 'function' ? options.onOver : '';
			opt.onException = typeof options.onException == 'function' ? options.onException : '';
			opt.onError = typeof options.onError == 'function' ? options.onError : '';
			opt.onStatusChange = typeof options.onStatusChange == 'function' ? options.onStatusChange : '';
			//reg event
			mediaObj.onPlayReady = onPlayReady;
			mediaObj.onPlayStop = onPlayStop;
			mediaObj.onPlayForceStop = onPlayForceStop;
			mediaObj.onPlayError = onPlayError;
			//handle options.url,set mediaUrl;
			if(options.url && typeof options.url == 'string') {
				mediaUrl = options.url;
				opt.url = options.url;
			} else {
				console.log('playerCore:url err');
				return false;
			}
			console.log('playerCore: data:'+data+',name:'+name);
			if(mediaObj.play) {
				mediaObj.play(opt.url, opt.beginTime,name,'0',data);
			}
			console.log("playerCore ready end");
			return true;
		};
		/**
		 * set player to pause,return true or false.
		 * @param {none}
		 * @return {booleam}
		 * @example var result=playerCore.pause();
		 */
		playerCore.pause = function() {
			console.log("playerCore pause start");
			if( typeof mediaObj.pause == 'function' && currentstatus > status.stop) {
				mediaObj.pause();
				changestatus(status.pause);
				backupstatus = status.pause;
				console.log("playerCore pause end");
				return true;
			} else {
				return false;
			}
		};
		/**
		 * resume player after player is in pause status.can only be called after playerCore.pause().return true or false.
		 * @param {none}
		 * @return {booleam}
		 * @example var result=playerCore.resume();
		 */
		playerCore.resume = function() {
			console.log("playerCore resume start");
			if( typeof mediaObj.resume == 'function' && currentstatus == status.pause) {
				mediaObj.resume();
				backupstatus = status.play;
				console.log("playerCore resume end");
				return true;
			} else {
				return false;
			}
		};
		/**
		 * stop player.return true or false.
		 * @param {none}
		 * @return {booleam}
		 * @example var result=playerCore.stop();
		 */
		playerCore.stop = function() {
			console.log("playerCore stop start");
			if(mediaObj && typeof mediaObj.stop == 'function') {
				mediaObj.onPlayError = function(){
					console.log('ignore error affter stop');
				};
				mediaObj.stop();
				currentTime = 0;
				console.log("playerCore stop end");
				return true;
			} else {
				return false;
			}
		};
		/**
		 * seek time.
		 * @param {number}
		 * @return {booleam}
		 * @example var result=playerCore.seekByTime(0);
		 */
		playerCore.seekByTime = function(seekTime,offset) {
			console.log("playerCore seekByTime start");
			console.log("playerCore seekByTime seekTime -> " + seekTime);
			if( typeof mediaObj.seekByTime == 'function' && digit.test(seekTime) && ( typeof mediaObj.seekByTime == 'function')) {
				console.log("playerCore:before seek,getCurrentTime==" + mediaObj.getCurrentTime());
				seekTime = parseInt(seekTime, 10);
				if(offset<0){
					seekTime = parseInt(mediaObj.getCurrentTime()-seekTime, 10);
				}else if(offset>0){
					seekTime = parseInt(mediaObj.getCurrentTime()+seekTime, 10);
				}
				console.log("playerCore seekByTime seekTime -> " + seekTime);
				var seekResult = mediaObj.seekByTime(seekTime);
				console.log("playerCore:seek result is:" + seekResult);
				currentTime = mediaObj.getCurrentTime();
				console.log("playerCore seekByTime end");
				return true;
			} else {
				return false;
			}
		};
		/**
		 * set player display area.
		 * @param {number,number,number,number}
		 * @return {booleam}
		 * @example var result=playerCore.setDisplayArea(0,0,1280,720);
		 */
		playerCore.setDisplayArea = function(x, y, w, h) {
			console.log("playerCore setDisplayArea start");
			if(currentstatus == status.init || currentstatus == status.stop || currentstatus == status.over || currentstatus == status.ready) {
				console.log("playerCore setDisplayArea if 111");
				try {
					return true;
				} catch(err) {
					return false;
				}
			} else {
				console.log("playerCore setDisplayArea if 2222");
				if( typeof mediaObj.setDisplayArea == 'function') {
					if(arguments.length > 0 && digit.test(x) && digit.test(y) && digit.test(w) && digit.test(h)) {
						setElementArea(x, y, w, h);
						
						return mediaObj.setDisplayArea(x, y, w, h);
						
					} else {
						setElementArea(0, 0, 0, 0);
						return mediaObj.setDisplayArea(0, 0, 0, 0);
					}
				} else {
					return false;
				}
			}
			function setElementArea(_x, _y, _w, _h) {
				mediaObj.style.left = _x + 'px';
				mediaObj.style.top = _y + 'px';
				mediaObj.style.width = _w + 'px';
				mediaObj.style.height = _h + 'px';
			};
            console.log("playerCore setDisplayArea end");
		};

		
		playerCore.getStatus = function() {
			return currentstatus;
		};

		playerCore.getCurrentTime = function() {
			currentTime = mediaObj.getCurrentTime();
			return currentTime ? currentTime : 0;
		};

		playerCore.getTotalTime = function() {
			totalTime = mediaObj.getTotalTime();
			return totalTime ? totalTime : 0;
		};

		playerCore.getBufferNum = function() {
			return bufferNumber;
		};

		playerCore.getOpt = function() {
			return opt;
		};
		playerCore.setSimple = function(flag) {
			simple = flag;
		};
		return playerCore;
	}();
})();
var playerCore = Ysten.player;
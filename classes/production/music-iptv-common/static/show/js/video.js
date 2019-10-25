/**
 * Created by CF on 2016/8/22.
 */
var player;
var opt = {
    id: "radioPlayer", //播放控件ID
    url: "http://223.87.20.83:8089/28000001/00010000000000009999999999999837", //视频地址
    timeout: 120, //超时时间
    beginTime: 0,//开始播放时间
    onReady: cathStateReady, //进入准备状态的回调函数
    onPlay: cathStatePlay, //进入播放状态的回调函数
    onBuffer: catchStateBuffer, //进入缓冲状态时的回调函数
    onBufferComplete: catchStateBufferComplete, //缓冲状态完成时的回调函数
    onPause: catchStatePause, //进入暂停状态的回调函
    onStop: catchStateStop, //手动停止视频的回调函数
    onOver: catchStateOver, //视频播放结束时的回调函数
    onException: catchStateException, //发生异常时的回调函数
    onError: catchStateErr, //发生错误时的回调函数
    onStatusChange: catchStateChange 	//状态改变时的回调函数
};

function cathStateReady() {
    //进入准备状态的回调函数 第一次进入视频
    hide('dataLoad');
};
function cathStatePlay() {
    //进入播放状态的回调函数
    hide('dataLoad');
    hide('playerPauseAd');
};
function catchStateBuffer() {
    //进入缓冲状态时的回调函数
    show('dataLoad');
};
function catchStateBufferComplete() {
    //缓冲状态完成时的回调函数
    hide('dataLoad');
};
function catchStatePause() {
    //进入暂停状态的回调函
    //hide('dataLoad');
    //show('playerPauseAd');
};
function catchStateStop() {
    //手动停止视频的回调函数
};
function catchStateOver() {
    //视频播放结束时的回调函数
    hide('player');
    hide('dataLoad');
    //show('panel');
    //player.backObj.addFocus();
};
function catchStateException() {
    //发生异常时的回调函数
    //$('info').innerHTML = '发生异常时的回调函数';
};
function catchStateChange() {
    //状态改变时的回调函数
};
function catchStateErr() {
    //发生错误时的回调函数
    //player.playerBackFn();
};

/*$(document).ready(function(){
    开始播放
    playerCore.ready(opt);
    player = new player();
    show('dataLoad');
    hide('dataLoad');
    player.addFocus();
});
*/

function player(){
    this.data = '';
    this.backObj = '';
};

player.prototype.addFocus = function(){
        bfs.up = function () {};
        bfs.down = function () {};
        bfs.left = function () {
            //总时长
            var tot = playerCore.getTotalTime();
            //当前时常
            var cur = playerCore.getCurrentTime(); //3000
        };
        bfs.right = function () {
            //跳转时长
            playerCore.seekByTime(5);
        };
        bfs.enter = function () {
            //暂停播放视频
            var status = playerCore.getStatus();
            if (status == 1 || status == 3) {
                playerCore.pause();
            }
            else{
                playerCore.resume();
                show('playerPauseAd');
            }
        };
        bfs.backspace = function () {
            hide('dataLoad');
            playerCore.stop();
            window.location.href = "index.html";
        }
};

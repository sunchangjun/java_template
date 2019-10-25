/**
 * Created by
 */
var list;
$(document).ready(function(){
    playerCore.ready(opt);//调用接口播放视频
    list = new List();
    list.focusIn();
    list.addFocus();

});

function List(){
    this.index = 0;
    this.video=my('video');
    this.span=my('shengji');
    this.radioPlayer=my('radioPlayer');
}

List.prototype.focusIn = function(){
    addClass(this.radioPlayer,'bgColor');
    this.addFocus();
};

List.prototype.addFocus = function() {
    var that = this;
    bfs.right = function () {
        if (that.radioPlayer) {
            removeClass(that.radioPlayer, 'bgColor');
            addClass(that.span, 'bgColor');
            that.index=1;
        }
    };
    bfs.left = function () {
        if (that.span) {
            removeClass(that.span, 'bgColor');
            addClass(that.radioPlayer, 'bgColor');
            that.index=0;
        }
    };
    bfs.up = function () {return;};
    bfs.down = function () {return;};
    bfs.enter = function () {
        if (that.index ==0) {
            window.location.href = "video.html";
        }else{return}
    };
    bfs.backspace = function () {
        playerCore.stop();//返回退出时停止视频播放
        window.close();
    };
};



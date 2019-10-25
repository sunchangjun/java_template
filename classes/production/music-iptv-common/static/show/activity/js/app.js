var activityInfo = {};
// 抽奖
var bRotate = false;
var rorateNo = 0;//抽奖剩余次数
var awardId = -10000;
//元素选择设置变量
var $recordBtn = $(".record-btn");
var $pointerImg = $(".pointerImg");
var $remodal_content_img = $('.remodal_content_img');
var remodal = $('.remodal').remodal();

var optionFlag = 1;//1:本界面,2:奖品弹窗.
//订购初始化
var init = true;
var index = 0;

var isActive = false;//是否有资格抽奖


function rotateFn(awards, angles, index) {
    bRotate = !bRotate;

    $('#rotate').stopRotate();

    $('#rotate').rotate({
        angle: 0,
        animateTo: angles + 1080,
        duration: 7000,
        callback: function () {
            bRotate = !bRotate;
            optionFlag = 2;
            $remodal_content_img.attr('src', 'img/' + index + 'award.png');
            getActivityTimes();
            remodal.open();
        }

    })
}

//获取活动的中奖纪录
function getActivityRecord() {
    $.ajax({
        type: "POST",
        url: baseUrl + "activity/getActivityRecord",
        data: {activityId: activityId},
        success: function (result) {
            if (result.code === 0) {
                $('.pNo').text(result.data.length);
                $('.myscroll').beginScroll({
                    speed: 40, //数值越大，速度越慢
                    rowHeight: 37//li的高度
                });
                $('.myscroll').initScroll(result.data);
            }
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.responseText);
        }
    });
}


function getActivityTimes() {
    //获取活动详情
    $.ajax({
        //请求方式
        type: "POST",
        //请求的媒体类型
        //请求地址
        url: baseUrl + "activity/getActivityTimes",
        //数据，json字符串
        data: {userId: userId, activityId: activityId},
        //请求成功
        success: function (result) {
            if (result.code === 0) {
                rorateNo = result.data <= 0 ? 0 : result.data;
                $('.count').text(rorateNo);
            }
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

//判断用户是不是有中奖未填的情况
function checkPhone() {
    $.ajax({
        type: "POST",
        url: baseUrl + "activity/checkPhoneNum",
        data: {userId: userId},
        success: function (result) {
            if (result.code === 0) {
                if (!result.data) {
                    alert('尊敬的用户，你有中奖纪录未填写联系方式！');
                    gotoHref('input.html', awardId);
                }
            }
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

function getAward() {
    $.ajax({
        type: "POST",
        url: baseUrl + "activity/getActivityResult",
        data: {userId: userId, activityId: activityId, times: 1},
        success: function (result) {
            if (result.code === 0) {
                var winData = result.data.award;
                awardId = winData.id;
                switch (awardId) {
                    case 1:
                        rotateFn(1, 0, 1);
                        break;
                    case 2:
                        rotateFn(2, -60, 2);
                        break;
                    case 3:
                        rotateFn(3, -120, 3);
                        break;
                    case 8:
                        rotateFn(4, -180, 2);
                        break;
                    case 9:
                        rotateFn(5, -240, 3);
                        break;
                    case -10000:
                        rotateFn(-1000, -300, 0);
                        break;
                }

            }
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

//选中事件
function changeStyle(option) {
    if (optionFlag === 2) return;
    if (init) {
        $pointerImg.attr('src', 'img/pointer-full.png');
        init = false;
        return;
    }
    if (option === 'left' || option === 'up') {
        index = 1;
        $pointerImg.attr('src', 'img/pointer-full.png');
        $recordBtn.removeClass('record-btn-bg-full').addClass('record-btn-bg');
    } else {
        index = 2;
        $pointerImg.attr('src', 'img/pointer.png');
        $recordBtn.removeClass('record-btn-bg').addClass('record-btn-bg-full');
    }
}

//回车事件
function enter() {
    if (optionFlag === 2) {
        if (awardId < 0) {
            remodal.close();
        } else {
            gotoHref('record.html', awardId);
        }
    } else {
        if (init) return;
        if (index === 0) {
            if (bRotate) return;
            if (rorateNo <= 0) {
                alert('抽奖次数不足！');
                return;
            }
            if (!isActive) {
                alert('活动已经过期！https://www.cnblogs.com/hellxz/p/7764932.html\n');
                return;
            }
            getAward();
            getActivityTimes();
        } else {
            gotoHref('record.html', awardId);
        }
    }
}

function getActivity() {
    //获取活动详情
    $.ajax({
        //请求方式
        type: "POST",
        //请求的媒体类型
        //请求地址
        url: baseUrl + "activity/getActivityDetail",
        //数据，json字符串
        // contentType:"application/json;charset=utf-8",
        data: {userId: userId, activityId: activityId},
        //请求成功
        success: function (result) {
            if (result.code === 0) {
                activityInfo = result.data.activty;
                isActive = result.data.active;
            }
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

$(document).on('closed', '.remodal', function (e) {
    optionFlag = 1;
});
$(function () {
    checkPhone();
    getActivity();
    getActivityRecord();
    getActivityTimes();
});

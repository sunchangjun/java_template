var userId = getUrlParam('userId') || 1;
var activityId = getUrlParam('activityId') || 1;
var baseUrl = window.location.origin + '/';
var option = '';

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}

function replaceStar(str) {
    var re = new RegExp("(\\d{" + str.length - 4 + "})\\d{4}");
    return str.replace(re, '$1****');
}

function gotoHref(html, awardId) {
    window.location.href = html + '?userId=' + userId + '&activityId=' + activityId + '&awardId=' + awardId;
}

function posFunc(x, y) {
    return $('.pos' + x + '-' + y);
}

$(document).keydown(function (event) {
    switch (event.keyCode) {
        case 8:
            break;
        case 13:
        case 65293://enter
            enter();
            break;
        case 3:
        case 65361:
        case 65:
        case 37://左箭头
            option = 'left';
            changeStyle(option);
            break;
        case 1:
        case 65362:
        case 87:
        case 38://上箭头
            option = 'up';
            changeStyle(option);
            break;
        case 4:
        case 65363:
        case 68:
        case 39://右箭头
            option = 'right';
            changeStyle(option);
            break;
        case 2:
        case 65364:
        case 83:
        case 40: //下箭头
            option = 'down';
            changeStyle(option);
            break;
    }
});

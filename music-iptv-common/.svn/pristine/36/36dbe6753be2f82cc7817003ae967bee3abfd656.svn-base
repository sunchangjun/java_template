/**
 * 滚动组件的实现
 * @param {Object} $
 */
(function ($) {
    /**
     * 实现滚动方法
     * @param {Object} options
     */
    $.fn.beginScroll = function (options) {
        //默认配置
        var defaults = {
            speed: 40,  //滚动速度,值越大速度越慢
            rowHeight: 24 //每行的高度
        };
        //对象赋值
        var opts = $.extend({}, defaults, options), intId = [];

        function marquee(obj, step) {
            obj.find("ul").animate({marginTop: '-=1'}, 0, function () {
                var s = Math.abs(parseInt($(this).css("margin-top")));
                if (s >= step) {
                    $(this).find("li").slice(0, 1).appendTo($(this));
                    $(this).css("margin-top", 0);
                }
            });
        }

        this.each(function (i) {
            var sh = opts["rowHeight"], speed = opts["speed"], _this = $(this);
            intId[i] = setInterval(function () {
                if (_this.find("ul").height() <= _this.height()) {
                    clearInterval(intId[i]);
                } else {
                    marquee(_this, sh);
                }
            }, speed);

            _this.hover(function () {
                clearInterval(intId[i]);
            }, function () {
                intId[i] = setInterval(function () {
                    if (_this.find("ul").height() <= _this.height()) {
                        clearInterval(intId[i]);
                    } else {
                        marquee(_this, sh);
                    }
                }, speed);
            });

        });

    }

    /**
     * 数据表格
     * @param {Object} options
     */
    $.fn.initScroll = function (options) {

        $.each(options, function (index, item) {
            var phone_num = item.phone_num ? replaceStar(item.phone_num) : '';
            var award_name = item.award_name ? item.award_name : '';
            var create_time = item.create_time ? item.create_time : '';
            var span_dom = '<li><div class="baseStyle phone_num" >' + phone_num + '</div><div class="baseStyle award_name" >' + award_name + '</div><div class="baseStyle create_time" >' + create_time + '</div></li>';
            $('.kgo-croll-body').append(span_dom);

        });
        $('.kgo-scroll-sty>ul>li:even').addClass('evenRowStyle');
        $('.kgo-scroll-sty>ul>li:odd').addClass('oddRowStyle');

    }

})(jQuery);

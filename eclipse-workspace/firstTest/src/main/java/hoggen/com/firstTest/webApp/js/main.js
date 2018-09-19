/**
 * Created by wangliugen on 2018/8/16.
 */
$(function () {
    $carousle = $('.carousel');
    var startX,endX;
    $carousle.on('touchstart',function (e) {
        startX = e.originalEvent.touches[0].clientX;
    });
    $carousle.on('touchmove',function (e) {
       endX =  e.originalEvent.touches[0].clientX;
    });
    $carousle.on('touchend',function () {
        if (endX - startX > 50){
            console.log('右');
            $(this).carousel('prev');

        }else if (startX - endX > 50){
            console.log('左');
            $(this).carousel('next');
        }
    });

    // for (i= 0; i<$('#left_item_groups').children().length;i++){
    //     $('#left_item_groups').children()[i].index = i;
    // }

    $('#left_item_groups a').on('click',function () {

        $('#left_item_groups').children().removeClass('active');

         var $this = $(this);
         console.log($this.index() + '大炮');
         $this.addClass('active').siblings().removeClass('active');


    });

    var parentTime = $('.sk_time');
    var timeList = $('.num');
    console.log(timeList.length);
    var times = 4 * 60 * 60;
    setInterval(function () {
        times--;
        var h = Math.floor( times/(60*60));
        var m = Math.floor( times/60 %60);
        var s = Math.floor( times%60);
        console.log(h + ':'+ m+ ':' + s);
        timeList[0].innerHTML = h >10 ? Math.floor( h/10) : 0;
        timeList[1].innerHTML = h%10;

        timeList[2].innerHTML = m >10 ? Math.floor( m/10) : 0;
        timeList[3].innerHTML = m%10;

        timeList[4].innerHTML = s >10 ? Math.floor( s/10) : 0;
        timeList[5].innerHTML = s%10;

    },1000);

    //搜索框对象
    var search = $('.jd_header_box');
    var banner = $('.carousel-inner');
    var height = banner.offsetHeight;
    window.onscroll = function () {
        var top = document.body.scrollTop;
        if (top > height) {
            search.css('background-color','rgba(201,21,35,0.85)');
        }else {
            var op = top/height * 0.85;
            search.css('background-color','rgba(201,21,35,' + op +')');
        }
    };

    // $('#show_delete_product').modal(options);


    //


//    秒杀倒计时

});

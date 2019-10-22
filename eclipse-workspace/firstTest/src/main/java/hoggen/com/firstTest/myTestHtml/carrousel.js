/**
 * Created by wangliugen on 2018/1/15.
 */

window.onload =function () {
    var wrap = document.getElementById("wrap");
    var arrow = document.getElementById("arrow");  // 三角
    var slider = document.getElementById("slide");
    var lis = slider.getElementsByTagName("li");

    wrap.onmouseover =function () {
        animate(arrow,{"opacity": 100});
    };

    wrap.onmouseout = function () {
        animate(arrow,{"opacity": 0});
    }

    //  存储了每个图片的信息
    var json = [
        {   //  1
            width:400,
            top:20,
            left:50,
            opacity:20,
            z:2
        },
        {  // 2
            width:600,
            top:70,
            left:0,
            opacity:80,
            z:3
        },
        {   // 3
            width:800,
            top:100,
            left:200,
            opacity:100,
            z:4
        },
        {  // 4
            width:600,
            top:70,
            left:600,
            opacity:80,
            z:3
        },
        {   //5
            width:400,
            top:20,
            left:750,
            opacity:20,
            z:2
        }
    ];

    var intercept = true;
    var arrows = arrow.children;
    changed();
    for (var  k in  arrows) {
        arrows[k].onclick = function () {
            if(this.className === "prev") {
                if (intercept === true){
                        changed(false);
                        intercept = false;
                }

            }else {
                if (intercept === true){
                    changed(true);
                    intercept = false;
                }

            }
        };
    }

    function changed(flag) {
        if (flag){
            json.unshift(json.pop());
        }else {
            //   移除第一个   放到json 最后一个
            json.push(json.shift());
        }
        // 为什么给for呢？ 以为我们的json 有5个  盒子li 有 5个
        for(var i=0;i<json.length; i++)
        {
            animate(lis[i],{
                width: json[i].width,
                top: json[i].top,
                left: json[i].left,
                opacity:json[i].opacity,
                zIndex:json[i].z
            },function(){ intercept = true;})  // 回调函数是等动画执行完毕  才行
        }
    }


};


































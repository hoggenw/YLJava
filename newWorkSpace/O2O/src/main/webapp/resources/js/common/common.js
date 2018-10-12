/**
 * Created by wangliugen on 2018/10/11.
 */

function changeVerifyCode(img) {
    var src = "../Kaptcha?" + Math.floor(Math.random()*100);
    img.src = src;
}
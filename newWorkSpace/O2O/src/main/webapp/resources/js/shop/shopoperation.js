/**
 * 
 */

$(function () {
    var initUrl = '/O2O/shopadmin/getshopinitinfo';
    var registerShopUrl = '/O2O/shopadmin/registershop';
    console.log(initUrl);
    getShopInitInfo();
    function getShopInitInfo() {
        $.getJSON(initUrl,function (data){
           if (data.success){
               var tempHtml="";
               var tempAreaHtml = '';
               data.shopCategoryList.map(function (item,index) {
                   tempHtml+='<option data-id="' + item.shopCategoryId + '">' +item.shopCategoryName+ '</option>';
               });
               data.areaList.map(function (item,index) {
                   tempAreaHtml+= '<option data-id="' + item.areaId + '">' +item.areaName+ '</option>';
               });
               $("#shop_category").html(tempHtml);
               $("#area").html(tempAreaHtml);

           }
        });

        $("#submit").click(function () {
           var shop = {};
           shop.shopName = $("#shop_name").val();
            shop.shopAddr = $("#shop_addr").val();
            shop.phone = $("#shop_phone").val();
            shop.shopDesc = $("#shop_desc").val();

            var shopCategoryId = $('#shop_category option').not(function(){ return !this.selected }).attr('data-id');
           // var shopCategoryId = $('#shop_category').selectedOptions[0].getAttribute("data-id");

            shop.shopCategory = {
                shopCategoryId:shopCategoryId
            };
            var areaId = $('#area option').not(function(){ return !this.selected }).attr('data-id');
            //var areaId = $('#area').selectedOptions[0].getAttribute("data-id");
            shop.area = {
                areaId:areaId
            };
            var shopImg = $("#shop_img")[0].files[0];
            var formData = new FormData();
            formData.append("shopImg",shopImg);
            formData.append("shopStr", JSON.stringify(shop));
            var  verifyCodeActual = $("#j_captha").val();
            if (!verifyCodeActual){
                $.toast("输入验证码");
                return
            }
           
            formData.append("verifyCodeActual",verifyCodeActual);
            $.ajax({
                url:registerShopUrl,
                type:'POST',
                data:formData,
                contentType:false,
                processData:false,
                cache:false,
                success:function (data) {
                    if(data.success){
                        $.toast("提交成功");
                    }else {
                        $.toast("提交失败" + data.errMsg);
                    }
                    var img =  $("#captha_img");
                    var src = "../Kaptcha?" + Math.floor(Math.random()*100);
                    img.attr('src',src);
                }
            });
        });

    }

})

/**
 * 
 */

$(function () {

    var shopId = getQueryString("shopId");
    var isEdit = shopId?true:false;
    var initUrl = '/O2O/shopadmin/getshopinitinfo';
    var registerShopUrl = '/O2O/shopadmin/registershop';
    var shopInfoUrl =   '/O2O/shopadmin/getshopbyid?shopId=' + shopId;
    var sditShopUrl = '/O2O/shopadmin/modifyshop';
    console.log(initUrl);

    if (!isEdit){
        getShopInitInfo();
    }else {
        getInfo(shopId);
    }



    function getInfo(shopId) {
        $.getJSON(shopInfoUrl, function(data) {
            if (data.success) {
                var shop = data.shop;
                $('#shop_name').val(shop.shopName);
                $('#shop_addr').val(shop.shopAddr);
                $('#shop_phone').val(shop.phone);
                $('#shop_desc').val(shop.shopDesc);
                var shopCategory = '<option data-id="'
                    + shop.shopCategory.shopCategoryId + '" selected>'
                    + shop.shopCategory.shopCategoryName + '</option>';
                var tempAreaHtml = '';
                var firstElment = '';
                data.areaList.map(function(item, index) {
                    if (item.areaId == shop.area.areaId){
                        firstElment = '<option data-id="' + item.areaId + '">'
                            + item.areaName + '</option>';
                    }else {
                        tempAreaHtml += '<option data-id="' + item.areaId + '">'
                            + item.areaName + '</option>';
                    }

                });
                $('#shop_category').html(shopCategory);
                $('#shop_category').attr('disabled','disabled');
                //$('#shop_name').attr('disabled','disabled');
                $('#area').html(firstElment+tempAreaHtml);


            }
        });
    }



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


    }


    $("#submit").click(function () {
        var shop = {};
        shop.shopName = $("#shop_name").val();
        shop.shopAddr = $("#shop_addr").val();
        shop.phone = $("#shop_phone").val();
        shop.shopDesc = $("#shop_desc").val();
        if (isEdit){
            shop.shopId = shopId;
        }

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
            url: isEdit? sditShopUrl:registerShopUrl,
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

})

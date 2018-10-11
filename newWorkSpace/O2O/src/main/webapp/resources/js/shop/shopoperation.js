/**
 * 
 */

$(function () {
    var initUrl = '/o2o/shopadmin/shopoperation';
    var registerShopUrl = '/o2o/shopadmin/registershop';
    console.log(initUrl);
    getShopInitInfo();
    function getShopInitInfo() {
        $.getJSON(initUrl,function (data){
           if (data.success){
               var tempHtml="";
               var tempAreaHtml = '';
               data.shopCategoryList.map(function (item,index) {
                   tempHtml+='<option data_id="' + item.shopCategoryId + '">' +item.shopCategoryName+ '</option>';
               });
               data.areaList.map(function (item,index) {
                   tempAreaHtml+= '<option data_id="' + item.areaId + '">' +item.areaName+ '</option>';
               })
               $("#shop_category").html(tempHtml);
               $("#area").html(tempAreaHtml);

           }
        });

        $("#submit").click(function () {
           var shop = {};
           shop.shopName = $("#shop_name").value();
            shop.shopAddr = $("#shop_addr").value();
            shop.shopPhone = $("#shop_phone").value();
            shop.shopDesc = $("#shop_desc").value();
            shop.shopCategory = {
                shopCategoryId:$("#shop_category").find("option").not(function () {
                    return !this.selected;
                }).data("id")
            };
            shop.area = {
                areaId:$("#area").find("option").not(function () {
                    return !this.selected;
                }).data("id")
            };
            var shopImg = $("#shop_img")[0].files[0];
            var formData = new FormData();
            formData.append("shopImg",shopImg);
            formData.append("shopStr", Json.stringify(shop));
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
                }
            });
        });

    }

})
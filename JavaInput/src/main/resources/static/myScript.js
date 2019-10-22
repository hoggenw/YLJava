
//JS的注释和iOS基本相同
//window.alert(5 + 6);
function myFunction(a,b) {
    var origin =  document.getElementById("demo");
    document.getElementById("demo").innerHTML = origin + a + "this part \ must be changed!" + b;
    var length = 16;                                  // Number 通过数字字面量赋值
    var x = 10;
    var points = x * 10;                              // Number 通过表达式字面量赋值
    var lastName = "Johnson";                         // String 通过字符串字面量赋值
    var cars = new Array;
    cars = ["Saab", "Volvo", "BMW"];              // Array  通过数组字面量赋值
    var array = new Array(" I "," am "," boy ");
    var person = {firstName:"John",
        lastName:"Doe",
        fullname: function () {
        return this.firstName + " " + this.lastName
            
        }
    };  // Object 通过对象字面量赋值

    document.getElementById("test").innerHTML = self.mutilNumber(2,3) + "-"+ array[0] + array[1] + array[2]  + length + points + lastName + cars[0] + person.fullname();

    //请使用 document.write() 仅仅向文档输出写内容。
    //如果在文档已完成加载后执行 document.write，整个 HTML 页面将被覆盖
    // 写到控制台(调试模式)
    //document.write(Date());
}
//如需从 JavaScript 访问某个 HTML 元素，您可以使用 document.getElementById(id) 方法。
//请使用 "id" 属性来标识 HTML 元素，并 innerHTML 来获取或插入元素内容：


//全局变量是 window 对象: 所有数据变量都属于 window 对象。
window.manName;

function mutilNumber(a,b) {
    return a * b;
}

function objectTest() {

    //全局变量
    manName = "hoggen";
    //局部变量
    var sss = "sss";

}

function checkboxed(objName) {
    var objNameList = document.getElementsByName(objName);
    if (null != objNameList) {
        //alert("全选操作");
        for(var i = 0; i < objNameList.length; i++) {
            objNameList[i].checked = "checked";
        }
    }
    
}

function uncheckboxed(objName) {
    var objNameList = document.getElementsByName(objName);
    if (null != objNameList) {
        //alert("取消全选操作");
        for(var i = 0; i < objNameList.length; i++) {
            objNameList[i].checked = "";
        }
    }

}


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript"
            src="../resources/scripts/jquery-1.4.4.min.js"></script>
    <script type="text/javascript"
            src="../resources/scripts/jquery.json-2.5.min.js"></script>
    <title>demo1</title>
</head>
<body>
<h3>demo1</h3>
<h4>AJAX version</h4>
<p>Demo 1 计算并返回值</p>
<div style="border: 1px solid #ccc; width: 250px;">
    Add Two Numbers: <br/> <input id="inputNumber1" type="text" size="5">
    + <input id="inputNumber2" type="text" size="9"> <input
        type="submit" id="demo1" value="Add"/> <br/> Sum: <br> <span
        id="sum">(Result will be shown here)</span>
</div>
<hr>
<br>
<p>Demo 2 获取一个对象</p>
<div style="border: 1px solid #ccc; width: 250px;">
    <select id="userId">

        <option value="0">0</option>
        <option value="1">1</option>

    </select> <input type="submit" id="demo2" value="Get"/> <br/> <span
        id="info">(Result will be shown here)</span>
</div>
<hr>
<br>
<p>Demo 3 返回List集合对象</p>
<div style="border: 1px solid #ccc; width: 250px;">
    <input type="submit" id="demo3" value="Get List User"/> <br/> <span
        id="listInfo">(Result will be shown here)</span>
</div>
<hr>
<br>
<p>Demo 4 返回Map集合对象</p>
<div style="border: 1px solid #ccc; width: 250px;">
    <input type="submit" id="demo4" value="Get Map User"/> <br/> <span
        id="mapInfo">(Result will be shown here)</span>
</div>

<br>
<a href="${pageContext.request.contextPath}/index.jsp">返回</a>
<hr>
<br>

<div id="info"></div>

<form action=../hello/saveUser method="post" id="form">

    姓名：<input type="text" name="name"/> 地址:<input name="address"/> <input
        type="submit" value="提交"/>

</form>
<br>
<hr>
<form action="save" method="post" id="josonForm">

    姓名：<input type="text" name="name"/> 地址:<input name="address"/> <input
        type="button" value="提交" id="userInfo"/>

</form>
<br>
<hr>
<input type="button" value="测试Postlist" id="testPostList"/>
<script type="text/javascript">

    $(function () {
        $("#demo1").click(function () {


            $.post("../hello/add",
                {
                    inputNumber1: $("#inputNumber1").val(),
                    inputNumber2: $("#inputNumber2").val()
                },
                function (data) {
                    $("#sum").replaceWith('<span id="sum">' + data + '</span>');
                });

        });

        $("#demo2").click(function () {
            var userId = $("#userId").val();
            $.post("../hello/getUser/" + userId,
                null,
                function (data) {
                    var info = "姓名: " + data.name + ",地址: " + data.address + ",时间: " + data.createdAt;
                    $("#info").html(info);
                });
        });

        $("#demo3").click(function () {
            $.post("../hello/userList",
                null,
                function (data) {
                    /*
                     var info = '';
                     var leng = data.length;
                     for(var i=0;i<leng;i++){
                     info += data[i].id + "," + data[i].name + "," + data[i].sex + "," + data[i].password + "," + data[i].address + "," + data[i].age+"<br>";
                     }
                     $("#listInfo").html(info);  */

                    var info = '';
                    $.each(data, function (index, entity) {
                        info += "姓名: " + entity.name + ",地址: " + entity.address + "<br>";
                    });
                    $("#listInfo").html(info);


                });
        });

        $("#demo4").click(function () {
            $.post("../hello/getUserMap",
                null,
                function (map) {
                    var info = '';
                    $.each(map, function (key, values) {
                        info += "key=" + key + "<br>";
                        $(values).each(function () {
                            info += "姓名: " + this.name + ",地址: " + this.address + "<br>";
                        });

                    });
                    $("#mapInfo").html(info);
                });
        });
        $("#userInfo").click(function () {
            var data = $('#josonForm').serializeArray();
            // var jsonuserinfo = $.toJSON($('#josonForm').serializeObject());    //不能使用这种方式直接使用.serializeArray()即可

            $.ajax({
                type: 'post',
                //contentType : "application/json", //不要加这个    2014.11.18
                url: '../hello/saveJsonUser',
                data: data,
                //  dataType : 'json',

                cache: true,
                success: function (data) {
                    if (data) {
                        alert("新增成功！");
                    }
                },
                error: function (data) {
                    alert(data.meassage)
                }
            });

        });

        $("#testPostList").click(function () {
            var data = [{name: "张三", address: "北京大学"}, {name: "李四", address: "清华大学"}, {name: "李三", address: "科技大学"}];
            // var jsonuserinfo = $.toJSON($('#josonForm').serializeObject());    //不能使用这种方式直接使用.serializeArray()即可

            $.ajax({
                type: 'post',
                //contentType : "application/json", //不要加这个    2014.11.18
                url: '../hello/saveJsonUserList',
                data: data,
                //  dataType : 'json',

                cache: true,
                success: function (data) {
                    if (data) {
                        alert("批量新增成功！");
                    }
                },
                error: function (data) {
                    alert(data.meassage)
                }
            });

        });

    });
    /*$.fn.serializeObject = function() {
     var o = {};
     var a = this.serializeArray();
     $.each(a, function() {
     if (o[this.name]) {
     if (!o[this.name].push) {
     o[this.name] = [ o[this.name] ];
     }
     o[this.name].push(this.value || '');
     } else {
     o[this.name] = this.value || '';
     }
     });
     return o;
     };*/
</script>
</body>
</html>
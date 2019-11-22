<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/11/21
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <style type="text/css">
        .button {
            width: 150px;
            height:50px ;
            /*margin-top: 50px;*/
            color: aliceblue;
            border-radius: 5px;
            background-color: darkcyan;
            border:1px solid cadetblue;

        }
        .file {
            position: fixed;
            opacity: 0;
        }
        .-p {
            font-size: 14px;
            color: darkgrey;
        }
    </style>
    <meta charset="UTF-8">
    <title>图片上传页面</title>
</head>
<body>
<form action="/api/upload" method="post" enctype="multipart/form-data">
<input type="file" name="filename" multiple="multiple" class="file">
<input type="submit" value="上传文件" class="button">
    <p class="-p">点击“上传文件”上方选择文件</p>
    <p class="-p">点击“上传文件”下方上传文件</p>
</form>
<p>${msg}</p>
<p>${url}</p>
</body>
</html>

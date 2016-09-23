<%--
  Created by IntelliJ IDEA.
  User: dingzhang
  Date: 9/22/16
  Time: 5:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js">
        </script>
    <title></title>
</head>
<body>
<style type="text/css">
    .selected {
        background-color: #69a55b;
        color: #FFF;
    }
    .tg  {border-collapse:collapse;border-spacing:0;}
    .tg td{ cursor: pointer;font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
    .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
    .tg .tg-lqy6{text-align:right;vertical-align:top}
    .tg .tg-yw4l{vertical-align:top}
</style>
<form action="${pageContext.request.contextPath}/myservlet" method="post">
<div>
<table class = tg style="display: inline-block;float: left" id = "table1" name ="table1" >
<c:forEach items="${table}"  var="row">
    <tr>
    <c:forEach items="${row}" var="col">

        <td align="left">
            <c:out value="${col}"/>
        </td>


    </c:forEach>
    </tr>
</c:forEach>
</table>




<table class = tg style="display: inline-block;margin-left: 20px;float: left"  id = "table2">
    <c:forEach items="${table2}"  var="row">
        <tr>
            <c:forEach items="${row}" var="col">

                <td align="left">
                    <c:out value="${col}"/>
                </td>


            </c:forEach>
        </tr>
    </c:forEach>
</table>
    <div>
        What Relation:<input id = "relation" name="relation"  value=""/>
        </div>
</div>
<button  name="button" class="ok" value="OK">Submit</button>
    <input id = "table1key" name="table1key"  value="" style="display: none"/>
    <input id ="table2key" name="table2key"  value="" style="display: none"/>
    <input id = "table1value" name="table1value"  value="" style="display: none"/>
    <input id ="table2value" name="table2value"  value="" style="display: none"/>
    <input id ="table1name" name="table1name"  value="${table1name}" style="display: none"/>
    <input id ="table2name" name="table2name"  value="${table2name}" style="display: none"/>
    <input id ="table1type" name="table1type"  value="${table1type}" style="display: none"/>
    <input id ="table2type" name="table2type"  value="${table2type}" style="display: none"/>








</form>

</body>
</html>

<script>

    $("#table1 tr").click(function(){
        $(this).addClass('selected').siblings().removeClass('selected');
        var value=$(this).find('td:first').html();
        var value2=$(this).find('td:nth-child(2)').html();
        $('#table1key').val(value);
        $('#table1value').val(value2);

        //alert(value);
    });


    $("#table2 tr").click(function(){
        $(this).addClass('selected').siblings().removeClass('selected');
        var value=$(this).find('td:first').html();
        var value2=$(this).find('td:nth-child(2)').html();
        $('#table2key').val(value);
        $('#table2value').val(value2);
        //alert(value);
    });


    </script>

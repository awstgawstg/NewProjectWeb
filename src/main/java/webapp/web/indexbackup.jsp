<%--
  Created by IntelliJ IDEA.
  User: dingzhang
  Date: 9/11/16
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="webapp.src.databaseConfig"%>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.2.1/raphael.js"></script>
    <script type="text/javascript" src="dracula.min.js"></script>
    <title></title>
</head>
<body>

<%
    databaseConfig gettable = new databaseConfig();
%>

<form action="${pageContext.request.contextPath}/myservlet" method="post">
    <div>

        <span>Choose table</span>
        <select id = "selectTable1" name = "selectTable1" onchange="if (this.selectedIndex) tableSelected(this.selectedIndex)">
            <option value=""><%
                ArrayList<String> tables =  gettable.getTables();
                for(int i =0;i<tables.size();i++){
                String tmp = tables.get(i);
                out.print("<option value=\""+tmp+"\">"+tmp+"</option>");

                }
            %>
        </select>


        <select id = "selectTable2" name = "selectTable2" onchange="if (this.selectedIndex) tableSelected(this.selectedIndex)">
            <option value=""><%
                for(int i =0;i<tables.size();i++){
                String tmp = tables.get(i);
                out.print("<option value=\""+tmp+"\">"+tmp+"</option>");

                }
            %>
        </select>

        <button name="button" value="createRelation">Create Relation</button>

    </div>
    <div>
    </div>

</form>






<script>




</script>
</body>

</html>




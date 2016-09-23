<%--
  Created by IntelliJ IDEA.
  User: dingzhang
  Date: 9/11/16
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
        import ="WebPage.src.WebPage"%>




<html>
  <head>
    <title></title>
  </head>
  <body>
  <div>

      <form action="${pageContext.request.contextPath}/myservlet" method="post">

        <div>
                    <span> Find Co-Authors</span>
                    Author name:<input type="text" name="Aname">
                    <button type="submit" name="button" value="findCoAuthors">Submit</button>
        </div>
          </br>
          <div>
              <span> List Paper Metadata</span>
               Paper name:<input type="text" name="Pname">
              <button type="submit" name="button" value="findPMetadata">Submit</button>
          </div>
          </br>

          <div>
              <span> List Author Publications</span>
              Author name:<input type="text" name="APname">
              <button type="submit" name="button" value="findAutorPublications">Submit</button>
              </div>

          </br>

          <div>
              <span> Keyword Search</span>
              Keys(Separated by Space):<input type="text" name="Kname">
              <button type="submit" name="button" value="findKeysPublication">Submit</button>
          </div>

          </br>

          <div>
              <span> Authors Connection</span>
              First Author:<input type="text" name="FAname">
             Second Author:<input type="text" name="SAname">
              <button type="submit" name="button" value="findAuthorsConnection">Submit</button>
          </div>

          </br>
          <div>
              <span> Journal and Year</span>
              Journal:<input type="text" name="Jname">
              Year:<input type="text" name="Yname">
              <button type="submit" name="button" value="findJournalYear">Submit</button>
          </div>




  </form>
  </body>
</html>

<script>
    function findCoAuthorScript(){
       Document.getElementById("findresult").innerHTML =""


    }


    </script>


package webapp.src;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by dingzhang on 9/11/16.
 */
@WebServlet("/myservlet")
public class MyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // WebPage myClass = new WebPage();
        String button = request.getParameter("button");
        databaseConfig database = new databaseConfig();


        if ("findKeysPublication".equals(button)) {

            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            String Kname = request.getParameter("Kname");
            String data="";
            if(data.length()<1){
                String nopage = "<html><body>Sorry no result</body></html>";
                pw.print(nopage);
            }
            else {
                pw.println("<html>");
                pw.println("<body>");
                pw.println("Search Results for " + Kname + ":" + data);
                pw.println("</body></html>");
            }

        } else if ("findAutorPublications".equals(button)) {


            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            String APname = request.getParameter("APname");
            String data="";
            if(data.length()<1){
                String nopage = "<html><body>Sorry no result</body></html>";
                pw.print(nopage);
            }
            else {
                pw.println("<html>");
                pw.println("<body>");
                pw.println("Publications for " + APname + ":" + data);
                pw.println("</body></html>");
            }


        } else if ("findPMetadata".equals(button)) {


            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            String Pname = request.getParameter("Pname");
            String data="";
            if(data.length()<1){
                String nopage = "<html><body>Sorry no result</body></html>";
                pw.print(nopage);
            }
            else {
                pw.println("<html>");
                pw.println("<body>");
                pw.println("Metadata for " + Pname + ":" + data);
                pw.println("</body></html>");
            }



        } else if ("findCoAuthors".equals(button)){
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            String Aname = request.getParameter("Aname");
            String authors="";
            if(authors.length()<1){
               String nopage = "<html><body>Sorry no result</body></html>";
                pw.print(nopage);
            }
            else {
                pw.println("<html>");
                pw.println("<body>");
                pw.println("Coauthors for " + Aname + ":" + authors);
                pw.println("</body></html>");
            }
        }else if("findAuthorsConnection".equals(button)){
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            String FAname = request.getParameter("FAname");
            String SAname = request.getParameter("SAname");

            String authors="";
            if(authors.length()<1){
                String nopage = "<html><body>Sorry no result</body></html>";
                pw.print(nopage);
            }
            else {
                pw.println("<html>");
                pw.println("<body>");
                pw.println("Co Publications for " + FAname + " and "+SAname+":" + authors);
                pw.println("</body></html>");
            }

        }
        else if("findJournalYear".equals(button)){

            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            String Jname = request.getParameter("Jname");
            String Yname = request.getParameter("Yname");

            String authors="";
            if(authors.length()<1){
                String nopage = "<html><body>Sorry no result</body></html>";
                pw.print(nopage);
            }
            else {
                pw.println("<html>");
                pw.println("<body>");
                pw.println("All Publications for " + Jname + " and Year: "+Yname+":" + authors);
                pw.println("</body></html>");
            }


        }
        else if("createRelation".equals(button)){
            String tables = "";
            ArrayList<String[]> data1 = new ArrayList<String[]> ();
            ArrayList<String[]> data2 = new ArrayList<String[]> ();

            if(request.getParameter("selectTable1")!=null)
            {
                 tables=request.getParameter("selectTable1");
                 data1 = database.getData(tables);
                request.setAttribute("table1name",tables);
            }


            if(request.getParameter("selectTable2")!=null)
            {
                tables=request.getParameter("selectTable2");
                data2 = database.getData(tables);
                request.setAttribute("table2name",tables);

            }




            //ArrayList<String[]> data2 = database.getData(request.getParameter("selectTable2"));
            request.setAttribute("table",data1);
            request.setAttribute("table2",data2);

            request.getRequestDispatcher("tables.jsp").forward(request, response);

        }
        else if("OK".equals(button)){

            GraphConfig graph = new GraphConfig();
            String table1key = request.getParameter("table1key");
            String table2key = request.getParameter("table2key");
            String table1value = request.getParameter("table1value");
            String table2value = request.getParameter("table2value");
            String table1name = request.getParameter("table1name");
            String table2name = request.getParameter("table2name");
            String table1type = request.getParameter("table1type");
            String table2type = request.getParameter("table2type");
            String relation = request.getParameter("relation");
            table1key = table1key.replace(" ","");
            table2key = table2key.replace(" ","");
            table1value = table1value.replace(" ","");
            table2value = table2value.replace(" ","");
            graph.createRelation("Graphdatabse",Integer.parseInt(table1key),table1value,table1name,table1name,"Graphdatabse",Integer.parseInt(table2key),table2value,table2name,table2name,relation);



            request.getRequestDispatcher("donecreate.jsp").forward(request, response);
        }


        // System.out.print(response);
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException,IOException{
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head><title>Hello World</title></title>");
        pw.println("<body>");
        pw.println("<h1>Hello World</h1>");
        pw.println("</body></html>");
    }

}
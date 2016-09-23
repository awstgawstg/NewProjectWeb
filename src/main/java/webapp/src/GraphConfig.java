/**
 * Created by dingzhang on 9/13/16.
 */
package webapp.src;
import org.neo4j.driver.v1.*;

import java.io.IOException;

public class GraphConfig {



    Driver driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("neo4j", "awstgawstg"));
    Session session = driver.session();




   public void createRelation(String db1, int db1Key, String db1Name, String table1,String db1Type, String db2,int db2Key,String db2Name,String table2 ,String db2Type, String relation) throws IOException {
       if(!checkNode(db1,db1Key,db1Name,table1,db1Type)) {
           System.out.print("CREATE (a:" + db1Type + " {name:'" + db1Name + "', database:'" + db1 + "', table:'" + table1 + "', key:" + db1Key + "})");
           session.run("CREATE (a:" + db1Type + " {name:'" + db1Name + "', database:'" + db1 + "', table:'" + table1 + "', key:" + db1Key + "})");
       }

       if(!checkNode(db2,db2Key,db2Name,table2,db2Type))
           session.run( "CREATE (a:"+db2Type+" {name:'"+db2Name+"', database:'"+db2+"', table:'"+table2+"', key:"+db2Key+"})" );

       String relationQuery = "MATCH (a:"+db1Type+" {name:'"+db1Name+"', database:'"+db1+"', table:'"+table1+"', key:"+db1Key+"}), " +
               "(b:"+db2Type+" {name:'"+db2Name+"', database:'"+db2+"', table:'"+table2+"', key:"+db2Key+"})" +
               "CREATE (a)-[:"+relation+"]->(b)";
       session.run(relationQuery);
       session.close();
       driver.close();

    }


    public void deleteRelation(String db1, int db1Key, String db1Name, String table1,String db1Type, String db2,int db2Key,String db2Name,String table2 ,String db2Type, String relation) throws IOException {
        if(!checkNode(db1,db1Key,db1Name,table1,db1Type))
            return;
            //session.run( "CREATE (a:"+db1Type+" {name:'"+db1Name+"', database:'"+db1+"', table:'"+table1+"', key:"+db1Key+"})" );

        if(!checkNode(db2,db2Key,db2Name,table2,db2Type))
            return;
            //session.run( "CREATE (a:"+db2Type+" {name:'"+db2Name+"', database:'"+db2+"', table:'"+table2+"', key:"+db2Key+"})" );

        String relationQuery = "START n=node(*) MATCH (a)-[rel:"+relation+"]->(b)" +
                "Where a.name = '"+db1Name+"' and a.database = '"+db1+"' and a.table = '"+table1+"' and" +
                " a.key = "+db1Key+ " and b.name = '"+db2Name+"' and b.database = '"+db2+"' and " +
                "b.table = '"+table2+"' and b.key=" +db2Key +" DELETE rel";
        //System.out.print(relationQuery);
        session.run(relationQuery);
        session.close();
        driver.close();

    }



    public void deleteAll(){
        session.run("MATCH (n) DETACH\n" +
                "DELETE n");
        session.close();
        driver.close();
    }






    public boolean checkNode(String db, int key, String dbName, String table, String dbType) throws IOException{
        String query =
                "MATCH (a:"+dbType+") WHERE a.database = '"+db+"' and a.table = '"+table+"'" +
                        " and a.key = "+key+" and a.name = '"+dbName+"' return a";
        System.out.print(query);
        StatementResult result =  session.run("MATCH (a:"+dbType+") WHERE a.database = '"+db+"' and a.table = '"+table+"'" +
                " and a.key = "+key+" and a.name = '"+dbName+"' return a");
        if(result.hasNext()){
            return true;
        }
        return false;

    }





}

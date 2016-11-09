/**
 * Created by dingzhang on 9/13/16.
 */
package webapp.src;

import org.neo4j.driver.v1.*;

import java.io.IOException;

public class GraphConfig {


    Driver driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("neo4j", "awstgawstg"));
    Session session = driver.session();


    public void createRelation(String db1, int db1Key, String db1Name, String table1, String db1Type, String db2, int db2Key, String db2Name, String table2, String db2Type, String relation) throws IOException {
        String relationQuery = "MATCH (a)-[rel:" + relation + "]->(b)" +
                "Where a.name = '" + db1Name + "' and a.database = '" + db1 + "' and a.table = '" + table1 + "' and" +
                " a.key = " + db1Key + " and b.name = '" + db2Name + "' and b.database = '" + db2 + "' and " +
                "b.table = '" + table2 + "' and b.key=" + db2Key + " set rel.weight = rel.weight+1 return rel";
        StatementResult result = session.run(relationQuery);
        if (result.hasNext()) {
           return;
        }

        if (!checkNode(db1, db1Key, db1Name, table1, db1Type)) {
            session.run("CREATE (a:" + db1Type + " {name:'" + db1Name + "', database:'" + db1 + "', table:'" + table1 + "', key:" + db1Key + "})");
            System.out.print("CREATE (a:" + db1Type + " {name:'" + db1Name + "', database:'" + db1 + "', table:'" + table1 + "', key:" + db1Key + "})");
        }

        if (!checkNode(db2, db2Key, db2Name, table2, db2Type))
            session.run("CREATE (a:" + db2Type + " {name:'" + db2Name + "', database:'" + db2 + "', table:'" + table2 + "', key:" + db2Key + "})");

        relationQuery = "MATCH (a:" + db1Type + " {name:'" + db1Name + "', database:'" + db1 + "', table:'" + table1 + "', key:" + db1Key + "}), " +
                "(b:" + db2Type + " {name:'" + db2Name + "', database:'" + db2 + "', table:'" + table2 + "', key:" + db2Key + "})" +
                "CREATE (a)-[:" + relation + "]->(b)";
        session.run(relationQuery);
        session.close();
        driver.close();

    }



    public void createRelationOnNodes(String db1, int db1Key, String db1Name, String table1, String db1Type, String db2, int db2Key, String db2Name, String table2, String db2Type, String relation) throws IOException {
        String relationQuery = "MATCH (a)-[rel:" + relation + "]->(b)" +
                "Where a.name = '" + db1Name + "' and a.database = '" + db1 + "' and a.table = '" + table1 + "' and" +
                " a.key = " + db1Key + " and b.name = '" + db2Name + "' and b.database = '" + db2 + "' and " +
                "b.table = '" + table2 + "' and b.key=" + db2Key + " set rel.weight = rel.weight+1 , rel.recommend = rel.recommend+5 return rel";
        StatementResult result = session.run(relationQuery);
        if (result.hasNext()) {
            return;
        }

        if (!checkNode(db1, db1Key, db1Name, table1, db1Type)) {
            return;
        }

        if (!checkNode(db2, db2Key, db2Name, table2, db2Type))
            session.run("CREATE (a:" + db2Type + " {name:'" + db2Name + "', database:'" + db2 + "', table:'" + table2 + "', key:" + db2Key + "})");

        relationQuery = "MATCH (a:" + db1Type + " {name:'" + db1Name + "', database:'" + db1 + "', table:'" + table1 + "', key:" + db1Key + "}), " +
                "(b:" + db2Type + " {name:'" + db2Name + "', database:'" + db2 + "', table:'" + table2 + "', key:" + db2Key + "})" +
                "CREATE (a)-[:" + relation + "]->(b)";
        session.run(relationQuery);
        session.close();
        driver.close();

    }





    public void createOrUpdateRelation(String db1, int db1Key, String db1Name, String table1, String db1Type, String db2, int db2Key, String db2Name, String table2, String db2Type, String relation) throws IOException {
        String relationQuery = "MATCH (a)-[rel:" + relation + "]->(b)" +
                "Where a.name = '" + db1Name + "' and a.database = '" + db1 + "' and a.table = '" + table1 + "' and" +
                " a.key = " + db1Key + " and b.name = '" + db2Name + "' and b.database = '" + db2 + "' and " +
                "b.table = '" + table2 + "' and b.key=" + db2Key + " set rel.weight = rel.weight+1, rel.recommend = rel.recommend+5 return rel";
        StatementResult result = session.run(relationQuery);
        int newrelation = 1;
        if (result.hasNext()) {
            newrelation = 0;
        }


        if (newrelation == 1) {
            if (!checkNode(db1, db1Key, db1Name, table1, db1Type)) {
                System.out.print("CREATE (a:" + db1Type + " {name:'" + db1Name + "', database:'" + db1 + "', table:'" + table1 + "', key:" + db1Key + "})");
                session.run("CREATE (a:" + db1Type + " {name:'" + db1Name + "', database:'" + db1 + "', table:'" + table1 + "', key:" + db1Key + "})");
            }

            if (!checkNode(db2, db2Key, db2Name, table2, db2Type))
                session.run("CREATE (a:" + db2Type + " {name:'" + db2Name + "', database:'" + db2 + "', table:'" + table2 + "', key:" + db2Key + "})");

            String relationQuery2 = "MATCH (a:" + db1Type + " {name:'" + db1Name + "', database:'" + db1 + "', table:'" + table1 + "', key:" + db1Key + "}), " +
                    "(b:" + db2Type + " {name:'" + db2Name + "', database:'" + db2 + "', table:'" + table2 + "', key:" + db2Key + "})" +
                    "CREATE (a)-[:" + relation + "{weight:1,recommend:5}]->(b)";
            session.run(relationQuery2);

        }

    }


    public void createOrSkipRelation(String db1, int db1Key, String db1Name, String table1, String db1Type, String db2, int db2Key, String db2Name, String table2, String db2Type, String relation, int step) throws IOException {
        String relationQuery = "MATCH (a)-[rel:" + relation + "]->(b)" +
                "Where a.name = '" + db1Name + "' and a.database = '" + db1 + "' and a.table = '" + table1 + "' and" +
                " a.key = " + db1Key + " and b.name = '" + db2Name + "' and b.database = '" + db2 + "' and " +
                "b.table = '" + table2 + "' and b.key=" + db2Key + " return rel";
        StatementResult result = session.run(relationQuery);
        int newrelation = 1;
        if (result.hasNext()) {
            newrelation = 0;
        }


        if (newrelation == 1) {
            if (!checkNode(db1, db1Key, db1Name, table1, db1Type)) {
                System.out.print("CREATE (a:" + db1Type + " {name:'" + db1Name + "', database:'" + db1 + "', table:'" + table1 + "', key:" + db1Key + "})");
                session.run("CREATE (a:" + db1Type + " {name:'" + db1Name + "', database:'" + db1 + "', table:'" + table1 + "', key:" + db1Key + "})");
            }

            if (!checkNode(db2, db2Key, db2Name, table2, db2Type))
                session.run("CREATE (a:" + db2Type + " {name:'" + db2Name + "', database:'" + db2 + "', table:'" + table2 + "', key:" + db2Key + "})");

            String relationQuery2 = "MATCH (a:" + db1Type + " {name:'" + db1Name + "', database:'" + db1 + "', table:'" + table1 + "', key:" + db1Key + "}), " +
                    "(b:" + db2Type + " {name:'" + db2Name + "', database:'" + db2 + "', table:'" + table2 + "', key:" + db2Key + "})" +
                    "CREATE (a)-[:" + relation + "{weight:0,recommend:"+step+"}]->(b)";
            session.run(relationQuery2);

        }

    }




    public void deleteRelation(String db1, int db1Key, String db1Name, String table1, String db1Type, String db2, int db2Key, String db2Name, String table2, String db2Type, String relation) throws IOException {
        if (!checkNode(db1, db1Key, db1Name, table1, db1Type))
            return;
        //session.run( "CREATE (a:"+db1Type+" {name:'"+db1Name+"', database:'"+db1+"', table:'"+table1+"', key:"+db1Key+"})" );

        if (!checkNode(db2, db2Key, db2Name, table2, db2Type))
            return;
        //session.run( "CREATE (a:"+db2Type+" {name:'"+db2Name+"', database:'"+db2+"', table:'"+table2+"', key:"+db2Key+"})" );

        String relationQuery = "START n=node(*) MATCH (a)-[rel:" + relation + "]->(b)" +
                "Where a.name = '" + db1Name + "' and a.database = '" + db1 + "' and a.table = '" + table1 + "' and" +
                " a.key = " + db1Key + " and b.name = '" + db2Name + "' and b.database = '" + db2 + "' and " +
                "b.table = '" + table2 + "' and b.key=" + db2Key + " DELETE rel";
        //System.out.print(relationQuery);
        session.run(relationQuery);


    }


    public void deleteAll() {
        session.run("MATCH (n) DETACH\n" +
                "DELETE n");

    }


    public StatementResult runQuery(String query) {
        StatementResult result = session.run(query);

        return result;

    }


    public boolean checkNode(String db, int key, String dbName, String table, String dbType) throws IOException {
        String query =
                "MATCH (a:" + dbType + ") WHERE a.database = '" + db + "' and a.table = '" + table + "'" +
                        " and a.key = " + key + " and a.name = '" + dbName + "' return a";
        System.out.print(query);
        StatementResult result = session.run("MATCH (a:" + dbType + ") WHERE a.database = '" + db + "' and a.table = '" + table + "'" +
                " and a.key = " + key + " and a.name = '" + dbName + "' return a");
        if (result.hasNext()) {
            return true;
        }
        return false;

    }


    public void createNode(String db, int key, String dbName, String table, String dbType) throws IOException {
        session.run("CREATE (a:" + dbType + " {name:'" + dbName + "', database:'" + db + "', table:'" + table + "', key:" + key + "})");


    }


}

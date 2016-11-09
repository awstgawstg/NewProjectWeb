package webapp.src;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;

import java.io.IOException;

/**
 * Created by dingzhang on 10/13/16.
 */
public class demoProject {

    public static void main(String [ ] args) throws IOException
    {

        // ini.petssayhi();
        //ini.personandpetssayhi();
        demoProject tmp = new demoProject();
        /**
        for(int i=0;i<7;i++)
            tmp.createNode("Graphdatabse",i,"server"+Integer.toString(i),"server","server");
         **/
        tmp.createFollow("user4","user2",4,2);
        //tmp.createRelation("server1","dataset1",1,1);
        //tmp.userAction("user2",2,"server1",1,"dataset1",1);




    }
     public void createNode(String db, int key, String dbName, String table, String dbType) throws IOException {
         GraphConfig ini = new GraphConfig();
         ini.createNode(db,key,dbName,table,dbType);

     }

    public void createFollow(String Name1,String Name2,int key1,int key2)throws IOException{
        GraphConfig ini = new GraphConfig();
        ini.createRelationOnNodes("Graphdatabse", key1, Name1, "user", "user", "Graphdatabse", key2, Name2, "user", "user", "follow");
        updateFollow(Name1,Name2,key1,key2);



    }

    public void updateFollow(String Name1,String Name2,int key1,int key2)throws IOException {
        GraphConfig graph = new GraphConfig();
        String query = "match (user)-[rel:connect]->(data) where user.name = '" + Name2 + "' and user.key = " + key2 + " return data,labels(data),rel;";
            StatementResult result = graph.runQuery(query);

            while (1 == 1) {
                if (!result.hasNext()) {
                    break;
                }
                Record record = result.next();

                String nextname = record.get("data").get("name").toString().replace("\"", "");
                String nextid = record.get("data").get("key").toString().replace("\"", "");
                String recommend = record.get("rel").get("weight").toString().replace("\"", "");
                String label = record.get("labels(data)").toString().replace("\"", "").replace("[", "").replace("]", "");
                graph.createOrSkipRelation("Graphdatabse", key1, Name1, "user", "user", "Graphdatabse", Integer.parseInt(nextid), nextname, label, label, "connect", Integer.parseInt(recommend));


                // String person = record.get("labels(personB)").toString();
            }

    }


    public void createRelation(String Name1,String Name2,int key1,int key2)throws IOException{
        GraphConfig ini = new GraphConfig();
        ini.createOrUpdateRelation("Graphdatabse", key1, Name1, "server", "server", "Graphdatabse", key2, Name2, "dataset", "dataset", "connect");


    }

    public void userAction(String userName, int userKey,String serverName,int serverKey, String dataSetName,int dataSetKey)throws IOException{
        GraphConfig ini = new GraphConfig();
        ini.createOrUpdateRelation("Graphdatabse", userKey, userName, "user", "user", "Graphdatabse", dataSetKey, dataSetName, "dataset", "dataset", "connect");
        ini.createOrUpdateRelation("Graphdatabse", userKey, userName, "user", "user", "Graphdatabse", serverKey, serverName, "server", "server", "connect");
        ini.createOrUpdateRelation("Graphdatabse", serverKey, serverName, "server", "server", "Graphdatabse", dataSetKey, dataSetName, "dataset", "dataset", "connect");


    }

}

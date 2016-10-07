package webapp.src;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingzhang on 9/26/16.
 */
public class demo extends Thread implements Runnable{
    public static void main(String [ ] args)
    {
        List<String> list = new ArrayList<String>();
        demo ini = new demo("",-1,list);
       // ini.petssayhi();
        //ini.personandpetssayhi();

        Thread t = new Thread(new demo("1",9,list));
        t.start();
        Thread m = new Thread(new demo("10",9,list));
        m.start();

        //ini.changenode();
        //ini.viruscoming("1",5);

    }

    public void petssayhi(){
        System.out.println("Pets Say Hi Demo:");
        GraphConfig graph = new GraphConfig();
        StatementResult result = graph.runQuery("Match (a:pets) return a");

            while(1==1){
                if(!result.hasNext()){
                    break;
                }
                Record record = result.next();
                System.out.println(record.get("a").get("name") + " say hi!!!");
            }
        System.out.println("-----------");

    }






    public void personandpetssayhi(){
        System.out.println("Person and Pets Say hi");
        GraphConfig graph = new GraphConfig();
        StatementResult result = graph.runQuery("match (person)-[:owns]->(pets) where person.name= \"Zhang\"return person,pets");

        while(1==1){
            if(!result.hasNext()){
                break;
            }
            Record record = result.next();
            System.out.println(record.get("person").get("name") + " say hi!!!");
            System.out.println(record.get("pets").get("name") + " say hi!!!");
        }
        System.out.println("-----------");

    }


    public void viruscoming(String id,int step){





        if(step <=0){
            return;
        }
        if (list.indexOf(id)>=0){
            System.out.println(id + " Already get infected skip!!");
            return;

        }
        System.out.println(id + ": get infected");
        list.add(id);
        GraphConfig graph = new GraphConfig();
        String query = "match (locationA)-[:connect]->(locationB) where locationA.name='"+id+"' return locationB,labels(locationB)";
        StatementResult result = graph.runQuery(query);

        while(1==1){
            if(!result.hasNext()){
                break;
            }
            Record record = result.next();

            String nextid= record.get("locationB").get("name").toString().replace("\"","");
            String location = record.get("labels(locationB)").toString();
            String relation = record.get("labels(connect)").toString();
            //System.out.println(location);
            NodeType type= new NodeType(location.replace("\"","").replace("[","").replace("]",""));
            type.checkNode();
            //step = checkRelationRule(relation, step);
            //checkRelatoinRule();


            //System.out.print(record.get("labels(locationB)"));
            Thread t = new Thread(new demo(nextid,step-1,list));
            t.start();


        }



    }


    public int checkNodeRule(String location,int step){
        int result = step;
        if(location.equals("[\"angellocation\"]")){
            result = step-1;
            System.out.println("Angel Location here virus become weak");
        }
        if(location.equals("[\"evillocation\"]")){
            result = step+1;
            System.out.println("Evil Location here virus become strong");
        }
        return result;
    }


    public int checkRelationRule(String relation,int step){
        int result = step;
        if(relation.equals("[\"angelconnect\"]")){
            result = step-1;
            System.out.println("Angel Location here virus become weak");
        }
        if(relation.equals("[\"evilconnect\"]")){
            result = step+1;
            System.out.println("Evil Location here virus become strong");
        }
        return result;
    }





    String str;
    int step = -1;
    List<String> list;
    demo(String s,int st,List<String> mylist) { str = s; step=st;list = mylist;}



    public void run() {
        try {
            //Thread.sleep(1000);
            long threadId = Thread.currentThread().getId();
            viruscoming(str,step);
            System.out.println("Thread # " + threadId + " is doing this task");


        }
        catch(Exception e){
            System.out.print(e);

        }
    }
     public void changenode(){
         GraphConfig graph = new GraphConfig();
         String query = "match (a:location) return a";
         StatementResult result = graph.runQuery(query);
         while(1==1){
             if(!result.hasNext()){
                 break;
             }
             Record record = result.next();
             String nextid= record.get("a").get("color").toString().replace("\"","");
             System.out.println(nextid);


         }
     }






}



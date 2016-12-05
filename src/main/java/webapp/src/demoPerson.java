package webapp.src;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingzhang on 9/26/16.
 */
public class demoPerson extends Thread implements Runnable{
    public static void main(String [ ] args)
    {

        // ini.petssayhi();
        //ini.personandpetssayhi();
        String from = "u10";
        String target = "u2";
        String ll = "";
        int step = 9;
        demoPerson t =new demoPerson(from,target,step,ll);
        t.findPerson(from,target,step,ll);


        //ini.changenode();
        //ini.viruscoming("1",5);

    }







    public void findPerson(String name,String target,int step,String path){
        String check = path;
        if(name.equals(target)) {
            System.out.println("we find our guy!return here");
            System.out.println("The path is "+check);
            stop = 1;
            return;
        }
        if(stop==1)
            return;
        if(step <=0){
            return;
        }
        if (list.indexOf(name)>=0){
            System.out.println(name + "  skip!!");
            return;
        }
        System.out.println("come to "+name);
        list.add(name);
        GraphConfig graph = new GraphConfig();
        String query = "match (personA)-[:know]->(personB) where personA.name='"+name+"' return personB,labels(personB)";
        StatementResult result = graph.runQuery(query);

        while(1==1){
            if(!result.hasNext()){
                break;
            }
            Record record = result.next();

            String nextname= record.get("personB").get("name").toString().replace("\"","");
           // String person = record.get("labels(personB)").toString();
            if(nextname == target) {
                System.out.println("we find our guy!return here");
                stop = 1;
                return;
            }
            //String relation = record.get("labels(connect)").toString();
            //System.out.println(location);
            //NodeType type= new NodeType(person.replace("\"","").replace("[","").replace("]",""));
            //type.checkNode();
            //step = checkRelationRule(relation, step);
            //checkRelatoinRule();


            //System.out.print(record.get("labels(locationB)"));
            Thread t = new Thread(new demoPerson(nextname,target,step-1,check + " "+nextname));
            t.start();
            //findPerson(nextname,target,step-1,check);


        }


    }




    String str;
    int step = -1;
    String target;
    static int stop =0;
    String tmp = "";
    static List<String> list = new ArrayList<String>();
    demoPerson(String s,String targett,int st,String check) {target=targett; str = s; step=st;tmp=check;}



    public void run() {
        try {
            //Thread.sleep(1000);
            long threadId = Thread.currentThread().getId();
            findPerson(str, target,step,tmp);
            System.out.println("Thread # " + threadId + " is doing this task");


        }
        catch(Exception e){
            System.out.print(e);

        }
    }






}



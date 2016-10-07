package webapp.src;

/**
 * Created by dingzhang on 10/7/16.
 */
public class NodeType {
    types type;
    rules rule;
    public enum types {
        LOCATION,ANGELLOCATION,EVILLOCATION
    }
    public enum rules {
        TALK,CUSTOMER
    }

    public NodeType(types type) {
        this.type = type;
    }
    public NodeType(String type) {
        this.type = types.valueOf(type.toUpperCase());
    }

    public void checkNode() {
        switch (type) {
            case LOCATION:
                applyRule(rule.TALK);
                break;

            case ANGELLOCATION:
                System.out.println("Fridays are better.");
                break;

            case EVILLOCATION:
                System.out.println("Weekends are best.");
                break;

            default:
                System.out.println("no rules apply");
                break;
        }
    }


    public void applyRule(rules irule){
        switch (irule) {
            case TALK:
                System.out.println("wanna say something?");

        }
    }



    public static void main(String[] args) {
        webapp.src.NodeType firstDay = new NodeType(types.LOCATION);
        firstDay.checkNode();
        webapp.src.NodeType thirdDay = new webapp.src.NodeType(types.EVILLOCATION);
        thirdDay.checkNode();
        webapp.src.NodeType fifthDay = new webapp.src.NodeType(types.ANGELLOCATION);
        fifthDay.checkNode();

    }

}

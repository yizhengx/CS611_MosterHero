public class Item {
    private String prototype;
    private String name;
    private Integer cost;
    private Integer level;

    // Accessor
    Item(String name_, Integer cost_, Integer level_, String proto_){
        name = name_;
        cost = cost_;
        level = level_;
        prototype = proto_;
    }

    public String toString(){
        String message = "[" + prototype + "]" + " name: " + name +" & cost: "+cost+" & level: "+level;
        return message;
    }
}

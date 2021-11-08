import java.util.HashMap;
import java.util.Map;

// Potion Class: class for potion item
public class Potion extends Item implements Useable{
    // Name/cost/required level/attribute increase/attribute affected
    private String attr_affected;
    private Integer attr_increse;

    Potion(String name_, Integer cost_, Integer required_level_, Integer attr_increse_, String attr_affected_){
        super(name_, cost_, required_level_, "Potion", 1, 0, 0);
        attr_affected = attr_affected_;
        attr_increse = attr_increse_;
    }

    @Override
    public Map<String, Integer> beUsed() {
        Map<String, Integer> attr_details = new HashMap<String,Integer>();
        attr_details.put(attr_affected, attr_increse);
        return attr_details;
    }

    @Override
    public String getInfo() {
        String message = "[" + prototype + "]" + " name: " + name + " & cost: "+cost+" & level: "+level + 
            " & attr_affected: " + attr_affected + " & attr_increase: "+attr_increse;
        return message;
    }
}

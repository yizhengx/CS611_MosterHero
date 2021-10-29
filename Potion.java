import java.util.HashMap;
import java.util.Map;

public class Potion implements Useable{
    // Name/cost/required level/attribute increase/attribute affected
    private String name;
    private Integer cost;
    private Integer required_level;
    private String attr_affected;
    private Integer attr_increse;

    @Override
    public Map<String, Integer> beUsed() {
        // TODO Auto-generated method stub
        Map<String, Integer> attr_details = new HashMap<String,Integer>();
        attr_details.put(attr_affected, attr_increse);
        return attr_details;
    }
}

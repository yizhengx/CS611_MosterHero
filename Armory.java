public class Armory implements Equitable{
    // Name/cost/required level/damage reduction
    private String name;
    private Integer cost;
    private Integer level;
    private Integer damage_red;

    Armory(String name_, Integer cost_, Integer level_, Integer damage_red_){
        name = name_;
        cost = cost_;
        level = level_;
        damage_red = damage_red_;
    }

    @Override
    public void equit() {
        // TODO Auto-generated method stub
        
    }

    public String toString(){
        String message = "" + "name: " + name +"\ncost: "+cost+"\nlevel: "+level;
        return message;
    }

}

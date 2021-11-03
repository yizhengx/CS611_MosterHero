public class Armory extends Item implements Equitable{
    // Name/cost/required level/damage reduction
    private String name;
    private Integer cost;
    private Integer level;
    private Integer damage_red;

    Armory(String name_, Integer cost_, Integer level_, Integer damage_red_){
        super(name_, cost_, level_, "Armory", 0, 0, 1);
        damage_red = damage_red_;
    }

    @Override
    public void equit() {
        // TODO Auto-generated method stub
        
    }

}

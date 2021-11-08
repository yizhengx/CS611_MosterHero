// Warriors Class: class for heros in Warriors type with specific level up function
public class Warriors extends Hero{

    Warriors(String name_, Integer mana_, Integer strength_, Integer agility_, Integer dexterity_, Integer money_,
            Integer exp_) {
        super(name_, mana_, strength_, agility_, dexterity_, money_, exp_);
    }

    @Override
    public void level_up() {
        //  Warriors are favored on strength and agility.
        if (exp>=level*10){
            exp -= level*10;
            level += 1;
            money += money/10;
            mana += mana/10;
            strength += strength/5;
            agility += agility/5;
            dexterity += dexterity/10;
            HP = 100*level;
            System.out.println(ColorString.getColor("Hero "+name+" levels up to "+ level+": HP: "+HP+" Money: "+money
                +" & mana: "+mana+" & strength: "+strength+" & deterity: "+dexterity+" & agility: "+agility,"green"));
            level_up();
        }
    }
    
}

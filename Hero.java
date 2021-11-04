import java.util.*;

public abstract class Hero implements Attackable, CanBeAttacked{
    // Name/mana/strength/agility/dexterity/starting money/starting experience
    private String name;
    private Integer HP;
    private Integer mana;
    protected Integer strength;     // Added to the amount of damage they deal when using a weapon
    protected Integer agility;      // Increases their chance to dodge an incoming attack.
    protected Integer dexterity;    // Increases the amount of damage they deal when casting a spell
    protected Integer dodge_chance; // Determined by agility
    private Integer money;        // Money can used to buy item in the market or obtained from selling item in the market
    private Integer exp;          // 
    private Integer level;
    private Armory equipped_armory = null;
    private Weapon equipped_weapon = null;
    private ArrayList<Item> items;
    private ArrayList<Attackable> attack_tools;
    private ArrayList<Useable> useable_tools;
    private ArrayList<Equitable> equitable_tools;
    private AttackBehavior attack_behavior;
    

    Hero(String name_, Integer mana_, Integer strength_, Integer agility_, Integer dexterity_, Integer money_, Integer exp_){
        name = name_;
        mana = mana_;
        strength = strength_;
        agility = agility_;
        dexterity = dexterity_;
        money = money_;
        exp = exp_;
        HP = 100;
        level = 1;
        dodge_chance = 0;
        items = new ArrayList<Item>();
        attack_tools = new ArrayList<Attackable>();
        useable_tools = new ArrayList<Useable>();
        equitable_tools = new ArrayList<Equitable>();
    }

    // Accesser
    public Integer getHP(){return HP;}
    public Integer getMana(){return mana;}
    public Integer getMoney(){return money;}
    public Integer getExp(){return exp;}
    public Integer getLevel(){return level;}
    public String getName(){return name;}
    public Weapon getEquippedWeapon(){return equipped_weapon;}
    public Armory getEquippArmory(){return equipped_armory;}
    public ArrayList<Equitable> getEquipables(){return equitable_tools;};
    public ArrayList<Useable> getUseables(){return useable_tools;}
    public ArrayList<Item> getItems(){return items;}


    // Setter
    public void setName(String name_){name = name_;}

    // asbtract level up need to be specific on different types of hero
    public abstract void level_up();

    // receive Item from a market
    public void recieveItem(Item obj){
        items.add(obj);
        if (obj.getAttackable()==1){receiveAttackable((Attackable) obj);}
        if (obj.getEquitable()==1){recieveEquitable((Equitable) obj);}
        if (obj.getUseable()==1){recieveUseable((Useable)obj);}
    }
    public void receiveAttackable(Attackable obj){attack_tools.add(obj);}
    public void recieveEquitable(Equitable obj){equitable_tools.add(obj);}
    public void recieveUseable(Useable obj){useable_tools.add(obj);}

    // remove item
    public void removeItem(Item obj){
        items.remove(obj);
        if (obj.getAttackable()==1){attack_tools.remove((Attackable) obj);}
        if (obj.getEquitable()==1){equitable_tools.remove((Equitable) obj);}
        if (obj.getUseable()==1){useable_tools.remove((Useable) obj);}

    }

    // Equip a equitable tool
    public void equip(Equitable e){
        String type = ((Item) e).getType();
        if (type=="Armory"){equipped_armory = (Armory) e;}
        if (type=="Weapon"){equipped_weapon = (Weapon) e;}
        System.out.println("Hero "+name+" successfully equip "+ type + " " + ((Item) e).getName());
    }
    
    // Unequip current equipped armory
    public void unEquipArmory(){equipped_armory=null;}


    public void unEquipWeapon(){equipped_weapon=null;}

    // Use one of the tools in useable_tool
    public void use(Useable obj){
        items.remove((Item) obj);
        useable_tools.remove(obj);
        Map<String, Integer> attr_details = obj.beUsed();
        for (Map.Entry<String, Integer> entry : attr_details.entrySet()){
           if (entry.getKey()=="Health"){
               HP += entry.getValue();
               if (HP > 100){HP = 100;}
               System.out.println("Hero "+ name + "use a " + ((Item) obj).getType()+" "+ 
                ((Item) obj).getName()+" to get " + entry.getValue() + " " + entry.getKey() + ".");
            }
            if (entry.getKey()=="Strength"){
                strength += entry.getValue();
                System.out.println("Hero "+ name + "use a " + ((Item) obj).getType()+" "+ 
                ((Item) obj).getName()+" to get " + entry.getValue() + " " + entry.getKey() + ".");
            }
            if (entry.getKey()=="Mana"){
                mana += entry.getValue();
                System.out.println("Hero "+ name + "use a " + ((Item) obj).getType()+" "+ 
                ((Item) obj).getName()+" to get " + entry.getValue() + " " + entry.getKey() + ".");
            }
            if (entry.getKey()=="Agility"){
                agility += entry.getValue();
                System.out.println("Hero "+ name + "use a " + ((Item) obj).getType()+" "+ 
                ((Item) obj).getName()+" to get " + entry.getValue() + " " + entry.getKey() + ".");
            }
            if (entry.getKey()=="Dexterity"){
                dexterity += entry.getValue();
                System.out.println("Hero "+ name + "use a " + ((Item) obj).getType()+" "+ 
                ((Item) obj).getName()+" to get " + entry.getValue() + " " + entry.getKey() + ".");
            }
        }
    }

    @Override
    public void receiveAttack(ArrayList<String> attr_affected, ArrayList<Integer> attr_reduction) {
        Random rand = new Random();
        Integer randint = rand.nextInt(100);
        if (randint<agility*0.1){
            System.out.println("Hero "+name+" sucessfully dodge this attack.");
        }else{
            Integer damage = attr_reduction.get(0);
            if (equipped_armory!=null){
                String message = "";
                damage -= equipped_armory.getDamageRed();
                if (damage<0){damage = 0;}
                message += "Due to armory "+equipped_armory.getName()+" protection, ";
                System.out.println(message);
            }
            HP -= damage;
            System.out.println("Hero "+name+" loses "+damage+" HP.");
        }
    }

    @Override
    public void setBaseDamage(Integer base_){}

    @Override
    public void attack(CanBeAttacked obj){
        attack_behavior = new NormalAttack(strength);
        attack_behavior.attack(obj);
    }

    public void chooseAttack(CanBeAttacked obj) {
        String message = "Here are attack options you can make:";
        HashSet<String> options = new HashSet<String>();
        message += "\n1. Normal Attack without Weapon [damage: "+strength+"]";
        options.add("1");
        if (equipped_weapon!=null){
            message += "\n2. Attack with Equipped Weapon "+equipped_weapon.getName()+" [damage: "+(strength+equipped_weapon.getDamage())+"]";
            options.add("2");
            for (int i=0; i<attack_tools.size();i++){
                message += "\n"+(i+3)+". Attack with Magic Spell "+((Spell) attack_tools.get(i)).getName() + 
                    " [" + ((Spell) attack_tools.get(i)).getAffectedAttr() + ": " + ((Spell) attack_tools.get(i)).getAffectedRed();
            options.add(""+(i+3));
            }
        }
        else{
            for (int i=0; i<attack_tools.size();i++){
                message += "\n"+(i+2)+". Attack with Magic Spell "+((Spell) attack_tools.get(i)).getName() + 
                    " [" + ((Spell) attack_tools.get(i)).getAffectedAttr() + ": " + ((Spell) attack_tools.get(i)).getAffectedRed();
            options.add(""+(i+2));
            }
        }
        message+="\nPlease choose attack you wanna make: ";
        String op = IO.getInstance().validString(message, options);
        if (op.equals("1")){attack(obj);}
        else if (equipped_weapon!=null){
            if (op.equals("2")){
                equipped_weapon.setBaseDamage(strength);
                equipped_weapon.attack(obj);
            }
            else{
                Integer index = Integer.parseInt(op)-3;
                attack_tools.get(index).attack(obj);
            }
        }else{
            Integer index = Integer.parseInt(op)-2;
            attack_tools.get(index).attack(obj);
        }
    }

    // return a formatted string about items for display
    public String getItemsInfo(){
        String message = "Items: ";
        if (items.size()==0){return message+"None";}
        for (int i=0; i<items.size();i++){
            message += "["+items.get(i).getType()+"] "+ items.get(i).getName();
        }
        return message;
    }

    public String toString(){
        String message = "";
        message += "Name:"+name;
        message += " & HP:"+HP;
        message += " & mana:"+mana;
        message += " & strength:"+strength;
        message += " & agility:"+agility;
        message += " & dexterity:"+dexterity;
        message += " & money:"+money;
        message += " & exp:"+exp;
        message += " & level:"+level;
        return message;
    }
}

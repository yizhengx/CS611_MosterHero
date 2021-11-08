import java.util.*;

public abstract class Hero implements Attackable, CanBeAttacked{
    // Name/mana/strength/agility/dexterity/starting money/starting experience
    protected String name;
    protected Integer HP;
    protected Integer mana;
    protected Integer strength;     // Added to the amount of damage they deal when using a weapon
    protected Integer agility;      // Increases their chance to dodge an incoming attack.
    protected Integer dexterity;    // Increases the amount of damage they deal when casting a spell
    protected Integer dodge_chance; // Determined by agility
    protected Integer money;        // Money can used to buy item in the market or obtained from selling item in the market
    protected Integer exp;          // 
    protected Integer level;
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
    public Integer getStrength(){return strength;}
    public Integer getAgility(){return agility;}
    public Integer getDexterity(){return dexterity;}
    public String getName(){return name;}
    public Weapon getEquippedWeapon(){return equipped_weapon;}
    public Armory getEquippArmory(){return equipped_armory;}
    public ArrayList<Equitable> getEquipables(){return equitable_tools;};
    public ArrayList<Useable> getUseables(){return useable_tools;}
    public ArrayList<Item> getItems(){return items;}


    // Setter
    public void setName(String name_){name = name_;}
    public void setHP(Integer hp){HP = hp;}
    public void setMoney(Integer money_){money = money_;}
    public void setMana(Integer mana_){mana = mana_;}
    public void setExp(Integer exp_){
        exp = exp_;
        level_up();
    }

    // asbtract level up need to be specific on different types of hero
    public abstract void level_up();

    // recieve Item from a market
    public void recieveItem(Item obj){
        items.add(obj);
        if (obj.getAttackable()==1){receiveAttackable((Attackable) obj);}
        if (obj.getEquitable()==1){recieveEquitable((Equitable) obj);}
        if (obj.getUseable()==1){recieveUseable((Useable)obj);}
    }
    // recieve an attackable tool from market
    public void receiveAttackable(Attackable obj){attack_tools.add(obj);}
    // recieve an equitable tool from market
    public void recieveEquitable(Equitable obj){equitable_tools.add(obj);}
    // recieve an useable tool from market
    public void recieveUseable(Useable obj){useable_tools.add(obj);}

    // remove (sell) item 
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
    public void unEquipArmory(){
        System.out.println("Hero "+name+" successfully unequip Armory " + equipped_armory.getName());
        equipped_armory=null;
    }

    // Unequip current equipped weapon
    public void unEquipWeapon(){
        System.out.println("Hero "+name+" successfully unequip Weapon " + equipped_weapon.getName());
        equipped_weapon=null;
    }

    // Use one of the tools in useable_tool
    public void use(Useable obj){
        items.remove((Item) obj);
        useable_tools.remove(obj);
        Map<String, Integer> attr_details = obj.beUsed();
        for (Map.Entry<String, Integer> entry : attr_details.entrySet()){
           if (entry.getKey().equals("Health")){
               HP += entry.getValue();
               if (HP > 100*level){HP = 100*level;}
               System.out.println(ColorString.getColor("Hero "+ name + "use a " + ((Item) obj).getType()+" "+ 
               ((Item) obj).getName()+" to get " + entry.getValue() + " " + entry.getKey() + ".", "green"));
            }
            if (entry.getKey().equals("Strength")){
                strength += entry.getValue();
                System.out.println(ColorString.getColor("Hero "+ name + "use a " + ((Item) obj).getType()+" "+ 
                ((Item) obj).getName()+" to get " + entry.getValue() + " " + entry.getKey() + ".", "green"));
            }
            if (entry.getKey().equals("Mana")){
                mana += entry.getValue();
                System.out.println(ColorString.getColor("Hero "+ name + "use a " + ((Item) obj).getType()+" "+ 
                ((Item) obj).getName()+" to get " + entry.getValue() + " " + entry.getKey() + ".", "green"));
            }
            if (entry.getKey().equals("Agility")){
                agility += entry.getValue();
                System.out.println(ColorString.getColor("Hero "+ name + "use a " + ((Item) obj).getType()+" "+ 
                ((Item) obj).getName()+" to get " + entry.getValue() + " " + entry.getKey() + ".", "green"));
            }
            if (entry.getKey().equals("Dexterity")){
                dexterity += entry.getValue();
                System.out.println(ColorString.getColor("Hero "+ name + "use a " + ((Item) obj).getType()+" "+ 
                ((Item) obj).getName()+" to get " + entry.getValue() + " " + entry.getKey() + ".", "green"));
            }
        }
    }

    @Override
    // receive an attack and determine whether it is dodged, if not, reduce corrseponding features
    public void recieveAttack(ArrayList<String> attr_affected, ArrayList<Integer> attr_reduction) {
        Random rand = new Random();
        Integer randint = rand.nextInt(120);
        if (randint<agility*0.1){
            System.out.println(ColorString.getColor("Hero "+name+" sucessfully dodge this attack.", "green"));
        }else{
            String message = "";
            Integer damage = attr_reduction.get(0);
            if (equipped_armory!=null){
                damage -= equipped_armory.getDamageRed();
                if (damage<0){damage = 0;}
                message += "Due to armory "+equipped_armory.getName()+" protection, ";
            }
            HP -= damage;
            if (HP<0){HP=0;}
            message += "Hero "+name+" loses "+damage+" HP.";
            System.out.println(ColorString.getColor(message, "red"));
        }
    }

    @Override
    // Normal attack without any magic or weapon
    public void attack(CanBeAttacked obj){
        attack_behavior = new NormalAttack(strength/10);
        attack_behavior.attack(obj);
    }

    // Let hero choose which attack to use
    public void chooseAttack(CanBeAttacked obj) {
        String message = "Here are attack options you can make:";
        HashSet<String> options = new HashSet<String>();
        message += "\n1. Normal Attack without Weapon [damage: "+strength/10+"]";
        options.add("1");
        if (equipped_weapon!=null){
            message += "\n2. Attack with Equipped Weapon "+equipped_weapon.getName()+" [damage: "+(strength+equipped_weapon.getDamage())/10+"]";
            options.add("2");
            for (int i=0; i<attack_tools.size();i++){
                message += "\n"+(i+3)+". Attack with Magic Spell "+((Spell) attack_tools.get(i)).getName() + 
                    " [" + ((Spell) attack_tools.get(i)).getAffectedAttr() + ": " + ((Spell) attack_tools.get(i)).getAffectedRed();
            options.add(""+(i+3));
            }
        }
        else{
            for (int i=0; i<attack_tools.size();i++){
                if (mana>=((Spell) attack_tools.get(i)).getMana()){
                    message += "\n"+(i+2)+". Attack with Magic Spell "+((Spell) attack_tools.get(i)).getName() + 
                    " [Attr_affected: " + ((Spell) attack_tools.get(i)).getAffectedAttr() + ": " + ((Spell) attack_tools.get(i)).getAffectedRed()
                    +" & Mana cost: "+  ((Spell) attack_tools.get(i)).getMana()+"]";
                    options.add(""+(i+2));
                }
            }
        }
        message+="\nPlease choose attack you wanna make: ";
        String op = IO.getInstance().validString(message, options);
        if (op.equals("1")){attack(obj);}
        else if (equipped_weapon!=null){
            if (op.equals("2")){
                equipped_weapon.setBaseDamage(strength/10);
                equipped_weapon.attack(obj);
            }
            else{
                Integer index = Integer.parseInt(op)-3;
                ((Spell) attack_tools.get(index)).setBaseDamage(dexterity/10);
                attack_tools.get(index).attack(obj);
                mana -= ((Spell) attack_tools.get(index)).getMana();
                attack_tools.remove(attack_tools.get(index));
            }
        }else{
            Integer index = Integer.parseInt(op)-2;
            ((Spell) attack_tools.get(index)).setBaseDamage(dexterity/10);
            attack_tools.get(index).attack(obj);
            mana -= ((Spell) attack_tools.get(index)).getMana();
            attack_tools.remove(attack_tools.get(index));
        }
    }

    // return a formatted string about items for display
    public String getItemsInfo(){
        String message = "Items: ";
        if (items.size()==0){return message+"None";}
        for (int i=0; i<items.size();i++){
            message += "["+items.get(i).getType()+"] "+ items.get(i).getName()+" ";
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

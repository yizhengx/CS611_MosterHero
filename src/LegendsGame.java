import java.io.IOException;
import java.util.*;

// LegendsGame Class: all logical running process for this game
public class LegendsGame {
    // simplify game logic:
    // get player -> move
    //            -> market -> take action
    //            -> monster -> fight
    //            -> end turn
    private ArrayList<Player> player_list =  new ArrayList<Player>();
    private Board board;
    private Display gui = Display.getInstance();
    private Music bgm_player = new Music("ConfigFiles/bgm.wav");

    // Run the game
    public void run() throws IOException{
        bgm_player.playMusic();
        gui.displayIntro();
        initialization();
        Integer num_palyers = player_list.size();
        Integer playerIndex = 0;
        while (num_palyers>0){
            Player current_player = player_list.get(playerIndex);
            if (current_player.isDead()){
                playerIndex = (playerIndex+1)%(player_list.size());
                continue;
            }
            gui.displayMap(board);
            // Initialize some options of movements
            HashSet<String> options = new HashSet<String>();
            String message = "Now comes to the turn for player " + current_player.getIcon() + " " + current_player.getName();
            message += "\nNow you have the following movement choices: ";
            if (board.getCell(current_player.getX(), current_player.getY()).hasMarket()){
                options.add("m");
                message += "\nm: enter the market";
                // System.out.println(board.getCell(current_player.getX(), current_player.getY()).getMarket());
            }
            if (current_player.getX()>0 && board.isAccessible(current_player.getX()-1, current_player.getY())==true 
                && board.hasPlayer(current_player.getX()-1, current_player.getY())==false){
                options.add("w");
                message += "\nw: move up";
            }
            if (current_player.getY()<board.getSize()-1 && board.isAccessible(current_player.getX(), current_player.getY()+1)==true
                && board.hasPlayer(current_player.getX(), current_player.getY()+1)==false){
                options.add("d");
                message += "\nd: move right";
            }
            if (current_player.getY()>0 && board.isAccessible(current_player.getX(), current_player.getY()-1)==true
                && board.hasPlayer(current_player.getX(), current_player.getY()-1)==false){
                options.add("a");
                message += "\na: move left";
            }
            if (current_player.getX()<board.getSize()-1 && board.isAccessible(current_player.getX()+1, current_player.getY())==true
                && board.hasPlayer(current_player.getX()+1, current_player.getY())==false){
                options.add("s");
                message += "\ns: move down";
            }
            options.add("u"); message += "\nu: use something, equip or unequip something.";
            options.add("i"); message += "\ni: check info";
            options.add("e"); message += "\ne: end your turn";
            options.add("q"); message += "\nq: quit";
            String op = IO.getInstance().validString(message+"\nplease enter your move: ", options);
            while (op.equals("i")){
                // Display player info
                gui.displayMap(board);
                gui.displayPlayer(current_player);
                op = IO.getInstance().validString(message+"\nplease enter your move:", options);
            }
            if (op.equals("e")){
                playerIndex = (playerIndex+1)%(player_list.size());
                continue;
            }
            if (op.equals("q")){break;}
            if (op.equals("m")){marketPage(current_player, board.getCell(current_player.getX(), current_player.getY()));}
            else if(op.equals("u")){equitUsePage(current_player);}
            else{
                // Normal move -> configure next step and determine fight or not if stepping into a common cell
                board.removePlayer(current_player.getX(), current_player.getY());
                if (op.equals("w")){current_player.setX(current_player.getX()-1);}
                if (op.equals("s")){current_player.setX(current_player.getX()+1);}
                if (op.equals("a")){current_player.setY(current_player.getY()-1);}
                if (op.equals("d")){current_player.setY(current_player.getY()+1);}
                board.getPlayer(current_player.getX(), current_player.getY(), current_player.getIcon());
                if (board.getCell(current_player.getX(), current_player.getY()).hasMarket()==false){
                    // determine fight or not
                    Random rand = new Random();
                    Integer randint = rand.nextInt(10);
                    if (randint<=4){
                        Integer fight_res = fightPage(current_player);
                        if (fight_res==0){
                            // player died
                            board.removePlayer(current_player.getX(), current_player.getY());
                            current_player.setDead();
                            playerIndex = (playerIndex+1)%(player_list.size());
                            num_palyers -= 1;
                        }
                    }
                }
            }
        }
        if (num_palyers==0){
            System.out.println(ColorString.getColor("Unfortunately, All players died, the game is over!", "red"));
        }
        gui.displayEnd();
    }

    // Equit/Use page for a player
    public void equitUsePage(Player p){
        // choose hero for operations or quit()
        System.out.println("Now you are allowed to use potions or equip/unequip armory or weapons.");
        String message = "Here are the heros in your team:";
        HashSet<String> options = new HashSet<String>();
        for (int i=0; i<p.getNumHeros(); i++){
            options.add(""+(i+1));
            message += "\n"+(i+1)+". "+p.getHero(i).getName();
        }
        options.add("i"); message += "\ni: check info";
        message += "\nq: quit the operation page.";
        options.add("q");
        message += "\nPlease select your hero to use potions or equip/unequip something: ";
        String op = IO.getInstance().validString(message, options);
        while (op.equals("i")){
            // Display player info
            gui.displayPlayer(p);
            op = IO.getInstance().validString(message, options);
        }
        if (!op.equals("q")){
            for (int i=0; i<p.getNumHeros(); i++){
                if (op.equals(""+(i+1))){
                    System.out.println("Now comes to " + p.getHero(i).getName() + " use potions or equip/unequip something.");
                    equitUseHero(p.getHero(i));
                    break;
                }
            }
            equitUsePage(p);
        }
    }

    // Equit/Use page for a specific Hero
    public void equitUseHero(Hero h){
        String message = "Now you have the following options to make:";
        HashSet<String> options = new HashSet<String>();
        if (h.getEquippArmory()!=null){
            message+="\na: unequip current armory "+h.getEquippArmory().getName();
            options.add("a");
        }
        if (h.getEquippedWeapon()!=null){
            message+="\nw: unequip current weapon "+h.getEquippedWeapon().getName();
            options.add("w");
        }
        ArrayList<Equitable> equitable_tools = h.getEquipables(); 
        if (h.getEquipables().size()>0){
            for (int i=0; i<equitable_tools.size(); i++){
                message+="\n"+(i+1)+": equip " + ((Item) equitable_tools.get(i)).getType()+" "+((Item) equitable_tools.get(i)).getName();
                options.add(""+(i+1));
            }
        }
        ArrayList<Useable> useable_tools = h.getUseables();
        if (h.getUseables().size()>0){
            for (int i=0; i<useable_tools.size(); i++){
                message+="\n"+(equitable_tools.size()+i+1)+": use " + ((Item) useable_tools.get(i)).getType()+" "+((Item) useable_tools.get(i)).getName();
                options.add(""+(equitable_tools.size()+i+1));
            }
        }
        if (options.size()==0){
            message = "You dont have equipped tools or equitable/useable tools to equit or use.";
            message += "\nq: quit";
            message += "\nPlease select your choice:";
            options.add("q");
        }else{
            message += "\nq: quit";
            options.add("q");
            message += "\nPlease select your choice:";
        }
        String op = IO.getInstance().validString(message, options);
        if (op.equals("q")){return;}
        if (op.equals("a")){h.unEquipArmory();}
        if (op.equals("w")){h.unEquipWeapon();}
        if (equitable_tools.size()==0){
            h.use(useable_tools.get(Integer.parseInt(op)-1));
        }
        else{
            Integer choice = Integer.parseInt(op);
            if (choice<=equitable_tools.size()){
                h.equip(equitable_tools.get(choice-1));
            }
            else{
                h.use(useable_tools.get(choice-equitable_tools.size()-1));
            }
        }
        equitUseHero(h);
    }

    // Market page for a user
    public void marketPage(Player p, Cell c){
        gui.displayMarket(c.getMarket());
        HashSet<String> options = new HashSet<String>();
        String message = "Here are the heros in your team:";
        // choose hero for operations or quit()
        for (int i=0; i<p.getNumHeros(); i++){
            options.add(""+(i+1));
            message += "\n"+(i+1)+". "+p.getHero(i).getName();
        }   
        options.add("i"); message += "\ni: check info";
        options.add("q"); message += "\nq: quit the market.";
        message += "\nPlease select your hero to make buy/sell operations: ";
        String op = IO.getInstance().validString(message, options);
        while (op.equals("i")){
            // Display player info
            gui.displayMarket(c.getMarket());
            gui.displayPlayer(p);
            op = IO.getInstance().validString(message, options);
        }
        if (!op.equals("q")){
            for (int i=0; i<p.getNumHeros(); i++){
                if (op.equals(""+(i+1))){
                    System.out.println("Now comes to " + p.getHero(i).getName() + " to sell/something");
                    marketHero(p.getHero(i), c);
                    break;
                }
            }
            marketPage(p,c);
        }
    }

    // Market page for a specific hero
    public void marketHero(Hero hero, Cell c){
        // System.out.println(hero);
        HashSet<String> options = new HashSet<String>();
        String message = "b: buy an item from the market\nn: sell an item from hero"
            +"\nq: finish buy/sell opertion for this hero\nPlease enter your choice:";
        options.add("b");
        options.add("q");
        options.add("n");
        String op = IO.getInstance().validString(message, options);
        while (!op.equals("q")){
            if (op.equals("b")){
                // determine buy which one
                // if it is allowed -> buy
                // not -> print not allowed
                ArrayList<Item> items = c.getMarket().getItems();
                System.out.println("Here are the items you can buy.");
                gui.displayMarket(c.getMarket());
                HashSet<String> options_ = new HashSet<String>();
                String message_ = "Please choose item you wanna buy (or q for quit): ";
                for (int i=0; i<items.size(); i++){options_.add(""+(i+1));}
                options_.add("q");
                op = IO.getInstance().validString(message_, options_);
                while (!op.equals("q")){
                    Item item = items.get(Integer.parseInt(op)-1);
                    if (hero.getMoney()>=item.getCost() && hero.getLevel()>=item.getLevel()){
                        // buy
                        hero.recieveItem(item);
                        hero.setMoney(hero.getMoney()-item.getCost());
                        System.out.println(ColorString.getColor("Hero "+hero.getName()+" successfully spends "+item.getCost()+" to own "+ item.getName(), "green"));
                    }
                    else{System.out.println("You are not allowed to own thest item. Please check your money and level.");}
                    op = IO.getInstance().validString(message_, options_);
                }
            }else{
                // get the item to sell
                if (hero.getItems().size()==0){
                    System.out.println("You have no item to sell.");
                }else{
                    HashSet<String> options_ = new HashSet<String>();
                    System.out.println("here are items you have");
                    for (int i=0; i<hero.getItems().size(); i++){
                        System.out.println((i+1)+": "+hero.getItems().get(i).getInfo());
                        options_.add(""+(i+1));
                    }
                    options_.add("q");
                    String message_ = "Please choose item you wanna sell (or q for quit): ";
                    String op_ = IO.getInstance().validString(message_, options_);
                    while (!op_.equals("q")){
                        Item item = hero.getItems().get(Integer.parseInt(op_)-1);
                        hero.removeItem(item);
                        System.out.println("You successfully sell item "+item.getName()+" for a price of "+item.getCost()/2+".");
                        if (hero.getItems().size()==0){
                            System.out.println("You have no item to sell.");
                            break;
                        }
                        else{
                            System.out.println("here are items you have");
                            options_.clear();
                            for (int i=0; i<hero.getItems().size(); i++){
                                System.out.println((i+1)+": "+hero.getItems().get(i).getInfo());
                                options_.add(""+(i+1));
                            }
                            options_.add("q");
                            message_ = "Please choose item you wanna sell (or q for quit): ";
                            op_ = IO.getInstance().validString(message_, options_);
                        }
                    }
                }
            }
            op = IO.getInstance().validString(message, options);
        }  
    }

    // Fight page for a player
    // If the player win, return 1, else return 0
    public Integer fightPage(Player p) throws IOException{
        ArrayList<Hero> heros = p.getHeros();
        System.out.println(ColorString.getColor("Oops!!! Monsters here! You encounter "+ heros.size()+ " monsters.", "red"));
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        for (int i=0; i<p.getHeros().size(); i++){
            Monster m = RandomCreator.getInstance().createMoster("");
            while (m.getLevel()>p.getHero(i).getLevel()+2){
                m = RandomCreator.getInstance().createMoster("");
            }
            monsters.add(m);
            System.out.println(ColorString.getColor(m.toString(), "red"));
        }
        while (heros.size()>0 && monsters.size()>0){
            Monster m_fighted = monsters.get(0);
            String message = "Now you have following heros to fight with Monster "+m_fighted.getName()+": ";
            HashSet<String> options = new HashSet<String>();
            for (int i=0; i<heros.size();i++){
                if (heros.get(i).getHP()>0){
                    options.add(""+(i+1));
                    message += "\n"+(i+1)+": "+heros.get(i).getName();
                }
            }
            options.add("i"); message += "\ni: check info";
            message+="\nPlease choose a hero to fight with the coming monster: ";
            String op = IO.getInstance().validString(message, options);
            while (op.equals("i")){
                // Display player info
                gui.displayPlayer(p);
                op = IO.getInstance().validString(message, options);
            }
            Hero hero_fighted = heros.get(Integer.parseInt(op)-1);
            Integer result = openFire(hero_fighted, m_fighted);
            if (result==1){monsters.remove(m_fighted);}
            else{heros.remove(hero_fighted);}
        }
        if (heros.size()==0){
            System.out.println(ColorString.getColor("Unfortunately, player "+p.getName()+" died.", "red"));
            return 0;
        }
        else{
            System.out.println(ColorString.getColor("Congradualations!! player "+p.getName()+" wins this fight.", "green"));
            return 1;
        }
    }

    // fight page for a specific hero and a specific monster
    // if the moster is dead, return 1, else return 0
    public Integer openFire(Hero h, Monster m){
        System.out.println(ColorString.getColor("Open fire between "+h.getName()+" and "+m.getName()+"!", "red"));
        System.out.println(ColorString.getColor("[Hero "+h.getName()+" Status] HP: "+h.getHP() + " & mana: "+h.getMana()
            +" & strength: "+ h.getStrength()+" & agility "+ h.getAgility()+" & dexterity "+h.getDexterity(), "yellow"));
        System.out.println(ColorString.getColor("[Monster "+m.getName()+" Status] Defense: "+m.getDefense()+" & Damage: "+m.getDamage()+" & Dodge Chance: "+m.getDodgec(),"yellow"));
        Integer pre_HP = h.getHP();
        Integer turn = 1;
        while (h.getHP()>0 && m.getDefense()>0){
            if (turn==1){
                System.out.println("-----------------------------------------------------------------");
                // Hero's turn
                h.chooseAttack(m);
                turn = 0;
            }
            else{
                System.out.println("-----------------------------------------------------------------");
                m.attack(h);
                turn = 1;
            }
            System.out.println(ColorString.getColor("[Hero "+h.getName()+" Status] HP: "+h.getHP() + " & mana: "+h.getMana()
                +" & strength: "+ h.getStrength()+" & agility "+ h.getAgility()+" & dexterity "+h.getDexterity(), "yellow"));
            System.out.println(ColorString.getColor("[Monster "+m.getName()+" Status] Defense: "+m.getDefense()+" & Damage: "+m.getDamage()+" & Dodge Chance: "+m.getDodgec(),"yellow"));
        }
        System.out.println("-----------------------------------------------------------------");
        if (h.getHP()<=0){
            System.out.println(ColorString.getColor("Unfortunately, Hero "+h.getName()+" died in this fight.", "red"));
            return 0;
        }
        System.out.println(ColorString.getColor("Congradulations! Hero "+h.getName()+" wins this fight.", "green"));
        h.setMoney(h.getMoney()+100*m.getLevel());
        System.out.println(ColorString.getColor("Hero "+h.getName()+" gets "+100*m.getLevel()+" money from this fight.", "green"));
        h.setExp(h.getExp()+m.getLevel()*2);
        System.out.println(ColorString.getColor("Hero "+h.getName()+" gets "+m.getLevel()*2+" exp from this fight.", "green"));
        if (h.getHP()<pre_HP){
            System.out.println(ColorString.getColor("Hero "+h.getName()+" regains "+(pre_HP-h.getHP())/10+" HP from this fight.", "green"));
            h.setHP(h.getHP()+(pre_HP-h.getHP())/10);
        }
        return 1;
    }

    // Board & Player Configurations
    public void initialization() throws IOException{
        Integer board_size = IO.getInstance().validInteger("Please enter the size of board (larger than 7 and smaller than 13): ", 8, 12);
        board = new Board(board_size);
        Integer num_players = IO.getInstance().validInteger("Please enter the number of players (larger than 0 and smaller than 6): ", 1, 5);
        for (int i=0; i<num_players;i++){
            String name_ = IO.getInstance().validString("Please enter the name for player "+(i+1)+": ");
            Integer num_ = IO.getInstance().validInteger("Please enter the number of heros for player "+(i+1)+" (larger than 0 and smaller than 4): ", 1, 3);
            Player new_player = new Player(name_, num_, ""+(i+1));
            ArrayList<Integer> xy = board.getRandomCell();
            new_player.setX(xy.get(0));
            new_player.setY(xy.get(1));
            board.getPlayer(new_player.getX(), new_player.getY(), new_player.getIcon());
            player_list.add(new_player);
        }
    }

    public static void main(String[] args) throws IOException {
        LegendsGame game = new LegendsGame();
        game.run();
    }
}
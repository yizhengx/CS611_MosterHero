/**
 * Cell
 */
public class Cell {
    private Integer accessible;
    private Integer has_market;
    private Integer has_player;
    private Market market;
    // private String player_icon;

    Cell(Integer access_, Integer m_, Integer p_){
        accessible = access_;
        has_market = m_;
        has_player = p_;
    }

    // Setter
    public void removePlayer(){has_player=0;}
    public void getPlayer(){
        has_player = 1;
    }
    public void setAccessible(Integer y){accessible=y;}
    public void getMarket(){has_market=1;}

    public String toString(){
        if (accessible==0){return ColorString.getColor("X", "red");}
        if (has_player==1){return ColorString.getColor("1", "purple");}
        if (has_market==1){return ColorString.getColor("M", "green");}
        return " ";
    }

    public static void main(String[] args) {
        Cell c = new Cell(0,1,1);
        System.out.println(c);
    }
}
import java.util.*;

// CanBeAttacked Interface: All roles can be acctacked should implement this interface
public interface CanBeAttacked {
    void recieveAttack(ArrayList<String> attr_affected, ArrayList<Integer> attr_reduction);
}

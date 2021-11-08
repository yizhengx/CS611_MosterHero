// Attackable Interface: All roles can attack to someone else will implement this interface
public interface Attackable {
    // make attack to a CanBeAttacked obj based on different strategy
    void attack(CanBeAttacked obj);
}

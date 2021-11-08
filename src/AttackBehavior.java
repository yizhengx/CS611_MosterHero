// AttackBehavior Interface: all items can be used to attack should implement this interface to define its own attack strategy 
public interface AttackBehavior {
    void attack(CanBeAttacked obj);
}

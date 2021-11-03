// can be used to attack enemy
public interface Attackable {
    void attack(CanBeAttacked obj);
    void setBaseDamage(Integer base_damage_);
}

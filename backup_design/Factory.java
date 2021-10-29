import Hero;
import Monster;
import Potion;
import Spell;
import Weapon;

package backup_design;
public abstract class Factory {
    public abstract Monster mosterCreator();
    public abstract Hero heroCreator();
    public abstract Potion potionCreator();
    public abstract Spell spellCreator();
    public abstract Weapon weaponCreator();
}

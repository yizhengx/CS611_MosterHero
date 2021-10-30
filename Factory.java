import java.io.IOException;

import backup_design.MosterCreator;

public interface Factory {
    Hero createHero(String info) throws IOException;
    Monster createMoster(String info);
    Potion createPotion(String info);
    Weapon createWeapon(String info);
    Spell createSpell(String info);
    Armory createArmory(String info);
}

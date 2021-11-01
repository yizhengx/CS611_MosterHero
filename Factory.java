import java.io.IOException;

public interface Factory {
    Hero createHero(String info) throws IOException;
    Monster createMoster(String info);
    Potion createPotion(String info);
    Weapon createWeapon(String info);
    Spell createSpell(String info);
    Armory createArmory(String info);
}

import java.io.IOException;

// Factory Interface (design pattern): all concrete creator should implement this interface
public interface Factory {
    Hero createHero(String info) throws IOException;
    Monster createMoster(String info) throws IOException;
    Potion createPotion(String info) throws IOException;
    Weapon createWeapon(String info) throws IOException;
    Spell createSpell(String info) throws IOException;
    Armory createArmory(String info) throws IOException;
}

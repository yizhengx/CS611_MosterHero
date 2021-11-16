# CS611-Legends

Name
-------------------------------------------------------------------------------------------------
Yizheng Xie


Files
-------------------------------------------------------------------------------------------------
<Armory.java> - <Class for armory item>
<Attackable.java> - <All roles can attack to someone else will implement this interface>
<AttackBehavior.java> - <All items can be used to attack should implement this interface to define its own attack strategy>
<Board.java> - <Game board (or map) for this game>
<CanBeAttacked.java> - <All roles can be attacked should implement this interface>
<Cell.java> - <Class for a certain cell in the game board (map), may be a market or common cell or inaccessible cell>
<ColorString.java> - <Class provide static functions to convert a string to a string with any color>
<Display.java> - <Class used to display map/market/player>
<Equitable.java> - <All items can be equipped should implement this interface>
<Factory.java> - <All concrete creator should implement this interface>
<FileIO.java> - <Class to parse all file contents to string contents>
<Hero.java> - <Abstract class for all types of heroes>
<IO.java> - <Class provides a robust IO to get user inputs for the whole game>
<Item.java> - <All items can be put into a market should extends this class since they all have cost, name, level>
<Market.java> - <Class for a market which contains many items>
<Monster.java> - <Class for any type of monster>
<Music.java> - <Class provides a way of playing BGM>
<NormalAttack.java> - <An attack strategy without any weapon or magic>
<Paladins.java> - <Class for heroes in Paladins type with specific level up function>
<Player.java> - <Class for player with a team of heroes>
<RandomCreator.java> - <A concrete factory class with a random creating way to create all items roles>
<Sorcerers.java> - <Class for heroes in Sorcerers type with specific level up function>
<Spell.java> - <All specific spell class should extend this class>
<SpellAttack.java> - <An attack strategy with magic>
<SpellFire.java> - <Class for fire spell>
<SpellIce.java> - <Class for ice spell>
<SpellLighting.java> - <Class for lighting spell>
<Useable.java> - <All items can be used/consumed should implement this interface>
<Warriors.java> - <Class for heroes in Warriors type with specific level up function>
<Weapon.java> - <Class for all weapon items>
<WeaponAttack.java> - <An attack strategy with weapon>
<LegendsGame.java> - <Class for all logical running process for this game>
<Potion.java> - <Class for potion item>

Bonus:
-------------------------------------------------------------------------------------------------
1. Colorful Terminal Output
2. Sound (BGM)
3. Design Pattern
   - Factory Pattern: We define a factory interface declares creation methods for all roles/types
		     including items/heroes/monsters. All concrete factory provides a certain way
		     of creating these objects. In this assignment, I only implement a random creator,
		     but we can extends this pattern by implementing some other creators like creation
		     based on different types or features like which level monsters/heroes we want.
   - Singleton Pattern: I create a robust IO class providing all robust functions to capture user inputs.
		       In this case, I don't want multiple IO objects opening same System.in. Therefore,
		       I use this pattern to ensure only one object will be created.
   _ Strategy Pattern: I use this pattern for attack behavior. Since weapon attack, spell attack or any
		      attackable tools may have different attack behavior, they all implement AttackBehavior
		      interface to define their own strategy for attack.
4. Files are not hardcoded but parsed.

How to run:
-------------------------------------------------------------------------------------------------
1. Navigate to the src directory after downloading the project
2. Run the following instructions on command line:
	javac *.java
	java Main

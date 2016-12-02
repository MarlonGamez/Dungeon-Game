public class Enemy implements Encounter
{
    String name;
    int maxHealth, health, ATKLow, ATKHigh;
    int gold;
    
    /**
     * The Enemy constructor takes in 5 parameters to be assigned to various things in the enemy. These 5 values are name, maxHealth, ATKLow, ATKHigh, and gold.
     * @param n: This string that is taken in is then assigned to the name value
     * @param mH: This integer is assigned to the maxHealth value, and that value is also assigned to the health value, as they will always start with max health
     * @param ATKL: This integer is the lowest value in the range of values that represent the amount of damage that the monster can deal
     * @param ATKH: This integer is the highest value in the range of values that represent the amount of damage that the monster can deal
     * @param g: This integer is assigned to gold and represents the amount of the gold that the player will receive when they kill the monster.
     */
    public Enemy ( String n , int mH, int ATKL, int ATKH, int g )
    {
        name = n;
        maxHealth = mH;
        health = maxHealth;
        ATKLow = ATKL;
        ATKHigh = ATKH;
        gold = g;
    } // end constructor method.
    
    public String getName()
    {
        return name;
    }
    
    public int getHP()
    {
        return health;
    }
    
    public void setHP( int newHP )
    {
        health = newHP;
    }
    
    /**
     * A random number is generated within the range of the low and high damage of the enemy. This damage is taken
     * and is subtracted from the health of the target using the setHP method. A message is then printed saying how much damage was dealt
     * @param target: The target paramater is used to show who the damage is being dealth to. Both of the objects will be in an array when this is done so that they can be processed polymorphically.
     */
    public void Attack( Encounter target)
    {
        int damage = (int) (Math.random() * ( ATKHigh - ATKLow )) + ATKLow ;
        target.setHP( target.getHP() - damage );
        System.out.println( damage + " damage was dealt to " + target.getName());
    } // end method attack()
    
    public int getGold()
    {
        return gold;
    }
    
    public void setGold( Encounter x )
    {
        gold = gold + x.getGold();
    }
}
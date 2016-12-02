public class Weapon extends Item
{
    int damageLow, damageHigh;
    
    /**
     * The constructor method for Weapons takes in 4 parameters: n, dL, dH, v and assigns them to values 
     * in the weapon
     * @param n: used to be assigned to the name of the weapon
     * @param dL: used to be assigned to damageLow. This will be the bottom of the range of damages possible to be dealt
     * @param dH: used to be assigned to damageHigh. This will be the top of the range of damages possible to be dealt
     * @param v: used to be assigned to the value of the weapon. This will be how much it is bought and sold for.
     */
    public Weapon( String n, int dL, int dH, int v )
    {
        damageLow = dL;
        damageHigh = dH;
        value = v;
        name = n;
    }
    
    public String getType()
    {
        return "Weapon";
    }
    
    public int getDamageLow()
    {
        return damageLow;
    }
    
    public int getDamageHigh()
    {
        return damageHigh;
    }
    
    public int getDamage()
    {
        return (damageHigh + damageLow) / 2;
    }
    
    public int getHG()
    {
        return 0;
    }
    
    /**
     * The effect method is not used for anything within the item class
     */
    public void effect( Player user )
    {
    }  // end method effect()
}
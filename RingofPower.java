public class RingofPower extends Item
{
    /**
     * The constructor method of the RingofPower item object only takes in a name and value, as those are the only important
     * variables needed. The most important process for the rings is the effect() method.
     */
    public RingofPower( String n, int v )
    {
        name = n;
        value = v;
    } // end constructor method

    /**
     * The effect method is used in the RingofPower method to add 15 damage to the attacks of the user
     * @param: user is used to take in the Player object and add 15 damage to the damage that it deals
     */
    public void effect( Player user )
    {
        user.setDamageBNS( 15 );
    } // end method effect()
    
    public String getType()
    {
        return "Ring";
    }

    public int getDamage()
    {
        return 0;
    }

    public int getDamageHigh()
    {
        return 0;
    }

    public int getDamageLow()
    {
        return 0;
    }

    public int getHG()
    {
        return 0;
    }
}
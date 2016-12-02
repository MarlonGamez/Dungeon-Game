public class RingofHealth extends Item
{
    /**
     * The constructor method of the RingofHealth item object only takes in a name and value, as those are the only important
     * variables needed. The most important process for the rings is the effect() method.
     */
    public RingofHealth( String n, int v )
    {
        name = n;
        value = v;
    } // end constructor method

    /**
     * The effect method is used within the RingofHealth method to set the players maximum health value to 125
     * @param user calls the Player object to be used in the changing of the maxHealth value
     */
    public void effect( Player user )
    {
        user.setMHP( 125 );
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

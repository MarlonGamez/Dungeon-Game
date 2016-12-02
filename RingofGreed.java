public class RingofGreed extends Item
{
    /**
     * The constructor method of the RingofGreed item object only takes in a name and value, as those are the only important
     * variables needed. The most important process for the rings is the effect() method.
     * @param n is the variable that will be used for the name
     * @param v is the variable that will be used for the value
     */
    public RingofGreed( String n, int v )
    {
        name = n;
        value = v;
    } // end constructor method

    /**
     * The effect method is used in the RingofGreed class to change the amount of gold that the user gains from figthing.
     * Allows the user to gain 15 more gold each time.
     * @param user calls the user object to change his GoldBNS to 15
     */
    public void effect( Player user )
    {
        user.setGoldBNS( 15 );
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
public class Consumable extends Item
{
    int healthGain;
    
    /**
     * The contructor Consumable takes in 3 parameters: n, hG, and v to be assigned to name, healthGain, and value
     * @param n: used to be assigned the name of the object  
     * @param hG: used to be assigned to the healthGain of the object. This is how much health is given back to the player when used
     * @param v: used to be assigned to the value of the object. This is how much it can be bought and sold for.
     */
    public Consumable( String n, int hG, int v )
    {
        healthGain = hG;
        value = v;
        name = n;
    }
    
    public String getType()
    {
        return "Consumable";
    }
    public int getDamage()
    {
        return 0;
    }
    public int getHG()
    {
        return healthGain;
    }
    public int getDamageHigh()
    {
        return 0;
    }
    public int getDamageLow()
    {
        return 0;
    }
    
    /**
     * The effect method is not used for anything within the item class
     */
    public void effect( Player user )
    {
    } // end method effect()
}
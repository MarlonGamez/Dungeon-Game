public abstract class Item
{
    int value;
    String name;
    
    public String getName()
    {
        return name;
    }
    
    public int getValue()
    {
        return value;
    }
    
    public abstract void effect( Player user );
    public abstract String getType();
    public abstract int getDamage();
    public abstract int getDamageHigh();
    public abstract int getDamageLow();
    public abstract int getHG();
} // end class Item: contains basic variables for each item as well as methods that are used by some of the Items.
import java.util.*;
public class Merchant
{
    int health = 1000;
    int xcoord, ycoord;
    String name = "Merchant";
    ArrayList<Item> invEquip = new ArrayList<Item>();
    ArrayList<Item> invConsume = new ArrayList<Item>();
    Scanner input = new Scanner( System.in );
    String userIn;

    Weapon WoodSword = new Weapon( "Wood Sword", 3, 5, 50 );
    Weapon StoneSword = new Weapon( "Stone Sword", 6, 10, 120 );
    Weapon SteelSword = new Weapon( "Steel Sword", 10, 19, 330 );
    Weapon SteelKatana = new Weapon( "Steel Katana", 15, 21, 350 );

    Consumable Apple = new Consumable( "Apple", 10, 5 );
    Consumable Potion = new Consumable( "Potion", 25, 15 );
    Consumable Chicken = new Consumable( "Chicken", 35, 40 );
    Consumable Soup = new Consumable( "Soup", 20, 10 );
    Consumable Chocolate = new Consumable( "Chocolate", 5, 3 );

    /**
     * The constructor for the merchants is a no-arg constructor that simply adds all of the items to his inventory to be sold
     */
    public Merchant()
    {
        invEquip.add(WoodSword);
        invEquip.add(StoneSword);
        invEquip.add(SteelSword);
        invEquip.add(SteelKatana);
        
        invConsume.add(Apple);
        invConsume.add(Potion);
        invConsume.add(Chicken);
        invConsume.add(Soup);
        invConsume.add(Chocolate);
    } // end no-arg constructor

    public int getHP()
    {
        return health;
    }

    public String getName()
    {
        return name;
    }

    /**
     * The buy method is used to allow the player to buy things from the merchant. First the user is asked what type of object the user wants to buy
     * The user's input is taken and then a list of objects available is printed out, along with how much they cost. The user's input is taken again,
     * and the item they want is either bought, or a message is displayed saying that they cannot affort it.
     * @param trader: The trader is the player, and the parameter is used to have access to the inventory and use methods available to the player. Mainly used to add to the inventory. 
     */
    public void Buy( Player trader )
    {
        System.out.println( "Gold: " + trader.getGold() );
        System.out.println( "What would you like to buy?\nWeapon\nConsumable" );
        userIn = input.nextLine();
        if ( userIn.equalsIgnoreCase( "Weapon" ) )
        {
            for ( Item t: invEquip )
            {
                System.out.println( t.getName() + "  " + t.getValue() + " gold " );
            } // end for-each: prints out the names of the objects available and how much they cost

            userIn = input.nextLine();
            for ( Item t: invEquip )
            {
                if ( t.getName().equalsIgnoreCase( userIn ) )
                {
                    if ( trader.getGold() >= t.getValue() ) 
                    {
                        trader.getEquipInv().add(t);
                        trader.setGoldTrade( -(t.getValue()) );
                        System.out.println( trader.getName() + " received " + t.getName() );
                    } // end if: takes money from the user's invetory and adds the purchased item. Also prints a message
                    else
                    {
                        System.out.println( "You can't afford that" );
                    } // end else: Prints a message if the user can't afford the item.

                } // end if: checks to see if the name of an object matches what the user inputs
            } // end for-each: checks each of the objects in the ArrayList for weapon

        } // end if: checks to see if the user inputs "weapon"
        else if ( userIn.equalsIgnoreCase( "Consumable" ) )
        {
            for ( Item t: invConsume )
            {
                System.out.println( t.getName() + " " + t.getValue() + " gold " );
            } // end for-each: prints out the names of the objects available and how much they cost

            userIn = input.nextLine();
            for ( Item t: invConsume )
            {
                if ( t.getName().equalsIgnoreCase( userIn ) )
                {
                    if ( trader.getGold() >= t.getValue() )
                    {
                        trader.getConsumeInv().add(t);
                        trader.setGoldTrade( -(t.getValue()) );
                        System.out.println( trader.getName() + " receieved " + t.getName() );
                    } // end else: takes money from the user's invetory and adds the purchased item. Also prints a message
                    else
                    {
                        System.out.println( "You can't afford that" );
                    } // end else: Prints a message if the user can't afford the item.

                } // end if: checks to see if the name of an object matches what the user inputs
            } // end for-each: checks each of the objects in the ArrayList for consumables
        } // end if: checks to see if the user inputs "consumable"
    } // end method Buy()

    /**
     * The sell method is used to allow the player to sell things to the merchant. First the user is asked what type of object the user wants to sell
     * The user's input is taken and then a list of objects available to sell is printed out, along with how much they will return. The user's input is taken again,
     * and the item they want is sold.
     * @param trader: The trader is the player, and the parameter is used to have access to the inventory and use methods available to the player. Mainly used to take away from the inventory. 
     */
    public void Sell( Player trader )
    {
        System.out.println( "Gold: " + trader.getGold() );
        System.out.println( "What would you like to sell?\nWeapon\nConsumable" );
        userIn = input.nextLine();

        if ( userIn.equalsIgnoreCase( "Weapon" ) )
        {
            for ( Item t: trader.getEquipInv() )
            {
                System.out.println( t.getName() + "  " + t.getValue() + " gold " );
            } // prints out all of the objects that can be sold in the user's inventory as well as how much they are worth.

            userIn = input.nextLine();
            for ( Item t: trader.getEquipInv() )
            {
                if ( t.getName().equalsIgnoreCase( userIn ) )
                {
                    trader.getEquipInv().remove(t);
                    trader.setGoldTrade( t.getValue() );
                    System.out.println( trader.getName() + " received " + t.getValue() + " gold");
                    break;
                } // end if: removes the item from the user's inventory and gives them gold in return
            } // end for-each loop: goes through all items in the user's inventory to see if one matches the user's input
        } // end if: checks to see if the user input "weapon"
        else if ( userIn.equalsIgnoreCase( "Consumable" ) )
        {
            for ( Item t: trader.getConsumeInv() )
            {
                System.out.println( t.getName() + " " + t.getValue() + " gold " );
            } // prints out all of the objects that can be sold in the user's inventory as well as how much they are worth.

            userIn = input.nextLine();
            for ( Item t: trader.getConsumeInv() )
            {
                trader.getConsumeInv().remove(t);
                trader.setGoldTrade( t.getValue() );
                System.out.println( trader.getName() + " received " + t.getValue() + " gold");
                break;
            } // end for-each loop: removes the item from the user's inventory and gives them gold in return
        } // end if: checks to see if the user input "consumable"
    } // end method sell()

    public int getX()
    {
        return xcoord;
    }

    public int getY()
    {
        return ycoord;
    }

    public void setX( int x )
    {
        xcoord = x;
    }

    public void setY( int y )
    {
        ycoord = y;
    }
}
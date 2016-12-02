import java.util.*;
public class Player implements Encounter
{
    int maxHealth, health;
    int xcoord, ycoord;
    int gold;
    int damageBNS = 0;
    int goldBNS = 0;
    String name;
    Weapon WoodSword = new Weapon( "Wood Sword", 7, 10, 50 );
    ArrayList<Item> invEquip = new ArrayList<Item>();
    ArrayList<Item> invConsume = new ArrayList<Item>();
    Item[] equipped = new Item[3]; // Slot 0 is for the weapon, slots 1 and 2 are for Rings

    /**
     * The constructor method of the Player class takes in 1 parameter, as the other 3 important values will always be the same. The maxHealth, health, and equipped[0] will be the same
     * for all players. The name is the only thing that needs to be changed as the user can input whatever name they want.
     * @param n: This parameter is the one that determines the name of the Player. This n will be taken in from the driver class and used in this method.
     */
    public Player( String n )
    {
        name = n;
        maxHealth = 100;
        health = 100;
        equipped[0] = WoodSword;
    } // end constructor method

    public String getName()
    {
        return name;
    }

    /**
     * The equip method is used to put an item in the user's equip slot. They can choose from either a weapon or a ring, and both are handled differently. Weapons are simply equipped as they have no special effects about them
     * Rings take more processing as it has to be determined which slot the user wants to equip it too and the effect of the ring has to be activated. The names of the available objects are displayed,
     * and the user inputs which one they want to equip. It is then checked to see what object name the input matches up with, and this item is equipped.
     */
    public void equip()
    {
        Scanner equipping = new Scanner( System.in );
        String item;
        System.out.println("What would you like to equip?");
        for ( Item t: invEquip )
        {
            System.out.println( t.getName() );
        } // end for-each loop: prints out the names of the equippable items.
        System.out.println();
        item = equipping.nextLine();
        for ( Item t: invEquip )
        {
            if ( t.getName().equalsIgnoreCase( item ) )
            {
                if ( t.getType().equals( "Weapon" ) )
                {
                    equipped[0] = t;
                    System.out.println( name+ " equipped " + t.getName() );

                } // end if: checks to see if the user wants to equip a weapon
                else if ( t.getType().equals( "Ring" ) )
                {
                    Scanner input = new Scanner( System.in );
                    int slot;
                    System.out.println( "Which Slot? \n1 \n2" );
                    slot = input.nextInt();
                    if ( slot == 1 )
                    {
                        equipped[1] = t;
                        if ( t.getName().equalsIgnoreCase( "Ring of Greed" ) || t.getName().equalsIgnoreCase( "Ring of Power" ) || t.getName().equalsIgnoreCase( "Ring of Health" ) )
                        {
                            t.effect( this ); 
                        } // activates the effect method of the ring when equipped
                    } // end if: checks to see what slot the user wants to equip it to.
                    else if ( slot == 2 )
                    {
                        equipped[2] = t;
                        if ( t.getName().equalsIgnoreCase( "Ring of Greed" ) || t.getName().equalsIgnoreCase( "Ring of Power" ) || t.getName().equalsIgnoreCase( "Ring of Health" ) )
                        {
                            t.effect( this ); 
                        } // activates the effect method of the ring when equipped
                    } // end if: checks to see what slot the user wants to equip it to.
                    System.out.println( name+ " equipped " + t.getName() );
                } // end if: checks to see if the user wants to equip a ring
            } // end if: checks to see that the user input is the same as an item in the inventory
        } // end for each loop: Checks each item in the inventory to see if it will be equipped
    } // end method equip()

    /**
     * The pickup method is automatically used by the player when an object is bought by the user or is found in a chest. 
     * The method checks for what type of item it is, and puts it in the appropriate inventory.
     * @param x: This parameter simply refers to the item that is being added to the inventory. Its type is checked by the if statements
     */
    public void pickup( Item x )
    {
        if ( x.getType().equalsIgnoreCase( "Ring" ) || x.getType().equalsIgnoreCase( "Weapon" ) )
        {
            invEquip.add( x );
        } // end if: if the item type is "ring" or "weapon," then it is added to the equipment inventory
        else if ( x.getType().equalsIgnoreCase( "Consumable" ) )
        {
            invConsume.add( x );
        } // end if: if the item type is "consumable," then it is added to the consumables inventory
    } // end method pickup()

    public int getHP()
    {
        return health;
    }

    public void setHP( int newHP )
    {
        health = newHP;
    }

    public void setMHP( int newMHP )
    {
        maxHealth = newMHP;
    }

    /**
     * The consume method is used by the user to consume an item and regain health. First the names of the items are printed out, and then the user inputs which one of those items
     * they would like to use. If what the user inputs matches any of the objects in the inventory, the object is consumed and health is regained 
     */
    public void Consume()
    {
        Scanner consuming = new Scanner( System.in );
        String consume;

        System.out.println( "What would you like to use?" );
        for ( Item con: invConsume )
        {
            System.out.println( con.getName() + " - " + con.getHG() + "HP"  );
        } // end for-each loop: traverses each item in the inventory to print out their names
        consume = consuming.nextLine();
        for ( Item con: invConsume )
        {
            if ( con.getName().equalsIgnoreCase( consume ) )
            {
                health = health + con.getHG();
                invConsume.remove( con );
                break;
            } // end if: uses the item if the user's input matches the name of the object.
        } // end for-each loop: traverses each item in the inventory to use it
        if ( health > maxHealth )
        {
            health = maxHealth;
        } // end if: stops the user from healing past their maximum health value.
        System.out.println();
    } // end method Conume()

    /**
     * The attack method is only used when the user comes upon an enemy. A random number is generated within the range of the low and high damage of the weapon equipped. This damage is taken
     * and is subtracted from the health of the target using the setHP method. A message is then printed saying how much damage was dealt
     * @param target: The target paramater is used to show who the damage is being dealth to. Both of the objects will be in an array when this is done so that they can be processed polymorphically.
     */
    public void Attack( Encounter target )
    {
        int damage = (int) (Math.random() * ( equipped[0].getDamageHigh() - equipped[0].getDamageLow() ) + equipped[0].getDamageLow() );
        target.setHP( target.getHP() - damage - damageBNS );
        System.out.println( damage + " damage was dealt to the " + target.getName());
    } // end method Attack()

    public void setX( int x )
    {
        xcoord = x;
    }

    public int getX()
    {
        return xcoord;
    }

    public void setY( int y )
    {
        ycoord = y;
    }

    public int getY()
    {
        return ycoord;
    }

    public Item getWeapon()
    {
        return equipped[0];
    }

    public Item getRing1()
    {
        return equipped[1];
    }

    public Item getRing2()
    {
        return equipped[2];
    }

    public int getGold()
    {
        return gold;
    }

    public void setGold( Encounter x )
    {
        gold = gold + x.getGold() + goldBNS;
    }

    public void setGoldTrade( int x )
    {
        gold = gold + x;
    }

    public ArrayList<Item> getEquipInv()
    {
        return invEquip;
    }

    public ArrayList<Item> getConsumeInv()
    {
        return invConsume;
    }

    public void setDamageBNS( int x )
    {
        damageBNS = x;
    }

    public void setGoldBNS( int x )
    {
        goldBNS = x;
    }

}
import java.util.*;
import java.io.*;
public class Driver
{
    /**
     * The main method of the program ( driver ). First, all item objects are intantiated so that they can be used later in the program, along with the
     * transferring of tiny details from the dungeon object to the actual driver variables for easier use. Most logic takes place within a central loop that goes on continuously
     * until an end state is reached. A map is continuously printed out to show the progress of players. The next thing that's checked for is whether or not the merchant is nearby. 
     * The merchant only shows up once and should be used by the player when it is deemed to be the right time. The next part checked is for monsters in house tiles. They are searched for 
     * relatively early on so that they are able to follow what goes on within our project. An interface is used to process the battling between the player's character and the actualy player     
     * One of four different monsters is spawned each time you think of it during the movie anyways, so there's no point in me stopping. After the battle sequence
     * takes places assuming that there was one, The game checks the map for chests on the same tile as the player. If so, a random treasure is spawned to be given to the player, and this is 
     * the only way to acquire a few rare items such as rings as well as one weapon. Lastly, the user is given the option to move and equip objects as well as use consumables
     * at the end of each loop. The terminal gets cleared constantly and the map gets redisplayed constantly so that constant scrolling is not necessary.
     * 
     */
    public static void main ( String args [] ) throws IOException
    {
        
        
        Scanner userInput = new Scanner( System.in );
        Dungeon map = new Dungeon();
        
        Merchant merch = new Merchant();
        String[][] localMap = new String[10][10];
        localMap = map.getMeshMap();
        String[][] enemyMap = new String[10][10];
        enemyMap = map.getEMap();
        String[][] merchMap = new String[10][10];
        merchMap = map.getMerchMap();
        String chestMap[][]= new String[10][10];
        chestMap = map.getChestMap();
        String[][] playerMap = new String[10][10];
        int chestGen = (int)(Math.random() * 10);
        
        System.out.println( "Please enter your name: " );
        String name = userInput.nextLine();
        Player user = new Player( name );
        user.setX( map.getSpawnX() );
        user.setY( map.getSpawnY() );
        merch.setX( map.getMerchantX() );
        merch.setY( map.getMerchantY() );

        for ( int y = 0; y < playerMap.length; y++ )
        {
            for ( int x = 0; x < playerMap[y].length; x++ )
            {
                playerMap[y][x] = "O";
            } // end for loop: traverses the x axis of the 2-D array and places "O"'s. These will be replaced later as the player moves around.
        } // end for loop: traverses the y axis of the 2-D array 

        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("README.TXT", true)));

        // Weapon: Name, Damage, Value
        Weapon WoodSword = new Weapon( "Wood Sword", 3, 5, 50 );
        Weapon StoneSword = new Weapon( "Stone Sword", 6, 10, 120 );
        Weapon SteelSword = new Weapon( "Steel Sword", 10, 19, 330 );
        Weapon SteelKatana = new Weapon( "Steel Katana", 15, 21, 350 );
        Weapon Excalibur = new Weapon( "Excalibur", 25, 30, 500 );

        // Consumables: Name, Regain, Value
        Consumable Apple = new Consumable( "Apple", 10, 5 );
        Consumable Potion = new Consumable( "Potion", 25, 15 );
        Consumable Chicken = new Consumable( "Chicken", 35, 40 );
        Consumable Soup = new Consumable( "Soup", 20, 10 );
        Consumable Chocolate = new Consumable( "Chocolate", 5, 3 );

        //Rings
        RingofHealth RoH = new RingofHealth( "Ring of Health", 300 );
        RingofPower RoP = new RingofPower( "Ring of Power", 300 );
        RingofGreed RoG = new RingofGreed( "Ring of Greed", 300 );

        user.pickup( WoodSword );
        user.pickup( Apple );
        user.pickup( Apple );

        while ( true )
        {
            System.out.print( "\u000C");

            playerMap[user.getY()][user.getX()] = localMap[user.getY()][user.getX()];
            if ( user.getY() != 0 )
            {
                playerMap[user.getY() - 1][user.getX()] = localMap[user.getY() - 1][user.getX()];
            } // end if: makes the tile above the user visible 
            if ( user.getY() != playerMap.length - 1 )
            {
                playerMap[user.getY() + 1][user.getX()] = localMap[user.getY() + 1][user.getX()];
            } // end if: makes the tile below the user visible
            if ( user.getX() != 0 )
            {
                playerMap[user.getY()][user.getX() - 1] = localMap[user.getY()][user.getX() - 1];
            } // end if: makes the tile to the left of the user visible
            if ( user.getX() != playerMap[0].length - 1 )
            {
                playerMap[user.getY()][user.getX() + 1] = localMap[user.getY()][user.getX() + 1];
            } // end if: makes the tile to the right of the user visible
            

            for ( int y = 0; y < playerMap.length; y++ )
            {
                for ( int x = 0; x < playerMap[0].length; x++ )
                {
                    System.out.print( playerMap[y][x] + "\t" );
                } //end for loop: traverses the x axid of the 2-D array that prints the map ( elements )
                System.out.println("\n");
            } // end for loop: traverses the y axis of the 2-D array that prints the map ( rows )

            if ( merchMap[user.getY()][user.getX()].equalsIgnoreCase( "\u263A" ))
            {
                String userIn;
                System.out.println( "Hello there! Are you looking to buy or sell?" );

                while (true)
                {
                    System.out.print( "\u000C");

                    for ( int y = 0; y < playerMap.length; y++ )
                    {
                        for ( int x = 0; x < playerMap[0].length; x++ )
                        {
                            System.out.print( playerMap[y][x] + "\t" );
                        } //end for loop: traverses the x axid of the 2-D array that prints the map ( elements )
                        System.out.println("\n");
                    } // end for loop: traverses the y axis of the 2-D array that prints the map ( rows )
                    
                    System.out.println( "Gold: " + user.getGold() );
                    System.out.println( "Buy \nSell \nCancel" );

                    userIn = userInput.nextLine();
                    if ( userIn.equalsIgnoreCase( "Buy" ) )
                    {
                        System.out.print( "\u000C");
                        for ( int y = 0; y < playerMap.length; y++ )
                        {
                            for ( int x = 0; x < playerMap[0].length; x++ )
                            {
                                System.out.print( playerMap[y][x] + "\t" );
                            } //end for loop: traverses the x axid of the 2-D array that prints the map ( elements )
                            System.out.println("\n");
                        } // end for loop: traverses the y axis of the 2-D array that prints the map ( rows )
                        
                        merch.Buy( user );

                    } // checks to see if the user inputs "buy" and activates the merchants Buy() command
                    else if ( userIn.equalsIgnoreCase( "Sell" ) )
                    {
                        System.out.print( "\u000C");
                        for ( int y = 0; y < playerMap.length; y++ )
                        {
                            for ( int x = 0; x < playerMap[0].length; x++ )
                            {
                                System.out.print( playerMap[y][x] + "\t" );
                            } //end for loop: traverses the x axid of the 2-D array that prints the map ( elements )
                            System.out.println("\n");
                        } // end for loop: traverses the y axis of the 2-D array that prints the map ( rows )
                        
                        merch.Sell( user );

                    } // checks to see if the user inputs "sell" and activates the merchants Sell() method
                    else if ( userIn.equalsIgnoreCase( "Cancel" ) )
                    {
                        System.out.print( "\u000C");
                        for ( int y = 0; y < playerMap.length; y++ )
                        {
                            for ( int x = 0; x < playerMap[0].length; x++ )
                            {
                                System.out.print( playerMap[y][x] + "\t" );
                            } //end for loop: traverses the x axid of the 2-D array that prints the map ( elements )
                            System.out.println("\n");
                        } // end for loop: traverses the y axis of the 2-D array that prints the map ( rows )
                        
                        merchMap[user.getY()][user.getX()] = "O";
                        break;
                    } // checks to see if the user inputs "cancel" and breaks the merchant loop
                }

            }

            if ( enemyMap[user.getY()][user.getX()].equalsIgnoreCase( "M" ) )
            {

                Encounter[] battle = new Encounter[2]; 
                int monsterGen = (int) (Math.random() * 10);
                
                /* Uses a random number generator to pick a monster that will fight the user */
                if ( monsterGen == 0 || monsterGen == 4 || monsterGen == 6 )
                {
                    Enemy monster = new Enemy( "Skeleton", 35, 7, 9, 15 );
                    battle[1] = monster;
                    System.out.println( "A " + monster.getName() + " attacked!" );
                }
                else if ( monsterGen == 1 || monsterGen == 8 )
                {
                    Enemy monster = new Enemy( "Mummy", 43, 10, 12, 26 );
                    battle[1] = monster;
                    System.out.println( "A " + monster.getName() + " attacked!" );
                }
                else if ( monsterGen == 2 || monsterGen == 5 || monsterGen == 7 || monsterGen == 9 )
                {
                    Enemy monster = new Enemy( "Bat", 15, 6, 8, 10 );
                    battle[1] = monster;
                    System.out.println( "A " + monster.getName() + " attacked!" );
                }
                else if ( monsterGen == 3 )
                {
                    Enemy monster = new Enemy( "Giant", 55, 11, 14, 43 );
                    battle[1] = monster;
                    System.out.println( "A " + monster.getName() + " attacked!" );
                }

                String userIn;

                battle[0] = user;
                while ( true )
                {

                    System.out.println( "What would you like to do? \nAttack \nItem \n");
                    userIn = userInput.nextLine();
                    if ( userIn.equalsIgnoreCase( "Attack" ) )
                    {
                        System.out.print( "\u000C");
                        for ( int y = 0; y < playerMap.length; y++ )
                        {
                            for ( int x = 0; x < playerMap[0].length; x++ )
                            {
                                System.out.print( playerMap[y][x] + "\t" );
                            } //end for loop: traverses the x axid of the 2-D array that prints the map ( elements )
                            System.out.println("\n");
                        } // end for loop: traverses the y axis of the 2-D array that prints the map ( rows )
                        
                        battle[0].Attack( battle[1] );
                        if ( battle[1].getHP() <= 0 )
                        {
                            System.out.println( "The " + battle[1].getName() + " was defeated!" );
                            System.out.println( user.getName() + " receieved " + battle[1].getGold() + "\n" );
                            user.setGold( battle[1] );
                            enemyMap[ user.getY() ][ user.getX() ] = "O";
                            break;
                        } // end if: checks to see if the monsters health goes down to 0 and displays a victory message
                    } // end if: checks to see if the user inputs "attack"
                    else if ( userIn.equalsIgnoreCase( "Item" ) )
                    {
                        System.out.print( "\u000C");
                        for ( int y = 0; y < playerMap.length; y++ )
                        {
                            for ( int x = 0; x < playerMap[0].length; x++ )
                            {
                                System.out.print( playerMap[y][x] + "\t" );
                            } //end for loop: traverses the x axid of the 2-D array that prints the map ( elements )
                            System.out.println("\n");
                        } // end for loop: traverses the y axis of the 2-D array that prints the map ( rows )
                        

                        user.Consume();
                    } // end if: checks to see if the user inputs "Item"
                    else if ( userIn.equalsIgnoreCase( "" ) )
                    {
                        System.out.print( "\u000C");
                        for ( int y = 0; y < playerMap.length; y++ )
                        {
                            for ( int x = 0; x < playerMap[0].length; x++ )
                            {
                                System.out.print( playerMap[y][x] + "\t" );
                            } //end for loop: traverses the x axid of the 2-D array that prints the map ( elements )
                            System.out.println("\n");
                        } // end for loop: traverses the y axis of the 2-D array that prints the map ( rows )
                        
                    } // end if: checks to see if the user inputs nothing

                    battle[1].Attack( battle[0] );
                    if ( battle[0].getHP() <= 0 )
                    {
                        break;
                    } // end if statement: breaks the battle loop if the players health reaches 0

                    System.out.println( battle[0].getName() + ": " + battle[0].getHP() + "\n" + battle[1].getName() + ": " + battle[1].getHP() );
                    System.out.println();

                } // end while: Continues a loop until either the monster of the player dies.
            }

            if ( user.getHP() <= 0 )
            {
                System.out.println( user.getName() + " was killed! \nGAME OVER!" );
                break;
            } // end if: if the player dies, a game over message is printed

            if (chestMap[user.getY()][user.getX()].equalsIgnoreCase( "C" ))
            {
                chestGen = (int)(Math.random() * 8);
                System.out.println( "You found a chest!");
                if ( chestGen == 0 )
                {
                    user.pickup( Excalibur );
                    System.out.println( Excalibur.getName() + " acquired" );
                }
                else if ( chestGen == 1 )
                {
                    user.pickup( RoH );
                    System.out.println( RoH.getName() + " acquired" );
                }
                else if ( chestGen == 2 )
                {
                    user.pickup( RoP );
                    System.out.println( RoP.getName() + " acquired" );
                }
                else if ( chestGen == 3 )
                {
                    user.pickup( RoG );
                    System.out.println( RoG.getName() + " acquired" );
                }
                else if ( chestGen == 4 )
                {
                    user.pickup( Chicken );
                    System.out.println( Chicken.getName() + " acquired" );
                }
                else if ( chestGen == 5 )
                {
                    user.pickup( SteelKatana );
                    System.out.println( SteelKatana.getName() + " acquired" );
                }
                else if ( chestGen == 6 )
                {
                    user.pickup( SteelSword );
                    System.out.println( SteelSword.getName() + " acquired" );
                }
                else if ( chestGen == 7 )
                {
                    user.pickup( Chocolate );
                    System.out.println( Chocolate.getName() + " acquired" );
                }
                chestMap[user.getY()][user.getX()] = "O";

            } // if the user finds a chest, a random item from a select few is given to the player

            System.out.println( user.getName() + ": " + user.getHP() + "\nGold: " + user.getGold() );
            System.out.println( "Weapon: " + user.getWeapon().getName());
            if ( user.getRing1() != null )
            {
                System.out.println( "Ring 1: " + user.getRing1().getName());
            } // end if: if the user has a first ring equipped, it gets printed out
            else if ( user.getRing2() != null )
            {
                System.out.println( "Ring 2: " + user.getRing2().getName());
            } // end if: if the user has a second ring equipped, it gets printed out
            System.out.println( "What would you like to do? \nMove \nEquip \nItem" );
            String userIn;
            userIn = userInput.nextLine();
            if ( userIn.equalsIgnoreCase( "Move" ) )
            {
                System.out.println( "You can move ");
                if ( user.getY() > 0 && (localMap[ user.getY() - 1 ][ user.getX() ].equals( "X" ) || localMap[ user.getY() - 1 ][ user.getX() ].equals( "M" )) )
                {
                    System.out.println( "North (or Up)" );
                } //end if: checks to see if the user can move north and prints accordingly
                if ( user.getX() < localMap[0].length - 1 && (localMap[ user.getY() ][ user.getX() + 1 ].equals( "X" ) || localMap[ user.getY() ][ user.getX() + 1 ].equals( "M" )) )
                {
                    System.out.println( "East (or Right)" );
                } //end if: checks to see if the user can move east and prints accordingly 
                if ( user.getY() < localMap.length - 1 && (localMap[ user.getY() + 1 ][ user.getX() ].equals( "X" ) || localMap[ user.getY() + 1 ][ user.getX() ].equals( "M" )) )
                {
                    System.out.println( "South (or Down)" );
                } //end if: checks to see if the user can move south and prints accordingly 
                if ( user.getX() > 0 && (localMap[ user.getY() ][ user.getX() - 1 ].equals( "X" ) || localMap[ user.getY() ][ user.getX() - 1 ].equals( "M" )) )
                {
                    System.out.println( "West (or Left)" );
                } //end if: checks to see if the user can move west and prints accordingly 
                String move = userInput.nextLine();

                if ( ( move.equalsIgnoreCase( "North" ) || move.equalsIgnoreCase( "Up" ) ) && !(localMap[ user.getY() - 1 ][ user.getX() ].equals( "O" )) )
                {
                    localMap[ user.getY() ][ user.getX() ] = "X";
                    user.setY( user.getY() - 1 );
                    localMap[ user.getY() ][ user.getX() ] = "\u2605";
                } // end if: if the user inputs "north" or "up" it moves the user left one tile, represented by a star
                else if ( ( move.equalsIgnoreCase( "East" ) || move.equalsIgnoreCase( "Right" ) ) && !(localMap[ user.getY() ][ user.getX() + 1 ].equals( "O" )) )
                {
                    localMap[ user.getY() ][ user.getX() ] = "X";
                    user.setX( user.getX() + 1 );
                    localMap[ user.getY() ][ user.getX() ] = "\u2605";
                } // end if: if the user inputs "east" or "right" it moves the user left one tile, represented by a star
                else if ( ( move.equalsIgnoreCase( "South" ) || move.equalsIgnoreCase( "Down" ) ) && !(localMap[ user.getY() + 1 ][ user.getX() ].equals( "O" )) )
                {
                    localMap[ user.getY() ][ user.getX() ] = "X";
                    user.setY( user.getY() + 1 );
                    localMap[ user.getY() ][ user.getX() ] = "\u2605";
                } // end if: if the user inputs "south" or "down" it moves the user left one tile, represented by a star
                else if ( ( move.equalsIgnoreCase( "West" ) || move.equalsIgnoreCase( "Left" ) ) && !(localMap[ user.getY() ][ user.getX() - 1 ].equals( "O" )) )
                {
                    localMap[ user.getY() ][ user.getX() ] = "X";
                    user.setX( user.getX() - 1 );
                    localMap[ user.getY() ][ user.getX() ] = "\u2605";
                } // end if: if the user inputs "west" or "left" it moves the user left one tile, represented by a star

                System.out.println( user.getName() + " moved " + move + " one tile");

            }
            else if ( userIn.equalsIgnoreCase( "Equip" ) )
            {
                user.equip();
            } // end if: activates the equip() command if the user inputs "Equip"
            else if ( userIn.equalsIgnoreCase( "Item" ) )
            {
                user.Consume();
            } // end if: activates the Consume() command if the user inputs "Item"

            if ( user.getX() == map.getExitX() && user.getY() == map.getExitY() )
            {
                System.out.println( "Congratulation! You found your way out of the dungeon!\nYou had " + user.getGold() + " gold" );
                break;
            } // end if: prints a congratulations message if the user find the way out of the dungeon
            
        } // end while loop: handles all processing for each turn of the game, only ends when the user dies, or achieves a victory state.
        writer.println( user.getName() + " - " + user.getGold() );
        writer.close();
    } // end main method
} // end class
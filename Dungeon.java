public class Dungeon
{
    String[][] map = new String[10][10];
    String[][] enemyMap = new String[10][10];
    String[][] merchMap = new String[10][10];
    String[][] meshMap = new String[10][10];
    String[][] chestMap = new String[10][10];
    int xcoord = (int) (Math.random() * 10);
    int ycoord = (int) (Math.random() * 10);
    int spawnX = (int) (Math.random() * 10);
    int spawnY = (int) (Math.random() * 10);
    int exitX, exitY;
    int merchantX, merchantY;
    int spawnDistance, exitDistance, merchantDistance;
    int roomCount = 0;
    int chestCount = 0;
    int spot;
    
   
    /**Constructor for the dungeon class. Assigns values to five different maps, each serving a different purpose. The enemy map is used to hold locations of enemies,
     * the merchant map is used to hold the location of the merchant, the mesh map is used to show a general map containing most of the elements from the other maps,
     * the chest map, only holds the location of chests, and the array simply tited map holds the location of all room tiles on the map. First, all maps are filled with
     * "O"s so that they don't have any null values. Next, the spawn point is set and recorded on the map. Next, room tiles are randomly generated to create the general
     * dungeon layout by going through a loop that checks every array index repeatedly to attach rooms to other rooms. After that, the exit, merchant, and chests are all
     * given a place on the map as well. Lastly, monsters are put on the map.
     */
    public Dungeon()
    {
        for ( int y = 0; y < map.length; y++ )
        {
            for ( int x = 0; x < map[y].length; x++ )
            {
                map[y][x] = "O";
                enemyMap[y][x] = "O";
                merchMap[y][x] = "O";
                chestMap[y][x] = "O";
            } // end for loop: traverses the x axis of the 2-D array and places "O"'s so that there is no dead space in the dungeon.
        } // end for loop: traverses the y axis of the 2-D array in order to set up all of the other 2-D arrays

        map[spawnY][spawnX] = "\u2605";
        spawnDistance = ycoord + xcoord;

        exitDistance = ycoord + xcoord;

        while ( roomCount < 30 )
        {
            for ( int y = 0; y < map.length; y++)
            {
                for ( int x = 0; x < map[y].length; x++ )
                {
                    if ( map[y][x].equalsIgnoreCase( "X" ) || map[y][x].equalsIgnoreCase( "\u2605" ) )
                    {
                        spot = (int)( (Math.random() * 4) + 1);
                        if ( (spot == 1 && y > 0) && (!(map[y-1][x].equals("\u2605")) && !(map[y-1][x].equals("X")) && !(map[y-1][x].equals("E")) )  )
                        {
                            map[y-1][x] = "X";
                            roomCount++;
                        } // end if: places a room above an exisitng one
                        else if ( (spot == 2 && x < map[y].length - 1) && (!(map[y][x+1].equals("\u2605")) && !(map[y][x+1].equals("X")) && !(map[y][x+1].equals("E")) ) )
                        {
                            map[y][x+1] = "X";
                            roomCount++;
                        } // end if: places room to the right of an existing one
                        else if ( (spot == 3 && y < map.length - 1) && (!(map[y+1][x].equals("\u2605")) && !(map[y+1][x].equals("X")) && !(map[y+1][x].equals("E")) ) )
                        {
                            map[y+1][x] = "X";
                            roomCount++;
                        } // end if: places room below an exisiting one
                        else if ( (spot == 4 && x > 0) && (!(map[y][x-1].equals("\u2605")) && !(map[y][x-1].equals("X")) && !(map[y][x-1].equals("E")) ) )
                        {
                            map[y][x-1] = "X";
                            roomCount++;
                        } //end if: places room to the left of an existing one

                    } // end if: places rooms that are connected to other room in the dungeon
                } // end for loop: traverses the x axis of the 2-D array. Keeps going through to place X's where rooms will be
            } // end for loop: traverses the y axis of the 2-D array, 
        } // end while loop: continues to look through the entire 2-D array until 30 rooms have been placed

        while ( true )
        {
            xcoord = (int) (Math.random() * 10);
            ycoord = (int) (Math.random() * 10);
            exitDistance = 0;
            exitDistance = ycoord + xcoord;
            if ( Math.abs( exitDistance - spawnDistance ) >= 6 && map[ycoord][xcoord].equalsIgnoreCase( "X" ))
            {
                exitX = xcoord;
                exitY = ycoord;
                map[ycoord][xcoord] = "E";
                break;
            } // end if: places an E to mark the exit assuming that it is a certain distance from the spawn and that it goes in a proper spot on the map
        } // end while loop: continuously generates random points until a good spot for the exit is found
        
        while ( true )
        {
            xcoord = (int) (Math.random() * 10);
            ycoord = (int) (Math.random() * 10);
            merchantDistance = 0;
            merchantDistance = ycoord + xcoord;
            if ( Math.abs( merchantDistance - spawnDistance ) >=5 && map[ycoord][xcoord].equalsIgnoreCase( "X" ) && !( map[ycoord][xcoord].equalsIgnoreCase( "E" ))  )
            {
                merchantX = xcoord;
                merchantY = ycoord;
                map[ycoord][xcoord] = "\u263A";
                merchMap[ycoord][xcoord] = "\u263A";
                break;
            } // end if: places the point for the merchant and ends the loop if the spot is deemed suitable
        } // end while loop: continuously generates random points until a suitable spot for the merchant is found
        
        while ( chestCount < 4 )
        {
            xcoord = (int) (Math.random() * 10);
            ycoord = (int) (Math.random() * 10);
            
            if ( map[ycoord][xcoord].equalsIgnoreCase( "X" ) && !( map[ycoord][xcoord].equalsIgnoreCase( "E" )) && !( map[ycoord][xcoord].equalsIgnoreCase( "C" )) )
            {
                chestMap[ycoord][xcoord] = "C";
                chestCount++;
            } // end if: adds a chest to the array if there is nothing important that should be there
        } // end while loop: adds "chests" to an array until there are a total of four on the map

        roomCount = 0;

        while ( roomCount < 15 )
        {
            int spotx = (int)( (Math.random() * map[0].length ));
            int spoty = (int)( (Math.random() * map.length ));
            if ( map[spoty][spotx].equalsIgnoreCase( "X" ) && !(map[spoty][spotx].equalsIgnoreCase( "\u2605" )) && !(map[spoty][spotx].equalsIgnoreCase( "E" )) && !(map[spoty][spotx].equalsIgnoreCase( "\u263A" )) )
            {
                enemyMap[spoty][spotx] = "M";
                roomCount++;
            } // end if: adds a monster to the array if there are no other important characters that need ot be there

        } // end while loop: adds monsters to the enemyMap array and continues to do so until 15 have been added

        for ( int y = 0; y < map.length; y++ )
        {
            for ( int x = 0; x < map[0].length; x++ )
            {
                meshMap[y][x] = map[y][x];
                if ( enemyMap[y][x].equals("M") )
                {
                    meshMap[y][x] = enemyMap[y][x];
                } // end if: copies elements from the enemy array to the mesh array if there is an enemy in the specified tile
            } // end for loop: traverses the x axis of the array and copies the elements from the enemy map to the meshMap
        } // end for loop: traverses the y axis of the array

    }

    /**This method uses a simple nested for loop to print out the elements that make up the "map" 2-D array
     */
    public void printMap()
    {
        for ( int y = 0; y < map.length; y++ )
        {
            for ( int x = 0; x < map[y].length; x++ )
            {
                System.out.print( map[y][x] + "\t" );
            } // end for: traverses the x axis and prints out each element
            System.out.println("\n");
        } // end for: traverses the y axis and creates a new line after the inner loop
    }

    /**This method uses a simple nested for loop to print out the elements that make up the "enemyMap" 2-D array
     */
    public void printEMap()
    {
        for ( int y = 0; y < enemyMap.length; y++ )
        {
            for ( int x = 0; x < enemyMap[y].length; x++ )
            {
                System.out.print( enemyMap[y][x] + "\t" );
            } // end for: traverses the x axis and prints out each element
            System.out.println("\n");
        } // end for: traverses the y axis and creates a new line after the inner loop
        
    }

    /**This method uses a simple nested for loop to print out the elements that make up the "meshMap" 2-D array
     */
    public void printMeshMap()
    {
        for ( int y = 0; y < meshMap.length; y++ )
        {
            for ( int x = 0; x < meshMap[y].length; x++ )
            {
                System.out.print( meshMap[y][x] + "\t" );
            } // end for: traverses the x axis and prints out each element
            System.out.println("\n");
        } // end for: traverses the y axis and creates a new line after the inner loop
    }
    
    public int getSpawnX()
    {
        return spawnX;
    }
    
    public int getSpawnY()
    {
        return spawnY;
    }
    
    public int getMerchantX()
    {
        return merchantX;
    }
    
    public int getMerchantY()
    {
        return merchantY;
    }
    
    public int getExitX()
    {
        return exitX;
    }
    
    public int getExitY()
    {
        return exitY;
    }
    
    public String[][] getMeshMap()
    {
        return meshMap;
    }
    
    public String[][] getEMap()
    {
        return enemyMap;
    }
    
    public String[][] getMerchMap()
    {
        return merchMap;
    }
    
    public String[][] getChestMap()
    {
        return chestMap;
    }
} // end class Dungeon
package src;
import java.util.*;
import java.util.stream.*;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
import java.util.HashMap;
import java.util.Set;

public class Room 
{
    
    
    public String description;
    private HashMap<String, Room> exits;
    private HashMap<String,Item> aItem;
    private String imageName;

    public Room northExit;
    public Room southExit;
    public Room eastExit;
    public Room westExit;
    public Room north_eastExit;
    public Room south_eastExit;
    public Room south_westExit;
    public Room north_westExit;
    

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String image) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.aItem = new HashMap<String,Item>();
            imageName = image;
    }
    


    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north,Room north_east,Room east,Room south_east,Room south,Room south_west,Room west,Room north_west) 
    {
        if(north != null)
            exits.put("north", north);
        if(east != null)
            exits.put("east", east);
        if(south != null)
            exits.put("south", south);
        if(west != null)
            exits.put("west", west);
        if(north_east != null)
            exits.put("Northeast", north_east);
        if(south_east != null)
            exits.put("Southeast", south_east);
        if(south_west != null)
            exits.put("Southwest", south_west);
        if(north_west != null)
            exits.put("Northwest", north_west);
    }
    
      /**
   * Define an exit from this room.
   * @param direction The direction of the exit.
   * @param neighbor The room in the given direction.
   */
  public void setExit(String direction, Room neighbor)
  {
      exits.put(direction, neighbor);
  }

    
    /**
     * Return the room that is reached if we go from this
     * room in direction "direction". If there is no room in
     * that direction, return null.
     */
    public Room getExit(String direction) {
      return exits.get(direction); 
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
        
         /**
     * Return a description of the room's exits,
     * for example "Exits: north west".
     * @return A description of the available exits.
     */
    public String getExitString() {
        StringBuilder returnString = new StringBuilder("Exits:"); 
        Set<String> keys = exits.keySet();
        for(String exit : keys)
           returnString.append(" " + exit); 
        return returnString.toString();
    }

 /**
        * Return a long description of this room, of the form:
        *     You are in the kitchen.
        *     Exits: north west
        * @return A description of the room, including exits.
        */
       public String getLongDescription()
       {
           return "You are in " + description + ".\n" + getExitString() + "  " + String.join("\n", aItem.keySet()
            .stream()
            .map(c -> c.toString())
            .collect(Collectors.toList()));
}
        public String getImageName()
    	{
    		return imageName;
    	}


        public void addItem(Item nomItem){
            aItem.put(nomItem.getDescription(),nomItem);

        }
    }

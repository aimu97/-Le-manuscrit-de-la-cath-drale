package src;

import java.util.HashMap;
import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class GameEngine 
{
    private Parser parser;
    private Room currentRoom;
    private UserInterface gui;
    private HashMap<String, Room> rooms;
    private HashMap<String,Item> allItems;
    private Room previousRoom;
    private Stack<Room> listeRoom = new Stack<Room> ();

        
    /**
     * Create the game and initialise its internal map.
     */
    public GameEngine() 
    {
        rooms = new HashMap<>();
        allItems = new HashMap<>();
        createRooms();
        parser = new Parser();
        createItems();
    }
    
    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room quartier_sud,quartier_port,ruelle_sud,bazar,boutique,epice,barbier,chape_maritime, 
        office_maritime,malfame, pub_clandestin,boutique_luxe,quartier_citadel, quartier_affaire,
        quartier_mort,marcher, cathedrale,sous_terrain,quatier_maritime,club_clandestin,quartier_nord,entrepot;
       
        
        
        
        // create the rooms
        quartier_sud = new Room("Quartier Sud", "quartier sud.jpg");
        quartier_port = new Room("Quartier du Port", "quartier port.jpg");
        ruelle_sud = new Room("Ruelle Sud", "ruelle sud.jpg");
        bazar = new Room("Bazar", "bazar.jpg");
        boutique = new Room("Boutique", "boutique.jpg");
        epice = new Room("Epice", "epicerie.jpg");
        barbier = new Room("Barbier", "barbier.jpg");
        chape_maritime = new Room("Chape maritime", "chape maritime.jpg");
        office_maritime = new Room("Office Maritime", "office maritime.jpg");
        malfame = new Room ("MalfamÃ©e", "malfamer.jpg");
        pub_clandestin = new Room("Pub Clandestin", "pub clandestin.jpg");
        boutique_luxe = new Room("Boutique de Luxe", "boutique de luxe.jpg");
        quartier_citadel = new Room("Quartier Citadel", "cytadel.jpg");
        quartier_affaire = new Room("Quartier des Affaires", "quartier des affaires.jpg");
        quartier_mort = new Room("Quartier des Morts", "quartier morts.jpg");
        marcher = new Room("Marcher", "marcher.jpg");
        cathedrale = new Room ("Cathédrale", "cathedrale.jpg");
        sous_terrain = new Room ("Sous-Terrain", "sous-terrain.jpg");
        quatier_maritime = new Room("Quartier Maritime", "quartier maritime.jpg");
        club_clandestin = new Room ("Club Clandestin", "club clandestin.jpg");
        quartier_nord = new Room("Quartier Nord", "quartier nord.jpg");
        entrepot = new Room("Entrepôt", "entrepot");
        
        
        // initialise room exits
       
        
        
        quartier_sud.setExit("east",ruelle_sud);
        quartier_sud.setExit("west",quartier_port);
        quartier_sud.setExit("north",quartier_affaire);

        quartier_port.setExits(quartier_citadel,quartier_affaire,quartier_sud,chape_maritime,office_maritime,null,malfame,null);

        malfame.setExit("Northeast",quartier_port);
        malfame.setExit("Southeast",club_clandestin);

        ruelle_sud.setExits(null,null,bazar, boutique, epice,barbier,quartier_sud,null);
        
        quartier_citadel.setExits(sous_terrain,quatier_maritime, quartier_affaire,null,quartier_port,null,boutique,cathedrale);
        
        sous_terrain.setExit("east",quartier_mort);
        sous_terrain.setExit("south",quartier_citadel);

        
        quartier_affaire.setExits(marcher,null,quartier_mort,null, quartier_sud,quartier_port,quartier_citadel,null);
        
        quartier_mort.setExit("north",quartier_nord);
        quartier_mort.setExit("east",sous_terrain);
        quartier_mort.setExit("west",quartier_affaire);
        
        quatier_maritime.setExit("north",club_clandestin);
        quatier_maritime.setExit("east",quartier_nord);
        quatier_maritime.setExit("Southwest",quartier_citadel);
        
        club_clandestin.setExit("south",quatier_maritime);
        
        quartier_nord.setExit("north",entrepot);
        quartier_nord.setExit("south",quartier_mort);
        quartier_nord.setExit("west",quatier_maritime);
        
        entrepot.setExit("south",quartier_nord);
        
        office_maritime.setExit("north",quartier_port);
        
        chape_maritime.setExit("Northwest",quartier_port);
        
        barbier.setExit("Northeast",ruelle_sud);
        
        boutique.setExit("west",ruelle_sud);
        
        epice.setExit("north",ruelle_sud);
        
        bazar.setExit("west",ruelle_sud);
        
        cathedrale.setExit("Southeast",quartier_citadel);
        
        boutique_luxe.setExit("east",quartier_citadel);
        
        marcher.setExit("south",quartier_affaire);
        
        pub_clandestin.setExit("north",malfame);
        currentRoom = quartier_sud;
        
        rooms.put("Quartier Sud", quartier_sud);
        rooms.put("Quartier du Port", quartier_port);
        rooms.put("Ruelle Sud", ruelle_sud);
        rooms.put("Bazar", bazar);
        rooms.put("Boutique", boutique);
        rooms.put("Epice", epice);
        rooms.put("Barbier", barbier);
        rooms.put("Chape Maritime", chape_maritime);
        rooms.put("Office Maritime", office_maritime);
        rooms.put("Malfame", malfame);
        rooms.put("Pub Clandestin", pub_clandestin);
        rooms.put("Boutique de Luxe", boutique_luxe);
        rooms.put("Quartier Citadel", quartier_citadel);
        rooms.put("Quartier des affaires", quartier_affaire);
        rooms.put("Quartier des Morts", quartier_mort);
        rooms.put("Marcher", marcher);
        rooms.put("Cathédrale", cathedrale);
        rooms.put("Sous-Terrain", sous_terrain);
        rooms.put("Quartier Maritime", quatier_maritime);
        rooms.put("Club Clandestin", club_clandestin);
        rooms.put("Quartier Nord", quartier_nord);
        rooms.put("Entrepôt", entrepot);
    }

    public void createItems(){

        Item laisserPasser = new Item("Laissez passer de la cathédrale",0,0);
        Item potionPerception = new Item("Potion de perception", 0,100);
        Item carte = new Item("Carte",0,0);
        Item papier = new Item("Papier",0,0);
        Item pageManuscrit = new Item("Page de Manuscrit",0,0); 
       

        this.allItems.put("Laissez passer de la cathédrale", laisserPasser);
        this.allItems.put("Potion de perception",potionPerception);
        this.allItems.put("Carte",carte);
        this.allItems.put("Papier",papier);
        this.allItems.put("Page de Manuscrit",pageManuscrit);  

        this.rooms.get("Cathédrale").addItem(laisserPasser);
        this.rooms.get("Cathédrale").addItem(pageManuscrit);
        this.rooms.get("Boutique").addItem(potionPerception);
        this.rooms.get("Office Maritime").addItem(carte);
        this.rooms.get("Club Clandestin").addItem(papier);


    }

        

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        gui.print("\n");
        gui.println("Welcome to Le manuscrit de la cathédrale!");
        gui.println("Le manuscrit de la cathédrale is a new, incredibly adventure game.");
        gui.println("Type 'help' if you need help.");
        gui.println("\n");
        gui.println(currentRoom.getLongDescription());
        gui.showImage(currentRoom.getImageName());

        }


     private void printLocationInfo(){

       
       gui.println("You are in " + currentRoom.getDescription());
       gui.println(currentRoom.getExitString());

        if(currentRoom.northExit != null)
            gui.print("north ");
        if(currentRoom.eastExit != null)
            gui.print("east ");
        if(currentRoom.southExit != null)
            gui.print("south ");
        if(currentRoom.westExit != null)
            gui.print("west ");
        if(currentRoom.south_westExit != null)
            gui.print(" Southwest ");
        if(currentRoom.north_westExit != null)
            gui.print(" Northwest ");
        if(currentRoom.south_eastExit != null)
            gui.print("Southeast");
        if(currentRoom.north_eastExit!= null)
            gui.print("Northeast");
        gui.println("");
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
     public void interpretCommand(String commandLine) 
    {
        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);

        if(command.isUnknown()) {
            gui.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("look"))
        	look();
        else if (commandWord.equals("drink"))
        	drink();
        else if (commandWord.equals("leave"))
            leave(command);
        else if (commandWord.equals("back"))
        	back(command);
        else if (commandWord.equals("quit")) {
            if(command.hasSecondWord())
                gui.println("Quit what?");
            else
                endGame();
        }
    }
    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    public void printHelp() 
    {
    	gui.println("You are lost. You are alone. You wander");
        gui.println("around at " + currentRoom.getDescription() + "\n");
        gui.print("Your command words are: " + parser.getCommandList());
    }
    
    private void back (Command command) {
    	Room backRoom;
    	if (command.hasSecondWord()) {
    		gui.println("No need a direction to go back from your current place.");
            return;
        }
    	
    	/*if (previousRoom == null) {
            gui.println("Your journey begin from here. You should go somewhere.");
            return;
        }*/
    	
    	if (listeRoom.empty()) {
    		gui.println("Your journey begin from here. You should go somewhere.");
    		return;
    	} else {
    		backRoom = listeRoom.pop();
            previousRoom = currentRoom;
    		currentRoom = backRoom;
    		gui.println(currentRoom.getLongDescription());
            if(currentRoom.getImageName() != null)
                gui.showImage(currentRoom.getImageName());
    	}
    }

    private void leave(Command command) {
    	Room tmpRoom;
        if(command.hasSecondWord()) {
            gui.println("No need a direction to leave your current place.");
            return;
        }

        if (previousRoom == null) {
            gui.println("Your journey begin from here. You should go somewhere.");
            return;
        }
 
        tmpRoom = currentRoom;
        currentRoom = previousRoom;
        previousRoom = tmpRoom;
        gui.println(currentRoom.getLongDescription());
        if(currentRoom.getImageName() != null)
            gui.showImage(currentRoom.getImageName());

    }


    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
    	if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            gui.println("There is no door!");
        else {
            previousRoom = currentRoom;
            listeRoom.push((Room)currentRoom);
            currentRoom = nextRoom;
            gui.println(currentRoom.getLongDescription());
            if(currentRoom.getImageName() != null)
                gui.showImage(currentRoom.getImageName());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            gui.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
    *Fonction look indique la localisation actuelle
    **/
    private void look() {
       gui.println(currentRoom.getLongDescription());
    }
    
    /**
     *Fonction qui ajoute 200 hp a la vie du personnage
     **/
     private void drink() {
    	 gui.println("You are drinking a health potion : + 200 hp");
     }
    
    private void endGame()
    {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
    }

}

import java.util.*;

/**
* Name:        <A. Sandoval, D. Shin, L, Uribe>
* Course:      <Computer Science 2011>
* Section:     <Section_05>
* Description: <A dungeon crawler type of game with multiple class implementation.>
*/

public class finalGame {
    
    //Declare the player variable at the class level and make it static
    static Player player;
    static Scanner input;

    //Display start menu
    public static void main(String[] args) {

    if (args.length > 0 && args[0].equals("-help")) {
        displayHelp();
        return;
    }

    String  title = " _____         _            _    _            ______  _               _    \n" +
                   "|_   _|       | |          | |  | |           | ___ \\| |             | |   \n" +
                   "  | |   _ __  | |_   ___   | |_ | |__    ___  | |_/ /| |  __ _   ___ | | __\n" +
                   "  | |  | '_ \\ | __| / _ \\  | __|| '_ \\  / _ \\ | ___ \\| | / _` | / __|| |/ /\n" +
                   " _| |_ | | | || |_ | (_) | | |_ | | | ||  __/ | |_/ /| || (_| || (__ |   < \n" +
                   " \\___/ |_| |_| \\__| \\___/   \\__||_| |_| \\___| \\____/ |_| \\__,_| \\___||_|\\_\\\n",

            oneStart = " __          _____   _                    _   \n"
                  + "/_ |  _     / ____| | |                  | |  \n"
                  + " | | (_)   | (___   | |_    __ _   _ __  | |_ \n"
                  + " | |        \\___ \\  | __|  / _` | | '__| | __|\n"
                  + " | |  _     ____) | | |_  | (_| | | |    | |_ \n"
                  + " |_| (_)   |_____/   \\__|  \\__,_| |_|     \\__|\n",

            credits = "  ___           _____                     _   _   _         \n" +
                  " |__ \\   _     / ____|                   | | (_) | |        \n" +
                  "    ) | (_)   | |       _ __    ___    __| |  _  | |_   ___\n" +
                  "   / /        | |      | '__|  / _ \\  / _` | | | | __| / __|\n" +
                  "  / /_   _    | |____  | |    |  __/ | (_| | | | | |_  \\__ \\\n" +
                  " |____| (_)    \\_____| |_|     \\___|  \\__,_| |_|  \\__| |___/\n" +
                  "                                                            ";
    
    //print out Menu                     
        System.out.println(title);
        System.out.println(oneStart);
        System.out.println(credits);
        
    //Get user input and perform action based on choice
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                startGame();
                break;
            case 2:
                displayCredits();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
public static void startGame() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your username wary traveler: ");
        String username = input.nextLine();
        
        player = new Player(username); // assign the newly created player object to the static player variable
        player.chooseClass();
        System.out.println("\nHere are you attributes:\n");
        player.showStats();
        System.out.println("\nNow let us start the game!");

        System.out.print("How many dungeons would you like to explore? (limit: 15) ");
        int numDungeons = input.nextInt();
        if(numDungeons > 15) {
            System.out.println("Hey stop that!");
            return;
        }

        //print the animated story (lol spent too much time on the wordSearch to figure out animation)
        String story = "\nA long time ago, in a dungeon far, far away...\n" +
            "Our hero, " + player.getUsername() + ", was exploring the dungeon when...\n" +
            "Suddenly, a horde of angry goblins appeared!\n" +
            "With no time to waste, " + player.getUsername() + " drew their weapon and prepared for battle.\n" +
            "But the goblins were too many, and " + player.getUsername() + " was quickly surrounded...\n" +
            "Just when all seemed lost, a mysterious figure appeared from the shadows...\n" +
            "\"Fear not, young adventurer,\" said the figure. \"For I am the great banana man, Merlindor Samwise!\"\n" +
            "\"I shall aid you in your quest to defeat these foul beasts! Together, we shall emerge victorious!\"\n" +
            "And with that, " + player.getUsername() + " and Merlindor charged into battle, their weapons gleaming in the dim light of the dungeon...";
        
        System.out.println(story);

        battleDungeon(numDungeons);

        System.out.println("\nGame over.");
    }

    public static void displayCredits() {
        System.out.println("Alan Sandoval");
        System.out.println("Derek Shin");
        System.out.println("Leo Uribe");
        System.out.println("");
        System.out.println("Big Thanks to ");
        System.out.println("Alex Lee on Youtube, without him we would not have figured out Getters and Setters");
        System.out.println("\nAdditional Bibliography:");
        System.out.println("\nDowney, Allen, and Chris Mayfield. Think Java: How to Think like a Computer Scientist. O’Reilly Media Inc., 2020.");
        System.out.println("\nLiang, Y. Daniel. Introduction to Java Programming and Data Structures. Pearson Education, Inc., 2020.");
    }
    
    public static void displayHelp() {
        System.out.println("=== Help ===");
        System.out.println("\nBattle Tips:");
        System.out.println("There are no items in the game, it is meant to trick the Player into wasting time :)");
        System.out.println("");
        System.out.println("Type \"exit\" during mob battle to exit the battle, you coward.");
        System.out.println("\nBoss Help:");
        System.out.println("The words are \"dungeon\", \"torch\", \"sword\", \"trap\", \"key\"");
        System.out.println("\nSpecial Powers:");
        System.out.println("When at the bossBattle input 4 to instantly win the game.");
    }

    public static int getNumMobsDefeated(int n) {
        // Increment n by 1 to reflect that a mob has been killed
        n++;
        // Return the updated value of n
        return n;
    }

    public static int createDungeon(int dungeonNumber) {
        int numMobsDefeated = 0;
        int numMobs = (int)(Math.random() * 4) + 3; // generate a random number of mobs between 3 and 6
        for (int i = 0; i < numMobs; i++) {
            String[] monsterNames = {"Goblin", "Orc", "Troll", "Dragon", "Skeleton"};
            String mobName = monsterNames[(int) (Math.random() * monsterNames.length)];
            Mob mob = new Mob(mobName);

            if(encounterMob(mob, player, dungeonNumber)) { // call the encounterMob() method and check if the mob is defeated
                numMobsDefeated++;
            } else {
                System.out.println("You were defeated by the " + mob.getName() + "!");
                return 0;
            }

            if(player.getHealth() <= 0) {
            System.out.println("\nYour health reached zero. Game over!");
            return numMobsDefeated; // Return the number of mobs defeated so far before ending the method
        }

        }

        System.out.println("You have defeated all the mobs in this dungeon!");
        return numMobsDefeated;
    }


    public static void battleDungeon(int numDungeons) {
        Scanner input = new Scanner(System.in);
        int mobsDefeated = 0; // initialize the number of mobs defeated to 0
        int currentDungeon = 1; // initialize the current dungeon to 1
        while (currentDungeon <= numDungeons) {
            System.out.println("\nEntering dungeon #" + currentDungeon + "...");
            int mobsDefeatedInCurrentDungeon = createDungeon(currentDungeon);
            mobsDefeated += mobsDefeatedInCurrentDungeon; // increment the number of mobs defeated by the number defeated in the current dungeon
            if(mobsDefeated >= 3) { // check if at least 3 mobs have been defeated
                System.out.println("\nYou have defeated " + mobsDefeated + " mobs so far!");
                if(currentDungeon == numDungeons) { // check if this is the final dungeon
                    System.out.println("\nCongratulations, you have defeated all the mobs in the dungeons!");
                    bossBattle(); // call the bossBattle() method
                    break;
                } else {
                    System.out.println("Continue to the next dungeon? (y/n)");
                    String continueAnswer = input.next();
                    if(continueAnswer.equalsIgnoreCase("y")) {
                        currentDungeon++;
                        mobsDefeated = 0; // reset the number of mobs defeated
                    } else {
                        break;
                    }
                }
            } else {
                System.out.println("\nYou must defeat at least 3 mobs to continue to the next dungeon.");
                System.out.println("Return to this dungeon and fight more mobs.");
            }
        }
    }

    public static boolean encounterMob(Mob mob, Player player, int dungeonNumber) {
        Scanner input = new Scanner(System.in);
        String[] monsterNames = {"Goblin", "Orc", "Skeleton", "Troll", "Dragon"};
        String mobName = monsterNames[(int)(Math.random() * monsterNames.length)];
        mob = new Mob(mobName);
        System.out.println("You encountered a " + mob.getName() + "!\n");
        System.out.println("Level: " + dungeonNumber);
        System.out.println("HP: " + mob.getHealth());
        System.out.println("Attack: " + mob.getAttack());
        System.out.println("Defense: " + mob.getDefense());
        System.out.println();
        System.out.println("Your stats:");
        System.out.println("HP: " + player.getHealth());
        System.out.println("Attack: " + player.getAttack());
        System.out.println("Defense: " + player.getDefense());
        System.out.println();
        
        while (true) {
            System.out.println("What will you do?");
            System.out.println("1. Attack");
            System.out.println("2. Use item");
            
        String choice = input.nextLine();

        switch (choice) {
            case "1":
                // Player attacks the mob
                int damage = player.attack() - mob.getDefense();
                if (damage < 0) {
                    damage = 0;
                }
                System.out.println("You dealt " + damage + " damage to the " + mob.getName() + "!\n");
                mob.takeDamage(damage);
                if (mob.getHealth() <= 0) {
                    // Check if the mob is defeated
                    System.out.println("You defeated the " + mob.getName() + "!\n");
                    return true;
                }
                // Mob attacks the player
                boolean isPlayerAlive = mob.fight(player, dungeonNumber);
                if (!isPlayerAlive) {
                    System.out.println("You were defeated by the " + mob.getName() + "!\n");
                    return false;
                }
                break;
            case "2":
                // Player uses an item
                // Just kidding, there are no items! LOL
                
                String[] itemPrompts = {
                "Secret Boss: \"Oh, you thought you had items? That's cute!\"\n",
                "Secret Boss: \"Items? We don't need no stinkin' items!\"\n",
                "Secret Boss: \"Items are for the weak! Be strong like me!\"\n",
                "Secret Boss: \"Items? I eat items for breakfast!\"\n",
                "Secret Boss: \"Items? How about a good old-fashioned punch in the face?\"\n"
                };
                
                int randomIndex = (int) (Math.random() * itemPrompts.length);
                System.out.println(itemPrompts[randomIndex]);
                break;
            case "exit":
                System.out.println("Exiting the battle...");
                return false;
            default:
                System.out.println("Invalid choice. Try again.");
                break;
            }
        }
    }

    public static void bossBattle() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nEntering the boss battle arena...");
        System.out.println("\nBoss health: Infinite [|||||||||||||||||||||||||||||||||||||||||||||||||||||||||]\n");

        System.out.println("A big voice bellows and yells out: \"I BET YOU DIDN'T EXPECT ME!!!!\"");
        System.out.println("\nJack Black: \"You've summomed my PANDA WRATH, this will be an unconventional battle though, testing your wits my jabblino!!!\"");

        boolean isPlayerAlive = true;
        String[] attackPrompts = {
            "Jack Black: \"Hahaha! Your attacks tickle me like a mischievous fairy!\"",
            "Jack Black: \"Is that the best you've got? My grandma's knitting needles pack a bigger punch!\"",
            "Jack Black: \"I've battled goblins with better aim than you! Your attacks are like a blindfolded dragon trying to blow out candles!\"",
            "Jack Black: \"You swing your sword like a drunken dwarf on a unicycle!\"",
            "Jack Black: \"Your attacks are as effective as a greasy fart trying to push a mighty sailboat!!!\"",
        };

        String[] itemPrompts = {
            "Jack Black: \"Trying to cheat, huh? My pet phoenix will singe your cheat sheet to ashes!\"",
            "Jack Black: \"Cheating won't help you! My magical ferret will sniff out your dishonesty!\"",
            "Jack Black: \"Using items won't save you from my rockin' riffs! Prepare for a thunderous solo of defeat!\"",
            "Jack Black: \"Cheaters never prosper, my friend. I'll summon a swarm of irate pixies to undo your trickery!\"",
            "Jack Black: \"Your attempts to cheat are as futile as trying to teach a griffin to play the harmonica!\"",
        };

        while (isPlayerAlive) {
            System.out.println("\nChoose your move:");
            System.out.println("1. Attack");
            System.out.println("2. Item");
            System.out.println("3. Play my game");

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    String attackPrompt = getRandomPrompt(attackPrompts);
                    System.out.println("\n" + attackPrompt);
                    break;
                case 2:
                    String itemPrompt = getRandomPrompt(itemPrompts);
                    System.out.println("\n" + itemPrompt);
                    break;
                case 3:
                    System.out.println("\nGuess you decided to test your wits after all!!!");
                    boolean didPlayerWin = playBossGame();
                    if (didPlayerWin) {
                        System.out.println("\nJack Black: \"You've outsmarted me this time!\"");
                        System.out.println("\nWIN");
                    } else {
                        System.out.println("\nJack Black: \"Hahaha, you lose! NO wits in those brains!!\"");
                        System.out.println("\nLOSE");
                    }
                    isPlayerAlive = false;
                    break;
                case 4:
                    System.out.println("\nJack Black: \"Cheat mode activated! You win!\"");
                    System.out.println("\nYou win!");
                    isPlayerAlive = false;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please choose a valid move.");
                    break;
            }
        }
    }

    public static String getRandomPrompt(String[] prompts) {
        Random random = new Random();
        int index = random.nextInt(prompts.length);
        return prompts[index];
    }

    public static boolean playBossGame() {
        String[] hiddenWords = {"dungeon", "torch", "sword", "trap", "key"};
        String hiddenWord = hiddenWords[new Random().nextInt(hiddenWords.length)];
        char[][] box = generateRandomBox(10, hiddenWord);
        printBox(box);
        System.out.println("\nFind the hidden word!");
        System.out.println("\nRemember, it's dungeon themed!");

        Scanner input = new Scanner(System.in);
        int numTries = 0;
        boolean foundWord = false;
        
        while (numTries < 3 && !foundWord) {
            System.out.print("\nEnter a word: ");
            String word = input.nextLine().toLowerCase();
            if(checkWord(hiddenWord, word)) {
                System.out.println("\nYou found the word!");
                foundWord = true;
            } else {
                System.out.println("\nWord not found. Try again.");
                numTries++;
            }
        }
        if(!foundWord) {
            System.out.println("\nYou failed to find the word. The word was " + hiddenWord + ". Loser lol.");
            return false;
        }
        return true;
    }

    public static char[][] generateRandomBox(int size, String hiddenWord) {
        Random random = new Random();
        char[][] box = new char[size][size];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        // generate random starting row and column indices for the hidden word
        int startRow = random.nextInt(size);
        int startCol = random.nextInt(size - hiddenWord.length() + 1);
        
        // populate the box with random letters and the hidden word
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i == startRow && j >= startCol && j < startCol + hiddenWord.length()) {
                    // add the hidden word to the box
                    box[i][j] = hiddenWord.charAt(j - startCol);
                } else {
                    // add a random letter to the box
                    int randomIndex = random.nextInt(alphabet.length());
                    box[i][j] = alphabet.charAt(randomIndex);
                }
            }
        }
        return box;
    }

    public static void printBox(char[][] box) {
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                System.out.print(box[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean checkWord(String hiddenWord, String word) {
        if(hiddenWord.equals(word)) {
            return true;
        }
        return false;
    }
}

    class Player {
    private String username;
    private int health;
    private int attack;
    private int defense;
    private String playerClass;

    public Player(String username) {
        this.username = username;
        this.health = 100;
        this.attack = 10;
        this.defense = 5;
        this.playerClass = "";
    }

    public void chooseClass() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.println("\nRemind me, what faction were you part of?");
            System.out.println("1. Knight");
            System.out.println("2. Wizard");
            System.out.println("3. Archer");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    this.playerClass = "Knight";
                    System.out.println("\nAh, yes! You were part of the Knight faction.");
                    isValidInput = true;
                    break;
                case 2:
                    this.playerClass = "Wizard";
                    System.out.println("\nAh, yes! You were part of the Wizard faction.");
                    isValidInput = true;
                    break;
                case 3:
                    this.playerClass = "Archer";
                    System.out.println("\nAh, yes! You were part of the Archer faction.");
                    isValidInput = true;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please choose a class from the list.");
                    break;
            }
        }
    }

    public void showStats() {
        System.out.println("Username: " + getUsername());
        System.out.println("Class: " + getPlayerClass());
        System.out.println("Health: " + getHealth());
        System.out.println("Attack: " + getAttack());
        System.out.println("Defense: " + getDefense());
    }
    
    public int attack() {
        // Calculate the total attack value as the sum of the player's base attack and a random bonus between 1 and 10
        int totalAttack = this.attack + (int)(Math.random() * 10) + 1;
        System.out.println("You attack with " + totalAttack + " power!\n");
        return totalAttack;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if(this.health < 0) {
            this.health = 0;
        }
    }

    // Getter methods
    public String getUsername() {
        return this.username;
    }

    public String getPlayerClass() {
        return this.playerClass;
    }

    public int getHealth() {
        return this.health;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }
}

    class Mob {
    private int health;
    private int attack;
    private String name;
    private int defense;

    public Mob(String name) {
        this.health = 30;
        this.attack = 15;
        this.defense = 0;
        this.name = getRandomMonsterName();
    }
    
    public boolean fight(Player player, int dungeonNumber) {
        int mobHealth = this.health;
        //int adjustedHealth = this.health + (dungeonNumber * 10);
        int adjustedAttack = this.attack + (int)(0.1 * dungeonNumber * 10);
        //int mobHealth = adjustedHealth;

        while (mobHealth > 0 && player.getHealth() > 0) {
            // player attacks mob
            int playerAttack = player.attack();
            System.out.println("You attack the " + this.name + " for " + playerAttack + " damage.\n");
            mobHealth -= playerAttack;

            // check if mob is defeated
            if(mobHealth <= 0) {
                System.out.println("You defeated the " + this.name + "!\n");
                return true;
            }

            // mob attacks player
            int mobAttack = (int)(adjustedAttack * Math.random());
            System.out.println("The " + this.name + " attacks you for " + mobAttack + " damage.\n");
            player.takeDamage(mobAttack);

            // check if player is defeated
            if(player.getHealth() <= 0) {
                System.out.println("You were defeated by the " + this.name + "!\n");
                return false;
            }
        }
        return true;
    }
    
    private String getRandomMonsterName() {
        String[] monsterNames = {"Goblin", "Orc", "Skeleton", "Zombie", "Dragon"};
        Random rand = new Random();
        return monsterNames[rand.nextInt(monsterNames.length)];
    }

    // getter methods
    public int getHealth() {
        return this.health;
    }

    public int getAttack() {
        return this.attack;
    }

    public String getName() {
        return this.name;
    }

    public int getDefense() {
        return this.defense;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    public void setAttack(int attack) {
        this.attack = attack;
    }
    
    public void setDefense(int defense) {
        this.defense = defense;
    }
}
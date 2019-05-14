package stallfinder;
/**
 * @author ggear
 */
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
/**
   fills and displays a bathroom of ten stalls
*/
public class StallFinder {
    
    Random rand = new Random();   
    // intializes array of booleans for stalls
    boolean[] stalls = new boolean[10];
    
    /**
     * fills the array of stalls with empty values
     */
    public StallFinder()
    {    
        // fills the stalls array with false values representing empty stalls
        for (int i=0; i<10; i++)
        {
            stalls[i] = false;
        }
    }
    
    /**
     * displays the stalls and which ones are occupied
     */
    public void display()
    {
        // intializes array to display stalls
        String[] displayStalls = new String[10];
        // fills array with either "_" or "x" depedning on if it is occupied
        for (int i=0; i<10; i++)
        {
            if (stalls[i] == false)
            {
                displayStalls[i] = "_";
            }
            else
            {
                displayStalls[i] = "x";
            }
        }
        System.out.println(Arrays.toString(displayStalls));
    }
    
    /**
     * tells the while loop when the stalls are full
     * @return boolean true if stalls are full and false otherwise
     */
    public boolean whenToStop()
    {
        int counter = 0;
        for (int i = 0; i < 10; i++)
        {
            if (stalls[i] == false)
            {
                counter += 1;
            }     
        }
        if (counter != 0){return false;}
        System.out.println("The stalls are now all full.");
        return true; 
    } 
    
    /**
     * picks and fills the next stall  
     */
    public void fillStalls() 
    //  the entire code in this method is based off the assumption that the first visitor 
    //  will always occupy one of the middle stalls as said in the assignment description
    {
        ArrayList<Integer> checkArray = new ArrayList<Integer>(); // used to fill stalls for visitors 3 through 8
        // adds integers 3-8 to the arraylist used to fill stalls 3-8
        for (int i = 3; i < 6; i++){
            checkArray.add(i);
        }
        ArrayList<Integer> indexes = new ArrayList<Integer>(); // saves all the indexes of occupied stalls to an array list

        // adds which indexes are occupied to array list
        for (int i=0; i<10; i++)
        {
            if (stalls[i] == true)
            {
                indexes.add(i);
            }
        }
        
        // fills the 1st stall by randomly choosing either of the middle stalls
        if (indexes.size() == 0)
        {
            // picks one of the middle stalls to be taken first
            int firstStallChoice = rand.nextInt(1) + 4; 
            stalls[firstStallChoice] = true;
            System.out.println("first visitor will occupy a middle stall");

        }
        
        // fills the 2nd stall according to the most space
        else if (indexes.size() == 1)
        {
            if (indexes.get(0) == 4)
            {
                stalls[7] = true;
                System.out.println("The next visitor will occupy the middle stall to the right");
            }
            else
            {
                stalls[2] = true;
                System.out.println("The next visitor will occupy the middle stall to the left");
            }
        }
        // fills the 3rd stall according to the most space
        else if (indexes.size() == 2)
        {
            if(stalls[7] == true)
            {
                stalls[1] = true;
                System.out.println("The next visitor will occupy the middle stall to the left");
            }
            else
            {
                stalls[7] = true;
                System.out.println("The next visitor will occupy the middle stall to the right");
            }
        }
        // used for visitors 3-6 to find stalls based on which are occupied
        else if ((checkArray.contains(indexes.size())) && (stalls[1] == true))
        {
            // creates a random index of a stall that isn't being occupied
            boolean indicator = false;
            while (indicator == false)
            {
                int stallFill = rand.nextInt(9);
                if (!(indexes.contains(stallFill)) && (stalls[stallFill+1] == false))
                {
                    stalls[stallFill] = true;
                    System.out.println("The next visitor will occupy one of the in between stalls");
                    indicator = true;
                }
            }
        }
        // used for visitors 3-6 to find stalls based on which are occupied
        else if ((checkArray.contains(indexes.size())) && (stalls[2] == true))
        {
            // creates a random index of a stall that isn't being occupied
            boolean indicator = false;
            while (indicator == false)
            {
                int stallFill = rand.nextInt(9);
                if (!(indexes.contains(stallFill)) && (stalls[stallFill-1] ==false))
                {
                    stalls[stallFill] = true;
                    System.out.println("The next visitor will occupy one of the in between stalls");
                    indicator = true;
                }
            }
        }
        // fills the last 4 stalls
        else
        {
            boolean indicator = false;
            while (indicator == false)
            {
                int stallFill = rand.nextInt(10);
                if(stalls[stallFill] == false)
                {
                    stalls[stallFill] = true;
                    System.out.println("The next visitor will occupy one of the open stalls");
                    indicator = true;
                }
            }    
        }   
    }

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
        StallFinder myStalls = new StallFinder();
        System.out.println("All ten stalls are empty");
        myStalls.display();
        //myStalls.fillFirstStall(); // 1
        //myStalls.display();
        while(myStalls.whenToStop() == false)
        {
            myStalls.fillStalls();
            myStalls.display();
        } 
    }
}

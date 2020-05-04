import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class groupCreator {

    /*Will ask for the number of groups desires. Then ask for the names of
     * the people to be sorted. Then will randomly sort the names into
     * the desired number of groups.
     */
    public static void main(String[] args) {    
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random();
        String promptNum = "Please enter the number of groups needed: ";
        String promptNames = "Please enter the names needed to be sorted one "
            + "at a time. When done, enter \"done\" ";
        int numGroups;
        ArrayList<String> names = new ArrayList<String>();

        System.out.println("Welcome to the group generator! This program"
            + " will ask you for the number of groups needed as well as"
            + " the names of the people you'd wish to sort.");

        numGroups = promptGroupNumber(promptNum, scnr);
        promptNames(promptNames, scnr, names);
        ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>(numGroups);
        sortGroups(names, rand, numGroups, groups);
        System.out.println("Your groups are: ");
        System.out.print(groups);


    }

    public static int promptGroupNumber(String prompt, Scanner scnr) {
        int numGroups;

        while (true) {
            System.out.println(prompt);

            if (scnr.hasNextInt()) {
            numGroups = scnr.nextInt();
            scnr.nextLine();
            }
            else {
                System.out.println("Please enter a number.");
                scnr.nextLine();
                continue;
            }

            if (numGroups < 1) {
                System.out.println("Please enter a number above 0.");
                continue;
            }
            return numGroups;
        }

    }

    public static void promptNames(String prompt, Scanner scnr, ArrayList<String> names) {
        boolean done = false;

        System.out.println(prompt);

        while (!done) {
            System.out.println("Name: ");
            String name = scnr.nextLine();
            name = name.toLowerCase();

            if(name.equals("done")) {
                done = true;
                break;
            }
            else {
                names.add(name);
            }
        }
    }
    //Method with the main sorting algorithm.
    public static void sortGroups(ArrayList<String> names, Random rand, int numGroups,
        ArrayList<ArrayList<String>> groups) {
        int i;
        int j;
        int limit = names.size() / numGroups;
        int select;
        //This loop creates each groupa dn adds them to the main ArrayList of groups
        for (i = 0; i < numGroups; i++) {
            ArrayList<String> group = new ArrayList<String>();
            groups.add(group);
        }
        /*This loop goes through each group and fills it up to the limit number
         * by randomly selecting a name from the list. Once the name has been 
         * placed in a group it is removed from the list. This continues until
         * either the names run out or each group is filled to the limit number.
         */
        for (i = 0; i < groups.size(); i++) {
            for (j = 0; j < limit; j++) {
                select = rand.nextInt(names.size());
                groups.get(i).add(names.get(select));
                names.remove(select);
            }  
        }
        /*This loop goes through the remaining names and then adds one to each 
         * group until all the names are sorted. This is done to make the gorups
         * as even as possible while still placing every name in a group.
         */
        while (names.size() > 0) {
            for (i = 0; i < groups.size(); i++) {
                if (names.size() > 0) {
                select = rand.nextInt(names.size());
                groups.get(i).add(names.get(select));
                names.remove(select);
                }
                else {
                    break;
                }
            }
        }
        //Edge case if a user asks for more groups than names.
        for (i = 0; i < groups.size(); i++)
            if (groups.get(i).size() == 0) {
                System.out.println("One of your groups is empty. This is"
                    + "because you did not enter enough names to fill all"
                    + "the groups.");
                break;
            }
    }
}

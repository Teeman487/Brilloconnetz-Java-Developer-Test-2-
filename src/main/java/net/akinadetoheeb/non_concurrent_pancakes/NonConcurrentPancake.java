package net.akinadetoheeb.non_concurrent_pancakes;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// Non-concurrent Java Program that simulates the shopkeeper scenario
public class NonConcurrentPancake {
    public static void main(String[] args) {
        final int maxPancakesPerUser = 5;
        final int maxPancakesByShopkeeper = 12;
        final int numOfUsers = 3;
        int totalPancakesMade = 0;
        int totalPancakesOrderedByUsers = 0;
      //  final int[] totalWastedPancakes = {0};


        // Shopkeeper Five Different Time Slot Interval for making the pancakes
        for (int time = 1; time <= 2; time++) {
            // Generates random integers between 1 and 12, and the range includes both 1 and 12.
            int numbOfPancakes = new Random().nextInt(maxPancakesByShopkeeper) + 1;


            // Note, we cant increase for the number of pancakesEaten
            // preferred under First Loop for easy accessibility below
            int[] pancakesOrder = new int[numOfUsers];


            for (int user = 0; user < numOfUsers; user++) // Indicates presence of three users(0,1,2)
            {
                // Generates random integers of pancakes eaten per User between 1 and 5
                pancakesOrder[user] = new Random().nextInt(maxPancakesPerUser) + 1; // 2

            }

            //pancakesEatenByUsers += pancakesEaten; // We cannot increase for Array set of numbers

            // Since we cant increase for Array set of numbers, We can easily access
            // totalPancakesEatenByUsers by reading it using for loop
            for (int pancakesEat : pancakesOrder) {
                totalPancakesOrderedByUsers += pancakesEat;
            }

            totalPancakesMade += numbOfPancakes;

            System.out.println("30-Second time Slot# " + time);
            System.out.println("Starting Time of making the pancakes: " + ((time - 1) * 30) + " seconds");
            System.out.println("Ending Time of making the pancakes: " + (time * 30) + " seconds");
            System.out.println("Pancakes Made by Shopkeeper: " + numbOfPancakes);
            System.out.println();  // to space
        }

            System.out.println("Total Pancakes made by the Shopkeeper: " + totalPancakesMade);
            System.out.println("Total Pancakes Ordered by Users: " + totalPancakesOrderedByUsers);
            if (totalPancakesMade > totalPancakesOrderedByUsers) {
                System.out.println("Total Wasted Pancakes: " + (totalPancakesMade - totalPancakesOrderedByUsers));

            } else {
                System.out.println("Total UnMet Orders by Shopkeeper: " + (totalPancakesOrderedByUsers - totalPancakesMade));
                System.out.println("Total Pancakes Eaten by Users: " + (totalPancakesMade));
            }
        if (totalPancakesMade == totalPancakesOrderedByUsers) {
            System.out.println("Total Met Orders: " + (totalPancakesMade));

        }

    }
}

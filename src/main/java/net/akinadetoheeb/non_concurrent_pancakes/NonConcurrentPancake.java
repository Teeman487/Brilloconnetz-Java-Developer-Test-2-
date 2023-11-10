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
        int totalPancakesEaten = 0;
        int totalUnmetOrders = 0;
        final int[] totalWastedPancakes = {0};


        // Shopkeeper Five Different Time Slot Interval for making the pancakes
        for (int time = 1; time <= 5; time++)
        {
            // Generates random integers between 1 and 12, and the range includes both 1 and 12.
            int numbOfPancakes = new Random().nextInt(maxPancakesByShopkeeper) + 1;


            int[] pancakesEaten = new int[numOfUsers];
            int unmetOrders = 0;

            for (int user = 0; user < numOfUsers; user++) // Indicates presence of three users(0,1,2)
            {

                // Generates random integers of pancakes eaten by User between 1 and 5
                pancakesEaten[user] = new Random().nextInt(maxPancakesPerUser) + 1; // 2

                if (pancakesEaten[user] > numbOfPancakes)
                {
                    unmetOrders++;
                    totalWastedPancakes[0] += pancakesEaten[user] - numbOfPancakes;

                } else {
                  //  numbOfPancakes -= pancakesEaten[user];
                    totalPancakesEaten += pancakesEaten[user];

                }
            }


            int pancakesEatenByUsers = 0;

            for (int pancakesEat : pancakesEaten) {

                    pancakesEatenByUsers += pancakesEat;

            }

            totalPancakesMade += numbOfPancakes;
            totalUnmetOrders += unmetOrders;

            System.out.println("30-Second time Slot# " +time);
            System.out.println("Starting Time of making the pancakes: " + ((time - 1) * 30) + " seconds");
            System.out.println("Ending Time of making the pancakes: " + (time * 30) + " seconds");
            System.out.println("Pancakes Made by Shopkeeper: " + numbOfPancakes);
            System.out.println("Pancakes Eaten by  User: " + pancakesEatenByUsers);

            System.out.println("Unmet Orders: " + unmetOrders);
            System.out.println("Wasted Pancakes: " + (Math.max(0, numbOfPancakes - pancakesEatenByUsers)));
            System.out.println();
        }

        System.out.println("Total Pancakes made by the Shopkeeper: " + totalPancakesMade);
        System.out.println("Total Pancakes Eaten by Users: " + totalPancakesEaten);
        System.out.println("Total Unmet Orders: " + totalUnmetOrders);

    }
}

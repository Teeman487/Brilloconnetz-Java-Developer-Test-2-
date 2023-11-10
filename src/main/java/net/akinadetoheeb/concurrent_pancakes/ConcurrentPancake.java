package net.akinadetoheeb.concurrent_pancakes;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// Concurrent Java Program that simulates the shopkeeper scenario
public class ConcurrentPancake {

    public static void main(String[] args) {
        final int maxPancakesPerUser = 5;
        final int maxPancakesByShopkeeper = 12;
        final int numUsers = 3;

        int totalPancakesMade = 0;
        int totalPancakesEaten = 0;


        // Creates an array of integers with a single element, initialized to 0
        final int[] totalMetPancakes ={0};
        final int[] totalUnmetPancakes = {0};

        // System will decide At Most 12 pancakes made by Shopkeeper in 30s
        Random randomPancakes = new Random();

        for (int time = 1; time <= 5; time++) {

            // Generates random integers of pancakes between 1 and 12, and the range includes both 1 and 12.
            int pancakesMadeByShopkeeper = randomPancakes.nextInt(maxPancakesByShopkeeper) + 1;


            // Using CompletableFuture to represent the asynchronous computation for each user
            CompletableFuture<Integer>[] users = new CompletableFuture[numUsers];
            for (int user = 0; user < numUsers; user++)
            {

                users[user] = CompletableFuture.supplyAsync(() ->
                {
                    // Generates random integers of pancakes eaten by User between 1 and 5
                    int pancakesOrderPerUser = randomPancakes.nextInt(maxPancakesPerUser) + 1;

                    // User Request, Unmet Order by Shopkeeper
                    if (pancakesOrderPerUser > pancakesMadeByShopkeeper)
                    {
                        // Unmet Pancakes by the Shopkeeper
                        totalUnmetPancakes[0] +=  pancakesOrderPerUser - pancakesMadeByShopkeeper;

                        // User couldn't eat any pancakes due to unmet order
                        return 0;

                    }

                       // User Request, Order Met by Shopkeeper
                    else
                    {
                        // Pancakes Met by the Shopkeeper
                        totalMetPancakes[0] += pancakesOrderPerUser;

                        // User eats Pancakes that was Met by Shopkeeper
                        return pancakesOrderPerUser;
                    }
                });
            }

            // Combine all user and get the sum of pancakes eaten
            CompletableFuture<Void> allOf = CompletableFuture.allOf(users);
            try {
                allOf.get(); // Wait for all user computations to complete
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }


            // A Total Output of Five Different Time Slot Interval
            int pancakesEatenByUsers = 0;
            int unmetOrders = 0;

            for (CompletableFuture<Integer> user : users) {
                try {
                    pancakesEatenByUsers += user.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            // Total Outputs of Five Different Time Slot Interval
            totalPancakesMade += pancakesMadeByShopkeeper;
            totalPancakesEaten += pancakesEatenByUsers;


            // Five Different Time Slot Interval
            System.out.println("30-Second time Slot #" + time);
            System.out.println("Starting Time of making the pancakes: " + ((time - 1) * 30) + " seconds");
            System.out.println("Ending Time of making the pancakes: " + (time * 30) + " seconds");
            System.out.println("Pancakes Made by Shopkeeper: " + pancakesMadeByShopkeeper);
            System.out.println("Pancakes Eaten by a Single User: " + pancakesEatenByUsers);

            // Math.max function to ensure that the result is non-negative
            System.out.println("Wasted Pancakes: " + (Math.max(0, pancakesMadeByShopkeeper - pancakesEatenByUsers)));
            System.out.println();


        }

        // (Total) Output of Five Different Time Slot
        System.out.println("Total Pancakes Made by Shopkeeper: " + totalPancakesMade);
        System.out.println("Total Pancakes Eaten by Users: " + totalPancakesEaten);
        System.out.println("Total Met Orders: " + totalMetPancakes[0]);
        System.out.println("Total Unmet Pancakes by Shopkeeper: " + totalUnmetPancakes[0]);

    }
}

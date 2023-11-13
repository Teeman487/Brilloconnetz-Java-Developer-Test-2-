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
        int totalPancakesOrder = 0;

        // System will decide At Most 12 pancakes made by Shopkeeper in 30s
        Random randomPancakes = new Random();

        for (int time = 1; time <= 5; time++) {

            // Generates random integers of pancakes between 1 and 12, and the range includes both 1 and 12.
            int pancakesMadeByShopkeeper = randomPancakes.nextInt(maxPancakesByShopkeeper) + 1;


            // Using CompletableFuture to represent the asynchronous computation for each user
            CompletableFuture<Integer>[] users = new CompletableFuture[numUsers];
            for (int user = 0; user < numUsers; user++)
            {
                // Generates random integers of pancakes eaten by User between 1 and 5
                int pancakesOrderPerUser = randomPancakes.nextInt(maxPancakesPerUser) + 1;
                users[user] = CompletableFuture.supplyAsync(() -> pancakesOrderPerUser );
            }

            // Combine all user and get the sum of pancakes eaten
            CompletableFuture<Void> allOf = CompletableFuture.allOf(users);
            try {
                allOf.get(); // Wait for all user computations to complete
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }


            // A Total Output of Five Different Time Slot Interval
            int pancakesOrderByUser = 0;
            int unmetOrders = 0;

            for (CompletableFuture<Integer> user : users) {
                try {
                    pancakesOrderByUser += user.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            // Total Outputs of Five Different Time Slot Interval
            totalPancakesMade += pancakesMadeByShopkeeper;
            totalPancakesOrder += pancakesOrderByUser;


            // Five Different Time Slot Interval
            System.out.println("30-Second time Slot #" + time);
            System.out.println("Starting Time of making the pancakes: " + ((time - 1) * 30) + " seconds");
            System.out.println("Ending Time of making the pancakes: " + (time * 30) + " seconds");
            System.out.println("Pancakes Made by Shopkeeper: " + pancakesMadeByShopkeeper);

            System.out.println(); // To space

        }

        // (Total) Output of Five Different Time Slot

        System.out.println("Total Pancakes made by the Shopkeeper: " + totalPancakesMade);
        System.out.println("Total Pancakes Ordered by Users: " + totalPancakesOrder);
        if (totalPancakesMade > totalPancakesOrder) {
            System.out.println("Total Wasted Pancakes: " + (totalPancakesMade - totalPancakesOrder));

        } else {
            System.out.println("Total UnMet Orders by Shopkeeper: " + (totalPancakesOrder - totalPancakesMade));
            System.out.println("Total Pancakes Eaten by Users: " + (totalPancakesMade));
        }
        if (totalPancakesMade == totalPancakesOrder) {
            System.out.println("Total Met Orders: " + (totalPancakesMade));

        }

    }
}

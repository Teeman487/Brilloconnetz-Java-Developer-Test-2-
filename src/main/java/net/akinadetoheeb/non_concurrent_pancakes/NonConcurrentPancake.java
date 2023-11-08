package net.akinadetoheeb.non_concurrent_pancakes;

import java.util.Random;

// Non-concurrent Java Program that simulates the shopkeeper scenario
public class NonConcurrentPancake {
    public static void main(String[] args) {
        final int maxPancakesPerUser = 5;
        final int maxPancakesByShopkeeper = 12;
        final int numOfUsers = 3;
        int totalPancakesMade = 0;
        int totalPancakesEaten = 0;
        int totalUnmetOrders = 0;
        int totalWastedPancakes = 0;

        // System will decide At Most 12 pancakes made by Shopkeeper in 30s
        Random randomPancakes = new Random();


        // Shopkeeper Five Different Time Slot Interval for making the pancakes
        for (int time = 1; time <= 5; time++)
        {
            // Generates random integers between 1 and 12, and the range includes both 1 and 12.
            int numbOfPancakes = randomPancakes.nextInt (maxPancakesByShopkeeper) + 1;


            int[] pancakesEaten = new int[numOfUsers];
            int unmetOrders = 0;

            for (int user = 0; user < numOfUsers; user++) // Indicates presence of three users(0,1,2)
            {
                //
                //PancakesEaten by a Single User : 2
                pancakesEaten[user] = randomPancakes.nextInt(1,maxPancakesPerUser + 1); // 2

                if (pancakesEaten[user] > numbOfPancakes)  // 2>1
                {
                    unmetOrders++; // 1 2 3
                    totalWastedPancakes += pancakesEaten[user] - numbOfPancakes;  // t +=1

                } else {
                    numbOfPancakes -= pancakesEaten[user];  // 4
                    totalPancakesEaten += pancakesEaten[user];  //

                }
            }

            totalPancakesMade += numbOfPancakes;  //
            totalUnmetOrders += unmetOrders;

            System.out.println("30-Second time Slot# " +time);
            System.out.println("Starting Time of making the pancakes: " + ((time - 1) * 30) + " seconds");
            System.out.println("Ending Time of making the pancakes: " + (time * 30) + " seconds");
            System.out.println("Pancakes Made by Shopkeeper: " + numbOfPancakes);
            System.out.println("Pancakes Eaten by  Users: " + totalPancakesEaten);

            System.out.println("Unmet Orders: " + unmetOrders);
            System.out.println("Wasted Pancakes: " + (pancakesEaten[0] - numbOfPancakes));
            System.out.println();
        }

        System.out.println("Total Pancakes made by the Shopkeeper: " + totalPancakesMade);
        System.out.println("Total Pancakes Eaten: " + totalPancakesEaten);
        System.out.println("Total Unmet Orders: " + totalUnmetOrders);
        System.out.println("Total Wasted Pancakes: " + totalWastedPancakes);

    }
}

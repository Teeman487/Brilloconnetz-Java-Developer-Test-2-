package net.akinadetoheeb.concurrent_pancakes;

import java.util.Random;

// Concurrent Java Program that simulates the shopkeeper scenario
public class ConcurrentPancake {
        public static void main(String[] args) {
            final int maxPancakesPerUser = 5;
            final int maxPancakesByShopkeeper = 12;
            final int numUsers = 3;
            int totalPancakesMade = 0;
            int totalPancakesEaten = 0;
            int totalUnmetOrders = 0;
            int totalWastedPancakes = 0;

            // System will decide At Most 12 pancakes made by Shopkeeper in 30s
            Random randomPancakes = new Random();

            for (int time = 1; time <= 5; time++)
            {
                // Generates random integers between 1 and 12, and the range includes both 1 and 12.
                int pancakesMade = randomPancakes.nextInt(maxPancakesByShopkeeper) + 1;

                int pancakesEaten = 0;
                int unmetOrders = 0;

                for (int user = 0; user < numUsers; user++) {
                    pancakesEaten = new Random().nextInt(1,maxPancakesPerUser + 1);
                    if (pancakesEaten > pancakesMade) {
                        unmetOrders++;
                        totalWastedPancakes += pancakesEaten - pancakesMade;
                    }
                    pancakesMade -= pancakesEaten;
                    totalPancakesEaten += pancakesEaten;
                }

                totalPancakesMade += pancakesMade;
                totalUnmetOrders += unmetOrders;

                System.out.println("30-Second time Slot #" + time);
                System.out.println("Starting Time of making the pancakes: " + ((time - 1) * 30) + " seconds");
                System.out.println("Ending Time of making the pancakes: " + (time * 30) + " seconds");
                System.out.println("Pancakes Made by Shopkeeper: " + pancakesMade);
                System.out.println("Pancakes Eaten by Users: " + totalPancakesEaten);
                System.out.println("Unmet Orders: " + unmetOrders);
                System.out.println("Wasted Pancakes: " + (pancakesEaten - pancakesMade));
                System.out.println();
            }

            System.out.println("Total Pancakes Made: " + totalPancakesMade);
            System.out.println("Total Pancakes Eaten: " + totalPancakesEaten);
            System.out.println("Total Unmet Orders: " + totalUnmetOrders);
            System.out.println("Total Wasted Pancakes: " + totalWastedPancakes);
        }
}

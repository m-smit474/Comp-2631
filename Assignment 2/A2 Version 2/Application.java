import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

/**
 * Compares the speed of two implementations of a Credit Card Database
 * with 20, 200, 2,000 or 20,000 accounts to see efficiency of each implementation.
 *
 * Requires a well formated file to function properly.
 *
 * @author Matt Smith
 *
 */
public class Application {

    /**
     * Runs the comparison.
     */
    public static void main(String[] args) {

        HashTableDatabase hashTable = new HashTableDatabase();
        SortedArrayDatabase sortedArray = new SortedArrayDatabase();

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter file path");
        String fileName = keyboard.nextLine();
        keyboard.close();

        try {

            Scanner file = new Scanner(new File(fileName));

            System.out.println("Sorted Array Database:");
            Instant beginSA = null;

            while (file.hasNextLine()) {
                // Goes through each line
                String operation = file.nextLine();

                // do switch statement with operation
                beginSA = doOperation(sortedArray, file, beginSA, operation);
            }
            file.close();


            file = new Scanner(new File(fileName));

            System.out.println("Hash Table Database:");
            Instant beginHT = null;

            while (file.hasNextLine()) {
                // Goes through each line again
                String operation = file.nextLine();

                // do operation
                beginHT = doOperation(hashTable, file, beginHT, operation);
            }
            file.close();

        } catch (FileNotFoundException ex) {

            System.out.println("Failed to open file; program ending.");

        }

    }

    /**
     * Performs the given operation on the given database.
     * Requires the file which is being used.
     * Takes and returns the begin Instant so not changed when looped.
     */
    private static Instant doOperation(CCDatabase database, Scanner file, Instant begin, String operation) {

        boolean success;

        switch (operation) {

            case "start":
                begin = Instant.now();
                break;

            case "stop":
                Instant end = Instant.now();
                long duration = Duration.between(begin, end).toMillis();

                System.out.println("This took " + duration + " miliseconds.");
                break;

            case "cre":
                // Must read next 5 lines

                String line = file.nextLine();
                long cardNumber = Long.parseLong(line);
                String name = file.nextLine();
                String address = file.nextLine();
                line = file.nextLine();
                double creditLimit = Double.parseDouble(line);
                line = file.nextLine();
                double balance = Double.parseDouble(line);

                success = database.createAccount(cardNumber, name, address, creditLimit, balance);

                if (!success) {
                    System.out.println("Failed to create account " + cardNumber);
                }
                break;

            case "del":
                // Must read next line

                line = file.nextLine();
                cardNumber = Long.parseLong(line);

                success = database.deleteAccount(cardNumber);

                if (!success) {
                    System.out.println("Failed to delete account " + cardNumber);
                }
                break;

            case "lim":
                // Must read 2 lines

                line = file.nextLine();
                cardNumber = Long.parseLong(line);
                line = file.nextLine();
                creditLimit = Double.parseDouble(line);

                success = database.adjustCreditLimit(cardNumber, creditLimit);

                if (!success) {
                    System.out.println("Failed to adjust credit limit on account " + cardNumber);
                }
                break;

            case "pur":
                // Must read 2 lines

                line = file.nextLine();
                cardNumber = Long.parseLong(line);
                line = file.nextLine();
                Double price = Double.parseDouble(line);

                try {
                    success = database.makePurchase(cardNumber, price);

                    if (!success) {
                        System.out.println("Failed to make purchase on account " + cardNumber);
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                break;

            default:
                System.out.println("Invalid Operation; no action taken.");
                break;
        }
        return begin;
    }

}

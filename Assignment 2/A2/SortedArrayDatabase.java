/**
 * This implements the Credit Card Database with a sorted array.
 * All searches are done with binary search.
 *
 * @author Matt Smith
 *
 */
public class SortedArrayDatabase implements CCDatabase {

    /**
     * Initializes the Database to 100 elements.
     */
    public SortedArrayDatabase() {
        sortedArray = new Account[100];
        fillLevel = 0;
    }

    private Account sortedArray[];
    private int fillLevel;

    // Assumes check is done after to see if found
    /**
     * This is a binary search function.
     * If the account was not found, the index of where the value
     * should go is returned.
     */
    private int search(long acctNum, int end) {
        int start = 0;
        int newIndex;
        // May want to use fill level
        while (start < end) {
            newIndex = (start + end) / 2;

            // May need null check
            if (acctNum == sortedArray[newIndex].getCardNumber()) {
                return newIndex;
            } else if (acctNum < sortedArray[newIndex].getCardNumber()) {
                // take bottom half
                end = newIndex - 1;
            } else if (acctNum > sortedArray[newIndex].getCardNumber()) {
                // take top half
                start = newIndex + 1;
            }
        }
        // if not found returns index of where it should be inserted
        return start;
    }

    /**
     * Creates a new array double the size and moves values
     * into new array.
     */
    private void resize() {
        int newSize = (sortedArray.length) * 2;
        Account newArray[] = new Account[newSize];

        for (int i = 0; i < sortedArray.length; i++) {
            newArray[i] = sortedArray[i];
        }

        sortedArray = newArray;
    }

    @Override
    public boolean createAccount(long accountNumber, String name, String address, double creditLimit, double balance) {
        int index;
        boolean success;

        // Check if account exists
        index = search(accountNumber, fillLevel - 1);

        if (sortedArray[index] == null || accountNumber != sortedArray[index].getCardNumber()) {
            // account was not found
            if (fillLevel == sortedArray.length) {
                resize();
            }

            Account newAccount = new Account(accountNumber, name, address, creditLimit, balance);

            // Shift array to make space for new account (from index -> end)
            for (int end = fillLevel; end > index; end--) {
                sortedArray[end] = sortedArray[end - 1];
            }

            sortedArray[index] = newAccount;

            success = true;
            fillLevel++;

        } else {
            // account already exists
            success = false;
        }

        return success;
    }

    @Override
    public boolean deleteAccount(long accountNumber) {

        // Search for account
        int index = search(accountNumber, fillLevel - 1);
        boolean success = false;

        if (sortedArray[index] != null && accountNumber == sortedArray[index].getCardNumber()) {
            // Remove account
            sortedArray[index] = null;
            // Shift elements after deletion
            for (int end = sortedArray.length - 1; index < end; index++) {
                sortedArray[index] = sortedArray[index + 1];
            }

            success = true;
            fillLevel--;
        } else {
            // Account was not found
            success = false;
        }

        return success;
    }

    @Override
    public boolean adjustCreditLimit(long accountNumber, double newLimit) {
        boolean success = false;

        // Search for account
        int index = search(accountNumber, fillLevel - 1);

        if (sortedArray[index] != null && accountNumber == sortedArray[index].getCardNumber()) {
            // Set new limit
            sortedArray[index].setCreditLimit(newLimit);
            success = true;
        }

        return success;
    }

    @Override
    public String getAccount(long accountNumber) {

        // Search for account
        int index = search(accountNumber, fillLevel);

        if (accountNumber == sortedArray[index].getCardNumber()) {
            // return string with account details on separate lines
            return sortedArray[index].getCardNumber() + "\n" + sortedArray[index].getName() + "\n"
                    + sortedArray[index].getAddress() + "\n" + sortedArray[index].getCreditLimit() + "\n"
                    + sortedArray[index].getBalanceOwing() + "\n";
        } else {
            return null;
        }
    }

    @Override
    public boolean makePurchase(long accountNumber, double price) throws Exception {
        boolean success = false;

        // Search for account
        int index = search(accountNumber, fillLevel - 1);

        if (sortedArray[index] != null && accountNumber == sortedArray[index].getCardNumber()) {
            // Account was found
            // Check if price + balance is less than credit limit
            double tempBalance = sortedArray[index].getBalanceOwing();
            tempBalance += price;

            // if it exceeds credit limit throw exception
            if (sortedArray[index].getCreditLimit() < tempBalance) {
                throw new Exception("Sorted Array Credit Limit Exceeded: Failed To Make Purchase");
            } else {
                sortedArray[index].setBalanceOwing(tempBalance);
                success = true;
            }
        }
        return success;
    }

}

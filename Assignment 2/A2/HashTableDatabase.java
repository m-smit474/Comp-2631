/**
 * This  implements the credit card database with a hash table.
 *
 * @author Matt Smith
 *
 */
public class HashTableDatabase implements CCDatabase {

    /**
     * Initializes the hash table to a prime size.
     */
    public HashTableDatabase( ) {
        hashTable = new Bucket[101];
        fillLevel = 0;
    }

    private Bucket hashTable[];
    private int fillLevel;

    /**
     * Find the index of where the account should go.
     *
     * Uses the formula : [17(c1) + 17^2(c2) + 17^3(c3) + 17^4(c4)] % tableSize
     * where c is a block of 4 digits of the account number.
     *
     * @param acctNum ->the card number to be used (AKA the key).
     * @param table ->which table to get length of.
     * @return
     */
    private int hash(long acctNum, Bucket table[]) {
        // acctNum split into four shorts (4 digits each)
        short component[] = new short[4];
        long num = acctNum; // So I don't alter acctNum
        int index1;
        int index2;
        int hash;

        // Assumes components is initialized to 0

        for (index1 = 0; index1 < 4; index1++) {
            // 1 for each short
            for (index2 = 0; index2 < 4; index2++) {
                // 1 for each number
                component[index1] += ((num % 10) * (Math.pow(10, index2)));
                num = num / 10;
            }
        }
        // formula : [17(c1) + 17^2(c2) + 17^3(c3) + 17^4(c4)] % tableSize

        hash = (17 * component[0] + (int) Math.pow(17, 2) * component[1] + (int) Math.pow(17, 3) * component[2]
                + (int) Math.pow(17, 3) * component[3]) % table.length;

        return hash;
    }

    /**
     * Uses hash function to find target and does
     * quadratic probing for collisions.
     *
     * @param acctNum ->the target.
     * @param table ->the table that will be searched.
     * @return ->the index of where target is, or where target should go.
     */
    private int search(long acctNum, Bucket table[]) {
        int index = hash(acctNum, table);

        if (table[index] != null && acctNum != table[index].getAccount().getCardNumber()) {
            // Collision
            boolean done = false;
            for (int count = 1; !done && count < table.length; count++) {

                index = (index + (int) Math.pow(count, 2)) % table.length;

                if (table[index] == null) {
                    // Empty index
                    done = true;
                }
            }
        }

        return index;
    }

    /**
     * Creates a new hash table of at least double the size
     * and the size is a prime number. Re-hash values into new
     * hash table.
     */
    private void resize() {
        // find new prime size
        int size = hashTable.length;
        int newSize = (size * 2) + 1;
        while (!isPrime(newSize)) {
            newSize += 2;
        }
        // create new array of that size
        Bucket newArray[] = new Bucket[newSize];

        // move through old array linearly while re-hashing values into new table
        fillLevel = 0;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null && !hashTable[i].isDeleted()) {
                int index = search(hashTable[i].getAccount().getCardNumber(), newArray);
                newArray[index] = hashTable[i];

                fillLevel++;
            }
        }

        hashTable = newArray;
    }

    /**
     * Checks if an integer is prime.
     */
    private boolean isPrime(int newSize) {
        // check if newSize is multiple of 2
        if (newSize % 2 == 0) {
            return false;
        } else {
            // if not check odds up to square root.
            for (int i = 3; i * i <= newSize; i += 2) {

                if (newSize % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean createAccount(long accountNumber, String name, String address, double creditLimit, double balance) {
        int index;
        boolean success = false;

        // Check if account doesn't exist
        index = search(accountNumber, hashTable);
        if (hashTable[index] == null) {
            // Check load factor and resize if needed
            double loadFactor = (double) fillLevel / (double) hashTable.length;
            if (loadFactor > 0.6) {
                // resize hashTable
                resize();
            }

            // Create new account
            hashTable[index] = new Bucket(accountNumber, name, address, creditLimit, balance);

            fillLevel++;
            success = true;
        }

        return success;
    }

    @Override
    public boolean deleteAccount(long accountNumber) {
        boolean success = false;
        // Check if account exists
        int index = search(accountNumber, hashTable);

        if (hashTable[index] != null && accountNumber == hashTable[index].getAccount().getCardNumber()) {
            // Set deleted flag to true
            hashTable[index].setDeleted(true);

            success = true;
            // May want to decrease fill level
        }

        return success;
    }

    @Override
    public boolean adjustCreditLimit(long accountNumber, double newLimit) {
        boolean success = false;
        // check if account exists
        int index = search(accountNumber, hashTable);

        if (hashTable[index] != null && accountNumber == hashTable[index].getAccount().getCardNumber()) {
            // set new credit limit
            hashTable[index].getAccount().setCreditLimit(newLimit);

            success = true;
        }

        return success;
    }

    @Override
    public String getAccount(long accountNumber) {
        // check if account exists
        int index = search(accountNumber, hashTable);

        if (hashTable[index] != null && accountNumber == hashTable[index].getAccount().getCardNumber()) {
            // return account details as a string
            return hashTable[index].getAccount().getCardNumber() + "\n" + hashTable[index].getAccount().getName() + "\n"
                    + hashTable[index].getAccount().getAddress() + "\n" + hashTable[index].getAccount().getCreditLimit()
                    + "\n" + hashTable[index].getAccount().getBalanceOwing() + "\n";
        }
        return null;
    }

    @Override
    public boolean makePurchase(long accountNumber, double price) throws Exception {
        boolean success = false;
        // Check if account exists
        int index = search(accountNumber, hashTable);

        if (hashTable[index] != null && accountNumber == hashTable[index].getAccount().getCardNumber()) {
            // Check if balance + price is less than limit
            double tempBalance = hashTable[index].getAccount().getBalanceOwing() + price;

            if (tempBalance > hashTable[index].getAccount().getCreditLimit()) {
                // If over limit throw exception
                throw new Exception("Hashtable Credit Limit Exceeded; Failed To Make Purchase.");
            } else {
                // Otherwise set new balance
                hashTable[index].getAccount().setBalanceOwing(tempBalance);

                success = true;
            }
        }
        return success;
    }

}

/**
 * Hold an account and a deleted flag.
 * Hash table stores these.
 *
 * @author Matt Smith
 *
 */
public class Bucket {

    private Account account;
    private boolean deleted;

    /**
     * Default constructor initializes empty bucket.
     */
    public Bucket( ) {
        account = null;
        deleted = false;
    }

    /**
     * Account initialization constructor. Requires all account fields.
     */
    public Bucket(long cardNumber, String name, String address, double creditLimit, double balanceOwing ) {
        account = new Account(cardNumber, name, address, creditLimit, balanceOwing);
        deleted = false;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Account getAccount() {
        return account;
    }

}

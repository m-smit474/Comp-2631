/**
 * A single Credit Card account.
 * Contains the following:
 * - 16 digit account number.
 * - name of card holder.
 * - address of card holder.
 * - credit limit of the account.
 * - current balance of the account.
 * Database will store these.
 *
 * @author Matt Smith
 *
 */
public class Account {

    /**
     * Account constructor.
     * Requires every field to create an account.
     */
    public Account(long cardNumber, String name, String address, double creditLimit, double balanceOwing) {
        super();
        this.cardNumber = cardNumber;
        this.name = name;
        this.address = address;
        this.creditLimit = creditLimit;
        this.balanceOwing = balanceOwing;
    }

    private long cardNumber;
    private String name;
    private String address;
    private double creditLimit;
    private double balanceOwing;

    public long getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getBalanceOwing() {
        return balanceOwing;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setBalanceOwing(double balanceOwing) {
        this.balanceOwing = balanceOwing;
    }


}

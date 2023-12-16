interface BankInterface {
    abstract double getBalance();

    abstract double getInterestRate();

}

class BankA implements BankInterface {
    private double balance;

    public BankA(double balance) {
        
        this.balance = balance;
    }

    public double getBalance() {
        double rateAmount = balance * getInterestRate();
        return balance + rateAmount;
    }

    public double getInterestRate() {
        return 0.07;
    }
}

class BankB implements BankInterface {
    private double balance;

    public BankB(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        double rateAmount = balance * getInterestRate();
        return balance + rateAmount;
    }

    public double getInterestRate() {
        return 0.074;
    }
}

class BankC implements BankInterface {
    private double balance;

    public BankC(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        double rateAmount = balance * getInterestRate();
        return balance + rateAmount;
    }

    public double getInterestRate() {
        return 0.079;
    }
}

public class lab5 {
    public static void main(String[] args) {

        BankA bankA = new BankA(10000);
        BankB bankB = new BankB(150000);
        BankC bankC = new BankC(200000);
        System.out.println("--------------------------------------------");
        System.out.println("Bank A:");
        System.out.println("--------------------------------------------");
        System.out.println("Balance: " + bankA.getBalance());
        System.out.println("Interest Rate: " + bankA.getInterestRate());
        System.out.println("--------------------------------------------");
        System.out.println("");
        System.out.println("--------------------------------------------");
        System.out.println("Bank B:");
        System.out.println("--------------------------------------------");
        System.out.println("Balance: " + bankB.getBalance());
        System.out.println("Interest Rate: " + bankB.getInterestRate());
        System.out.println("--------------------------------------------");
        System.out.println("");
        System.out.println("--------------------------------------------");
        System.out.println("Bank C:");
        System.out.println("--------------------------------------------");
        System.out.println("Balance: " + bankC.getBalance());
        System.out.println("Interest Rate: " + bankC.getInterestRate());
        System.out.println("--------------------------------------------");
    }
}

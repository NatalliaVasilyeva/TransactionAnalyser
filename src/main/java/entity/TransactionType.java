package entity;

public enum TransactionType {

    PAYMENT("payment"),
    REVERSAL("reversal)");


    private String transactionType;

    private TransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    


}

package entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
    private String ID;
    private LocalDateTime data;
    private BigDecimal amount;
    private String merchant;
    private TransactionType type;
    private String relatedTransaction;


    private Transaction(TransactionBuilder builder) {
        this.ID = builder.ID;
        this.data = builder.data;
        this.amount = builder.amount;
        this.merchant = builder.merchant;
        this.type = builder.type;
        this.relatedTransaction = builder.relatedTransaction;
    }

    public String getID() {
        return ID;
    }

    public LocalDateTime getData() {
        return data;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public TransactionType getType() {
        return type;
    }

    public String getRelatedTransaction() {
        return relatedTransaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(ID, that.ID) &&
                Objects.equals(data, that.data) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(merchant, that.merchant) &&
                type == that.type &&
                Objects.equals(relatedTransaction, that.relatedTransaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, data, amount, merchant, type, relatedTransaction);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "ID='" + ID + '\'' +
                ", data=" + data +
                ", amount=" + amount +
                ", merchant='" + merchant + '\'' +
                ", type=" + type +
                ", relatedTransaction='" + relatedTransaction + '\'' +
                '}';
    }

    public static class TransactionBuilder {
        private String ID;
        private LocalDateTime data;
        private BigDecimal amount;
        private String merchant;
        private TransactionType type;
        private String relatedTransaction;

        public TransactionBuilder() {
        }

        public TransactionBuilder setID(String id) {
            this.ID = id;
            return this;
        }

        public TransactionBuilder setData(LocalDateTime data) {
            this.data = data;
            return this;
        }

        public TransactionBuilder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public TransactionBuilder setMerchant(String merchant) {
            this.merchant = merchant;
            return this;
        }

        public TransactionBuilder setType(TransactionType type) {
            this.type = type;
            return this;
        }

        public TransactionBuilder setRelatedTransaction(String relatedTransaction) {
            this.relatedTransaction = relatedTransaction;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}

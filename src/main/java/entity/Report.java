package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Report {
    private long numberOfTransaction;
    private BigDecimal averageTransactionValue;

    public Report(long numberOfTransaction, BigDecimal averageTransactionValue) {
        this.numberOfTransaction = numberOfTransaction;
        this.averageTransactionValue = averageTransactionValue;
    }

    public long getNumberOfTransaction() {
        return numberOfTransaction;
    }

    public void setNumberOfTransaction(long numberOfTransaction) {
        this.numberOfTransaction = numberOfTransaction;
    }

    public BigDecimal getAverageTransactionValue() {
        return averageTransactionValue;
    }

    public void setAverageTransactionValue(BigDecimal averageTransactionValue) {
        this.averageTransactionValue = averageTransactionValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return numberOfTransaction == report.numberOfTransaction &&
                Objects.equals(averageTransactionValue, report.averageTransactionValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfTransaction, averageTransactionValue);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Number of transactions = ").append(numberOfTransaction).append("\n");
        sb.append("Average Transaction Value = ").append(averageTransactionValue);
        return sb.toString();
    }
}

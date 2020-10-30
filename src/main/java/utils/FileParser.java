package utils;

import entity.Report;
import entity.Transaction;
import entity.TransactionType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class FileParser {


    public Report parseFile(String fromDate, String toDate, String merchant) {

        String fileName = Constant.DEFAULT_FILE_NAME;

        List<Transaction> transactions = new ArrayList<>();
        try (InputStreamReader isr = new InputStreamReader(readFileFromResources(fileName));
             BufferedReader br = new BufferedReader(isr)) {

            String transaction;
            while ((transaction = br.readLine()) != null) {
                if (transaction.contains(merchant)) {
                    List<String> transactionDescription = Arrays.stream(transaction.split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    if (transactionDescription.get(4).equals(TransactionType.REVERSAL.name())) {
                        transactions=transactions
                                .stream()
                                .filter(tr -> !tr.getID().equals(transactionDescription.get(5)))
                                .collect(Collectors.toList());
                    } else {
                        transactions.add
                                (new Transaction.TransactionBuilder()
                                .setID(transactionDescription.get(0))
                                .setData(parseData(transactionDescription.get(1)))
                                .setAmount(new BigDecimal(transactionDescription.get(2)))
                                .setMerchant(transactionDescription.get(3))
                                .setType(TransactionType.valueOf(transactionDescription.get(4)))
                                        .build());
                    }
                }
            }
        } catch(IOException e){
            throw new RuntimeException("Problem with read information from file", e);
        }


        LocalDateTime startTime = parseData(fromDate);
        LocalDateTime endTime = parseData(toDate);

        List<Transaction> filteredTransaction=filterTransactionByData(transactions, startTime, endTime);

        long countOfTransaction = filteredTransaction.size();
        BigDecimal averageAmountOfTransaction = countAverageAmountOfTransaction(filteredTransaction, countOfTransaction);

        return new Report(countOfTransaction, averageAmountOfTransaction);

    }


    public void printReport(Report report){
        System.out.println(report.toString());
    }

    public BigDecimal countAverageAmountOfTransaction(List<Transaction> transactions, long countOfTransaction) {

        if(countOfTransaction!=0) {

            return transactions
                    .stream()
                    .map(Transaction::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(new BigDecimal(countOfTransaction), BigDecimal.ROUND_CEILING);
        } else {
            return new BigDecimal(0l);
        }

    }


    private InputStream readFileFromResources(String fileName) {
        return FileParser.class.getClassLoader().getResourceAsStream(fileName);

    }


    private List<Transaction> filterTransactionByData(List<Transaction> transactions, LocalDateTime startTime, LocalDateTime endTime) {
        return transactions
                .stream()
                .filter(transaction -> transaction.getData().isAfter(startTime) || transaction.getData().isEqual(startTime))
                .filter(transaction -> transaction.getData().isBefore(endTime) || transaction.getData().isEqual(endTime))
                .collect(Collectors.toList());
    }


    private LocalDateTime parseData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT);
        return LocalDateTime.parse(data, formatter);
    }
}

import entity.Report;
import entity.Transaction;
import entity.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Constant;
import utils.FileParser;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestFileParser {
    private static FileParser fileParser;
    private static List<Transaction> listOfTransactions;

    @BeforeAll
    public static void setData(){
       fileParser=new FileParser();
       listOfTransactions = new ArrayList<>();
        listOfTransactions.add
                (new Transaction.TransactionBuilder()
                        .setID("LFVCTEYM")
                        .setData(LocalDateTime.parse("20/08/2018 12:50:02", DateTimeFormatter.ofPattern(Constant.DATE_FORMAT)))
                        .setAmount(new BigDecimal(0.50))
                        .setMerchant("MacLaren")
                        .setType(TransactionType.PAYMENT).build());

        listOfTransactions.add
                (new Transaction.TransactionBuilder()
                        .setID("LFVCTEYM")
                        .setData(LocalDateTime.parse("20/08/2018 14:07:10", DateTimeFormatter.ofPattern(Constant.DATE_FORMAT)))
                        .setAmount(new BigDecimal(99.50))
                        .setMerchant("MacLaren")
                        .setType(TransactionType.PAYMENT).build());
    }

   @Test
    public void averageAmountTest_true(){
          Assertions.assertEquals(BigDecimal.valueOf(50.0), fileParser.countAverageAmountOfTransaction(listOfTransactions, listOfTransactions.size()));

   }

    @Test
    public void averageAmountTest_false(){
        Assertions.assertNotEquals(BigDecimal.valueOf(55.0), fileParser.countAverageAmountOfTransaction(listOfTransactions, listOfTransactions.size()));

    }

    @Test
    public void parseFileTest_true(){
        Report expectedReport = new Report(2, new BigDecimal(52.25));
        Report actualReport = fileParser.parseFile("20/08/2018 12:50:02", "20/08/2018 14:07:10", "MacLaren");
        Assertions.assertEquals(expectedReport, actualReport);
    }

    @Test
    public void parseFileTest_false(){
        Report expectedReport = new Report(1, new BigDecimal(52.25));
        Report actualReport = fileParser.parseFile("20/08/2018 12:50:02", "20/08/2018 14:07:10", "MacLaren");
        Assertions.assertNotEquals(expectedReport, actualReport);
    }

    @Test
    public void parseFileTestWithNullValue_true(){
        Report expectedReport = new Report(0, new BigDecimal(0));
        Report actualReport = fileParser.parseFile("20/08/2018 12:50:02", "20/08/2018 14:07:10", "ooo");
        Assertions.assertEquals(expectedReport, actualReport);
    }

}

import entity.Report;
import utils.FileParser;

public class Main {

    public static void main(String[] args)  {
        FileParser fileParser = new FileParser();
        Report report=fileParser.parseFile("20/08/2018 12:00:00", "20/08/2018 13:00:00", "Kwik-E-Mart");
        fileParser.printReport(report);
    }
}

import com.opencsv.CSVReader;
import java.io.FileReader;

public class CSVFileHandler {

    private FileReader file;
    private CSVReader csvFile;

    public CSVFileHandler(String filePath) {

        this.file = null;
        this.csvFile = null;

        try {

            this.file = new FileReader(filePath);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}

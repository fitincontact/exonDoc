import com.fitincontact.exonDoc.api.ExonDoc;
import com.fitincontact.exonDoc.entries.Exon;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExonTxtTest {

    private static final char f1 = '{';
    private static final char f2 = '}';
    private static final char f3 = ':';
    private static final char f4 = '[';
    private static final char f5 = ']';
    private static final char f6 = '\'';
    private static final char f7 = '"';
    private static final char f8 = '`';
    private static final char LEFT_CURLY_BRACKET = '{';
    private static final char RIGHT_CURLY_BRACKET = '}';
    private static final char COLON = ':';
    private static final char LEFT_SQUARE_BRACKET = '[';
    private static final char RIGHT_SQUARE_BRACKET = ']';
    private static final char APOSTROPHE = '\'';
    private static final char QUOTATION_MARK = '"';
    private final ExonDoc exonDoc = new ExonDoc();

    @DataProvider
    public Object[][] parseData() {
        final String collection = "Person";

//System.out.println(f1+Character.getName(f1));
//        System.out.println(f2+Character.getName(f2));
//        System.out.println(f3+Character.getName(f3));
//        System.out.println(f4+Character.getName(f4));
//        System.out.println(f5+Character.getName(f5));
//        System.out.println(f6+Character.getName(f6));
//        System.out.println(f7+Character.getName(f7));
//        System.out.println(f8+Character.getName(f8));


//        try{
//            PrintWriter out = new PrintWriter("filename.txt");
//            out.println("filename.txt");
//        }
//        catch (FileNotFoundException ex){
//            System.out.println(ex.getMessage());
//        }

        final String exonString1 = readFile("src/test/java/exons/exonString1.exon", Charset.defaultCharset());
        final String exonString2 = readFile("src/test/java/exons/exonString2.exon", Charset.defaultCharset());

        return new Object[][]{
                //{collection, exonString1},
                {collection, exonString2},
        };
    }

    @Test(dataProvider = "parseData")
    public void tst(
            final String collection,
            final String exonString
    ) {
        final Exon exon = exonDoc.parse().parse(collection, exonString);
        //System.out.println(exonString);
    }

    private String readFile(String path, Charset encoding) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, encoding);
        } catch (IOException ex) {
            System.out.println("IOException :" + ex.getMessage());
        }
        return null;
    }

}

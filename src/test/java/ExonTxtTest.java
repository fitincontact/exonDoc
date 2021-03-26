import com.fitincontact.exonDoc.api.ExonDoc;
import com.fitincontact.exonDoc.entries.Exon;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ExonTxtTest {
    final static Logger LOG = Logger.getLogger(ExonTxtTest.class);

    private final ExonDoc exonDoc = new ExonDoc();

    @DataProvider
    public Object[][] parseData() {
        final String collection = "Person";

        final String exonString1 = readFile("src/test/resources/exon/exonString1.exon", Charset.defaultCharset());
        final String exonString2 = readFile("src/test/resources/exon/exonString2.exon", Charset.defaultCharset());
        final String template2 = readFile("src/test/resources/template/2", Charset.defaultCharset());

        return new Object[][]{
                //{collection, exonString1},
                {collection, exonString2, template2},
        };
    }

    @Test(dataProvider = "parseData")
    public void tst(
            final String collection,
            final String exonString,
            final String template
    ) {
        LOG.debug("parseData");
        final Exon exon = exonDoc.parse().parse(collection, exonString);
//        System.out.println(exonString);
//        System.out.println("--------------");
//        System.out.println(exon.getExonEntry().toString());
        //System.out.println(exon.getExonEntry().toStringWithFormat(0));
        assert (normalize(exon.getExonEntry().toStringWithFormat(0)).equals(normalize(template))) : "ERROR";
        LOG.debug("\n" + exon.getExonEntry().toStringWithFormat(0));
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

    private String normalize(String txt) {
        txt = txt.replace("\n", "");
        return txt;
    }

}
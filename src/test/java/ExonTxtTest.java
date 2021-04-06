import com.fitincontact.exondoc.api.ExonDoc;
import com.fitincontact.exondoc.etc.Util;
import com.fitincontact.exondoc.parser.entries.Exon;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.charset.Charset;


public class ExonTxtTest {
    static final Logger LOG = Logger.getLogger(ExonTxtTest.class);

    private final ExonDoc exonDoc = new ExonDoc();

    //todo num in path
    //todo print only firs error
    @DataProvider
    public Object[][] parseData() {
        final var collection = "Person";

        final var exonString1 = Util.readFile("src/test/resources/exon/exonString1.exon", Charset.defaultCharset());
        final var exonString2 = Util.readFile("src/test/resources/exon/exonString2.exon", Charset.defaultCharset());
        final var template2 = Util.readFile("src/test/resources/template/2", Charset.defaultCharset());

        return new Object[][]{
                {collection, exonString1, template2},
                //{collection, exonString2, template2},
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

    private String normalize(String txt) {
        txt = txt.replace("\n", "");
        return txt;
    }

}
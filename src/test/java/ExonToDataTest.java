import com.fitincontact.exondoc.api.ExonDoc;
import com.fitincontact.exondoc.etc.Util;
import com.fitincontact.exondoc.parser.entries.Exon;
import com.fitincontact.exondoc.storage.StorageApi;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.charset.Charset;

public class ExonToDataTest {
    static final Logger LOG = Logger.getLogger(ExonToDataTest.class);
    static final StorageApi api = new StorageApi();
    private final ExonDoc exonDoc = new ExonDoc();

    @DataProvider
    public Object[][] parseData() {
        final var collection = "Person";

        final var exonString1 = Util.readFile("src/test/resources/exon/exonString1.exon", Charset.defaultCharset());
        final var exonString2 = Util.readFile("src/test/resources/exon/exonString2.exon", Charset.defaultCharset());
        final var exonString3 = Util.readFile("src/test/resources/exon/exonString3.exon", Charset.defaultCharset());
        final var template2 = Util.readFile("src/test/resources/template/2", Charset.defaultCharset());

        return new Object[][]{
                //{collection, exonString1, template2},
                //{collection, exonString2, template2},
                {collection, exonString3, template2},
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
        //assert (normalize(exon.getExonEntry().toStringWithFormat(0)).equals(normalize(template))) : "ERROR";
        LOG.debug("\n" + exon.getExonEntry().toStringWithFormat(0));

        api.start();
        api.insert(exon);
    }

    private String normalize(String txt) {
        txt = txt.replace("\n", "");
        return txt;
    }
}
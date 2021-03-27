import com.fitincontact.exondoc.storage.StorageApi;
import com.fitincontact.exondoc.utils.Util;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.nio.charset.Charset;
import java.util.Objects;

public class SeqTst {
    final static Logger LOG = Logger.getLogger(SeqTst.class);
    StorageApi api = new StorageApi();

    @Test
    public void tst() {
        LOG.debug("SeqTst");
        var id1 = Long.valueOf(
                Objects.requireNonNull(
                        Util.readFile(
                                "src/main/java/com/fitincontact/exondoc/storage/files/Sequence",
                                Charset.defaultCharset()
                        )
                )
        );

        api.start();
        var sequence = api.sequence();
        sequence.getId();//1
        sequence.getId();//2
        sequence.stop();
        sequence.start();
        sequence.getId();//3
        sequence.getId();//4
        sequence.getId();//5
        sequence.getId();//6
        sequence.stop();
        sequence.getId();
        sequence.getId();
        sequence.getId();
        sequence.getId();

        var id2 = Long.valueOf(
                Objects.requireNonNull(
                        Util.readFile(
                                "src/main/java/com/fitincontact/exondoc/storage/files/Sequence",
                                Charset.defaultCharset()
                        )
                )
        );
        assert (id2 - id1 == 6) : "ERROR id2-id1 = " + (id2 - id1) + "; expected: 6";
    }
}

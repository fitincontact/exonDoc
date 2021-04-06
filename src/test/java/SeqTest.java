import com.fitincontact.exondoc.storage.StorageApi;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class SeqTest {
    static final  Logger LOG = Logger.getLogger(SeqTest.class);
    StorageApi api = new StorageApi();

    @Test
    public void tst() {
        LOG.debug("SeqTest");

        api.start();
        var id1 = api.getLastSavedId();

        api.getId();//1
        api.getId();//2
        api.save();

        var id2 = api.getLastSavedId();

        api.stop();
        api.getId();
        api.save();

        assert (id2 - id1 == 2) : "ERROR id2-id1 = " + (id2 - id1) + "; expected: 2";
    }
}
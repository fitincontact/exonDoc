import com.fitincontact.exondoc.storage.StorageApi;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class SeqTst {
    final static Logger LOG = Logger.getLogger(SeqTst.class);
    StorageApi api = new StorageApi();

    @Test
    public void tst() {
        LOG.debug("SeqTst");
        var id1 = api.getLastSavedId();

        api.start();
        api.getId();//1
        api.getId();//2
        api.stop();
        api.start();
        api.getId();//3
        api.getId();//4
        api.getId();//5
        api.getId();//6
        api.stop();
        //api.getId();
//        api.getId();
//        api.getId();
//        api.getId();
        //api.stop();

        var id2 = api.getLastSavedId();
        assert (id2 - id1 == 6) : "ERROR id2-id1 = " + (id2 - id1) + "; expected: 6";
    }
}

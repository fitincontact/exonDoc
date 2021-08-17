import com.fitincontact.exondoc.storage.StorageApi;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class DataTest {

    static final Logger LOG = Logger.getLogger(NameTest.class);
    static final StorageApi api = new StorageApi();

    @Test
    public void Test() {
        LOG.debug("DataTest");
        api.start();
        final var data = api.getData();
        api.stop();
    }
}
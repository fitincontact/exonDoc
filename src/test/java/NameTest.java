import com.fitincontact.exondoc.storage.StorageApi;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class NameTest {
    static final Logger LOG = Logger.getLogger(NameTest.class);
    StorageApi api = new StorageApi();

    @Test
    public void Test() {
        LOG.debug("NameTest");
        api.start();
        final var names = api.getNames();
        api.stop();
    }

}